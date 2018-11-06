package br.com.aluguel.service.impl;

import br.com.aluguel.service.ReservaService;
import br.com.aluguel.domain.Reserva;
import br.com.aluguel.repository.ReservaRepository;
import br.com.aluguel.service.dto.ReservaDTO;
import br.com.aluguel.service.mapper.ReservaMapper;
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
 * Service Implementation for managing Reserva.
 */
@Service
@Transactional
public class ReservaServiceImpl implements ReservaService {

    private final Logger log = LoggerFactory.getLogger(ReservaServiceImpl.class);

    private final ReservaRepository reservaRepository;

    private final ReservaMapper reservaMapper;

    public ReservaServiceImpl(ReservaRepository reservaRepository, ReservaMapper reservaMapper) {
        this.reservaRepository = reservaRepository;
        this.reservaMapper = reservaMapper;
    }

    /**
     * Save a reserva.
     *
     * @param reservaDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ReservaDTO save(ReservaDTO reservaDTO) {
        log.debug("Request to save Reserva : {}", reservaDTO);
        Reserva reserva = reservaMapper.toEntity(reservaDTO);
        reserva = reservaRepository.save(reserva);
        return reservaMapper.toDto(reserva);
    }

    /**
     * Get all the reservas.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ReservaDTO> findAll() {
        log.debug("Request to get all Reservas");
        return reservaRepository.findAll().stream()
            .map(reservaMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }



    /**
     *  get all the reservas where Cliente_id is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<ReservaDTO> findAllWhereCliente_idIsNull() {
        log.debug("Request to get all reservas where Cliente_id is null");
        return StreamSupport
            .stream(reservaRepository.findAll().spliterator(), false)
            .filter(reserva -> reserva.getCliente_id() == null)
            .map(reservaMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one reserva by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ReservaDTO> findOne(Long id) {
        log.debug("Request to get Reserva : {}", id);
        return reservaRepository.findById(id)
            .map(reservaMapper::toDto);
    }

    /**
     * Delete the reserva by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Reserva : {}", id);
        reservaRepository.deleteById(id);
    }
}
