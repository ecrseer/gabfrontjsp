package br.infnet.edu.gabriwebee.gabriwebee.controllers;

import br.infnet.edu.gabriwebee.gabriwebee.domain.Vaga;
import br.infnet.edu.gabriwebee.gabriwebee.services.VagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/vagas")
@EnableFeignClients(basePackages = "br.infnet.edu.gabriwebee.gabriwebee.services")
public class VagaController {

    @Autowired
    VagaService vagaService;

    @GetMapping()
    public String listar(Model model) {
        List<Vaga> vagasList = vagaService.listar();
        model.addAttribute("vagasList", vagasList);
        model.addAttribute("vagasListSize", vagasList.size());
        System.out.println(vagasList);

        return "vagas/lista";
    }

    @GetMapping("/inserir")
    public String inserir() {
        return "vagas/inserir";
    }

    @PostMapping("/inserir")
    public String publicarVaga(Vaga vaga) {
        try {
            System.out.println(vaga);
            List<Vaga> vagasList = vagaService.listar();
            int lastIndex = vagasList.size() - 1;
            var last = vagasList.get(lastIndex);
            vaga.setId(last.getId() + 1);
            // Vaga vaga;
            Vaga vagaCadastrada = vagaService.addVaga(vaga);

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
        Vaga vaga = vagaService.deleteVaga(id);
        return "vagas/lista";
    }

}
