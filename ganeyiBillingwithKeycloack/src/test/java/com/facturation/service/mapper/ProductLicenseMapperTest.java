package com.facturation.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProductLicenseMapperTest {

    private ProductLicenseMapper productLicenseMapper;

    @BeforeEach
    public void setUp() {
        productLicenseMapper = new ProductLicenseMapperImpl();
    }
}
