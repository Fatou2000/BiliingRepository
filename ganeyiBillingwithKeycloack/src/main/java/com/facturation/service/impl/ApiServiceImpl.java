package com.facturation.service.impl;

import com.facturation.domain.Api;
import com.facturation.repository.ApiRepository;
import com.facturation.service.ApiService;
import com.facturation.service.dto.ApiDTO;
import com.facturation.service.mapper.ApiMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing {@link Api}.
 */
@Service
public class ApiServiceImpl implements ApiService {

    private final Logger log = LoggerFactory.getLogger(ApiServiceImpl.class);

    private final ApiRepository apiRepository;

    private final ApiMapper apiMapper;

    public ApiServiceImpl(ApiRepository apiRepository, ApiMapper apiMapper) {
        this.apiRepository = apiRepository;
        this.apiMapper = apiMapper;
    }

    @Override
    public ApiDTO save(ApiDTO apiDTO) {
        log.debug("Request to save Api : {}", apiDTO);
        Api api = apiMapper.toEntity(apiDTO);
        api = apiRepository.save(api);
        return apiMapper.toDto(api);
    }

    @Override
    public ApiDTO update(ApiDTO apiDTO) {
        log.debug("Request to update Api : {}", apiDTO);
        Api api = apiMapper.toEntity(apiDTO);
        api = apiRepository.save(api);
        return apiMapper.toDto(api);
    }

    @Override
    public Optional<ApiDTO> partialUpdate(ApiDTO apiDTO) {
        log.debug("Request to partially update Api : {}", apiDTO);

        return apiRepository
            .findById(apiDTO.getId())
            .map(existingApi -> {
                apiMapper.partialUpdate(existingApi, apiDTO);

                return existingApi;
            })
            .map(apiRepository::save)
            .map(apiMapper::toDto);
    }

    @Override
    public Page<ApiDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Apis");
        return apiRepository.findAll(pageable).map(apiMapper::toDto);
    }

    @Override
    public Optional<ApiDTO> findOne(String id) {
        log.debug("Request to get Api : {}", id);
        return apiRepository.findById(id).map(apiMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Api : {}", id);
        apiRepository.deleteById(id);
    }
}
