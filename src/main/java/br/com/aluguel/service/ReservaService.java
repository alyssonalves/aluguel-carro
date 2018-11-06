package br.com.aluguel.service;

import br.com.aluguel.service.dto.ReservaDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Reserva.
 */
public interface ReservaService {

    /**
     * Save a reserva.
     *
     * @param reservaDTO the entity to save
     * @return the persisted entity
     */
    ReservaDTO save(ReservaDTO reservaDTO);

    /**
     * Get all the reservas.
     *
     * @return the list of entities
     */
    List<ReservaDTO> findAll();
    /**
     * Get all the ReservaDTO where Cliente_id is null.
     *
     * @return the list of entities
     */
    List<ReservaDTO> findAllWhereCliente_idIsNull();


    /**
     * Get the "id" reserva.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ReservaDTO> findOne(Long id);

    /**
     * Delete the "id" reserva.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
