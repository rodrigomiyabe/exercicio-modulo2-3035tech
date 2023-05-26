package br.com.curso3035Tech.modulo2.services;

import br.com.curso3035Tech.modulo2.dtos.ConsultaDTO;
import br.com.curso3035Tech.modulo2.entities.Consulta;
import br.com.curso3035Tech.modulo2.repositories.ConsultaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ConsultaService {

    private final ConsultaRepository repository;
    private final PacienteService pacienteService;
    private final MedicoService medicoService;

    public ConsultaService(ConsultaRepository repository, PacienteService pacienteService, MedicoService medicoService) {
        this.repository = repository;
        this.pacienteService = pacienteService;
        this.medicoService = medicoService;
    }

    @Transactional
    public ConsultaDTO criaNovaConsulta(ConsultaDTO dto){
        Consulta consulta = Consulta.builder()
                                    .medico(this.medicoService.buscaMedicoPorID(dto.getMedico()))
                                    .paciente(this.pacienteService.findEntity(dto.getPaciente()))
                                    .dataConsulta(dto.getDataConsulta())
                                    .build();
        this.repository.save(consulta);
            return ConsultaDTO.of(consulta);
    }

}
