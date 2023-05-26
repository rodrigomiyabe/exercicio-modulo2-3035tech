package br.com.curso3035Tech.modulo2.controllers;

import br.com.curso3035Tech.modulo2.dtos.ConsultaDTO;
import br.com.curso3035Tech.modulo2.services.ConsultaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {

    private final ConsultaService service;

    public ConsultaController(ConsultaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ConsultaDTO>novaConsulta(@RequestBody ConsultaDTO dto){
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(service.criaNovaConsulta(dto));
    }


}
