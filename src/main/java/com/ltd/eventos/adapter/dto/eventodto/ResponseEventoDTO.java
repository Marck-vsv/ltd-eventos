package com.ltd.eventos.adapter.dto.eventodto;

import com.ltd.eventos.adapter.dto.localdto.ResponseLocalDTO;
import com.ltd.eventos.infrastructure.db.model.EventoDomain;

import java.time.LocalDateTime;

public record ResponseEventoDTO(
    String evento_nome,
    String evento_desc,
    Integer evento_capacidade,
    LocalDateTime evento_inicio,
    LocalDateTime evento_fim,
    ResponseLocalDTO locais,
    String userMatricula
) {

    public ResponseEventoDTO(EventoDomain eventoDomain) {
        this(
            eventoDomain.getEvento_nome(),
            eventoDomain.getEvento_desc(),
            eventoDomain.getEvento_capacidade(),
            eventoDomain.getEvento_inicio(),
            eventoDomain.getEvento_fim(),
            new ResponseLocalDTO(eventoDomain.getLocal_local_id()),
            eventoDomain.getUser_id() != null ? eventoDomain.getUser_id().getMatricula() : ""
        );
    }
}
