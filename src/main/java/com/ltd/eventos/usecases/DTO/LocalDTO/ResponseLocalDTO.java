package com.ltd.eventos.usecases.DTO.LocalDTO;

import com.ltd.eventos.infrastructure.db.entities.LocalDomain;
import com.ltd.eventos.shared.LocalType;

public record ResponseLocalDTO(
        String local_endereco,
        Integer local_capacidade,
        LocalType local_tipo
) {
    public ResponseLocalDTO(LocalDomain localDomain) {
        this (
                localDomain.getLocal_endereco(),
                localDomain.getLocal_capacidade(),
                localDomain.getLocal_tipo()
        );
    }
}
