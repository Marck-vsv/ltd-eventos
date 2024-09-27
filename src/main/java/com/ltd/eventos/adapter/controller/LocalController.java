package com.ltd.eventos.adapter.controller;

import com.ltd.eventos.domain.entities.local.LocalBusinessRules;
import com.ltd.eventos.infrastructure.db.entities.LocalDomain;
import com.ltd.eventos.usecases.DTO.LocalDTO.ResponseLocalDTO;
import com.ltd.eventos.usecases.interactor.local.LocalUseCases;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/local")
public class LocalController {
    private final LocalUseCases localUseCases;

    @Autowired
    public LocalController(LocalUseCases localUseCases) {
        this.localUseCases = localUseCases;
    }

    @PostMapping("/create")
    public ResponseEntity<LocalDomain> createLocal(@RequestBody LocalBusinessRules local) {
        return ResponseEntity.ok(localUseCases.createLocal(local));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteLocal(@PathVariable String id) {
        return ResponseEntity.ok(localUseCases.deleteLocal(id));
    }

    @GetMapping("/getalllocal")
    public ResponseEntity<List<ResponseLocalDTO>> findAllLocal() {
        return ResponseEntity.ok(localUseCases.findAll());
    }
}
