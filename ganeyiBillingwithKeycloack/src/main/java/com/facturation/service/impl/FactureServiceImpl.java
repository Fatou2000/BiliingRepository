package com.facturation.service.impl;

import com.facturation.domain.Facture;
import com.facturation.repository.FactureRepository;
import com.facturation.service.FactureService;
import com.facturation.service.dto.FactureDTO;
import com.facturation.service.mapper.FactureMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing {@link Facture}.
 */
@Service
public class FactureServiceImpl implements FactureService {

    private final Logger log = LoggerFactory.getLogger(FactureServiceImpl.class);

    private final FactureRepository factureRepository;

    private final FactureMapper factureMapper;

    public FactureServiceImpl(FactureRepository factureRepository, FactureMapper factureMapper) {
        this.factureRepository = factureRepository;
        this.factureMapper = factureMapper;
    }

    @Override
    public FactureDTO save(FactureDTO factureDTO) {
        log.debug("Request to save Facture : {}", factureDTO);
        Facture facture = factureMapper.toEntity(factureDTO);
        facture = factureRepository.save(facture);
        return factureMapper.toDto(facture);
    }

    @Override
    public FactureDTO update(FactureDTO factureDTO) {
        log.debug("Request to update Facture : {}", factureDTO);
        Facture facture = factureMapper.toEntity(factureDTO);
        facture = factureRepository.save(facture);
        return factureMapper.toDto(facture);
    }

    @Override
    public Optional<FactureDTO> partialUpdate(FactureDTO factureDTO) {
        log.debug("Request to partially update Facture : {}", factureDTO);

        return factureRepository
            .findById(factureDTO.getId())
            .map(existingFacture -> {
                factureMapper.partialUpdate(existingFacture, factureDTO);

                return existingFacture;
            })
            .map(factureRepository::save)
            .map(factureMapper::toDto);
    }

    @Override
    public Page<FactureDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Factures");
        return factureRepository.findAll(pageable).map(factureMapper::toDto);
    }

    public Page<FactureDTO> findAllWithEagerRelationships(Pageable pageable) {
        return factureRepository.findAllWithEagerRelationships(pageable).map(factureMapper::toDto);
    }

    /**
     *  Get all the factures where Payment is {@code null}.
     *  @return the list of entities.
     */

    public List<FactureDTO> findAllWherePaymentIsNull() {
        log.debug("Request to get all factures where Payment is null");
        return StreamSupport
            .stream(factureRepository.findAll().spliterator(), false)
            .filter(facture -> facture.getPayment() == null)
            .map(factureMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public Optional<FactureDTO> findOne(String id) {
        log.debug("Request to get Facture : {}", id);
        return factureRepository.findOneWithEagerRelationships(id).map(factureMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Facture : {}", id);
        factureRepository.deleteById(id);
    }
}
