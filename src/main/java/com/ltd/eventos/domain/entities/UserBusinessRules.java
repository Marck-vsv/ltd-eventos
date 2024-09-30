package com.ltd.eventos.domain.entities;

import com.ltd.eventos.shared.UserType;
import com.ltd.eventos.usecases.DTO.UserDTO.CreateUserDTO;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserBusinessRules {
  private final UUID user_id;
  private final String username;
  private final String matricula;
  private final String senha;
  private final UserType userType;
  private final LocalDateTime created_at;

  public UserBusinessRules(UUID userId, String username, String matricula, String senha, UserType userType, LocalDateTime created_at) {
    this.user_id = userId;
    this.username = username;
    this.matricula = matricula;
    this.senha = senha;
    this.userType = userType;
    this.created_at = created_at;
  }

    public UserBusinessRules(CreateUserDTO userDTO) {
    this.user_id = UUID.randomUUID();
    this.username = userDTO.username();
    this.matricula = userDTO.matricula();
    this.senha = userDTO.senha();
    this.userType = userDTO.userType();
    this.created_at = LocalDateTime.now();
  }

  public UUID getUser_id() {
    return user_id;
  }

  public String getUsername() {
    return username;
  }

  public String getMatricula() {
    return matricula;
  }

  public LocalDateTime getCreated_at() {
    return created_at;
  }

  public String getSenha() {
    return senha;
  }

  public UserType getUserType() {
    return userType;
  }

  @Override
  public String toString() {
    return "UserBusinessRules{" +
        "user_id='" + user_id + '\'' +
        ", username='" + username + '\'' +
        ", matricula='" + matricula + '\'' +
        ", senha='" + senha + '\'' +
        ", userType=" + userType +
        ", created_at=" + created_at +
        '}';
  }
}
