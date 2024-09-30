package com.ltd.eventos.usecases.interactor.user;

import com.ltd.eventos.infrastructure.db.entities.UserDomain;
import com.ltd.eventos.usecases.DTO.UserDTO.CreateUserDTO;
import com.ltd.eventos.usecases.DTO.UserDTO.ResponseUserDTO;
import com.ltd.eventos.usecases.DTO.UserDTO.UpdateUserDTO;

import java.util.List;
import java.util.Optional;

public interface IUserUseCases {
    UserDomain createUser(CreateUserDTO user);
    String deleteUser(String id);
    Optional<UserDomain> updateUser(UpdateUserDTO user);
    Optional<ResponseUserDTO> findByMatricula(String matricula);
    List<ResponseUserDTO> findAll();
}
