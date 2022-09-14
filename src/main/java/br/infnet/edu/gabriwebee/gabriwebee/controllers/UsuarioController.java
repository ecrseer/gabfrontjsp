package br.infnet.edu.gabriwebee.gabriwebee.controllers;

import br.infnet.edu.gabriwebee.gabriwebee.domain.Candidato;
import br.infnet.edu.gabriwebee.gabriwebee.domain.Empresa;
import br.infnet.edu.gabriwebee.gabriwebee.domain.Usuario;
import br.infnet.edu.gabriwebee.gabriwebee.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/usuario")
@EnableFeignClients()
public class UsuarioController {
    private String KEY_SESSAO_USUARIO = new Usuario().getKey();

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/perfil")
    public String perfil(Model model, HttpServletRequest request) {
        var session = request.getSession();
        try {
            Usuario usuario = (Usuario) session.getAttribute("loggedUser");
            usuarioService.loadUser(usuario);
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
        } catch (Exception err) {
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
        } catch (Exception err) {
            System.out.println(err);
        }

        return "login/login";
    }

    @PostMapping("/cadastrar")
    public String cadastrarEmpresa(Usuario usuario) {
        var result = usuarioService.cadastrarUsuario(usuario);

        System.out.println(result);
        return "login/cadastrar";
    }


    @PostMapping("/cadastrar-candidato")
    public String cadastrarCandidato(Candidato usuario) {

        var result = usuarioService.cadastrarCandidato(usuario);
        System.out.println(result);

        return "login/cadastrarCandidato";
    }

    //deprecated above
    @GetMapping()
    public String listar(Model model) {

        return "Usuarios/lista";
    }

    @GetMapping("/{id}")
    public String exibirUm(@PathVariable long id) {
        return "Usuarios/exibe";
    }


    @GetMapping("/{id}/deletar")
    public String deletar(@PathVariable long id) {
        // Usuario Usuario = UsuarioService.deleteUsuario(id);
        return "Usuarios/lista";
    }



}
