package com.ltd.eventos.adapter.DTO.LocalDTO;

import com.ltd.eventos.shared.LocalType;

public record RequestLocalDTO(
    String localID,
    Integer localCapacidade,
    LocalType localTipo,
    String localEndereco
) {
}
