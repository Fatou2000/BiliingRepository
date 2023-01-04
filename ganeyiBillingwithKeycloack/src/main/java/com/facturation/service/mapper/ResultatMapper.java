package com.facturation.service.mapper;

import com.facturation.domain.Resultat;
import com.facturation.service.dto.ResultatDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Resultat} and its DTO {@link ResultatDTO}.
 */
@Mapper(componentModel = "spring")
public interface ResultatMapper extends EntityMapper<ResultatDTO, Resultat> {}
