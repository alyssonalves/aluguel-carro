package br.com.aluguel.repository;

import br.com.aluguel.domain.Aluguel;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Aluguel entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AluguelRepository extends JpaRepository<Aluguel, Long> {

}
