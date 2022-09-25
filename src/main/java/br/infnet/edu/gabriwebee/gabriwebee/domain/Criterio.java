package br.infnet.edu.gabriwebee.gabriwebee.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Criterio implements Serializable {
    private static final long serialVersionUID = 1L;

    long idCriterio;
    String descricao;

    int perfilMinimo;
    int peso;

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
