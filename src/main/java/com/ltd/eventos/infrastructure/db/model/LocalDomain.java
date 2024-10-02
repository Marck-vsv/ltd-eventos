package com.ltd.eventos.infrastructure.db.model;

import com.ltd.eventos.adapter.DTO.LocalDTO.RequestLocalDTO;
import com.ltd.eventos.domain.entities.LocalBusinessRules;
import com.ltd.eventos.shared.LocalType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDateTime;
import java.util.Objects;

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
        this.local_id = localBusinessRules.getLocal_id().toString();
        this.local_capacidade = localBusinessRules.getLocal_capacidade();
        this.local_tipo = localBusinessRules.getLocal_tipo();
        this.local_endereco = localBusinessRules.getLocal_endereco();
        this.created_at = localBusinessRules.getCreated_at();
    }

    public LocalDomain(RequestLocalDTO requestLocalDTO) {
        this.local_capacidade = requestLocalDTO.localCapacidade();
        this.local_tipo = requestLocalDTO.localTipo();
        this.local_endereco = requestLocalDTO.localEndereco();
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        LocalDomain that = (LocalDomain) o;
        return getLocal_id() != null && Objects.equals(getLocal_id(), that.getLocal_id());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
