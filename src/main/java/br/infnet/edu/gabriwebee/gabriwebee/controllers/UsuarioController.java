package br.infnet.edu.gabriwebee.gabriwebee.controllers;

import br.infnet.edu.gabriwebee.gabriwebee.domain.Usuario;
import br.infnet.edu.gabriwebee.gabriwebee.dtos.CadastraVagaDto;
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
    private String DTO_KEY_CADASTRAVAGA = new Usuario().getKey();

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
        /*
         * List<Usuario> UsuariosList = UsuarioService.listar();
         * model.addAttribute("UsuariosList", UsuariosList);
         * model.addAttribute("UsuariosListSize", UsuariosList.size());
         * System.out.println(UsuariosList);
         */
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
    public String inserir() {
        return "login/cadastrar";
    }

    @GetMapping("/logar")
    public String logar() {
        return "login/login";
    }

    @PostMapping("/logar")
    public String entrar(Usuario usuario, HttpServletRequest request) {
        var reqSession = request.getSession();
        try {
            var result = usuarioService.logarUsuario(usuario);
            System.out.println(result);
            reqSession.setAttribute("loggedUser", result);
        } catch (Exception err) {
            System.out.println(err);
        }

        return "login/login";
    }


    @PostMapping("/cadastrar")
    public String publicarUsuario(Usuario usuario) {
        var result = usuarioService.cadastrarUsuario(usuario);
        System.out.println(result);
        /*
         * try {
         * System.out.println(Usuario);
         * List<Usuario> UsuariosList = UsuarioService.listar();
         * int lastIndex = UsuariosList.size() - 1;
         * var last = UsuariosList.get(lastIndex);
         * Usuario.setId(last.getId() + 1);
         * // Usuario Usuario;
         * Usuario UsuarioCadastrada = UsuarioService.addUsuario(Usuario);
         *
         * } catch (Exception ex) {
         * System.out.println(ex.getMessage());
         * }
         */
        return "login/cadastrar";
    }

    //deprecated above
    @GetMapping()
    public String listar(Model model) {
        /*
         * List<Usuario> UsuariosList = UsuarioService.listar();
         * model.addAttribute("UsuariosList", UsuariosList);
         * model.addAttribute("UsuariosListSize", UsuariosList.size());
         * System.out.println(UsuariosList);
         */
        return "Usuarios/lista";
    }

    @GetMapping("/{id}")
    public String exibirUm(@PathVariable long id) {
        return "Usuarios/exibe";
    }

    @GetMapping("/{id}/alterar")
    public String alterar(@PathVariable long id) {
        return "";
    }

    @GetMapping("/{id}/deletar")
    public String deletar(@PathVariable long id) {
        // Usuario Usuario = UsuarioService.deleteUsuario(id);
        return "Usuarios/lista";
    }

}
