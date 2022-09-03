package br.infnet.edu.gabriwebee.gabriwebee.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
public class Criterio {
 
    String descricao;

    int peso;
    int perfilMinimo;


}
