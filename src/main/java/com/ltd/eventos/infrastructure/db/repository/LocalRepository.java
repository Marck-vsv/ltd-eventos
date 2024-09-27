package com.ltd.eventos.infrastructure.db.repository;

import com.ltd.eventos.infrastructure.db.entities.LocalDomain;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalRepository extends CrudRepository<LocalDomain, String> {

}
