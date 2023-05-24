package br.com.curso3035Tech.modulo2.repositories;

import br.com.curso3035Tech.modulo2.entities.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ConsultaRepository extends JpaRepository<Consulta,Integer> {
}
