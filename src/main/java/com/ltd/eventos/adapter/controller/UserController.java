package com.ltd.eventos.adapter.controller;

import com.ltd.eventos.adapter.dto.userdto.RequestUserDTO;
import com.ltd.eventos.adapter.dto.userdto.ResponseUserDTO;
import com.ltd.eventos.adapter.dto.userdto.UpdateUserDTO;
import com.ltd.eventos.usecases.exceptions.UsuarioNaoExiste;
import com.ltd.eventos.usecases.interactor.UserUseCases;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
  public ResponseEntity<?> createUser(@RequestBody RequestUserDTO user) {
    try {
      return ResponseEntity.ok(new ResponseUserDTO(userUseCases.createUser(user)));
    } catch (RuntimeException e) {
      return ResponseEntity.internalServerError().body("Erro Interno ao salvar a entidade. StackTrace: " + e.getMessage());
    }
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> deleteUser(@PathVariable String id) {
    try {
      return ResponseEntity.ok(userUseCases.deleteUser(id));
    } catch (UsuarioNaoExiste e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
  }

  @PatchMapping("/update")
  public ResponseEntity<?> updateUser(@RequestBody UpdateUserDTO user) {
    try {
      return ResponseEntity.ok(new ResponseUserDTO(userUseCases.updateUser(user)));
    } catch (UsuarioNaoExiste e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping("/finduserbymatricula/{matricula}")
  public ResponseEntity<?> findUserByMatricula(@PathVariable String matricula) {
    try {
      return ResponseEntity.ok(new ResponseUserDTO(userUseCases.findByMatricula(matricula)));
    } catch (UsuarioNaoExiste e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
  }

  @GetMapping("/findallusers")
  public ResponseEntity<List<ResponseUserDTO>> findAllUsers() {
    return ResponseEntity.ok(userUseCases.findAll());
  }
}
