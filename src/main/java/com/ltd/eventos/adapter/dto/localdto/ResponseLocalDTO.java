package com.ltd.eventos.adapter.dto.localdto;

import com.ltd.eventos.infrastructure.db.model.LocalDomain;
import com.ltd.eventos.shared.LocalType;

public record ResponseLocalDTO(
    Integer local_capacidade,
    LocalType local_tipo,
    String local_endereco
) {
  public ResponseLocalDTO(LocalDomain localDomain) {
    this(
        localDomain.getLocal_capacidade(),
        localDomain.getLocal_tipo(),
        localDomain.getLocal_endereco()
    );
  }
}
