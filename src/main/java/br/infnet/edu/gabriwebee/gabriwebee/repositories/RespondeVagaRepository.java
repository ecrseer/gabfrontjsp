package br.infnet.edu.gabriwebee.gabriwebee.repositories;

import br.infnet.edu.gabriwebee.gabriwebee.domain.RespostaVaga;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(url = "http://localhost:8082/respostaVaga",
        name = "RespondeVagaRepository")
public interface RespondeVagaRepository {

    @PostMapping("/responder")
    public RespostaVaga salvaResposta(
            @RequestBody RespostaVaga resposta);

    @GetMapping("/respostas/{idCandidato}")
    public ResponseEntity<List<RespostaVaga>> getVagasRespondidas(@PathVariable long idCandidato);
}
