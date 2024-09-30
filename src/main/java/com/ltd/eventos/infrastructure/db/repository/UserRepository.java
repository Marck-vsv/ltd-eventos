package com.ltd.eventos.infrastructure.db.repository;

import com.ltd.eventos.infrastructure.db.entities.UserDomain;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<UserDomain, String> {

    @Query("SELECT u FROM UserDomain u WHERE u.username = :username")
    UserDomain findByUsername(String username);

    @Query("SELECT u FROM UserDomain u WHERE u.matricula = :matricula")
    UserDomain findByMatricula(String matricula);
}
