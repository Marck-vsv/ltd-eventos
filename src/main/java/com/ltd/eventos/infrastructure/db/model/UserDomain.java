package com.ltd.eventos.infrastructure.db.model;

import com.ltd.eventos.domain.entities.UserBusinessRules;
import com.ltd.eventos.shared.UserType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

// Lombok
@Getter
@Setter
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
  @OneToMany(mappedBy = "user_id")
  private List<EventoDomain> evento_evento_id;

  public UserDomain(UserBusinessRules userBusinessRules) {
    this.user_id = userBusinessRules.user_id().toString();
    this.username = userBusinessRules.username();
    this.matricula = userBusinessRules.matricula();
    this.senha = userBusinessRules.senha();
    this.user_type = userBusinessRules.userType();
    this.created_at = userBusinessRules.created_at();
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + "(" +
        "user_id = " + user_id + ", " +
        "updated_at = " + updated_at + ", " +
        "created_at = " + created_at + ", " +
        "user_type = " + user_type + ", " +
        "senha = " + senha + ", " +
        "matricula = " + matricula + ", " +
        "username = " + username + ")";
  }
}
