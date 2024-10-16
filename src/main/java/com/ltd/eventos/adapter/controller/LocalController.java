package com.ltd.eventos.adapter.controller;

import com.ltd.eventos.adapter.dto.localdto.ResponseLocalDTO;
import com.ltd.eventos.adapter.dto.localdto.RequestLocalDTO;
import com.ltd.eventos.usecases.exceptions.LocalNaoExiste;
import com.ltd.eventos.usecases.interactor.LocalUseCases;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/local")
public class LocalController {
  private final LocalUseCases localUseCases;

  @Autowired
  public LocalController(LocalUseCases localUseCases) {
    this.localUseCases = localUseCases;
  }

  @PostMapping("/create")
  public ResponseEntity<?> createLocal(@RequestBody RequestLocalDTO local) {
    try {
      return ResponseEntity.ok(new ResponseLocalDTO(localUseCases.createLocal(local)));
    } catch (RuntimeException e) {
      return ResponseEntity.internalServerError().body(e.getMessage());
    }
  }

  @PatchMapping("/update")
  public ResponseEntity<?> updateLocal(@RequestBody RequestLocalDTO local) {
    try {
      return ResponseEntity.ok(new ResponseLocalDTO(localUseCases.updateLocal(local)));
    } catch (LocalNaoExiste e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> deleteLocal(@PathVariable String id) {
    try {
      return ResponseEntity.ok(localUseCases.deleteLocal(id));
    } catch (LocalNaoExiste e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
  }

  @GetMapping("/findbyid/{id}")
  public ResponseEntity<?> FindById(@PathVariable String id) {
    try {
      return ResponseEntity.ok(new ResponseLocalDTO(localUseCases.findById(id)));
    } catch (LocalNaoExiste e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
  }

  @GetMapping("/findall")
  public ResponseEntity<List<ResponseLocalDTO>> findAllLocal() {
    return ResponseEntity.ok(localUseCases.findAll().stream().map(ResponseLocalDTO::new).collect(Collectors.toList()));
  }
}
