package br.infnet.edu.gabriwebee.gabriwebee.controllers;

import br.infnet.edu.gabriwebee.gabriwebee.domain.Criterio;
import br.infnet.edu.gabriwebee.gabriwebee.domain.Vaga;
import br.infnet.edu.gabriwebee.gabriwebee.dtos.CadastraVagaDto;
import br.infnet.edu.gabriwebee.gabriwebee.repositories.VagaRepository;
import com.amazonaws.services.xray.model.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
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

    private String DTO_KEY_CADASTRAVAGA = new CadastraVagaDto().getKey();

    @Autowired
    VagaRepository vagaRepository;

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
            Vaga vagaCadastrada = vagaRepository.addVaga(vagaCadastro);


            session.setAttribute(DTO_KEY_CADASTRAVAGA, null);
            List<Vaga> vagasList = vagaRepository.listar();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return "redirect:/vagas";
    }

    @GetMapping("/{id}")
    public String exibirUm(@PathVariable long id) {
        return "vagas/exibe";
    }

    @GetMapping("/{id}/alterar")
    public String alterar(@PathVariable long id) {
        return "";
    }

    @GetMapping("/{id}/deletar")
    public String deletar(@PathVariable long id) {
        Vaga vaga = vagaRepository.deleteVaga(id);
        return "vagas/lista";
    }

}
