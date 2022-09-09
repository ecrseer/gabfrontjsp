package br.infnet.edu.gabriwebee.gabriwebee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Vaga implements Serializable {
    private static final long serialVersionUID = 1L;

    long idVaga;
    String cargo;

    List<Criterio> criterios;

    Empresa empresaFk;

    public Vaga(String cargo) {
        this.cargo = cargo;
        criterios = new ArrayList<>();
    }

    public Vaga(long idVaga, String cargo) {
        this.idVaga = idVaga;
        this.cargo = cargo;
        criterios = new ArrayList<>();
    }

    public Vaga() {

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
