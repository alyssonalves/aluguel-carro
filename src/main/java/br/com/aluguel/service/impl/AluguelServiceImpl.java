package br.com.aluguel.service.impl;

import br.com.aluguel.service.AluguelService;
import br.com.aluguel.domain.Aluguel;
import br.com.aluguel.repository.AluguelRepository;
import br.com.aluguel.service.dto.AluguelDTO;
import br.com.aluguel.service.mapper.AluguelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
/**
 * Service Implementation for managing Aluguel.
 */
@Service
@Transactional
public class AluguelServiceImpl implements AluguelService {

    private final Logger log = LoggerFactory.getLogger(AluguelServiceImpl.class);

    private final AluguelRepository aluguelRepository;

    private final AluguelMapper aluguelMapper;

    public AluguelServiceImpl(AluguelRepository aluguelRepository, AluguelMapper aluguelMapper) {
        this.aluguelRepository = aluguelRepository;
        this.aluguelMapper = aluguelMapper;
    }

    /**
     * Save a aluguel.
     *
     * @param aluguelDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public AluguelDTO save(AluguelDTO aluguelDTO) {
        log.debug("Request to save Aluguel : {}", aluguelDTO);
        Aluguel aluguel = aluguelMapper.toEntity(aluguelDTO);
        aluguel = aluguelRepository.save(aluguel);
        return aluguelMapper.toDto(aluguel);
    }

    /**
     * Get all the aluguels.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<AluguelDTO> findAll() {
        log.debug("Request to get all Aluguels");
        return aluguelRepository.findAll().stream()
            .map(aluguelMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }



    /**
     *  get all the aluguels where Reserva_id is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<AluguelDTO> findAllWhereReserva_idIsNull() {
        log.debug("Request to get all aluguels where Reserva_id is null");
        return StreamSupport
            .stream(aluguelRepository.findAll().spliterator(), false)
            .filter(aluguel -> aluguel.getReserva_id() == null)
            .map(aluguelMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one aluguel by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AluguelDTO> findOne(Long id) {
        log.debug("Request to get Aluguel : {}", id);
        return aluguelRepository.findById(id)
            .map(aluguelMapper::toDto);
    }

    /**
     * Delete the aluguel by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Aluguel : {}", id);
        aluguelRepository.deleteById(id);
    }
}
