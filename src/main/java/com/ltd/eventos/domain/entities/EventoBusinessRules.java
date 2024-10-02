package com.ltd.eventos.domain.entities;

import com.ltd.eventos.adapter.DTO.eventoDTO.CreateEventoDTO;
import com.ltd.eventos.adapter.DTO.eventoDTO.UpdateEventoDTO;

import java.time.LocalDateTime;
import java.util.UUID;

@SuppressWarnings({"LombokGetterMayBeUsed"})
public class EventoBusinessRules {
  private final String evento_id;
  private final String evento_nome;
  private final String evento_desc;
  private final Integer evento_capacidade;
  private final LocalDateTime evento_inicio;
  private final LocalDateTime evento_fim;
  private final LocalDateTime created_at;
  private final String local_id;

  public EventoBusinessRules(CreateEventoDTO createEventoDTO, String localId) {
    this.local_id = localId;
    this.created_at = LocalDateTime.now();
    this.evento_fim =createEventoDTO.evento_fim();
    this.evento_inicio =createEventoDTO.evento_inicio();
    this.evento_capacidade =createEventoDTO.evento_capacidade();
    this.evento_desc =createEventoDTO.evento_desc();
    this.evento_nome =createEventoDTO.evento_nome();
    this.evento_id = UUID.randomUUID().toString();
  }

   public EventoBusinessRules(UpdateEventoDTO updateEventoDTO) {
    this.local_id = updateEventoDTO.local_id();
    this.created_at = LocalDateTime.now();
    this.evento_fim = updateEventoDTO.evento_fim();
    this.evento_inicio = updateEventoDTO.evento_inicio();
    this.evento_capacidade = updateEventoDTO.evento_capacidade();
    this.evento_desc = updateEventoDTO.evento_desc();
    this.evento_nome = updateEventoDTO.evento_nome();
    this.evento_id = updateEventoDTO.evento_id();
  }

  public String getEvento_id() {
    return evento_id;
  }

  public String getEvento_nome() {
    return evento_nome;
  }

  public String getEvento_desc() {
    return evento_desc;
  }

  public LocalDateTime getEvento_inicio() {
    return evento_inicio;
  }

  public Integer getEvento_capacidade() {
    return evento_capacidade;
  }

  public LocalDateTime getEvento_fim() {
    return evento_fim;
  }

  public LocalDateTime getCreated_at() {
    return created_at;
  }

  public String getLocal_id() {
    return local_id;
  }
}
