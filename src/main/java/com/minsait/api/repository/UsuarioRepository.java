package com.minsait.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    Page<UsuarioEntity> findAll(@Nullable Specification<UsuarioEntity> spec, Pageable pageable);
    List<UsuarioEntity> findAll(@Nullable Specification<UsuarioEntity> spec);
    UsuarioEntity findByLogin(String login);
}
