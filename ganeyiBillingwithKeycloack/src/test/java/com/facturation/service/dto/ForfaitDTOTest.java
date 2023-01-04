package com.facturation.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.facturation.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ForfaitDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ForfaitDTO.class);
        ForfaitDTO forfaitDTO1 = new ForfaitDTO();
        forfaitDTO1.setId("id1");
        ForfaitDTO forfaitDTO2 = new ForfaitDTO();
        assertThat(forfaitDTO1).isNotEqualTo(forfaitDTO2);
        forfaitDTO2.setId(forfaitDTO1.getId());
        assertThat(forfaitDTO1).isEqualTo(forfaitDTO2);
        forfaitDTO2.setId("id2");
        assertThat(forfaitDTO1).isNotEqualTo(forfaitDTO2);
        forfaitDTO1.setId(null);
        assertThat(forfaitDTO1).isNotEqualTo(forfaitDTO2);
    }
}
