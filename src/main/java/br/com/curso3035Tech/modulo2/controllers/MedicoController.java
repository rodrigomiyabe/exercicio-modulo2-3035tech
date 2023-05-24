package br.com.curso3035Tech.modulo2.controllers;

import br.com.curso3035Tech.modulo2.dtos.MedicoDTO;
import br.com.curso3035Tech.modulo2.services.MedicoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/medico")
public class MedicoController {

    private final MedicoService service;

    public MedicoController(MedicoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<MedicoDTO>insereMedico(@RequestBody MedicoDTO dto){
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(dto.getCodMedico()).toUri();
        return ResponseEntity.created(uri).body(service.insereMedico(dto));
    }

    @GetMapping
    public ResponseEntity<List<MedicoDTO>>listaMedicos(){
        return ResponseEntity.ok().body(service.listaTodosMedicos());
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<MedicoDTO>updateMedico(@PathVariable Integer id, @RequestBody MedicoDTO dto){
       return ResponseEntity.ok().body(service.atualizaMedico(id,dto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void>delete(@PathVariable Integer id){
        service.deletaMedico(id);
        return ResponseEntity.noContent().build();
    }

}
