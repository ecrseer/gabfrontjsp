package br.infnet.edu.gabriwebee.gabriwebee.services;

import br.infnet.edu.gabriwebee.gabriwebee.domain.Produto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(url = "http://localhost:8090", name = "ProdutoService")
public interface ProdutoService {

    @GetMapping("produtos")
    List<Produto> listar();

}