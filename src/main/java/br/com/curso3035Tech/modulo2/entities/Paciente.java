package br.com.curso3035Tech.modulo2.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;


import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name ="paciente")
public class Paciente implements Serializable {
    private static final Long serialVersionUID = 1L;
    @Id
    @Column(nullable = false, unique = true)

    private Integer codPaciente;
    @Column(name = "nome",nullable = false)
    private String nome;
    @Column(name = "dataNascimento",nullable = false)
    private Date dataNascimento;
    @Column(name = "cpf",nullable = false)
    @CPF(message = "CPF inválido!")
    private String cpf;
    @Column(name = "email",nullable = false)
    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;
}
