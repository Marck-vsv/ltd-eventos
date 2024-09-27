package com.ltd.eventos.infrastructure.db.entities;

import com.ltd.eventos.domain.entities.eventos.EventosBusinessRules;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

// Lombok
@Data
@AllArgsConstructor
@NoArgsConstructor

// Spring JPA
@Entity
@Table(name = "evento")
public class EventoDomain {
    @Id
    @Column(unique = true, nullable = false)
    private String evento_id;
    @Column(nullable = false)
    private String evento_nome;
    @Column(nullable = false)
    private String evento_descricao;
    @Column(nullable = false)
    private Integer evento_capacidade;
    @Column(nullable = false)
    private LocalDateTime evento_inicio;
    @Column(nullable = false)
    private LocalDateTime evento_fim;
    @Column(nullable = false)
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public EventoDomain(EventosBusinessRules eventosBusinessRules) {
        this.evento_id = eventosBusinessRules.getEvento_id();
        this.evento_nome = eventosBusinessRules.getEvento_nome();
        this.evento_descricao = eventosBusinessRules.getEvento_descricao();
        this.evento_capacidade = eventosBusinessRules.getEvento_capacidade();
        this.evento_inicio = eventosBusinessRules.getEvento_inicio();
        this.evento_fim = eventosBusinessRules.getEvento_fim();
        this.created_at = eventosBusinessRules.getCreated_at();
    }
}
