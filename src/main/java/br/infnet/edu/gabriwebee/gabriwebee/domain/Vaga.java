package br.infnet.edu.gabriwebee.gabriwebee.domain;

import java.util.List;

public class Vaga {
    long id;
    String cargo;
    List<Criterio> criterios;

    public Vaga(long id, String cargo) {
        this.id = id;
        this.cargo = cargo;
    }

    public Vaga() {
    }

    public long getId() {
        return id;
    }

    public String getCargo() {
        return cargo;
    }

    public List<Criterio> getCriterios() {
        return criterios;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setCriterios(List<Criterio> criterios) {
        this.criterios = criterios;
    }

    @Override
    public String toString() {
        return "cargo: " + cargo;
    }
}
