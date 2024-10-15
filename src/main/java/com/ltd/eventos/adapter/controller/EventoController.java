package com.ltd.eventos.adapter.controller;

import com.ltd.eventos.adapter.dto.eventodto.CreateEventoDTO;
import com.ltd.eventos.adapter.dto.eventodto.ResponseEventoDTO;
import com.ltd.eventos.adapter.dto.eventodto.UpdateEventoDTO;
import com.ltd.eventos.usecases.exceptions.EventoNaoExiste;
import com.ltd.eventos.usecases.exceptions.LocalNaoExiste;
import com.ltd.eventos.usecases.interactor.EventoUseCases;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/evento")
public class EventoController {

  public final EventoUseCases eventoUseCases;

  @Autowired
  public EventoController(EventoUseCases eventoUseCases) {
    this.eventoUseCases = eventoUseCases;
  }

  @PostMapping("/create")
  public ResponseEntity<?> criarEvento(@RequestBody CreateEventoDTO createEventoDTO) {
    try {
      return ResponseEntity.ok(new ResponseEventoDTO(eventoUseCases.createEvento(createEventoDTO)));
    } catch (LocalNaoExiste e) {
      return ResponseEntity.badRequest().body("Erro no processamento da requisicao: " + e.getMessage());
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar evento. Stacktrace de erro: " + e.getMessage());
    }
  }

  @PatchMapping("/update")
  public ResponseEntity<?> updateEvento(@RequestBody UpdateEventoDTO updateEventoDTO) {
    try {
      return ResponseEntity.ok(new ResponseEventoDTO(eventoUseCases.updateEvento(updateEventoDTO)));
    } catch (LocalNaoExiste | EventoNaoExiste e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro no processamento da requisicao: " + e.getMessage());
    }
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> deleteEvento(@PathVariable String id) {
    try {
      return ResponseEntity.ok(eventoUseCases.deleteEvento(id));
    } catch (EventoNaoExiste e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro no processamento da requisicao: " + e.getMessage());
    }
  }

  @GetMapping("/findbyid/{id}")
  public ResponseEntity<?> findEventoById(@PathVariable String id) {
    try {
      return ResponseEntity.ok(new ResponseEventoDTO(eventoUseCases.findById(id)));
    } catch (EventoNaoExiste e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro no processamento da requisicao: " + e.getMessage());
    }
  }

  @GetMapping("/findall")
  public ResponseEntity<List<ResponseEventoDTO>> findAll() {
    return ResponseEntity.ok(eventoUseCases.findall().stream().map(ResponseEventoDTO::new).collect(Collectors.toList()));
  }
}
