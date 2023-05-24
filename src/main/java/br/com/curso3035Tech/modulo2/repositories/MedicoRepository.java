package br.com.curso3035Tech.modulo2.repositories;

import br.com.curso3035Tech.modulo2.entities.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepository extends JpaRepository<Medico,Integer> {
}
