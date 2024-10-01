package com.ltd.eventos.domain.entities;

import com.ltd.eventos.shared.LocalType;
import com.ltd.eventos.usecases.DTO.LocalDTO.CreateLocalDTO;

import java.time.LocalDateTime;
import java.util.UUID;

public class LocalBusinessRules {
  private final UUID local_id;
  private final Integer local_capacidade;
  private final LocalType local_tipo;
  private final String local_endereco;
  private final LocalDateTime created_at;

  public LocalBusinessRules(UUID local_id, Integer local_capacidade, LocalType local_tipo, String local_endereco, LocalDateTime created_at) {
    this.local_id = local_id;
    this.local_capacidade = local_capacidade;
    this.local_tipo = local_tipo;
    this.local_endereco = local_endereco;
    this.created_at = created_at;
  }

  public LocalBusinessRules(CreateLocalDTO createLocalDTO) {
    this.local_id = UUID.randomUUID();
    this.local_capacidade = createLocalDTO.localCapacidade();
    this.local_tipo = createLocalDTO.localTipo();
    this.local_endereco = createLocalDTO.localEndereco();
    this.created_at = LocalDateTime.now();
  }

  public UUID getLocal_id() {
    return local_id;
  }

  public Integer getLocal_capacidade() {
    return local_capacidade;
  }

  public LocalType getLocal_tipo() {
    return local_tipo;
  }

  public String getLocal_endereco() {
    return local_endereco;
  }

  public LocalDateTime getCreated_at() {
    return created_at;
  }

  @Override
  public String toString() {
    return "LocalBusinessRules{" +
        "local_id=" + local_id +
        ", local_capacidade=" + local_capacidade +
        ", local_tipo=" + local_tipo +
        ", local_endereco='" + local_endereco + '\'' +
        ", created_at=" + created_at +
        '}';
  }
}
