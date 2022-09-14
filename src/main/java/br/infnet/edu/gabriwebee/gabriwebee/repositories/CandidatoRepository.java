package br.infnet.edu.gabriwebee.gabriwebee.repositories;

import br.infnet.edu.gabriwebee.gabriwebee.domain.Candidato;
import br.infnet.edu.gabriwebee.gabriwebee.domain.Empresa;
import br.infnet.edu.gabriwebee.gabriwebee.domain.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "http://localhost:8082/login", name = "CandidatoRepository")
public interface CandidatoRepository {
    @PostMapping("/cadastrar")
    public Usuario cadastrarCandidato(@RequestBody Usuario usuario);

    @PostMapping("/logar")
    public Candidato logarCandidato(@RequestBody Usuario usuario);
}
