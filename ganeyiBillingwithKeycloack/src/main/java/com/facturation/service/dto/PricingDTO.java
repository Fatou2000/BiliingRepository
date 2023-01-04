package com.facturation.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.facturation.domain.Pricing} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PricingDTO implements Serializable {

    private String id;

    private Double value;

    private ProductLicenseDTO productLicense;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public ProductLicenseDTO getProductLicense() {
        return productLicense;
    }

    public void setProductLicense(ProductLicenseDTO productLicense) {
        this.productLicense = productLicense;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PricingDTO)) {
            return false;
        }

        PricingDTO pricingDTO = (PricingDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, pricingDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PricingDTO{" +
            "id='" + getId() + "'" +
            ", value=" + getValue() +
            ", productLicense=" + getProductLicense() +
            "}";
    }
}
