package br.infnet.edu.gabriwebee.gabriwebee.controllers;

import br.infnet.edu.gabriwebee.gabriwebee.domain.*;
import br.infnet.edu.gabriwebee.gabriwebee.dtos.CadastraVagaDto;
import br.infnet.edu.gabriwebee.gabriwebee.dtos.RespondeVagaIteratorDto;
import br.infnet.edu.gabriwebee.gabriwebee.repositories.RespondeVagaRepository;
import br.infnet.edu.gabriwebee.gabriwebee.repositories.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/vagas")
public class VagaController extends RespondeVagaController {

    private String DTO_KEY_CADASTRAVAGA = new CadastraVagaDto().getKey();


    @GetMapping()
    public String listar(Model model) {
        List<Vaga> vagasList = null;
        try {
            vagasList = vagaRepository.listar();
            model.addAttribute("vagasList", vagasList);
            model.addAttribute("vagasListSize", vagasList.size());
            System.out.println(vagasList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "vagas/lista";
    }


    private Vaga getCadastrarDto(HttpSession session, CadastraVagaDto dto) {
        try {
            Vaga vagaCadastro = (Vaga) session.getAttribute(DTO_KEY_CADASTRAVAGA);
            if (vagaCadastro == null) {
                session.setAttribute(DTO_KEY_CADASTRAVAGA, new Vaga(dto.getCargo()));
                vagaCadastro = (Vaga) session.getAttribute(DTO_KEY_CADASTRAVAGA);
            }
            return vagaCadastro;

        } catch (Exception err) {
            err.printStackTrace();
        }
        return null;
    }

    @PostMapping("/addCriterio")
    public String addCriterio(CadastraVagaDto dto, HttpServletRequest request) {

        try {

            HttpSession session = request.getSession();
            Vaga vagaCadastro = getCadastrarDto(session, dto);

            List<Criterio> criteriosCadastrados = vagaCadastro.getCriterios();

            criteriosCadastrados.add(dto.generateCriterio());
            session.setAttribute(DTO_KEY_CADASTRAVAGA, vagaCadastro);
        } catch (Exception err) {
            err.printStackTrace();
            System.out.println("Nao ha vaga/ criterios ainda");
        }

        return "vagas/inserir";
    }

    @GetMapping("/inserir")
    public String inserir() {
        return "vagas/inserir";
    }

    @PostMapping("/inserir")
    public String publicarVaga(Vaga vaga, HttpServletRequest request) {

        try {
            HttpSession session = request.getSession();
            Vaga vagaCadastro = (Vaga) session.getAttribute(DTO_KEY_CADASTRAVAGA);
            Empresa usuario = (Empresa) session.getAttribute(KEY_SESSAO_USUARIO);
            vagaCadastro.setEmpresaFk(usuario);

            Vaga vagaCadastrada = vagaRepository.addVaga(vagaCadastro);
            session.setAttribute(DTO_KEY_CADASTRAVAGA, null);
            List<Vaga> vagasList = vagaRepository.listar();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "redirect:/vagas";
    }


    @GetMapping("/deslogar")
    public String deslogar(HttpServletRequest request) {

        HttpSession session = request.getSession();
        session.setAttribute(DTO_KEY_CADASTRAVAGA, null);
        session.setAttribute(KEY_SESSAO_USUARIO, null);
        return "login/cadastrarCandidato";
    }

}
