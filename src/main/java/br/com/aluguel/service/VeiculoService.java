package br.com.aluguel.service;

import br.com.aluguel.service.dto.VeiculoDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Veiculo.
 */
public interface VeiculoService {

    /**
     * Save a veiculo.
     *
     * @param veiculoDTO the entity to save
     * @return the persisted entity
     */
    VeiculoDTO save(VeiculoDTO veiculoDTO);

    /**
     * Get all the veiculos.
     *
     * @return the list of entities
     */
    List<VeiculoDTO> findAll();


    /**
     * Get the "id" veiculo.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<VeiculoDTO> findOne(Long id);

    /**
     * Delete the "id" veiculo.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
