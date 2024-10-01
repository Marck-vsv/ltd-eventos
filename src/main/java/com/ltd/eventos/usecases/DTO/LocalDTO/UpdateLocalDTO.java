package com.ltd.eventos.usecases.DTO.LocalDTO;

import com.ltd.eventos.shared.LocalType;

public record UpdateLocalDTO(
    String localID,
    Integer localCapacidade,
    LocalType localTipo,
    String localEndereco
) {
}
