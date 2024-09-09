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

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserUseCases userUseCases;
    private final HandlerMapping resourceHandlerMapping;

    @Autowired
    public UserController(UserUseCases userUseCases, @Qualifier("resourceHandlerMapping") HandlerMapping resourceHandlerMapping) {
        this.userUseCases = userUseCases;
        this.resourceHandlerMapping = resourceHandlerMapping;
    }

    @PostMapping("/create")
    public ResponseEntity<UserDomain> createUser(@RequestBody UserBusinessRules user) {
        return ResponseEntity.ok(userUseCases.CreateUser(user));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        return ResponseEntity.ok("Usuário " + userUseCases.DeleteUser(id) + " deletado com sucesso!");
    }

    @PatchMapping("/update")
    public ResponseEntity<UserDomain> updateUser(@RequestBody UpdateUserDTO user) {
        return ResponseEntity.ok(userUseCases.UpdateUser(user));
    }

    @GetMapping("/finduserbymatricula/{matricula}")
    public ResponseEntity<ResponseUserDTO> findUserByMatricula(@PathVariable String matricula) {
        return ResponseEntity.ok(userUseCases.FindByMatricula(matricula));
    }
}
