package com.facturation.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.facturation.domain.Api} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ApiDTO implements Serializable {

    private String id;

    private String version;

    private String serviceURL;

    private String docURL;

    private Boolean isActice;

    private ProductDTO product;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getServiceURL() {
        return serviceURL;
    }

    public void setServiceURL(String serviceURL) {
        this.serviceURL = serviceURL;
    }

    public String getDocURL() {
        return docURL;
    }

    public void setDocURL(String docURL) {
        this.docURL = docURL;
    }

    public Boolean getIsActice() {
        return isActice;
    }

    public void setIsActice(Boolean isActice) {
        this.isActice = isActice;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ApiDTO)) {
            return false;
        }

        ApiDTO apiDTO = (ApiDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, apiDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ApiDTO{" +
            "id='" + getId() + "'" +
            ", version='" + getVersion() + "'" +
            ", serviceURL='" + getServiceURL() + "'" +
            ", docURL='" + getDocURL() + "'" +
            ", isActice='" + getIsActice() + "'" +
            ", product=" + getProduct() +
            "}";
    }
}
