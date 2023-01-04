package com.facturation.service.mapper;

import com.facturation.domain.Pricing;
import com.facturation.domain.ProductLicense;
import com.facturation.service.dto.PricingDTO;
import com.facturation.service.dto.ProductLicenseDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Pricing} and its DTO {@link PricingDTO}.
 */
@Mapper(componentModel = "spring")
public interface PricingMapper extends EntityMapper<PricingDTO, Pricing> {
    @Mapping(target = "productLicense", source = "productLicense", qualifiedByName = "productLicenseId")
    PricingDTO toDto(Pricing s);

    @Named("productLicenseId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ProductLicenseDTO toDtoProductLicenseId(ProductLicense productLicense);
}
