package br.infnet.edu.gabriwebee.gabriwebee.dtos;

import br.infnet.edu.gabriwebee.gabriwebee.domain.Criterio;
import br.infnet.edu.gabriwebee.gabriwebee.domain.Resposta;
import br.infnet.edu.gabriwebee.gabriwebee.domain.RespostaVaga;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class RespondeVagaIteratorDto implements iMyWebDto {

    public RespostaVaga respostaVaga;

    public boolean isLast;

    public int currentCriterio = 0;

    public boolean getIsLast() {
        return this.isLast;
    }


    public RespondeVagaIteratorDto(RespostaVaga respostaVaga) {
        this.respostaVaga = respostaVaga;
        this.currentCriterio = 0;
        this.isLast = false;
    }

    public RespondeVagaIteratorDto(RespostaVaga respostaVaga, int currentCriterio) {
        this.respostaVaga = respostaVaga;
        this.currentCriterio = currentCriterio;
        this.isLast = false;
    }


    public List<Criterio> getListaCriterios() {
        return this.respostaVaga.getVagaFk()
                .getCriterios();
    }

    public Resposta nextCriterio() {
        Criterio criterio = this.getListaCriterios().get(currentCriterio);

        return new Resposta(0, criterio.getIdCriterio(), 0, null);
    }

    public Criterio findCriterioById(long idCriterio) {
        Criterio founded = new Criterio();

        for (Criterio criterio :
                getListaCriterios()) {
            if (criterio.getIdCriterio() == idCriterio) {
                founded = criterio;
            }
        }
        return founded;

    }

    public void setNextCriterio(Resposta resposta) {
        var list = this.respostaVaga.getRespostas();
        if (list == null) {
            this.respostaVaga.setRespostas(new ArrayList<>());
            list = this.respostaVaga.getRespostas();
        }
        Criterio criterio = this.getListaCriterios().get(currentCriterio);
        resposta.setCriterioFk(criterio.getIdCriterio());
        list.add(resposta);
        currentCriterio++;
        setLast();

    }

    public void setLast() {
        List<Criterio> criterios = this.getListaCriterios();
        int lastIndex = criterios.size() - 1;

        if (currentCriterio >= lastIndex) {
            this.isLast = true;
            this.currentCriterio = lastIndex;
        }

    }

    public void bindFkRespostas() {
        var respostas = this.respostaVaga.getRespostas();
        for (Resposta resposta :
                respostas) {
            RespostaVaga clone = new RespostaVaga(this.respostaVaga.getIdRespostaVaga(),
                    this.getRespostaVaga().getVagaFk(), this.getRespostaVaga().getCandidatoFk(),
                    null);
            resposta.setRespostaCriterioFk(clone);
        }
    }

    public String getKey() {
        return "RespondeVagaDto";
    }


}
