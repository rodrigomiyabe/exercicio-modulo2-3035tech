package br.com.curso3035Tech.modulo2.repositories;

import br.com.curso3035Tech.modulo2.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente,Integer> {
    Optional<Paciente> findByEmail(String email);
    Optional<Paciente> findByCpf(String cpf);
    Optional<Paciente>findById(Integer id);
}
