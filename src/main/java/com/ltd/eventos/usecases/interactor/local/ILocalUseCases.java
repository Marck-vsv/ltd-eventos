package com.ltd.eventos.usecases.interactor.local;

import com.ltd.eventos.infrastructure.db.entities.LocalDomain;
import com.ltd.eventos.infrastructure.db.entities.UserDomain;
import com.ltd.eventos.usecases.DTO.LocalDTO.CreateLocalDTO;
import com.ltd.eventos.usecases.DTO.LocalDTO.ResponseLocalDTO;
import com.ltd.eventos.usecases.DTO.LocalDTO.UpdateLocalDTO;
import com.ltd.eventos.usecases.DTO.UserDTO.CreateUserDTO;
import com.ltd.eventos.usecases.DTO.UserDTO.ResponseUserDTO;

import java.util.List;
import java.util.Optional;

public interface ILocalUseCases {
  LocalDomain createLocal(CreateLocalDTO local);

  String deleteLocal(String id);

  LocalDomain updateLocal(UpdateLocalDTO local);

  ResponseLocalDTO findById(String id);

  List<ResponseLocalDTO> findAll();
}
