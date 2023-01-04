package com.facturation.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.facturation.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ProductLicenseDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProductLicenseDTO.class);
        ProductLicenseDTO productLicenseDTO1 = new ProductLicenseDTO();
        productLicenseDTO1.setId("id1");
        ProductLicenseDTO productLicenseDTO2 = new ProductLicenseDTO();
        assertThat(productLicenseDTO1).isNotEqualTo(productLicenseDTO2);
        productLicenseDTO2.setId(productLicenseDTO1.getId());
        assertThat(productLicenseDTO1).isEqualTo(productLicenseDTO2);
        productLicenseDTO2.setId("id2");
        assertThat(productLicenseDTO1).isNotEqualTo(productLicenseDTO2);
        productLicenseDTO1.setId(null);
        assertThat(productLicenseDTO1).isNotEqualTo(productLicenseDTO2);
    }
}
