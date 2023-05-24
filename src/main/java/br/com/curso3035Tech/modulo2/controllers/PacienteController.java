package br.com.curso3035Tech.modulo2.controllers;

import br.com.curso3035Tech.modulo2.dtos.PacienteDTO;
import br.com.curso3035Tech.modulo2.services.PacienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/paciente")
public class PacienteController {
    private final PacienteService service;

    public PacienteController(PacienteService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PacienteDTO> inserePaciente(@RequestBody PacienteDTO dto){
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(dto.getCodPaciente()).toUri();
        return ResponseEntity.created(uri).body(service.insereNovoPaciente(dto));
    }

    @GetMapping
    public ResponseEntity<List<PacienteDTO>>listaMedicos(){
        return ResponseEntity.ok().body(service.listaTodosPacientes());
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PacienteDTO>updateMedico(@PathVariable Integer id, @RequestBody PacienteDTO dto){
        return ResponseEntity.ok().body(service.atualizaPaciente(id,dto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void>delete(@PathVariable Integer id){
        service.deletaPaciente(id);
        return ResponseEntity.noContent().build();
    }
}
