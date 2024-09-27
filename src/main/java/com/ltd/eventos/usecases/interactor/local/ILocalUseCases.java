package com.ltd.eventos.usecases.interactor.local;

import com.ltd.eventos.domain.entities.local.LocalBusinessRules;
import com.ltd.eventos.infrastructure.db.entities.LocalDomain;
import com.ltd.eventos.usecases.DTO.LocalDTO.ResponseLocalDTO;

import java.util.List;

public interface ILocalUseCases {
    public LocalDomain createLocal (LocalBusinessRules local);
    public String deleteLocal (String id);
    public List<ResponseLocalDTO> findAll ();
}
