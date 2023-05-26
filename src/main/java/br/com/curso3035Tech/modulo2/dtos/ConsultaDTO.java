package br.com.curso3035Tech.modulo2.dtos;

import br.com.curso3035Tech.modulo2.entities.Consulta;
import br.com.curso3035Tech.modulo2.entities.Medico;
import br.com.curso3035Tech.modulo2.entities.Paciente;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ConsultaDTO implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Setter(AccessLevel.NONE)
    private Integer id;

    @NotBlank(message = "Código do medico não pode ser nulo")
    private Integer medico;

    @NotBlank(message = "Código do paciente não pode ser nulo")
    private Integer paciente;

    @NotBlank(message = "Data da consulta não pode ser nula")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataConsulta;

    public ConsultaDTO(Consulta consulta) {
        this.id = consulta.getId();
        this.medico = consulta.getMedico().getCodMedico();
        this.paciente = consulta.getPaciente().getCodPaciente();
        this.dataConsulta = consulta.getDataConsulta();
    }

    public static ConsultaDTO of(Consulta consulta) {
        return (consulta.getPaciente() == null) && (consulta.getMedico() == null)?null : new ConsultaDTO(consulta);
    }
}
