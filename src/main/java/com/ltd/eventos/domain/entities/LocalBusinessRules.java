package com.ltd.eventos.domain.entities;

import com.ltd.eventos.adapter.dto.localdto.RequestLocalDTO;
import com.ltd.eventos.shared.LocalType;

import java.time.LocalDateTime;
import java.util.UUID;

public record LocalBusinessRules(
    UUID local_id,
    Integer local_capacidade,
    LocalType local_tipo,
    String local_endereco,
    LocalDateTime created_at
) {

    public LocalBusinessRules(RequestLocalDTO requestLocalDTO) {
        this(
            UUID.randomUUID(),
            requestLocalDTO.localCapacidade(),
            requestLocalDTO.localTipo(),
            requestLocalDTO.localEndereco(),
            LocalDateTime.now()
        );
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
