package com.facturation.service.mapper;

import com.facturation.domain.Forfait;
import com.facturation.service.dto.ForfaitDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Forfait} and its DTO {@link ForfaitDTO}.
 */
@Mapper(componentModel = "spring")
public interface ForfaitMapper extends EntityMapper<ForfaitDTO, Forfait> {}
