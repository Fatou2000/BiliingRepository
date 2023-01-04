package com.facturation.service;

import com.facturation.service.dto.ProductLicenseDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.facturation.domain.ProductLicense}.
 */
public interface ProductLicenseService {
    /**
     * Save a productLicense.
     *
     * @param productLicenseDTO the entity to save.
     * @return the persisted entity.
     */
    ProductLicenseDTO save(ProductLicenseDTO productLicenseDTO);

    /**
     * Updates a productLicense.
     *
     * @param productLicenseDTO the entity to update.
     * @return the persisted entity.
     */
    ProductLicenseDTO update(ProductLicenseDTO productLicenseDTO);

    /**
     * Partially updates a productLicense.
     *
     * @param productLicenseDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ProductLicenseDTO> partialUpdate(ProductLicenseDTO productLicenseDTO);

    /**
     * Get all the productLicenses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ProductLicenseDTO> findAll(Pageable pageable);

    /**
     * Get the "id" productLicense.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProductLicenseDTO> findOne(String id);

    /**
     * Delete the "id" productLicense.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
