package com.ltd.eventos.usecases.interactor.user;

import com.ltd.eventos.infrastructure.db.entities.UserDomain;
import com.ltd.eventos.usecases.DTO.UserDTO.CreateUserDTO;
import com.ltd.eventos.usecases.DTO.UserDTO.ResponseUserDTO;
import com.ltd.eventos.usecases.DTO.UserDTO.UpdateUserDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUserUseCases {
    public UserDomain createUser (CreateUserDTO user);
    public String deleteUser (String id);
    public Optional<UserDomain> updateUser (UpdateUserDTO user);
    Optional<ResponseUserDTO> findByMatricula(String matricula);
    public List<ResponseUserDTO> findAll ();
}
