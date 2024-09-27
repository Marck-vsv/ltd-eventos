package com.ltd.eventos.adapter.controller;

import com.ltd.eventos.domain.entities.user.UserBusinessRules;
import com.ltd.eventos.infrastructure.db.entities.UserDomain;
import com.ltd.eventos.usecases.DTO.UserDTO.ResponseUserDTO;
import com.ltd.eventos.usecases.DTO.UserDTO.UpdateUserDTO;
import com.ltd.eventos.usecases.interactor.user.UserUseCases;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserUseCases userUseCases;

    @Autowired
    public UserController(UserUseCases userUseCases) {
        this.userUseCases = userUseCases;
    }

    @PostMapping("/create")
    public ResponseEntity<UserDomain> createUser(@RequestBody UserBusinessRules user) {
        return ResponseEntity.ok(userUseCases.createUser(user));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        return ResponseEntity.ok(userUseCases.deleteUser(id));
    }

    @PatchMapping("/update")
    public ResponseEntity<UserDomain> updateUser(@RequestBody UpdateUserDTO user) {
        return ResponseEntity.ok(userUseCases.updateUser(user));
    }

    @GetMapping("/finduserbymatricula/{matricula}")
    public ResponseEntity<ResponseUserDTO> findUserByMatricula(@PathVariable String matricula) {
        return ResponseEntity.ok(userUseCases.findByMatricula(matricula));
    }

    @GetMapping("/findallusers")
    public ResponseEntity<List<ResponseUserDTO>> findAllUsers() {
        return ResponseEntity.ok(userUseCases.findAll());
    }
}
