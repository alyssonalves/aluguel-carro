package br.com.aluguel.service.impl;

import br.com.aluguel.service.VeiculoService;
import br.com.aluguel.domain.Veiculo;
import br.com.aluguel.repository.VeiculoRepository;
import br.com.aluguel.service.dto.VeiculoDTO;
import br.com.aluguel.service.mapper.VeiculoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing Veiculo.
 */
@Service
@Transactional
public class VeiculoServiceImpl implements VeiculoService {

    private final Logger log = LoggerFactory.getLogger(VeiculoServiceImpl.class);

    private final VeiculoRepository veiculoRepository;

    private final VeiculoMapper veiculoMapper;

    public VeiculoServiceImpl(VeiculoRepository veiculoRepository, VeiculoMapper veiculoMapper) {
        this.veiculoRepository = veiculoRepository;
        this.veiculoMapper = veiculoMapper;
    }

    /**
     * Save a veiculo.
     *
     * @param veiculoDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public VeiculoDTO save(VeiculoDTO veiculoDTO) {
        log.debug("Request to save Veiculo : {}", veiculoDTO);
        Veiculo veiculo = veiculoMapper.toEntity(veiculoDTO);
        veiculo = veiculoRepository.save(veiculo);
        return veiculoMapper.toDto(veiculo);
    }

    /**
     * Get all the veiculos.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<VeiculoDTO> findAll() {
        log.debug("Request to get all Veiculos");
        return veiculoRepository.findAll().stream()
            .map(veiculoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one veiculo by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<VeiculoDTO> findOne(Long id) {
        log.debug("Request to get Veiculo : {}", id);
        return veiculoRepository.findById(id)
            .map(veiculoMapper::toDto);
    }

    /**
     * Delete the veiculo by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Veiculo : {}", id);
        veiculoRepository.deleteById(id);
    }
}
