package com.ltd.eventos.usecases.interactor.local;

import com.ltd.eventos.infrastructure.db.entities.LocalDomain;
import com.ltd.eventos.usecases.DTO.LocalDTO.CreateLocalDTO;
import com.ltd.eventos.usecases.DTO.LocalDTO.ResponseLocalDTO;
import com.ltd.eventos.usecases.DTO.LocalDTO.UpdateLocalDTO;

import java.util.List;

public interface ILocalUseCases {
  LocalDomain createLocal(CreateLocalDTO local);

  String deleteLocal(String id);

  LocalDomain updateLocal(UpdateLocalDTO local);

  ResponseLocalDTO findById(String id);

  List<ResponseLocalDTO> findAll();
}
