package com.facturation.service.impl;

import com.facturation.domain.Resultat;
import com.facturation.repository.ResultatRepository;
import com.facturation.service.ResultatService;
import com.facturation.service.dto.ResultatDTO;
import com.facturation.service.mapper.ResultatMapper;
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
 * Service Implementation for managing {@link Resultat}.
 */
@Service
public class ResultatServiceImpl implements ResultatService {

    private final Logger log = LoggerFactory.getLogger(ResultatServiceImpl.class);

    private final ResultatRepository resultatRepository;

    private final ResultatMapper resultatMapper;

    public ResultatServiceImpl(ResultatRepository resultatRepository, ResultatMapper resultatMapper) {
        this.resultatRepository = resultatRepository;
        this.resultatMapper = resultatMapper;
    }

    @Override
    public ResultatDTO save(ResultatDTO resultatDTO) {
        log.debug("Request to save Resultat : {}", resultatDTO);
        Resultat resultat = resultatMapper.toEntity(resultatDTO);
        resultat = resultatRepository.save(resultat);
        return resultatMapper.toDto(resultat);
    }

    @Override
    public ResultatDTO update(ResultatDTO resultatDTO) {
        log.debug("Request to update Resultat : {}", resultatDTO);
        Resultat resultat = resultatMapper.toEntity(resultatDTO);
        resultat = resultatRepository.save(resultat);
        return resultatMapper.toDto(resultat);
    }

    @Override
    public Optional<ResultatDTO> partialUpdate(ResultatDTO resultatDTO) {
        log.debug("Request to partially update Resultat : {}", resultatDTO);

        return resultatRepository
            .findById(resultatDTO.getId())
            .map(existingResultat -> {
                resultatMapper.partialUpdate(existingResultat, resultatDTO);

                return existingResultat;
            })
            .map(resultatRepository::save)
            .map(resultatMapper::toDto);
    }

    @Override
    public Page<ResultatDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Resultats");
        return resultatRepository.findAll(pageable).map(resultatMapper::toDto);
    }

    /**
     *  Get all the resultats where Request is {@code null}.
     *  @return the list of entities.
     */

    public List<ResultatDTO> findAllWhereRequestIsNull() {
        log.debug("Request to get all resultats where Request is null");
        return StreamSupport
            .stream(resultatRepository.findAll().spliterator(), false)
            .filter(resultat -> resultat.getRequest() == null)
            .map(resultatMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public Optional<ResultatDTO> findOne(String id) {
        log.debug("Request to get Resultat : {}", id);
        return resultatRepository.findById(id).map(resultatMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Resultat : {}", id);
        resultatRepository.deleteById(id);
    }
}
