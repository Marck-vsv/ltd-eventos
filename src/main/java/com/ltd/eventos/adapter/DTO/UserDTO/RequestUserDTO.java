package com.ltd.eventos.adapter.DTO.UserDTO;

import com.ltd.eventos.shared.UserType;

public record RequestUserDTO(
    String username,
    String matricula,
    String senha,
    UserType userType) {
}
