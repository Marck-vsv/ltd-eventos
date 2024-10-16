package com.ltd.eventos.adapter.dto.userdto;

import com.ltd.eventos.shared.UserType;

import java.util.List;

public record RequestUserDTO(
    String username,
    String matricula,
    String senha,
    UserType userType,
    List<String> eventos) {
}
