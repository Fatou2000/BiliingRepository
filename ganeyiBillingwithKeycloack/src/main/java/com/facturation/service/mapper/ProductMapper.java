package com.facturation.service.mapper;

import com.facturation.domain.Product;
import com.facturation.domain.ProductLicense;
import com.facturation.domain.Request;
import com.facturation.service.dto.ProductDTO;
import com.facturation.service.dto.ProductLicenseDTO;
import com.facturation.service.dto.RequestDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Product} and its DTO {@link ProductDTO}.
 */
@Mapper(componentModel = "spring")
public interface ProductMapper extends EntityMapper<ProductDTO, Product> {
    @Mapping(target = "request", source = "request", qualifiedByName = "requestId")
    @Mapping(target = "productLicense", source = "productLicense", qualifiedByName = "productLicenseId")
    ProductDTO toDto(Product s);

    @Named("requestId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    RequestDTO toDtoRequestId(Request request);

    @Named("productLicenseId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ProductLicenseDTO toDtoProductLicenseId(ProductLicense productLicense);
}
