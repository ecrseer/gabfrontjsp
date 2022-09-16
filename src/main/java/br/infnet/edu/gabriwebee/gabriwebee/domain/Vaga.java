package br.infnet.edu.gabriwebee.gabriwebee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vaga implements Serializable {
    private static final long serialVersionUID = 1L;

    long idVaga;
    String cargo;

    List<Criterio> criterios;

    Empresa empresaFk;
    List<RespostaVaga> respostaVagas;

    public Vaga(String cargo) {
        this.cargo = cargo;
        criterios = new ArrayList<>();
    }



    @Override
    public String toString() {
        return "Vaga{" +
                "idVaga=" + idVaga +
                ", cargo='" + cargo + '\'' +
                ", criterios=" + criterios +
                '}';
    }

}
