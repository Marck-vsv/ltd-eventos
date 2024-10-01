package com.ltd.eventos.usecases.DTO.LocalDTO;

import com.ltd.eventos.shared.LocalType;

public record CreateLocalDTO(
    Integer localCapacidade,
    LocalType localTipo,
    String localEndereco
) {
}
