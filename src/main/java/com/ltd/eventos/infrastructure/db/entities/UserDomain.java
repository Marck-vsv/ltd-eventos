package com.ltd.eventos.infrastructure.db.entities;

import com.ltd.eventos.domain.entities.user.UserBusinessRules;
import com.ltd.eventos.shared.UserType;
import com.ltd.eventos.usecases.DTO.UserDTO.UpdateUserDTO;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

// Lombok
@Data
@AllArgsConstructor
@NoArgsConstructor

// Spring JPA
@Entity
@Table(name = "user")
public class UserDomain {
    @Id
    @Column(unique = true, nullable = false, columnDefinition = "VARCHAR(36)")
    private String user_id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String matricula;
    @Column(nullable = false)
    private String senha;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserType user_type;
    @Column(nullable = false)
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    // TODO:
    // Quando criar eventos, Transformar esse campo em uma List<eventos> e estabelecer as relações de tabelas
    // Atualizar ResponseUserDTO Para retornar uma lista de eventos tbm.
    private String evento_evento_id;

    public UserDomain(UserBusinessRules userBusinessRules) {
        this.user_id = userBusinessRules.getUser_id().toString();
        this.username = userBusinessRules.getUsername();
        this.matricula = userBusinessRules.getMatricula();
        this.senha = userBusinessRules.getSenha();
        this.user_type = userBusinessRules.getUserType();
        this.created_at = userBusinessRules.getCreated_at();
    }

    public UserDomain(UpdateUserDTO user) {
        this.username = user.username();
        this.senha = user.senha();
    }
}
