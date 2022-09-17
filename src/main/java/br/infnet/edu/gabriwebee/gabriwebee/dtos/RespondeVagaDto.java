package br.infnet.edu.gabriwebee.gabriwebee.dtos;

import br.infnet.edu.gabriwebee.gabriwebee.domain.Criterio;
import br.infnet.edu.gabriwebee.gabriwebee.domain.Resposta;
import br.infnet.edu.gabriwebee.gabriwebee.domain.RespostaVaga;
import br.infnet.edu.gabriwebee.gabriwebee.domain.Vaga;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RespondeVagaDto implements iMyWebDto {
    RespostaVaga respostaVaga;

    int currentCriterio = 0;

    public Resposta nextCriterio() {
        Criterio criterio = this.respostaVaga.getVagaFk()
                .getCriterios().get(currentCriterio);
        if (criterio != null) {
            currentCriterio++;
        }

        return new Resposta(0, criterio, 0, null);
    }

    public void setNextCriterio(Resposta resposta) {
        var list = this.respostaVaga.getRespostas();
        if (list == null) {
            this.respostaVaga.setRespostas(new ArrayList<>());
            list = new ArrayList<>();
        }
        list.add(resposta);
    }

    public String getKey() {
        return "RespondeVagaDto";
    }


}
