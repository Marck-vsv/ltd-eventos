package com.ltd.eventos.usecases.interactor;

import com.ltd.eventos.domain.entities.LocalBusinessRules;
import com.ltd.eventos.infrastructure.db.model.LocalDomain;
import com.ltd.eventos.infrastructure.db.repository.LocalRepository;
import com.ltd.eventos.adapter.DTO.LocalDTO.RequestLocalDTO;
import com.ltd.eventos.usecases.exceptions.LocalNaoExiste;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class LocalUseCases {
  private final LocalRepository localRepository;

  @Autowired
  public LocalUseCases(LocalRepository localRepository) {
    this.localRepository = localRepository;
  }

  public LocalDomain createLocal(RequestLocalDTO local) throws RuntimeException {
    try {
      LocalDomain localDomain = new LocalDomain(new LocalBusinessRules(local));
      localRepository.save(localDomain);
          return localDomain;
    } catch (RuntimeException e) {
      throw new RuntimeException("Erro ao cadastrar local. Stacktrace: " + e);
    }
  }

  public String deleteLocal(String id) throws LocalNaoExiste {
    if (localRepository.existsById(id)) {
      localRepository.deleteById(id);
    } else {
      throw new LocalNaoExiste("Local com o ID: " + id + " não encontrado.");
    }
    return id;
  }

  public LocalDomain updateLocal(RequestLocalDTO user) throws LocalNaoExiste {
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

  public LocalDomain  findById(String id) throws LocalNaoExiste {
    Optional<LocalDomain> localDomain = localRepository.findById(id);
    if (localDomain.isEmpty()) {
      throw new LocalNaoExiste("Local nao existe.");
    }
    return localDomain.get();
  }

  public List<LocalDomain> findAll() {
    return StreamSupport.stream(localRepository.findAll().spliterator(), true).collect(Collectors.toList());
  }
}
