package com.ltd.eventos.usecases.interactor.local;

import com.ltd.eventos.domain.entities.LocalBusinessRules;
import com.ltd.eventos.infrastructure.db.entities.LocalDomain;
import com.ltd.eventos.infrastructure.db.repository.LocalRepository;
import com.ltd.eventos.usecases.DTO.LocalDTO.CreateLocalDTO;
import com.ltd.eventos.usecases.DTO.LocalDTO.ResponseLocalDTO;
import com.ltd.eventos.usecases.DTO.LocalDTO.UpdateLocalDTO;
import com.ltd.eventos.usecases.DTO.UserDTO.ResponseUserDTO;
import com.ltd.eventos.usecases.exceptions.LocalNaoExiste;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class LocalUseCases implements ILocalUseCases {
  private final LocalRepository localRepository;

  @Autowired
  public LocalUseCases(LocalRepository localRepository) {
    this.localRepository = localRepository;
  }

  @Override
  public LocalDomain createLocal(CreateLocalDTO local) {
    LocalDomain localDomain = new LocalDomain(new LocalBusinessRules(local));
    localRepository.save(localDomain);
    return localDomain;
  }

  @Override
  public String deleteLocal(String id) throws IllegalArgumentException {
    if (localRepository.existsById(id)) {
      localRepository.deleteById(id);
    } else {
      throw new IllegalArgumentException("Local com o ID: " + id + " não encontrado.");
    }
    return id;
  }

  @Override
  public LocalDomain updateLocal(UpdateLocalDTO user) throws LocalNaoExiste {
    Optional<LocalDomain> localDomain = localRepository.findById(user.localID());
    if (localDomain.isPresent()) {
      if (user.localCapacidade() != null) {
        localDomain.get().setLocal_capacidade(user.localCapacidade());
      }
      if (user.localTipo() != null) {
        localDomain.get().setLocal_tipo(user.localTipo());
      }
      if (user.localTipo() != null) {
        localDomain.get().setLocal_tipo(user.localTipo());
      }
      localRepository.save(localDomain.get());
    } else {
      throw new LocalNaoExiste("Local nao existe.");
    }
    return localDomain.get();
  }

  @Override
  public ResponseLocalDTO findById(String id) {
    Optional<LocalDomain> localDomain = localRepository.findById(id);
    if (localDomain.isEmpty()) {
      throw new LocalNaoExiste("Local nao existe.");
    }
    return new ResponseLocalDTO(localDomain.get());
  }

  @Override
  public List<ResponseLocalDTO> findAll() {
    Iterable<LocalDomain> localIterable = localRepository.findAll();

    return StreamSupport.stream(localIterable.spliterator(), true).map(ResponseLocalDTO::new).collect(Collectors.toList());
  }
}
