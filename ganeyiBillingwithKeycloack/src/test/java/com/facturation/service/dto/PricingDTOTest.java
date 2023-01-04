package com.facturation.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.facturation.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PricingDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PricingDTO.class);
        PricingDTO pricingDTO1 = new PricingDTO();
        pricingDTO1.setId("id1");
        PricingDTO pricingDTO2 = new PricingDTO();
        assertThat(pricingDTO1).isNotEqualTo(pricingDTO2);
        pricingDTO2.setId(pricingDTO1.getId());
        assertThat(pricingDTO1).isEqualTo(pricingDTO2);
        pricingDTO2.setId("id2");
        assertThat(pricingDTO1).isNotEqualTo(pricingDTO2);
        pricingDTO1.setId(null);
        assertThat(pricingDTO1).isNotEqualTo(pricingDTO2);
    }
}
