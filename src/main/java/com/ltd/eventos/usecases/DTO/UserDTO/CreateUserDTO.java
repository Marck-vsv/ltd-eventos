package com.ltd.eventos.usecases.DTO.UserDTO;

import com.ltd.eventos.shared.UserType;

public record CreateUserDTO(String username,
                            String matricula,
                            String senha,
                            UserType userType) {
}
