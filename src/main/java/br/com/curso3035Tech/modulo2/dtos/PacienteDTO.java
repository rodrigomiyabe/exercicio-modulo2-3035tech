package br.com.curso3035Tech.modulo2.dtos;

import br.com.curso3035Tech.modulo2.entities.Paciente;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;


import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PacienteDTO implements Serializable {

    @Serial
    private static final long serialVersionUID =1L;

    @NotBlank(message = "Codigo do paciente não pode ser nulo")
    private Integer codPaciente;

    @NotBlank(message = "Nome do paciente não pode ser nulo")
    private String nome;

    @NotBlank(message = "Data de nascimento do paciente não pode ser nulo")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataNascimento;

    @CPF(message = "CPF invalido")
    @NotBlank(message = "CPF não pode ser nulo")
    private String cpf;

    @NotBlank(message = "E-mail nao pode ser nulo")
    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;

    public PacienteDTO(Paciente paciente){
        this.codPaciente = paciente.getCodPaciente();
        this.nome = paciente.getNome();
        this.dataNascimento = paciente.getDataNascimento();
        this.cpf = paciente.getCpf();
        this.email = paciente.getEmail();
    }

    public static PacienteDTO of (Paciente paciente){
        return (paciente == null)? null : new PacienteDTO(paciente);
    }

}
