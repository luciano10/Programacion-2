package cine.web.rest;

import com.codahale.metrics.annotation.Timed;
import cine.domain.Butaca;
import cine.repository.ButacaRepository;
import cine.web.rest.errors.BadRequestAlertException;
import cine.web.rest.util.HeaderUtil;
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

/**
 * REST controller for managing Butaca.
 */
@RestController
@RequestMapping("/api")
public class ButacaResource {

    private final Logger log = LoggerFactory.getLogger(ButacaResource.class);

    private static final String ENTITY_NAME = "butaca";

    private final ButacaRepository butacaRepository;

    public ButacaResource(ButacaRepository butacaRepository) {
        this.butacaRepository = butacaRepository;
    }

    /**
     * POST  /butacas : Create a new butaca.
     *
     * @param butaca the butaca to create
     * @return the ResponseEntity with status 201 (Created) and with body the new butaca, or with status 400 (Bad Request) if the butaca has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/butacas")
    @Timed
    public ResponseEntity<Butaca> createButaca(@Valid @RequestBody Butaca butaca) throws URISyntaxException {
        log.debug("REST request to save Butaca : {}", butaca);
        if (butaca.getId() != null) {
            throw new BadRequestAlertException("A new butaca cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Butaca result = butacaRepository.save(butaca);
        return ResponseEntity.created(new URI("/api/butacas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /butacas : Updates an existing butaca.
     *
     * @param butaca the butaca to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated butaca,
     * or with status 400 (Bad Request) if the butaca is not valid,
     * or with status 500 (Internal Server Error) if the butaca couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/butacas")
    @Timed
    public ResponseEntity<Butaca> updateButaca(@Valid @RequestBody Butaca butaca) throws URISyntaxException {
        log.debug("REST request to update Butaca : {}", butaca);
        if (butaca.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Butaca result = butacaRepository.save(butaca);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, butaca.getId().toString()))
            .body(result);
    }

    /**
     * GET  /butacas : get all the butacas.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of butacas in body
     */
    @GetMapping("/butacas")
    @Timed
    public List<Butaca> getAllButacas() {
        log.debug("REST request to get all Butacas");
        return butacaRepository.findAll();
    }

    /**
     * GET  /butacas/:id : get the "id" butaca.
     *
     * @param id the id of the butaca to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the butaca, or with status 404 (Not Found)
     */
    @GetMapping("/butacas/{id}")
    @Timed
    public ResponseEntity<Butaca> getButaca(@PathVariable Long id) {
        log.debug("REST request to get Butaca : {}", id);
        Optional<Butaca> butaca = butacaRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(butaca);
    }

    /**
     * DELETE  /butacas/:id : delete the "id" butaca.
     *
     * @param id the id of the butaca to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/butacas/{id}")
    @Timed
    public ResponseEntity<Void> deleteButaca(@PathVariable Long id) {
        log.debug("REST request to delete Butaca : {}", id);

        butacaRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
