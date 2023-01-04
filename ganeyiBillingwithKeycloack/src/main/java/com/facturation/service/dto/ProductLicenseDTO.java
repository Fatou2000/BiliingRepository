package com.facturation.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link com.facturation.domain.ProductLicense} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ProductLicenseDTO implements Serializable {

    private String id;

    private String accessKey;

    private LocalDate startDate;

    private LocalDate endDate;

    private Boolean isActive;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductLicenseDTO)) {
            return false;
        }

        ProductLicenseDTO productLicenseDTO = (ProductLicenseDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, productLicenseDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProductLicenseDTO{" +
            "id='" + getId() + "'" +
            ", accessKey='" + getAccessKey() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", isActive='" + getIsActive() + "'" +
            "}";
    }
}
