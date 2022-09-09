package br.infnet.edu.gabriwebee.gabriwebee.domain;

import br.infnet.edu.gabriwebee.gabriwebee.dtos.iMyWebDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class Empresa extends Usuario {
    public String causaSocial;
    List<Vaga> vagas;
}
