package com.ltd.eventos.adapter.controller;

import com.ltd.eventos.domain.entities.LocalBusinessRules;
import com.ltd.eventos.infrastructure.db.entities.LocalDomain;
import com.ltd.eventos.usecases.DTO.LocalDTO.CreateLocalDTO;
import com.ltd.eventos.usecases.DTO.LocalDTO.ResponseLocalDTO;
import com.ltd.eventos.usecases.DTO.LocalDTO.UpdateLocalDTO;
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
    public ResponseEntity<LocalDomain> createLocal(@RequestBody CreateLocalDTO local) {
        return ResponseEntity.ok(localUseCases.createLocal(local));
    }

    @PatchMapping("/update")
    public ResponseEntity<ResponseLocalDTO> updateLocal(@RequestBody UpdateLocalDTO local) {
        return ResponseEntity.ok(new ResponseLocalDTO(localUseCases.updateLocal(local)));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteLocal(@PathVariable String id) {
        return ResponseEntity.ok(localUseCases.deleteLocal(id));
    }

    @GetMapping("/findbyid/{id}")
    public ResponseEntity<ResponseLocalDTO> FindById(@PathVariable String id) {
        return ResponseEntity.ok(localUseCases.findById(id));
    }
    @GetMapping("/findall")
    public ResponseEntity<List<ResponseLocalDTO>> findAllLocal() {
        return ResponseEntity.ok(localUseCases.findAll());
    }
}
