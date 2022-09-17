package br.infnet.edu.gabriwebee.gabriwebee.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class Criterio implements Serializable {
    private static final long serialVersionUID = 1L;

    long idCriterio;
    String descricao;

    int peso;
    int perfilMinimo;

    Vaga vagaFk;

    @Override
    public String toString() {
        return "Criterio{" +
                "descricao='" + descricao + '\'' +
                ", peso=" + peso +
                ", perfilMinimo=" + perfilMinimo +
                '}';
    }
}
