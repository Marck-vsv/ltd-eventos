package com.ltd.eventos.usecases.DTO.UserDTO;

import com.ltd.eventos.infrastructure.db.entities.UserDomain;
import com.ltd.eventos.shared.UserType;

import java.time.LocalDateTime;
import java.util.List;

public record ResponseUserDTO(String username,
                              String matricula,
                              UserType user_type,
                              LocalDateTime created_at,
                              LocalDateTime updated_at,
                              String evento_evento_id) {
    public ResponseUserDTO(UserDomain userDomain) {
        this (
                userDomain.getUsername(),
                userDomain.getMatricula(),
                userDomain.getUser_type(),
                userDomain.getCreated_at(),
                userDomain.getUpdated_at(),
                userDomain.getEvento_evento_id()
        );
    }
}
