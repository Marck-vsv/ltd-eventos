package com.ltd.eventos.domain.entities;

import com.ltd.eventos.adapter.DTO.LocalDTO.RequestLocalDTO;
import com.ltd.eventos.infrastructure.db.model.LocalDomain;
import com.ltd.eventos.shared.LocalType;

import java.time.LocalDateTime;
import java.util.UUID;

@SuppressWarnings({"LombokGetterMayBeUsed"})
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

    public LocalBusinessRules(LocalDomain localDomain) {
    this.local_id = UUID.fromString(localDomain.getLocal_id()) ;
    this.local_capacidade = localDomain.getLocal_capacidade();
    this.local_tipo = localDomain.getLocal_tipo();
    this.local_endereco = localDomain.getLocal_endereco();
    this.created_at = localDomain.getCreated_at();
  }

  public LocalBusinessRules(RequestLocalDTO requestLocalDTO) {
    this.local_id = UUID.randomUUID();
    this.local_capacidade = requestLocalDTO.localCapacidade();
    this.local_tipo = requestLocalDTO.localTipo();
    this.local_endereco = requestLocalDTO.localEndereco();
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
