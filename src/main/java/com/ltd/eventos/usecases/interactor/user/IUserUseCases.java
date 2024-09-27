package com.ltd.eventos.usecases.interactor.user;

import com.ltd.eventos.domain.entities.user.UserBusinessRules;
import com.ltd.eventos.infrastructure.db.entities.UserDomain;
import com.ltd.eventos.usecases.DTO.UserDTO.ResponseUserDTO;
import com.ltd.eventos.usecases.DTO.UserDTO.UpdateUserDTO;

import java.util.List;
import java.util.Optional;

public interface IUserUseCases {
    public UserDomain createUser (UserBusinessRules user);
    public String deleteUser (String id);
    public Optional<UserDomain> updateUser (UpdateUserDTO user);
    Optional<ResponseUserDTO> findByMatricula(String matricula);
    public List<ResponseUserDTO> findAll ();
}
