package br.infnet.edu.gabriwebee.gabriwebee.dtos;

import br.infnet.edu.gabriwebee.gabriwebee.domain.RespostaVaga;
import br.infnet.edu.gabriwebee.gabriwebee.domain.Vaga;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RespondeVagaDto implements iMyWebDto {
    RespostaVaga respostaVaga;

    public String getKey() {
        return "RespondeVagaDto";
    }


}
