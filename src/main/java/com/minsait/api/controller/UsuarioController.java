package com.minsait.api.controller;

import com.minsait.api.controller.dto.*;
import com.minsait.api.repository.UsuarioEntity;
import com.minsait.api.repository.UsuarioRepository;
import com.minsait.api.util.ObjectMapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/usuario")
public class UsuarioController implements UsuarioSwagger {

    private final UsuarioRepository repository;
    public UsuarioController(UsuarioRepository repository) {
        this.repository = repository;
    }

    @PreAuthorize("hasAuthority('LEITURA_USUARIO')")
    @GetMapping
    public ResponseEntity<Page<UsuarioResponse>> usuarioFindAll(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int pageSize) {

        Page<UsuarioEntity> usuarioEntities = repository.findAll(PageRequest.of(page, pageSize));
        Page<UsuarioResponse> usuarioResponses = ObjectMapperUtil.mapAll(usuarioEntities, UsuarioResponse.class);
        return ResponseEntity.ok(usuarioResponses);
    }

    @PreAuthorize("hasAuthority('LEITURA_USUARIO')")
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> findById(@PathVariable Long id) {
        Optional<UsuarioEntity> usuarioById = repository.findById(id);

        return usuarioById.map(
                usuarioEntity -> ResponseEntity.ok(
                        ObjectMapperUtil.map(usuarioEntity, UsuarioResponse.class))).orElseGet(
                                () -> ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAuthority('ESCRITA_USUARIO')")
    @PostMapping()
    @Transactional
    public ResponseEntity<UsuarioResponse> insert(@RequestBody UsuarioRequest request){

        final var usuarioEntity = ObjectMapperUtil.map(request, UsuarioEntity.class);
        usuarioEntity.encryptPassword();
        final var usuarioInserted = repository.save(usuarioEntity);
        final var usuarioResponse = ObjectMapperUtil.map(usuarioInserted, UsuarioResponse.class);

        return new ResponseEntity<>(usuarioResponse, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ESCRITA_USUARIO')")
    @PutMapping()
    @Transactional
    public ResponseEntity<UsuarioResponse> update(@RequestBody UsuarioRequest request){
        final var usuarioEntityFound = repository.findById(request.getId());

        if(usuarioEntityFound.isEmpty()){
            return new ResponseEntity<>(new UsuarioResponse(), HttpStatus.NOT_FOUND);
        }

        UsuarioEntity usuario = usuarioEntityFound.get();
        usuario.atualizaDados(request);

        final var usuarioUpdated = repository.save(usuario);
        final var usuarioResponse = ObjectMapperUtil.map(usuarioUpdated, UsuarioResponse.class);

        return new ResponseEntity<>(usuarioResponse, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ESCRITA_USUARIO')")
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<MessageResponse> delete(@PathVariable Long id){
        final var usuarioEntityFound = repository.findById(id);

        if (usuarioEntityFound.isEmpty()) {
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("Usuario não encontrado!")
                    .date(LocalDateTime.now())
                    .error(false)
                    .build(), HttpStatus.NOT_FOUND);
        }

        repository.delete(usuarioEntityFound.get());
        return new ResponseEntity<>(MessageResponse.builder()
                .message("OK")
                .date(LocalDateTime.now())
                .error(false)
                .build(), HttpStatus.OK);
    }

}
