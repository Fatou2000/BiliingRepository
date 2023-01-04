package com.facturation.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ForfaitMapperTest {

    private ForfaitMapper forfaitMapper;

    @BeforeEach
    public void setUp() {
        forfaitMapper = new ForfaitMapperImpl();
    }
}
