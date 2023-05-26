package br.com.curso3035Tech.modulo2.services;

import br.com.curso3035Tech.modulo2.dtos.MedicoDTO;
import br.com.curso3035Tech.modulo2.entities.Medico;
import br.com.curso3035Tech.modulo2.entities.Paciente;
import br.com.curso3035Tech.modulo2.repositories.MedicoRepository;
import br.com.curso3035Tech.modulo2.services.exceptions.EntityAlreadyExists;
import br.com.curso3035Tech.modulo2.services.exceptions.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MedicoService {
    private final MedicoRepository repository;

    public MedicoService(MedicoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public MedicoDTO insereMedico(@Valid MedicoDTO dto) throws ResourceNotFoundException{
        if (repository.findById(dto.getCodMedico()).isPresent()) {
            throw new EntityAlreadyExists("PACIENTE EXISTENTE!");
        } else{
            Medico medico = new Medico();
            BeanUtils.copyProperties(dto,medico);
            repository.save(medico);
            return new MedicoDTO(medico);
        }
    }

    @Transactional(readOnly = true)
    public Medico buscaMedicoPorID(Integer id){
        return this.repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado!"));
    }

    @Transactional(readOnly = true)
    public List<MedicoDTO> listaTodosMedicos() {
        try {
            List<Medico> MedicoList = this.repository.findAll();
            return MedicoList.stream().map(MedicoDTO::new).toList();
        } catch (Exception e) {
            throw new ResourceNotFoundException("Médico inexistente!");
        }
    }

    @Transactional
    public MedicoDTO atualizaMedico(@Valid Integer id, MedicoDTO dto){
        Medico medico = this.buscaMedicoPorID(id);
        medico.setNome(dto.getNome());
        medico.setEspecialidade(dto.getEspecialidade());
        return MedicoDTO.of(medico);
    }

    @Transactional
    public void deletaMedico(Integer id){
        this.repository.deleteById(this.repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entidade nao encontrada!")).getCodMedico());
    }


}
