package com.ltd.eventos.infrastructure.db.repository;

import com.ltd.eventos.infrastructure.db.model.UserDomain;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserDomain, String> {

    @Query("SELECT u FROM UserDomain u WHERE u.username = :username")
    UserDomain findByUsername(String username);

    @Query("SELECT u FROM UserDomain u WHERE u.matricula = :matricula")
    UserDomain findByMatricula(String matricula);
}
