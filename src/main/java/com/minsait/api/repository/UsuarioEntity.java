package com.minsait.api.repository;

import com.minsait.api.controller.dto.UsuarioRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USUARIO", schema = "API")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "API.SQ_ID_USUARIO")
    @SequenceGenerator(name="API.SQ_ID_USUARIO", sequenceName = "API.SQ_ID_USUARIO", allocationSize = 1)
    @Column(name = "ID_USUARIO")
    private Long id;

    @Column(name = "NOME", nullable = false, length = 200)
    private String nome;

    @Column(name = "LOGIN", nullable = false, length = 200)
    private String login;

    @Column(name = "SENHA", nullable = false, length = 200)
    private String senha;

    @Column(name = "EMAIL", length = 100)
    private String email;

    @Column(name = "PERMISSOES")
    private String permissoes;

    public void encryptPassword() {
        this.senha = new BCryptPasswordEncoder().encode(this.senha);
    }

    public void atualizaDados(UsuarioRequest request){
        this.setNome(request.getNome());
        this.setLogin(request.getLogin());
        this.setEmail(request.getEmail());
        this.setPermissoes(request.getPermissoes());
    }
}
