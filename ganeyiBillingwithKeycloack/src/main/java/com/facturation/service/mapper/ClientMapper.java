package com.facturation.service.mapper;

import com.facturation.domain.Client;
import com.facturation.domain.Forfait;
import com.facturation.domain.Product;
import com.facturation.service.dto.ClientDTO;
import com.facturation.service.dto.ForfaitDTO;
import com.facturation.service.dto.ProductDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Client} and its DTO {@link ClientDTO}.
 */
@Mapper(componentModel = "spring")
public interface ClientMapper extends EntityMapper<ClientDTO, Client> {
    @Mapping(target = "products", source = "products", qualifiedByName = "productIdSet")
    @Mapping(target = "forfaits", source = "forfaits", qualifiedByName = "forfaitIdSet")
    ClientDTO toDto(Client s);

    @Mapping(target = "removeProduct", ignore = true)
    @Mapping(target = "removeForfait", ignore = true)
    Client toEntity(ClientDTO clientDTO);

    @Named("productId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ProductDTO toDtoProductId(Product product);

    @Named("productIdSet")
    default Set<ProductDTO> toDtoProductIdSet(Set<Product> product) {
        return product.stream().map(this::toDtoProductId).collect(Collectors.toSet());
    }

    @Named("forfaitId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ForfaitDTO toDtoForfaitId(Forfait forfait);

    @Named("forfaitIdSet")
    default Set<ForfaitDTO> toDtoForfaitIdSet(Set<Forfait> forfait) {
        return forfait.stream().map(this::toDtoForfaitId).collect(Collectors.toSet());
    }
}
