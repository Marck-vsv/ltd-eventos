package com.ltd.eventos.adapter.dto.localdto;

import com.ltd.eventos.shared.LocalType;

public record RequestLocalDTO(
    String localID,
    Integer localCapacidade,
    LocalType localTipo,
    String localEndereco
) {
}
