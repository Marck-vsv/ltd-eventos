package com.ltd.eventos.adapter.dto.eventodto;

import java.time.LocalDateTime;

public record CreateEventoDTO(
    String evento_nome,
    String evento_desc,
    Integer evento_capacidade,
    LocalDateTime evento_inicio,
    LocalDateTime evento_fim,
    String local_id
) {
}
