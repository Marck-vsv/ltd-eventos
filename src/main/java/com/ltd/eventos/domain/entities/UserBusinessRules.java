package com.ltd.eventos.domain.entities;

import com.ltd.eventos.shared.UserType;
import com.ltd.eventos.adapter.dto.userdto.RequestUserDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record UserBusinessRules(
    UUID user_id,
    String username,
    String matricula,
    String senha,
    UserType userType,
    LocalDateTime created_at,
    List<String> eventos
) {

    public UserBusinessRules(RequestUserDTO requestUserDTO) {
        this(
            UUID.randomUUID(),
            requestUserDTO.username(),
            requestUserDTO.matricula(),
            requestUserDTO.senha(),
            requestUserDTO.userType(),
            LocalDateTime.now(),
            requestUserDTO.eventos()
        );
    }

    @Override
    public String toString() {
        return "UserBusinessRules{" +
                "user_id=" + user_id +
                ", username='" + username + '\'' +
                ", matricula='" + matricula + '\'' +
                ", senha='" + senha + '\'' +
                ", userType=" + userType +
                ", created_at=" + created_at +
                ", eventos=" + eventos +
                '}';
    }
}
