package br.com.curso3035Tech.modulo2.entities;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "medico")
public class Medico implements Serializable {
    private static final Long serialVersionUID = 1L;
    @Id
    @Column(nullable = false)
    private Integer codMedico;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String especialidade;

}
