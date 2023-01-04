package com.facturation.web.rest;

import com.facturation.repository.ForfaitRepository;
import com.facturation.service.ForfaitService;
import com.facturation.service.dto.ForfaitDTO;
import com.facturation.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.facturation.domain.Forfait}.
 */
@RestController
@RequestMapping("/api")
public class ForfaitResource {

    private final Logger log = LoggerFactory.getLogger(ForfaitResource.class);

    private static final String ENTITY_NAME = "forfait";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ForfaitService forfaitService;

    private final ForfaitRepository forfaitRepository;

    public ForfaitResource(ForfaitService forfaitService, ForfaitRepository forfaitRepository) {
        this.forfaitService = forfaitService;
        this.forfaitRepository = forfaitRepository;
    }

    /**
     * {@code POST  /forfaits} : Create a new forfait.
     *
     * @param forfaitDTO the forfaitDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new forfaitDTO, or with status {@code 400 (Bad Request)} if the forfait has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/forfaits")
    public ResponseEntity<ForfaitDTO> createForfait(@RequestBody ForfaitDTO forfaitDTO) throws URISyntaxException {
        log.debug("REST request to save Forfait : {}", forfaitDTO);
        if (forfaitDTO.getId() != null) {
            throw new BadRequestAlertException("A new forfait cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ForfaitDTO result = forfaitService.save(forfaitDTO);
        return ResponseEntity
            .created(new URI("/api/forfaits/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /forfaits/:id} : Updates an existing forfait.
     *
     * @param id the id of the forfaitDTO to save.
     * @param forfaitDTO the forfaitDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated forfaitDTO,
     * or with status {@code 400 (Bad Request)} if the forfaitDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the forfaitDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/forfaits/{id}")
    public ResponseEntity<ForfaitDTO> updateForfait(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody ForfaitDTO forfaitDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Forfait : {}, {}", id, forfaitDTO);
        if (forfaitDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, forfaitDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!forfaitRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ForfaitDTO result = forfaitService.update(forfaitDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, forfaitDTO.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /forfaits/:id} : Partial updates given fields of an existing forfait, field will ignore if it is null
     *
     * @param id the id of the forfaitDTO to save.
     * @param forfaitDTO the forfaitDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated forfaitDTO,
     * or with status {@code 400 (Bad Request)} if the forfaitDTO is not valid,
     * or with status {@code 404 (Not Found)} if the forfaitDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the forfaitDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/forfaits/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ForfaitDTO> partialUpdateForfait(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody ForfaitDTO forfaitDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Forfait partially : {}, {}", id, forfaitDTO);
        if (forfaitDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, forfaitDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!forfaitRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ForfaitDTO> result = forfaitService.partialUpdate(forfaitDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, forfaitDTO.getId())
        );
    }

    /**
     * {@code GET  /forfaits} : get all the forfaits.
     *
     * @param pageable the pagination information.
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of forfaits in body.
     */
    @GetMapping("/forfaits")
    public ResponseEntity<List<ForfaitDTO>> getAllForfaits(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        @RequestParam(required = false) String filter
    ) {
        if ("facture-is-null".equals(filter)) {
            log.debug("REST request to get all Forfaits where facture is null");
            return new ResponseEntity<>(forfaitService.findAllWhereFactureIsNull(), HttpStatus.OK);
        }
        log.debug("REST request to get a page of Forfaits");
        Page<ForfaitDTO> page = forfaitService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /forfaits/:id} : get the "id" forfait.
     *
     * @param id the id of the forfaitDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the forfaitDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/forfaits/{id}")
    public ResponseEntity<ForfaitDTO> getForfait(@PathVariable String id) {
        log.debug("REST request to get Forfait : {}", id);
        Optional<ForfaitDTO> forfaitDTO = forfaitService.findOne(id);
        return ResponseUtil.wrapOrNotFound(forfaitDTO);
    }

    /**
     * {@code DELETE  /forfaits/:id} : delete the "id" forfait.
     *
     * @param id the id of the forfaitDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/forfaits/{id}")
    public ResponseEntity<Void> deleteForfait(@PathVariable String id) {
        log.debug("REST request to delete Forfait : {}", id);
        forfaitService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
