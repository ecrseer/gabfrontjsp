package br.infnet.edu.gabriwebee.gabriwebee.services;

import br.infnet.edu.gabriwebee.gabriwebee.domain.Empresa;
import br.infnet.edu.gabriwebee.gabriwebee.domain.Usuario;
import br.infnet.edu.gabriwebee.gabriwebee.repositories.UsuarioRepository;
import br.infnet.edu.gabriwebee.gabriwebee.services.AmazonService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class UsuarioService {
    final String DEFAULT_BUCKET = "gabecrbuck";

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    AmazonService amazonService;

    public String editUser(Usuario usuario, MultipartFile multipartFile) {
        String filename = multipartFile.getOriginalFilename();
        String extension = FilenameUtils.getExtension(filename);

        String newName = "/profilePic." + extension;
        String fileKeyAWS = "users/" + usuario.getLogin() + "" + newName;
        String localPath = "src/main/resources/static/images/"
                + usuario.getLogin() + newName;

        System.out.println("localPath:::" + localPath);
        File filePointer = amazonService.convertMultiPartToFile(multipartFile, new File(localPath));
        var result = amazonService.uploadSetFile(DEFAULT_BUCKET, fileKeyAWS, filePointer);

        return "";
    }

    public void loadUser(Usuario usuario) {
        
        //temp
        String extension = "png";

        String newName = "/profilePic." + extension;
        String fileKeyAWS = "users/" + usuario.getLogin() + "" + newName;
        String localPath = "src/main/resources/static/images/"
                + usuario.getLogin() + newName;

        File filePointer = new File(localPath);
        amazonService.getFileFrom(DEFAULT_BUCKET, fileKeyAWS, filePointer);
    }


    public Usuario cadastrarUsuario(@RequestBody Usuario usuario) {
        return usuarioRepository.cadastrarUsuario(usuario);
    }

    public Empresa logarUsuario(@RequestBody Usuario usuario) {
        return usuarioRepository.logarUsuario(usuario);
    }


}
