package br.infnet.edu.gabriwebee.gabriwebee.repositories;

import br.infnet.edu.gabriwebee.gabriwebee.domain.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(url = "http://localhost:8097/login", name = "UsuarioRepository")
public interface UsuarioRepository {

    @PostMapping("/cadastrar")
    public Usuario cadastrarUsuario(@RequestBody Usuario usuario);

    @PostMapping("/logar")
    public Usuario logarUsuario(@RequestBody Usuario usuario);

}