package com.facturation.service.mapper;

import com.facturation.domain.ProductLicense;
import com.facturation.service.dto.ProductLicenseDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProductLicense} and its DTO {@link ProductLicenseDTO}.
 */
@Mapper(componentModel = "spring")
public interface ProductLicenseMapper extends EntityMapper<ProductLicenseDTO, ProductLicense> {}
