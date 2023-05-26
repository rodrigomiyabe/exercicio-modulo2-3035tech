package br.com.curso3035Tech.modulo2.services;

import br.com.curso3035Tech.modulo2.dtos.PacienteDTO;
import br.com.curso3035Tech.modulo2.entities.Paciente;
import br.com.curso3035Tech.modulo2.repositories.PacienteRepository;
import br.com.curso3035Tech.modulo2.services.exceptions.*;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class PacienteService {
    private final PacienteRepository repository;

    public PacienteService(PacienteRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public PacienteDTO insereNovoPaciente(@Valid PacienteDTO pacienteDTO){

        if(repository.findByCpf(pacienteDTO.getCpf()).isPresent()){
            throw new CpfExistenteException("CPF EXISTENTE!" );
        } else if (repository.findByEmail(pacienteDTO.getEmail()).isPresent()) {
            throw new EmailExistenteException("EMAIL EXISTENTE!");
        } else if (repository.findById(pacienteDTO.getCodPaciente()).isPresent()) {
            throw new EntityAlreadyExists("PACIENTE EXISTENTE!");
        } else{
            Paciente novoPaciente = new Paciente();
            BeanUtils.copyProperties(pacienteDTO,novoPaciente);
            repository.save(novoPaciente);
            return new PacienteDTO(novoPaciente);
        }
    }

    @Transactional(readOnly = true)
    public PacienteDTO findById(Integer id){
        return PacienteDTO.of(this.findEntity(id));
    }

    @Transactional(readOnly = true)
    public List<PacienteDTO> listaTodosPacientes() {
        try{
            List<Paciente> pacienteList = this.repository.findAll();
            return pacienteList.stream().map(PacienteDTO::new).toList();
        }catch (Exception e){
            throw new PacienteInexistenteException("Não há pacientes");
        }
    }

    @Transactional
    public PacienteDTO atualizaPaciente(Integer id, @Valid PacienteDTO dto){
       Paciente paciente = this.findEntity(id);
       paciente.setCpf(dto.getCpf());
       paciente.setNome(dto.getNome());
       paciente.setDataNascimento(dto.getDataNascimento());
       paciente.setEmail(dto.getEmail());
       return new PacienteDTO(paciente);
    }

    @Transactional
    public void deletaPaciente(Integer id) {
        this.repository.deleteById(this.repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entidade nao encontrada!")).getCodPaciente());
    }


    @Transactional(readOnly = true)
    public Boolean pacienteExists(Integer id){
        return this.repository.existsById(id);
    }

    public Paciente findEntity(Integer id){
        return this.repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado!"));
    }
}
