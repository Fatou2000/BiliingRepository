package com.facturation.service.mapper;

import com.facturation.domain.Api;
import com.facturation.domain.Product;
import com.facturation.service.dto.ApiDTO;
import com.facturation.service.dto.ProductDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Api} and its DTO {@link ApiDTO}.
 */
@Mapper(componentModel = "spring")
public interface ApiMapper extends EntityMapper<ApiDTO, Api> {
    @Mapping(target = "product", source = "product", qualifiedByName = "productId")
    ApiDTO toDto(Api s);

    @Named("productId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ProductDTO toDtoProductId(Product product);
}
