package com.facturation.service;

import com.facturation.service.dto.ApiDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.facturation.domain.Api}.
 */
public interface ApiService {
    /**
     * Save a api.
     *
     * @param apiDTO the entity to save.
     * @return the persisted entity.
     */
    ApiDTO save(ApiDTO apiDTO);

    /**
     * Updates a api.
     *
     * @param apiDTO the entity to update.
     * @return the persisted entity.
     */
    ApiDTO update(ApiDTO apiDTO);

    /**
     * Partially updates a api.
     *
     * @param apiDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ApiDTO> partialUpdate(ApiDTO apiDTO);

    /**
     * Get all the apis.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ApiDTO> findAll(Pageable pageable);

    /**
     * Get the "id" api.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ApiDTO> findOne(String id);

    /**
     * Delete the "id" api.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
