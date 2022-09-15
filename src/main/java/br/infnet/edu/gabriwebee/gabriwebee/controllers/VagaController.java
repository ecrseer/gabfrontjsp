package br.infnet.edu.gabriwebee.gabriwebee.controllers;

import br.infnet.edu.gabriwebee.gabriwebee.domain.*;
import br.infnet.edu.gabriwebee.gabriwebee.dtos.CadastraVagaDto;
import br.infnet.edu.gabriwebee.gabriwebee.repositories.RespondeVagaRepository;
import br.infnet.edu.gabriwebee.gabriwebee.repositories.VagaRepository;
import com.amazonaws.services.xray.model.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/vagas")
@EnableFeignClients(basePackages = "br.infnet.edu.gabriwebee.gabriwebee.repositories")
public class VagaController {
    private String KEY_SESSAO_USUARIO = new Usuario().getKey();
    private String DTO_KEY_CADASTRAVAGA = new CadastraVagaDto().getKey();

    @Autowired
    VagaRepository vagaRepository;

    @Autowired
    RespondeVagaRepository respondeVagaRepository;

    @GetMapping()
    public String listar(Model model) {
        List<Vaga> vagasList = vagaRepository.listar();
        model.addAttribute("vagasList", vagasList);
        model.addAttribute("vagasListSize", vagasList.size());
        System.out.println(vagasList);

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
            System.out.println(ex.getMessage());
        }
        return "redirect:/vagas";
    }

    @GetMapping("/{idVaga}/candidatar")
    public String candidatar(@PathVariable long idVaga,
                             HttpServletRequest request) {

        //ResponseEntity<Vaga> data = vagaRepository.getOneVaga(idVaga);
        //Vaga vaga = data.getBody();
        HttpSession session = request.getSession();

        Vaga vaga = vagaRepository.getOneVaga(idVaga);

        request.setAttribute("respondeVaga", vaga);
        Candidato candidato = (Candidato) session.getAttribute(KEY_SESSAO_USUARIO);

        RespostaVaga resposta = new RespostaVaga(0,
                vaga, candidato, "empp");
        respondeVagaRepository.salvaResposta(resposta);

        return "candidatar/responder";
    }


    @GetMapping("/{id}/deletar")
    public String deletar(@PathVariable long id) {
        Vaga vaga = vagaRepository.deleteVaga(id);
        return "vagas/lista";
    }

    @GetMapping("/deslogar")
    public String deslogar(HttpServletRequest request) {

        HttpSession session = request.getSession();
        session.setAttribute(DTO_KEY_CADASTRAVAGA, null);
        session.setAttribute(KEY_SESSAO_USUARIO, null);
        return ":redirect:/";
    }

}
