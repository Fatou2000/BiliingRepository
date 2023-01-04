package com.facturation.service.impl;

import com.facturation.domain.Forfait;
import com.facturation.repository.ForfaitRepository;
import com.facturation.service.ForfaitService;
import com.facturation.service.dto.ForfaitDTO;
import com.facturation.service.mapper.ForfaitMapper;
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
 * Service Implementation for managing {@link Forfait}.
 */
@Service
public class ForfaitServiceImpl implements ForfaitService {

    private final Logger log = LoggerFactory.getLogger(ForfaitServiceImpl.class);

    private final ForfaitRepository forfaitRepository;

    private final ForfaitMapper forfaitMapper;

    public ForfaitServiceImpl(ForfaitRepository forfaitRepository, ForfaitMapper forfaitMapper) {
        this.forfaitRepository = forfaitRepository;
        this.forfaitMapper = forfaitMapper;
    }

    @Override
    public ForfaitDTO save(ForfaitDTO forfaitDTO) {
        log.debug("Request to save Forfait : {}", forfaitDTO);
        Forfait forfait = forfaitMapper.toEntity(forfaitDTO);
        forfait = forfaitRepository.save(forfait);
        return forfaitMapper.toDto(forfait);
    }

    @Override
    public ForfaitDTO update(ForfaitDTO forfaitDTO) {
        log.debug("Request to update Forfait : {}", forfaitDTO);
        Forfait forfait = forfaitMapper.toEntity(forfaitDTO);
        forfait = forfaitRepository.save(forfait);
        return forfaitMapper.toDto(forfait);
    }

    @Override
    public Optional<ForfaitDTO> partialUpdate(ForfaitDTO forfaitDTO) {
        log.debug("Request to partially update Forfait : {}", forfaitDTO);

        return forfaitRepository
            .findById(forfaitDTO.getId())
            .map(existingForfait -> {
                forfaitMapper.partialUpdate(existingForfait, forfaitDTO);

                return existingForfait;
            })
            .map(forfaitRepository::save)
            .map(forfaitMapper::toDto);
    }

    @Override
    public Page<ForfaitDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Forfaits");
        return forfaitRepository.findAll(pageable).map(forfaitMapper::toDto);
    }

    /**
     *  Get all the forfaits where Facture is {@code null}.
     *  @return the list of entities.
     */

    public List<ForfaitDTO> findAllWhereFactureIsNull() {
        log.debug("Request to get all forfaits where Facture is null");
        return StreamSupport
            .stream(forfaitRepository.findAll().spliterator(), false)
            .filter(forfait -> forfait.getFacture() == null)
            .map(forfaitMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public Optional<ForfaitDTO> findOne(String id) {
        log.debug("Request to get Forfait : {}", id);
        return forfaitRepository.findById(id).map(forfaitMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Forfait : {}", id);
        forfaitRepository.deleteById(id);
    }
}
