package br.infnet.edu.gabriwebee.gabriwebee.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Vaga implements Serializable {
    private static final long serialVersionUID = 1L;

    long idVaga;
    String cargo;

    List<Criterio> criterios;

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

    public long getId() {
        return idVaga;
    }

    public String getCargo() {
        return cargo;
    }

    public List<Criterio> getCriterios() {
        return criterios;
    }

    public void setId(long id) {
        this.idVaga = id;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setCriterios(List<Criterio> criterios) {
        this.criterios = criterios;
    }
}
