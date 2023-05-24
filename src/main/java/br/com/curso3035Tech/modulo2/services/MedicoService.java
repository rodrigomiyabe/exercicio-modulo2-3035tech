package br.com.curso3035Tech.modulo2.services;

import br.com.curso3035Tech.modulo2.dtos.MedicoDTO;
import br.com.curso3035Tech.modulo2.entities.Medico;
import br.com.curso3035Tech.modulo2.repositories.MedicoRepository;
import br.com.curso3035Tech.modulo2.services.exceptions.DatabaseException;
import br.com.curso3035Tech.modulo2.services.exceptions.MedicoInexistenteException;
import br.com.curso3035Tech.modulo2.services.exceptions.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicoService {
    private final MedicoRepository repository;

    public MedicoService(MedicoRepository repository) {
        this.repository = repository;
    }

    public MedicoDTO insereMedico(@Valid MedicoDTO dto){
        Medico novoMedico = new Medico();
        BeanUtils.copyProperties(dto,novoMedico);
        final Medico med = this.repository.save(novoMedico);
        return new MedicoDTO(med);
    }

    public MedicoDTO findById(Integer id) {
        try {
            Optional<Medico> medicoRecuperado = repository.findById(id);
            Medico medico = new Medico();
            BeanUtils.copyProperties(medicoRecuperado, medico);
            return new MedicoDTO(medico);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Médico inexistente!");
        }
    }

    public List<MedicoDTO> listaTodosMedicos() {
        try {
            List<Medico> MedicoList = this.repository.findAll();
            return MedicoList.stream().map(MedicoDTO::new).toList();
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Médico inexistente!");
        }
    }

    public MedicoDTO atualizaMedico(@Valid Integer id, MedicoDTO dto){
        try {
            Medico medico = this.repository.getOne(id);
            medico.setNome(dto.getNome());
            medico.setEspecialidade(dto.getEspecialidade());
            return new MedicoDTO(medico);
        }catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Médico inexistente!");
        }
    }

    public void deletaMedico(Integer id){
        try{
            this.repository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new MedicoInexistenteException("Médico com id " + id + " não encontrado!");
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException("Integridade violada!");
        }

    }

    public Boolean medicoExists(Integer id) {
        return this.repository.existsById(id);
    }
}
