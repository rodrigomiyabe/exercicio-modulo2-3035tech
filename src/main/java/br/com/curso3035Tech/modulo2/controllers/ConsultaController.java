package br.com.curso3035Tech.modulo2.controllers;

import br.com.curso3035Tech.modulo2.dtos.ConsultaDTO;
import br.com.curso3035Tech.modulo2.services.ConsultaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {

    private final ConsultaService service;

    public ConsultaController(ConsultaService service) {
        this.service = service;
    }

    @PostMapping("")
    public ResponseEntity<Optional<ConsultaDTO>> criaConsulta(@RequestBody ConsultaDTO dto){
        return ResponseEntity.ok().body(service.criaNovaConsulta(dto));
    }
}
