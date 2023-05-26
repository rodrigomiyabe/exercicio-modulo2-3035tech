package br.com.curso3035Tech.modulo2.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "consulta")
@Builder
public class Consulta implements Serializable {

    private static final Long serialVersionUID = 1L;
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "codMedico", nullable = false)
    private Medico medico;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "codPaciente", nullable = false)
    private Paciente paciente;
    private Date dataConsulta;
}
