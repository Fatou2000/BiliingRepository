package com.facturation.service.mapper;

import com.facturation.domain.Client;
import com.facturation.domain.Facture;
import com.facturation.domain.Forfait;
import com.facturation.domain.Product;
import com.facturation.service.dto.ClientDTO;
import com.facturation.service.dto.FactureDTO;
import com.facturation.service.dto.ForfaitDTO;
import com.facturation.service.dto.ProductDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Facture} and its DTO {@link FactureDTO}.
 */
@Mapper(componentModel = "spring")
public interface FactureMapper extends EntityMapper<FactureDTO, Facture> {
    @Mapping(target = "forfait", source = "forfait", qualifiedByName = "forfaitId")
    @Mapping(target = "client", source = "client", qualifiedByName = "clientId")
    @Mapping(target = "products", source = "products", qualifiedByName = "productNameSet")
    FactureDTO toDto(Facture s);

    @Mapping(target = "removeProduct", ignore = true)
    Facture toEntity(FactureDTO factureDTO);

    @Named("forfaitId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ForfaitDTO toDtoForfaitId(Forfait forfait);

    @Named("clientId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ClientDTO toDtoClientId(Client client);

    @Named("productName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    ProductDTO toDtoProductName(Product product);

    @Named("productNameSet")
    default Set<ProductDTO> toDtoProductNameSet(Set<Product> product) {
        return product.stream().map(this::toDtoProductName).collect(Collectors.toSet());
    }
}
