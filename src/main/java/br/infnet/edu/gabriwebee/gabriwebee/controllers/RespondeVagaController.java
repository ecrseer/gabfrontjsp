package br.infnet.edu.gabriwebee.gabriwebee.controllers;

import br.infnet.edu.gabriwebee.gabriwebee.domain.*;
import br.infnet.edu.gabriwebee.gabriwebee.dtos.RespondeVagaIteratorDto;
import br.infnet.edu.gabriwebee.gabriwebee.repositories.RespondeVagaRepository;
import br.infnet.edu.gabriwebee.gabriwebee.repositories.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@EnableFeignClients(basePackages = "br.infnet.edu.gabriwebee.gabriwebee.repositories")
public class RespondeVagaController {
    public String KEY_SESSAO_USUARIO = new Usuario().getKey();
    private String DTO_KEY_RESPONDEVAGA = new RespondeVagaIteratorDto().getKey();

    @Autowired
    VagaRepository vagaRepository;

    @Autowired
    RespondeVagaRepository respondeVagaRepository;

    @GetMapping("/{idVaga}/candidatar")
    public String prepararCandidatar(@PathVariable long idVaga,
                                     HttpServletRequest request) {

        //ResponseEntity<Vaga> data = vagaRepository.getOneVaga(idVaga);
        //Vaga vaga = data.getBody();
        HttpSession session = request.getSession();

        Vaga vaga = vagaRepository.getOneVaga(idVaga);
        request.setAttribute("respondeVaga", vaga);
        Candidato candidato = (Candidato) session.getAttribute(KEY_SESSAO_USUARIO);


        RespostaVaga resposta = new RespostaVaga(0,
                vaga, candidato, null);
        RespondeVagaIteratorDto dto = new RespondeVagaIteratorDto(resposta);
        session.setAttribute(DTO_KEY_RESPONDEVAGA, dto);


        return "candidatar/responder";
    }

    private RespondeVagaIteratorDto respondeConhecimento(Resposta resposta, HttpServletRequest request) {
        HttpSession session = request.getSession();

        RespondeVagaIteratorDto dto = (RespondeVagaIteratorDto) session
                .getAttribute(DTO_KEY_RESPONDEVAGA);

        dto.setNextCriterio(resposta);
        session.setAttribute(DTO_KEY_RESPONDEVAGA, dto);
        return dto;
    }

    @PostMapping("/{idVaga}/respondeCriterio")
    public ModelAndView respondeCriterio(Resposta resposta, HttpServletRequest request) {
        RespondeVagaIteratorDto dto = this.respondeConhecimento(resposta, request);

        return new ModelAndView("candidatar/responder");
    }

    @PostMapping("/{idVaga}/responder")
    public ModelAndView candidatarAvaga(Resposta resposta, HttpServletRequest request) {
        String result = "result";
        try {
            RespondeVagaIteratorDto dto = this.respondeConhecimento(resposta, request);
            //dto.bindFkRespostas();

            RespostaVaga respostaAvaga = dto.getRespostaVaga();
            var saveResult = respondeVagaRepository.salvaResposta(respostaAvaga);
            //var saveResult = respondeVagaRepository.salvaResposta(respostaAvaga.getRespostas().get(0));
            if (saveResult != null) {
                request.getSession().setAttribute(DTO_KEY_RESPONDEVAGA, null);
                request.setAttribute(result, "Candidatou-se com sucesso, Boa sorte");
            }
        } catch (Exception e) {
            request.setAttribute(result, "Nao foi possivel salvar");
            e.printStackTrace();
        }

        return new ModelAndView("candidatar/responder");
    }


}
