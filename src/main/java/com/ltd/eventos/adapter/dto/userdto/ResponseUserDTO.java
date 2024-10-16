package com.ltd.eventos.adapter.dto.userdto;

import com.ltd.eventos.adapter.dto.eventodto.ResponseEventoDTO;
import com.ltd.eventos.infrastructure.db.model.UserDomain;
import com.ltd.eventos.shared.UserType;

import java.util.List;
import java.util.stream.Collectors;

public record ResponseUserDTO(
        String username,
        String matricula,
        UserType user_type,
        List<ResponseEventoDTO> eventos
) {
    public ResponseUserDTO(UserDomain userDomain) {
        this (
                userDomain.getUsername(),
                userDomain.getMatricula(),
                userDomain.getUser_type(),
                userDomain.getEvento_evento_id().stream().map(ResponseEventoDTO::new).collect(Collectors.toList())
        );
    }
}
