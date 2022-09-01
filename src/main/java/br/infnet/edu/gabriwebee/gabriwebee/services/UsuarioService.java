package br.infnet.edu.gabriwebee.gabriwebee.services;

import br.infnet.edu.gabriwebee.gabriwebee.domain.Usuario;
import br.infnet.edu.gabriwebee.gabriwebee.repositories.UsuarioRepository;
import br.infnet.edu.gabriwebee.gabriwebee.services.AmazonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UsuarioService {
    final String DEFAULT_BUCKET = "gabecrbuck";

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    AmazonService amazonService;

    public String editUser(Usuario usuario, MultipartFile multipartFile) {
        String nameeditUser = multipartFile.getOriginalFilename();

        String filePath = "users/" + usuario.getLogin() + "/";
        var result = amazonService.uploadSetFile(DEFAULT_BUCKET, multipartFile, filePath);

        return "";
    }


    public Usuario cadastrarUsuario(@RequestBody Usuario usuario) {
        return usuarioRepository.cadastrarUsuario(usuario);
    }

    public Usuario logarUsuario(@RequestBody Usuario usuario) {
        return usuarioRepository.logarUsuario(usuario);
    }


}
