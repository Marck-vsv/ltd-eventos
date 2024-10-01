package com.ltd.eventos.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@ToString
public class EventosBusinessRules {
    private String evento_id;
    private String evento_nome;
    private String evento_descricao;
    private Integer evento_capacidade;
    private LocalDateTime evento_inicio;
    private LocalDateTime evento_fim;
    private LocalDateTime created_at;
//    private LocalBusinessRules Local;
}
