package br.com.aluguel.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.aluguel.service.ReservaService;
import br.com.aluguel.web.rest.errors.BadRequestAlertException;
import br.com.aluguel.web.rest.util.HeaderUtil;
import br.com.aluguel.service.dto.ReservaDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

/**
 * REST controller for managing Reserva.
 */
@RestController
@RequestMapping("/api")
public class ReservaResource {

    private final Logger log = LoggerFactory.getLogger(ReservaResource.class);

    private static final String ENTITY_NAME = "reserva";

    private final ReservaService reservaService;

    public ReservaResource(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    /**
     * POST  /reservas : Create a new reserva.
     *
     * @param reservaDTO the reservaDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new reservaDTO, or with status 400 (Bad Request) if the reserva has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/reservas")
    @Timed
    public ResponseEntity<ReservaDTO> createReserva(@Valid @RequestBody ReservaDTO reservaDTO) throws URISyntaxException {
        log.debug("REST request to save Reserva : {}", reservaDTO);
        if (reservaDTO.getId() != null) {
            throw new BadRequestAlertException("A new reserva cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ReservaDTO result = reservaService.save(reservaDTO);
        return ResponseEntity.created(new URI("/api/reservas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /reservas : Updates an existing reserva.
     *
     * @param reservaDTO the reservaDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated reservaDTO,
     * or with status 400 (Bad Request) if the reservaDTO is not valid,
     * or with status 500 (Internal Server Error) if the reservaDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/reservas")
    @Timed
    public ResponseEntity<ReservaDTO> updateReserva(@Valid @RequestBody ReservaDTO reservaDTO) throws URISyntaxException {
        log.debug("REST request to update Reserva : {}", reservaDTO);
        if (reservaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ReservaDTO result = reservaService.save(reservaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, reservaDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /reservas : get all the reservas.
     *
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of reservas in body
     */
    @GetMapping("/reservas")
    @Timed
    public List<ReservaDTO> getAllReservas(@RequestParam(required = false) String filter) {
        if ("cliente_id-is-null".equals(filter)) {
            log.debug("REST request to get all Reservas where cliente_id is null");
            return reservaService.findAllWhereCliente_idIsNull();
        }
        log.debug("REST request to get all Reservas");
        return reservaService.findAll();
    }

    /**
     * GET  /reservas/:id : get the "id" reserva.
     *
     * @param id the id of the reservaDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the reservaDTO, or with status 404 (Not Found)
     */
    @GetMapping("/reservas/{id}")
    @Timed
    public ResponseEntity<ReservaDTO> getReserva(@PathVariable Long id) {
        log.debug("REST request to get Reserva : {}", id);
        Optional<ReservaDTO> reservaDTO = reservaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(reservaDTO);
    }

    /**
     * DELETE  /reservas/:id : delete the "id" reserva.
     *
     * @param id the id of the reservaDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/reservas/{id}")
    @Timed
    public ResponseEntity<Void> deleteReserva(@PathVariable Long id) {
        log.debug("REST request to delete Reserva : {}", id);
        reservaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
