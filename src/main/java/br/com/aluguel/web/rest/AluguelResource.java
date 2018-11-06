package br.com.aluguel.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.aluguel.service.AluguelService;
import br.com.aluguel.web.rest.errors.BadRequestAlertException;
import br.com.aluguel.web.rest.util.HeaderUtil;
import br.com.aluguel.service.dto.AluguelDTO;
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
 * REST controller for managing Aluguel.
 */
@RestController
@RequestMapping("/api")
public class AluguelResource {

    private final Logger log = LoggerFactory.getLogger(AluguelResource.class);

    private static final String ENTITY_NAME = "aluguel";

    private final AluguelService aluguelService;

    public AluguelResource(AluguelService aluguelService) {
        this.aluguelService = aluguelService;
    }

    /**
     * POST  /aluguels : Create a new aluguel.
     *
     * @param aluguelDTO the aluguelDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new aluguelDTO, or with status 400 (Bad Request) if the aluguel has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/aluguels")
    @Timed
    public ResponseEntity<AluguelDTO> createAluguel(@Valid @RequestBody AluguelDTO aluguelDTO) throws URISyntaxException {
        log.debug("REST request to save Aluguel : {}", aluguelDTO);
        if (aluguelDTO.getId() != null) {
            throw new BadRequestAlertException("A new aluguel cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AluguelDTO result = aluguelService.save(aluguelDTO);
        return ResponseEntity.created(new URI("/api/aluguels/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /aluguels : Updates an existing aluguel.
     *
     * @param aluguelDTO the aluguelDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated aluguelDTO,
     * or with status 400 (Bad Request) if the aluguelDTO is not valid,
     * or with status 500 (Internal Server Error) if the aluguelDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/aluguels")
    @Timed
    public ResponseEntity<AluguelDTO> updateAluguel(@Valid @RequestBody AluguelDTO aluguelDTO) throws URISyntaxException {
        log.debug("REST request to update Aluguel : {}", aluguelDTO);
        if (aluguelDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AluguelDTO result = aluguelService.save(aluguelDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, aluguelDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /aluguels : get all the aluguels.
     *
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of aluguels in body
     */
    @GetMapping("/aluguels")
    @Timed
    public List<AluguelDTO> getAllAluguels(@RequestParam(required = false) String filter) {
        if ("reserva_id-is-null".equals(filter)) {
            log.debug("REST request to get all Aluguels where reserva_id is null");
            return aluguelService.findAllWhereReserva_idIsNull();
        }
        log.debug("REST request to get all Aluguels");
        return aluguelService.findAll();
    }

    /**
     * GET  /aluguels/:id : get the "id" aluguel.
     *
     * @param id the id of the aluguelDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the aluguelDTO, or with status 404 (Not Found)
     */
    @GetMapping("/aluguels/{id}")
    @Timed
    public ResponseEntity<AluguelDTO> getAluguel(@PathVariable Long id) {
        log.debug("REST request to get Aluguel : {}", id);
        Optional<AluguelDTO> aluguelDTO = aluguelService.findOne(id);
        return ResponseUtil.wrapOrNotFound(aluguelDTO);
    }

    /**
     * DELETE  /aluguels/:id : delete the "id" aluguel.
     *
     * @param id the id of the aluguelDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/aluguels/{id}")
    @Timed
    public ResponseEntity<Void> deleteAluguel(@PathVariable Long id) {
        log.debug("REST request to delete Aluguel : {}", id);
        aluguelService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
