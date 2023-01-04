package com.facturation.service;

import com.facturation.service.dto.RequestDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.time.LocalDate;
import com.facturation.domain.Request;
import com.facturation.domain.Client;

/**
 * Service Interface for managing {@link com.facturation.domain.Request}.
 */
public interface RequestService {
    /**
     * Save a request.
     *
     * @param requestDTO the entity to save.
     * @return the persisted entity.
     */
    RequestDTO save(RequestDTO requestDTO);

    /**
     * Updates a request.
     *
     * @param requestDTO the entity to update.
     * @return the persisted entity.
     */
    RequestDTO update(RequestDTO requestDTO);

    /**
     * Partially updates a request.
     *
     * @param requestDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<RequestDTO> partialUpdate(RequestDTO requestDTO);

    /**
     * Get all the requests.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RequestDTO> findAll(Pageable pageable);

    /**
     * Get the "id" request.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RequestDTO> findOne(String id);

    /**
     * Delete the "id" request.
     *
     * @param id the id of the entity.
     */
    void delete(String id);

    /**
     * Get the requests made by the client between the startDate and the endDate .
     *
     * @param startDate the start date.
     * @param endDate the end date.
     * @param client the client.
     */
    List<Request> filterRequestsByDate(LocalDate startDate , LocalDate endDate, Client client);

    

   
}
