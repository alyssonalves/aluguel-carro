package br.com.aluguel.service;

import br.com.aluguel.service.dto.AluguelDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Aluguel.
 */
public interface AluguelService {

    /**
     * Save a aluguel.
     *
     * @param aluguelDTO the entity to save
     * @return the persisted entity
     */
    AluguelDTO save(AluguelDTO aluguelDTO);

    /**
     * Get all the aluguels.
     *
     * @return the list of entities
     */
    List<AluguelDTO> findAll();
    /**
     * Get all the AluguelDTO where Reserva_id is null.
     *
     * @return the list of entities
     */
    List<AluguelDTO> findAllWhereReserva_idIsNull();


    /**
     * Get the "id" aluguel.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<AluguelDTO> findOne(Long id);

    /**
     * Delete the "id" aluguel.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
