package br.com.curso3035Tech.modulo2.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Entity
@Table(name ="paciente")
public class Paciente implements Serializable {
    private static final Long serialVersionUID = 1L;
    @Id
    @Column(nullable = false)
    private Integer codPaciente;
    @Column(name = "nome",nullable = false)
    private String nome;
    @Column(name = "dataNascimento",nullable = false)
    private Date dataNascimento;
    @Column(name = "cpf",nullable = false)
    private String cpf;
    @Column(name = "email",nullable = false)
    private String email;
}
