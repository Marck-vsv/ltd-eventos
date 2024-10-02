package com.ltd.eventos.adapter.DTO.eventoDTO;

import java.time.LocalDateTime;

public record UpdateEventoDTO(
    String evento_id,
    String evento_nome,
    String evento_desc,
    Integer evento_capacidade,
    LocalDateTime evento_inicio,
    LocalDateTime evento_fim,
    LocalDateTime created_at,
    String local_id
) {
}
