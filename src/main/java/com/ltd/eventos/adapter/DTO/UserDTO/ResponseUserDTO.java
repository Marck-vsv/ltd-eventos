package com.ltd.eventos.adapter.DTO.UserDTO;

import com.ltd.eventos.infrastructure.db.model.UserDomain;
import com.ltd.eventos.shared.UserType;

public record ResponseUserDTO(
        String username,
        String matricula,
        UserType user_type,
        String evento_evento_id
) {
    public ResponseUserDTO(UserDomain userDomain) {
        this (
                userDomain.getUsername(),
                userDomain.getMatricula(),
                userDomain.getUser_type(),
                userDomain.getEvento_evento_id()
        );
    }
}
