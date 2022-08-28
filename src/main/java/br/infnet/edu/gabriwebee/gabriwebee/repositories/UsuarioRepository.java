package br.infnet.edu.gabriwebee.gabriwebee.repositories;

import br.infnet.edu.gabriwebee.gabriwebee.domain.Usuario;
import br.infnet.edu.gabriwebee.gabriwebee.services.AmazonService;
import br.infnet.edu.gabriwebee.gabriwebee.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UsuarioRepository {
    final String DEFAULT_BUCKET = "gabecrbuck";

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    AmazonService amazonService;

    public String editUser(Usuario usuario, MultipartFile multipartFile) {
        String filename = "users/" + usuario.getLogin() + "/profilePic";
        var result = amazonService.uploadSetFile(DEFAULT_BUCKET, multipartFile, filename);

        return "";
    }


    public Usuario cadastrarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.cadastrarUsuario(usuario);
    }

    public Usuario logarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.logarUsuario(usuario);
    }


}
