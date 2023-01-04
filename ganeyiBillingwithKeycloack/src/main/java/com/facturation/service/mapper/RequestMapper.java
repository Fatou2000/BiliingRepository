package com.facturation.service.mapper;

import com.facturation.domain.Api;
import com.facturation.domain.Client;
import com.facturation.domain.Forfait;
import com.facturation.domain.Request;
import com.facturation.domain.Resultat;
import com.facturation.service.dto.ApiDTO;
import com.facturation.service.dto.ClientDTO;
import com.facturation.service.dto.ForfaitDTO;
import com.facturation.service.dto.RequestDTO;
import com.facturation.service.dto.ResultatDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Request} and its DTO {@link RequestDTO}.
 */
@Mapper(componentModel = "spring")
public interface RequestMapper extends EntityMapper<RequestDTO, Request> {
    @Mapping(target = "resultat", source = "resultat", qualifiedByName = "resultatId")
    @Mapping(target = "forfait", source = "forfait", qualifiedByName = "forfaitId")
    @Mapping(target = "client", source = "client", qualifiedByName = "clientId")
    @Mapping(target = "api", source = "api", qualifiedByName = "apiId")
    RequestDTO toDto(Request s);

    @Named("resultatId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ResultatDTO toDtoResultatId(Resultat resultat);

    @Named("forfaitId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ForfaitDTO toDtoForfaitId(Forfait forfait);

    @Named("clientId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ClientDTO toDtoClientId(Client client);

    @Named("apiId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ApiDTO toDtoApiId(Api api);
}
