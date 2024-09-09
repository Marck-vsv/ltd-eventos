package com.ltd.eventos.domain.entities.user;

import com.ltd.eventos.shared.UserType;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserBusinessRules {
    private String user_id;
    private String username;
    private String matricula;
    private String senha;
    private UserType userType;
    private LocalDateTime created_at;
}
