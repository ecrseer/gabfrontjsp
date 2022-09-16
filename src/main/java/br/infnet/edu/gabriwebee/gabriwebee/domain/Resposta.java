package br.infnet.edu.gabriwebee.gabriwebee.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Resposta {

    long idResposta;
    Criterio criterio;

    private int conhecimento;
    RespostaVaga respostaCriterioFk;

}
