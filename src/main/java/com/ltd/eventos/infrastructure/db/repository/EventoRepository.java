package com.ltd.eventos.infrastructure.db.repository;

import com.ltd.eventos.infrastructure.db.model.EventoDomain;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends CrudRepository<EventoDomain, String> {
}
