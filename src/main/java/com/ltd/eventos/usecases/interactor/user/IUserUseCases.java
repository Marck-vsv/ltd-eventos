package com.ltd.eventos.usecases.interactor.user;

import com.ltd.eventos.domain.entities.user.UserBusinessRules;
import com.ltd.eventos.infrastructure.db.entities.UserDomain;
import com.ltd.eventos.usecases.DTO.UserDTO.ResponseUserDTO;
import com.ltd.eventos.usecases.DTO.UserDTO.UpdateUserDTO;

import java.util.List;

public interface IUserUseCases {
    public UserDomain CreateUser (UserBusinessRules user);
    public String DeleteUser (String id);
    public UserDomain UpdateUser (UpdateUserDTO user);
    ResponseUserDTO FindByMatricula(String matricula);
    public List<ResponseUserDTO> FindAll ();
}
