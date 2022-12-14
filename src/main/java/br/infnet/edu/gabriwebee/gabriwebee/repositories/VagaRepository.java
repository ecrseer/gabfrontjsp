package br.infnet.edu.gabriwebee.gabriwebee.repositories;

import br.infnet.edu.gabriwebee.gabriwebee.domain.Vaga;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(url = "http://localhost:8097/vagasEmpresa", name = "VagaRepository")
public interface VagaRepository {

    @GetMapping()
    List<Vaga> listar();

    @PostMapping()
    public Vaga addVaga(@RequestBody Vaga vaga);

    @DeleteMapping("/{id}")
    public Vaga deleteVaga(@PathVariable long id);

    @GetMapping("{idVaga}")
    public Vaga getOneVaga(@PathVariable long idVaga);
}