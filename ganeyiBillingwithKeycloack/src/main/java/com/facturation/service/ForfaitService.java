package com.facturation.service;

import com.facturation.service.dto.ForfaitDTO;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.facturation.domain.Forfait}.
 */
public interface ForfaitService {
    /**
     * Save a forfait.
     *
     * @param forfaitDTO the entity to save.
     * @return the persisted entity.
     */
    ForfaitDTO save(ForfaitDTO forfaitDTO);

    /**
     * Updates a forfait.
     *
     * @param forfaitDTO the entity to update.
     * @return the persisted entity.
     */
    ForfaitDTO update(ForfaitDTO forfaitDTO);

    /**
     * Partially updates a forfait.
     *
     * @param forfaitDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ForfaitDTO> partialUpdate(ForfaitDTO forfaitDTO);

    /**
     * Get all the forfaits.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ForfaitDTO> findAll(Pageable pageable);
    /**
     * Get all the ForfaitDTO where Facture is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<ForfaitDTO> findAllWhereFactureIsNull();

    /**
     * Get the "id" forfait.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ForfaitDTO> findOne(String id);

    /**
     * Delete the "id" forfait.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
