package com.ltd.eventos.infrastructure.db.model;

import com.ltd.eventos.domain.entities.LocalBusinessRules;
import com.ltd.eventos.shared.LocalType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

// Lombok
@Getter
@Setter
@ToString
@NoArgsConstructor

// Spring JPA
@Entity
@Table(name = "local")
public class LocalDomain {
    @Id
    @Column(unique = true, nullable = false, columnDefinition = "VARCHAR(36)")
    private String local_id;
    @Column(nullable = false)
    private Integer local_capacidade;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LocalType local_tipo;
    @Column(nullable = false)
    private String local_endereco;
    @Column(nullable = false)
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public LocalDomain(LocalBusinessRules localBusinessRules) {
        this.local_id = localBusinessRules.local_id().toString();
        this.local_capacidade = localBusinessRules.local_capacidade();
        this.local_tipo = localBusinessRules.local_tipo();
        this.local_endereco = localBusinessRules.local_endereco();
        this.created_at = localBusinessRules.created_at();
    }
}
