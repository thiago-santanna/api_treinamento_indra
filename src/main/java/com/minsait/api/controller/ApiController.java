package com.minsait.api.controller;

import com.minsait.api.controller.dto.ClienteRequest;
import com.minsait.api.controller.dto.ClienteResponse;
import com.minsait.api.controller.dto.MessageResponse;
import com.minsait.api.repository.ClienteEntity;
import com.minsait.api.repository.ClienteRepository;
import com.minsait.api.util.ObjectMapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api")
public class ApiController{

	@Autowired
	private ClienteRepository clienteRepository;

	@GetMapping("/cliente")
	public ResponseEntity<List<ClienteResponse>> clienteFindAll() {
		final var clienteEntityList = clienteRepository.findAll();
		final var clienteResponseList = ObjectMapperUtil.mapAll(clienteEntityList, ClienteResponse.class);
		return ResponseEntity.ok(clienteResponseList);
	}

	@PostMapping("/cliente")
	public ResponseEntity<ClienteResponse> insert(@RequestBody ClienteRequest request){

		final var clienteEntity = ObjectMapperUtil.map(request, ClienteEntity.class);
		final var clienteInserted = clienteRepository.save(clienteEntity);
		final var clienteResponse = ObjectMapperUtil.map(clienteInserted, ClienteResponse.class);

		return new ResponseEntity<>(clienteResponse, HttpStatus.CREATED);
	}

	@PutMapping("/cliente")
	public ResponseEntity<ClienteResponse> update(@RequestBody ClienteRequest request){
		final var clienteEntity = ObjectMapperUtil.map(request, ClienteEntity.class);
		final var clienteUpdated = clienteRepository.save(clienteEntity);
		final var clienteResponse = ObjectMapperUtil.map(clienteUpdated, ClienteResponse.class);

		return new ResponseEntity<>(clienteResponse, HttpStatus.OK);
	}

	@DeleteMapping("/cliente/{id}")
	public ResponseEntity<MessageResponse> delete(@PathVariable Long id){
		final var clienteEntityFound = clienteRepository.findById(id);
		if(clienteEntityFound.isPresent()){
			clienteRepository.delete(clienteEntityFound.get());
		}else{
			return new ResponseEntity<>(MessageResponse.builder()
					.message("Cliente não encontrado!")
					.date(LocalDateTime.now())
					.error(false)
					.build(), HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(MessageResponse.builder()
				.message("OK")
				.date(LocalDateTime.now())
				.error(false)
				.build(), HttpStatus.OK);
	}

	@GetMapping("/cliente/{id}")
	public ResponseEntity<ClienteResponse> findById(@PathVariable Long id){
		final var clienteEntity = clienteRepository.findById(id);
		ClienteResponse clienteResponse = new ClienteResponse();

		if (clienteEntity.isPresent()){
			clienteResponse = ObjectMapperUtil.map(clienteEntity.get(), ClienteResponse.class);
		}else{
			return new ResponseEntity<>(clienteResponse, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(clienteResponse, HttpStatus.OK);
	}

	private Specification<ClienteEntity> clienteEntitySpecification(ClienteEntity clienteEntity) {

		return (root, query, criteriaBuilder) -> {

			List<Predicate> predicates = new ArrayList<>();

			if (clienteEntity.getNome() != null) {

				predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),
						"%" + clienteEntity.getNome().trim().toLowerCase() + "%"));
			}

			if (clienteEntity.getEndereco() != null) {

				predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("endereco")),
						"%" + clienteEntity.getEndereco().trim().toLowerCase() + "%"));
			}
			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
	}
}