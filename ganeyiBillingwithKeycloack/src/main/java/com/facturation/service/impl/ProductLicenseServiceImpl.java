package com.facturation.service.impl;

import com.facturation.domain.ProductLicense;
import com.facturation.repository.ProductLicenseRepository;
import com.facturation.service.ProductLicenseService;
import com.facturation.service.dto.ProductLicenseDTO;
import com.facturation.service.mapper.ProductLicenseMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing {@link ProductLicense}.
 */
@Service
public class ProductLicenseServiceImpl implements ProductLicenseService {

    private final Logger log = LoggerFactory.getLogger(ProductLicenseServiceImpl.class);

    private final ProductLicenseRepository productLicenseRepository;

    private final ProductLicenseMapper productLicenseMapper;

    public ProductLicenseServiceImpl(ProductLicenseRepository productLicenseRepository, ProductLicenseMapper productLicenseMapper) {
        this.productLicenseRepository = productLicenseRepository;
        this.productLicenseMapper = productLicenseMapper;
    }

    @Override
    public ProductLicenseDTO save(ProductLicenseDTO productLicenseDTO) {
        log.debug("Request to save ProductLicense : {}", productLicenseDTO);
        ProductLicense productLicense = productLicenseMapper.toEntity(productLicenseDTO);
        productLicense = productLicenseRepository.save(productLicense);
        return productLicenseMapper.toDto(productLicense);
    }

    @Override
    public ProductLicenseDTO update(ProductLicenseDTO productLicenseDTO) {
        log.debug("Request to update ProductLicense : {}", productLicenseDTO);
        ProductLicense productLicense = productLicenseMapper.toEntity(productLicenseDTO);
        productLicense = productLicenseRepository.save(productLicense);
        return productLicenseMapper.toDto(productLicense);
    }

    @Override
    public Optional<ProductLicenseDTO> partialUpdate(ProductLicenseDTO productLicenseDTO) {
        log.debug("Request to partially update ProductLicense : {}", productLicenseDTO);

        return productLicenseRepository
            .findById(productLicenseDTO.getId())
            .map(existingProductLicense -> {
                productLicenseMapper.partialUpdate(existingProductLicense, productLicenseDTO);

                return existingProductLicense;
            })
            .map(productLicenseRepository::save)
            .map(productLicenseMapper::toDto);
    }

    @Override
    public Page<ProductLicenseDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProductLicenses");
        return productLicenseRepository.findAll(pageable).map(productLicenseMapper::toDto);
    }

    @Override
    public Optional<ProductLicenseDTO> findOne(String id) {
        log.debug("Request to get ProductLicense : {}", id);
        return productLicenseRepository.findById(id).map(productLicenseMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete ProductLicense : {}", id);
        productLicenseRepository.deleteById(id);
    }
}
