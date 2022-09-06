package br.infnet.edu.gabriwebee.gabriwebee.dtos;

import br.infnet.edu.gabriwebee.gabriwebee.domain.Criterio;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CadastraVagaDto implements iMyWebDto {

    String cargo;
    String criterio;
    int peso;
    int perfilMinimo;

    public Criterio generateCriterio() {
        return new Criterio(criterio, peso, perfilMinimo);

    }

    public String getKey() {
        return "CadastraVagaDto";
    }


}
