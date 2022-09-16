package br.infnet.edu.gabriwebee.gabriwebee.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class RespostaVaga implements Serializable {


    private int idRespostaVaga;

    private Vaga vagaFk;

    private Candidato candidatoFk;

    private List<Resposta> respostas;



}
