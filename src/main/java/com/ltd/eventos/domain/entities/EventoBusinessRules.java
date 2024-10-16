package com.ltd.eventos.domain.entities;

import com.ltd.eventos.adapter.dto.eventodto.CreateEventoDTO;

import java.time.LocalDateTime;
import java.util.UUID;

public record EventoBusinessRules(
    String evento_id,
    String evento_nome,
    String evento_desc,
    Integer evento_capacidade,
    LocalDateTime evento_inicio,
    LocalDateTime evento_fim,
    LocalDateTime created_at,
    String local_id
) {

    public EventoBusinessRules(CreateEventoDTO createEventoDTO, String localId) {
        this(
            UUID.randomUUID().toString(),
            createEventoDTO.evento_nome(),
            createEventoDTO.evento_desc(),
            createEventoDTO.evento_capacidade(),
            createEventoDTO.evento_inicio(),
            createEventoDTO.evento_fim(),
            LocalDateTime.now(),
            localId
        );
    }

  @Override
  public String toString() {
    return "EventoBusinessRules{" +
        "evento_id='" + evento_id + '\'' +
        ", evento_nome='" + evento_nome + '\'' +
        ", evento_desc='" + evento_desc + '\'' +
        ", evento_capacidade=" + evento_capacidade +
        ", evento_inicio=" + evento_inicio +
        ", evento_fim=" + evento_fim +
        ", created_at=" + created_at +
        ", local_id='" + local_id + '\'' +
        '}';
  }
}
