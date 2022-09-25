package br.infnet.edu.gabriwebee.gabriwebee.domain;

import br.infnet.edu.gabriwebee.gabriwebee.dtos.iMyWebDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements Serializable, iMyWebDto {
    private static final long serialVersionUID = 1L;


    private long idUsuario;
    private String login;
    private String password;
    String tipo;


    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public String getKey() {
        return "loggedUser";
    }
}
