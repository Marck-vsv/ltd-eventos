package com.ltd.eventos.infrastructure.db.entities;

import com.ltd.eventos.domain.entities.LocalBusinessRules;
import com.ltd.eventos.shared.LocalType;
import com.ltd.eventos.usecases.DTO.LocalDTO.UpdateLocalDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

// Lombok
@Data
@AllArgsConstructor
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
        this.local_id = localBusinessRules.getLocal_id().toString();
        this.local_capacidade = localBusinessRules.getLocal_capacidade();
        this.local_tipo = localBusinessRules.getLocal_tipo();
        this.local_endereco = localBusinessRules.getLocal_endereco();
        this.created_at = localBusinessRules.getCreated_at();
    }

    public LocalDomain(UpdateLocalDTO updateLocalDTO) {
        this.local_capacidade = updateLocalDTO.localCapacidade();
        this.local_tipo = updateLocalDTO.localTipo();
        this.local_endereco = updateLocalDTO.localEndereco();
    }
}
