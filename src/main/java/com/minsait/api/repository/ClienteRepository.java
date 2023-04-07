package com.minsait.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
    Page<ClienteEntity> findAll(@Nullable Specification<ClienteEntity> spec, Pageable pageable);
    List<ClienteEntity> findAll(@Nullable Specification<ClienteEntity> spec);
}
