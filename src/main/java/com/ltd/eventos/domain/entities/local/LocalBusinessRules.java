package com.ltd.eventos.domain.entities.local;

import com.ltd.eventos.shared.LocalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@ToString
public class LocalBusinessRules {
    private String local_id;
    private Integer local_capacidade;
    private LocalType local_tipo;
    private String local_endereco;
    private LocalDateTime created_at;
}
