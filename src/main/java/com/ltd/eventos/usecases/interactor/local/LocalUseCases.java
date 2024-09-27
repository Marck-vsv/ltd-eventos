package com.ltd.eventos.usecases.interactor.local;

import com.ltd.eventos.domain.entities.local.LocalBusinessRules;
import com.ltd.eventos.infrastructure.db.entities.LocalDomain;
import com.ltd.eventos.infrastructure.db.repository.LocalRepository;
import com.ltd.eventos.usecases.DTO.LocalDTO.ResponseLocalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
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
    public LocalDomain createLocal(LocalBusinessRules local) {
        LocalDomain localDomain = new LocalDomain(local);
        localDomain.setCreated_at(LocalDateTime.now());
        localRepository.save(localDomain);
        return localDomain;
    }

    @Override
    public String deleteLocal(String id) {
        if (localRepository.existsById(id)) {
            localRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Local com o ID: " + id + " não encontrado.");
        }
        return id;
    }

    @Override
    public List<ResponseLocalDTO> findAll() {
        Iterable<LocalDomain> localIterable = localRepository.findAll();

        return StreamSupport.stream(localIterable.spliterator(), true).map(ResponseLocalDTO::new).collect(Collectors.toList());
    }
}
