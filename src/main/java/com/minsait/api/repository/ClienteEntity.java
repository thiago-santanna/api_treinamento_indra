package com.minsait.api.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.*;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CLIENTE", schema = "API")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "API.SQ_ID_CLIENTE")
    @SequenceGenerator(name="API.SQ_ID_CLIENTE", sequenceName = "API.SQ_ID_CLIENTE", allocationSize = 1)
    @Column(name = "ID_CLIENTE")
    private Long id;

    @Column(name = "NOME", nullable = false, length = 200)
    private String nome;

    @Column(name = "ENDERECO", nullable = false, length = 200)
    private String endereco;

    @Column(name = "EMAIL", nullable = false, length = 100)
    private String email;

    @Column(name = "TELEFONE", nullable = false, length = 100)
    private String telefone;

    public Specification<ClienteEntity> clienteEntitySpecification() {

        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (this.getNome() != null) {

                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("nome")),
                        "%" + this.getNome().trim().toLowerCase() + "%"));
            }

            if (this.getEndereco() != null) {

                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("endereco")),
                        "%" + this.getEndereco().trim().toLowerCase() + "%"));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
