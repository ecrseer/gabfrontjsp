package br.infnet.edu.gabriwebee.gabriwebee.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RespostaVaga {

    private int idRespostaVaga;

    private Vaga vagaFk;

    private Candidato candidatoFk;

    private String respostas;

}
