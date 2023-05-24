package br.com.curso3035Tech.modulo2.services;

import br.com.curso3035Tech.modulo2.dtos.PacienteDTO;
import br.com.curso3035Tech.modulo2.entities.Paciente;
import br.com.curso3035Tech.modulo2.repositories.PacienteRepository;
import br.com.curso3035Tech.modulo2.services.exceptions.*;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    private final PacienteRepository repository;

    public PacienteService(PacienteRepository repository) {
        this.repository = repository;
    }

    public PacienteDTO insereNovoPaciente(@Valid PacienteDTO pacienteDTO){

        if(repository.findByCpf(pacienteDTO.getCpf()).isPresent()){
            throw new CpfExistenteException("CPF EXISTENTE!" );
        } else if (repository.findByEmail(pacienteDTO.getEmail()).isPresent()) {
            throw new EmailExistenteException("EMAIL EXISTENTE!");
        }else{
            Paciente novoPaciente = new Paciente();
            BeanUtils.copyProperties(pacienteDTO,novoPaciente);
            repository.save(novoPaciente);
            return new PacienteDTO(novoPaciente);
        }
    }

    public PacienteDTO findById(Integer id){
        try{
            Optional<Paciente>pacienteRecuperado = repository.findById(id);
            Paciente paciente = new Paciente();
            BeanUtils.copyProperties(pacienteRecuperado,paciente);
            return new PacienteDTO(paciente);
        }catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException("Paciente com id " + id + "Não encontrado!");
        }
    }

    public List<PacienteDTO> listaTodosPacientes() {
        try{
            List<Paciente> pacienteList = this.repository.findAll();
            return pacienteList.stream().map(PacienteDTO::new).toList();
        }catch (EmptyResultDataAccessException e){
            throw new PacienteInexistenteException("Não há pacientes");
        }

    }

    public PacienteDTO atualizaPaciente(Integer id, @Valid PacienteDTO dto){
        try{
            Paciente paciente = this.repository.getOne(id);
            paciente.setCpf(dto.getCpf());
            paciente.setNome(dto.getNome());
            paciente.setDataNascimento(dto.getDataNascimento());
            paciente.setEmail(dto.getEmail());
            return new PacienteDTO(paciente);
        }catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException("Paciente com id " + id + "Não encontrado!");
        }

    }

    public void deletaPaciente(Integer id){
        try{
            this.repository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new MedicoInexistenteException("Paciente com id " + id + " não encontrado!");
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException("Integridade violada!");
        }
    }

    public Boolean pacienteExists(Integer id){
        return this.repository.existsById(id);
    }
}
