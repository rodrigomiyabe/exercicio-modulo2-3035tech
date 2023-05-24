package br.com.curso3035Tech.modulo2.services;

import br.com.curso3035Tech.modulo2.dtos.ConsultaDTO;
import br.com.curso3035Tech.modulo2.entities.Consulta;
import br.com.curso3035Tech.modulo2.repositories.ConsultaRepository;
import br.com.curso3035Tech.modulo2.services.exceptions.MedicoInexistenteException;
import br.com.curso3035Tech.modulo2.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConsultaService {

    private final ConsultaRepository repository;
    private final MedicoService medicoService;
    private final PacienteService pacienteService;

    public ConsultaService(ConsultaRepository repository, MedicoService medicoService, PacienteService pacienteService) {
        this.repository = repository;
        this.medicoService = medicoService;
        this.pacienteService = pacienteService;
    }

    public Optional<ConsultaDTO> criaNovaConsulta(ConsultaDTO dto){
        try{
            if(medicoService.medicoExists(dto.getCod_medico()) && pacienteService.pacienteExists(dto.getPaciente())) {
                Consulta consulta = new Consulta();
                BeanUtils.copyProperties(dto, consulta);
                this.repository.save(consulta);
                return Optional.of(new ConsultaDTO(consulta));
            }
        }
        catch (DataIntegrityViolationException e){
          throw new ResourceNotFoundException("Medico ou Paciente não encontrado!");
        }
        return Optional.empty();
    }

}
