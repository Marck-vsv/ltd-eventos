package com.ltd.eventos.usecases.interactor;

import com.ltd.eventos.adapter.DTO.eventoDTO.UpdateEventoDTO;
import com.ltd.eventos.domain.entities.EventoBusinessRules;
import com.ltd.eventos.infrastructure.db.model.EventoDomain;
import com.ltd.eventos.infrastructure.db.model.LocalDomain;
import com.ltd.eventos.infrastructure.db.repository.EventoRepository;
import com.ltd.eventos.adapter.DTO.eventoDTO.CreateEventoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class EventoUseCases {

  private final EventoRepository eventoRepository;
  private final LocalUseCases localUseCases;

  @Autowired
  public EventoUseCases(EventoRepository eventoRepository, LocalUseCases localUseCases) {
    this.eventoRepository = eventoRepository;
    this.localUseCases = localUseCases;
  }

  public EventoDomain createEvento(CreateEventoDTO createEventoDTO) {
    // Consultar a localDomain no banco e salvar em uma entidade
    LocalDomain localDomain = localUseCases.findById(createEventoDTO.local_id());
    // criar uma entidade de evento business com o dto e o uuid do local
    EventoBusinessRules eventoBusinessRules = new EventoBusinessRules(createEventoDTO, localDomain.getLocal_id());
    // criar uma entidade de dominio de evento completa
    EventoDomain eventoDomain = new EventoDomain(eventoBusinessRules, localDomain);
    // salvar a entidade
    eventoRepository.save(eventoDomain);
    return eventoDomain;
  }

  public EventoDomain updateEvento(UpdateEventoDTO updateEventoDTO) throws RuntimeException {
    Optional<EventoDomain> optionalEventoDomain = eventoRepository.findById(updateEventoDTO.evento_id());
    try {
      if (optionalEventoDomain.isPresent()) {
        if (updateEventoDTO.evento_nome() != null) {
          optionalEventoDomain.get().setEvento_nome(updateEventoDTO.evento_nome());
        }
        if (updateEventoDTO.evento_desc() != null) {
          optionalEventoDomain.get().setEvento_desc(updateEventoDTO.evento_desc());
        }
        if (updateEventoDTO.evento_capacidade() != null) {
          optionalEventoDomain.get().setEvento_capacidade(updateEventoDTO.evento_capacidade());
        }
        if (updateEventoDTO.evento_inicio() != null) {
          optionalEventoDomain.get().setEvento_inicio(updateEventoDTO.evento_inicio());
        }
        if (updateEventoDTO.evento_fim() != null) {
          optionalEventoDomain.get().setEvento_fim(updateEventoDTO.evento_fim());
        }
        if (updateEventoDTO.created_at() != null) {
          optionalEventoDomain.get().setCreated_at(updateEventoDTO.created_at());
        }
        optionalEventoDomain.get().setUpdated_at(LocalDateTime.now());
        if (updateEventoDTO.local_id() != null) {
          optionalEventoDomain.get().setLocal_local_id(localUseCases.findById(updateEventoDTO.local_id()));
        }
      } else {
        throw new RuntimeException("asd");
      }
      return eventoRepository.save(optionalEventoDomain.get());
    } catch (RuntimeException e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  public String deleteEvento(String evento_id) {
    if (eventoRepository.existsById(evento_id)) {
      eventoRepository.deleteById(evento_id);
    } else {
      throw new RuntimeException("asd");
    }
    return evento_id;
  }

  public Optional<EventoDomain> findById(String evento_id) {
    return  eventoRepository.findById(evento_id);
  }

    public List<EventoDomain> findall() {
    return StreamSupport.stream(eventoRepository.findAll().spliterator(), true).collect(Collectors.toList());
  }
}
