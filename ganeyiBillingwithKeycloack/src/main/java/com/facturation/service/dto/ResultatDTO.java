package com.facturation.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.facturation.domain.Resultat} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ResultatDTO implements Serializable {

    private String id;

    private byte[] value;

    private String valueContentType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public byte[] getValue() {
        return value;
    }

    public void setValue(byte[] value) {
        this.value = value;
    }

    public String getValueContentType() {
        return valueContentType;
    }

    public void setValueContentType(String valueContentType) {
        this.valueContentType = valueContentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ResultatDTO)) {
            return false;
        }

        ResultatDTO resultatDTO = (ResultatDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, resultatDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ResultatDTO{" +
            "id='" + getId() + "'" +
            ", value='" + getValue() + "'" +
            "}";
    }
}
