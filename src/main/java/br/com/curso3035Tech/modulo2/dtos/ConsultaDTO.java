package br.com.curso3035Tech.modulo2.dtos;

import br.com.curso3035Tech.modulo2.entities.Consulta;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ConsultaDTO implements Serializable {
    private static final Long serialVersionUID = 1L;

    @NotBlank(message = "Código do medico não pode ser nulo")
    private Integer cod_medico;
    @NotBlank(message = "Código do paciente não pode ser nulo")
    private Integer paciente;
    @NotBlank(message = "Data da consulta não pode ser nula")
    private Date dataConsulta;

    public ConsultaDTO(Consulta consulta) {
        this.cod_medico = consulta.getMedico().getCodMedico();
        this.paciente = consulta.getPaciente().getCodPaciente();
        this.dataConsulta = consulta.getDataConsulta();
    }
}
