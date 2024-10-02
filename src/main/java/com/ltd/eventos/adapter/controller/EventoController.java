package com.ltd.eventos.adapter.controller;

import com.ltd.eventos.adapter.DTO.eventoDTO.CreateEventoDTO;
import com.ltd.eventos.adapter.DTO.eventoDTO.ResponseEventoDTO;
import com.ltd.eventos.adapter.DTO.eventoDTO.UpdateEventoDTO;
import com.ltd.eventos.usecases.interactor.EventoUseCases;
import org.springframework.beans.factory.annotation.Autowired;
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
  public ResponseEntity<ResponseEventoDTO> criarEvento(@RequestBody CreateEventoDTO createEventoDTO) {
    return ResponseEntity.ok(new ResponseEventoDTO(eventoUseCases.createEvento(createEventoDTO)));
  }

  @PatchMapping("/update")
  public ResponseEntity<ResponseEventoDTO> updateEvento(@RequestBody UpdateEventoDTO updateEventoDTO) {
    return ResponseEntity.ok(new ResponseEventoDTO(eventoUseCases.updateEvento(updateEventoDTO)));
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> deleteEvento(@PathVariable String id) {
    return ResponseEntity.ok(eventoUseCases.deleteEvento(id));
  }

  @GetMapping("/findbyid/{id}")
  public ResponseEntity<ResponseEventoDTO> findEventoById(@PathVariable String id) {
    return ResponseEntity.ok(new ResponseEventoDTO(eventoUseCases.findById(id).get()));
  }

    @GetMapping("/findall")
  public ResponseEntity<List<ResponseEventoDTO>> findAll() {
    return ResponseEntity.ok(eventoUseCases.findall().stream().map(ResponseEventoDTO::new).collect(Collectors.toList()));
  }
}
