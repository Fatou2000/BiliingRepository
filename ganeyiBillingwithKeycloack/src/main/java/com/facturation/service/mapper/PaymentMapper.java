package com.facturation.service.mapper;

import com.facturation.domain.Facture;
import com.facturation.domain.Payment;
import com.facturation.service.dto.FactureDTO;
import com.facturation.service.dto.PaymentDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Payment} and its DTO {@link PaymentDTO}.
 */
@Mapper(componentModel = "spring")
public interface PaymentMapper extends EntityMapper<PaymentDTO, Payment> {
    @Mapping(target = "facture", source = "facture", qualifiedByName = "factureId")
    PaymentDTO toDto(Payment s);

    @Named("factureId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    FactureDTO toDtoFactureId(Facture facture);
}
