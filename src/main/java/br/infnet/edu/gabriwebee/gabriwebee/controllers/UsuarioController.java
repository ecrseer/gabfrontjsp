package br.infnet.edu.gabriwebee.gabriwebee.controllers;

import br.infnet.edu.gabriwebee.gabriwebee.domain.Candidato;
import br.infnet.edu.gabriwebee.gabriwebee.domain.Empresa;
import br.infnet.edu.gabriwebee.gabriwebee.domain.RespostaVaga;
import br.infnet.edu.gabriwebee.gabriwebee.domain.Usuario;
import br.infnet.edu.gabriwebee.gabriwebee.repositories.RespondeVagaRepository;
import br.infnet.edu.gabriwebee.gabriwebee.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/usuario")
@EnableFeignClients()
public class UsuarioController {
    private String KEY_SESSAO_USUARIO = new Usuario().getKey();

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RespondeVagaRepository respondeVagaRepository;

    @GetMapping("/perfil")
    public String perfil(Model model, HttpServletRequest request) {
        var session = request.getSession();
        try {
            Usuario usuario = (Usuario) session.getAttribute("loggedUser");
            usuarioService.carregaPerfilImagem(usuario);

            List<RespostaVaga> respostas = respondeVagaRepository
                    .getVagasRespondidas(usuario.getIdUsuario()).getBody();
            String primeiroCargo = respostas.get(0).getVagaFk().getCargo();
            System.out.println(primeiroCargo);


        } catch (Exception excep) {
            excep.printStackTrace();
        }
        return "login/perfil";
    }

    @PostMapping("/editarPerfil")
    public String editPerfil(@RequestPart(value = "profilePic") MultipartFile profilePic,
                             HttpServletRequest request) {

        var reqSession = request.getSession();
        try {
            Usuario user = (Usuario) reqSession.getAttribute("loggedUser");

            var result = usuarioService.editUser(user, profilePic);
        } catch (Exception err) {
        }
        return "login/perfil";
    }


    @GetMapping("/cadastrar")
    public String paginaCadastrarEmpresa() {
        return "login/cadastrar";
    }

    @GetMapping("/logar")
    public String paginaLogar() {
        return "login/login";
    }

    @GetMapping("/cadastrar-candidato")
    public String paginaCadastrarCandidato() {
        return "login/cadastrarCandidato";
    }

    @PostMapping("/logar")
    public String entrarEmpresa(Usuario usuario, HttpServletRequest request) {
        var reqSession = request.getSession();

        try {
            Empresa empresa = usuarioService.logarUsuario(usuario);
            System.out.println(empresa);
            reqSession.setAttribute(KEY_SESSAO_USUARIO, empresa);
            request.setAttribute("sucesso", empresa);
        } catch (Exception err) {
            request.setAttribute("falha", err);
            System.out.println(err);
        }

        return "login/login";
    }


    @PostMapping("/logar-candidato")
    public String entrarCandidato(Candidato usuario, HttpServletRequest request) {
        var reqSession = request.getSession();

        try {
            Candidato candidato = usuarioService.logarCandidato(usuario);
            System.out.println(candidato);
            reqSession.setAttribute(KEY_SESSAO_USUARIO, candidato);
            request.setAttribute("sucesso", candidato);
        } catch (Exception err) {
            request.setAttribute("falha", err);
            System.out.println(err);
        }

        return "login/login";
    }

    @PostMapping("/cadastrar")
    public String cadastrarEmpresa(Usuario usuario, HttpServletRequest request) {
        Usuario result = null;
        try {
            result = usuarioService.cadastrarUsuario(usuario);
            request.setAttribute("sucesso", result);
        } catch (Exception e) {
            request.setAttribute("falha", result);
            e.printStackTrace();
        }

        System.out.println(result);
        return "login/cadastrar";
    }


    @PostMapping("/cadastrar-candidato")
    public String cadastrarCandidato(Candidato usuario, HttpServletRequest request) {

        Candidato result = null;
        try {
            result = usuarioService.cadastrarCandidato(usuario);
            request.setAttribute("sucesso", result);
        } catch (Exception e) {
            request.setAttribute("falha", result);
            e.printStackTrace();
        }
        System.out.println(result);

        return "login/cadastrarCandidato";
    }


}
