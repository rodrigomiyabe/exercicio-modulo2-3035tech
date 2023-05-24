package br.com.curso3035Tech.modulo2.dtos;

import br.com.curso3035Tech.modulo2.entities.Medico;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicoDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @NotBlank(message = "Codigo do medico não pode ser nulo")
    private Integer codMedico;
    @NotBlank(message = "Nome do médico não pode ser nulo")
    private String nome;
    @NotBlank(message = "Especialidade do medico não pode ser nulo")
    private String especialidade;

    public MedicoDTO(Medico medico){
        this.codMedico = medico.getCodMedico();
        this.nome = medico.getNome();
        this.especialidade = medico.getEspecialidade();
    }

}
