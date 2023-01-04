package com.facturation.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.facturation.domain.Forfait} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ForfaitDTO implements Serializable {

    private String id;

    private String nom;

    private String description;

    private Double numberOfQueries;

    private Double price;

    private String periode;

    private Boolean actif;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getNumberOfQueries() {
        return numberOfQueries;
    }

    public void setNumberOfQueries(Double numberOfQueries) {
        this.numberOfQueries = numberOfQueries;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }

    public Boolean getActif() {
        return actif;
    }

    public void setActif(Boolean actif) {
        this.actif = actif;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ForfaitDTO)) {
            return false;
        }

        ForfaitDTO forfaitDTO = (ForfaitDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, forfaitDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ForfaitDTO{" +
            "id='" + getId() + "'" +
            ", nom='" + getNom() + "'" +
            ", description='" + getDescription() + "'" +
            ", numberOfQueries=" + getNumberOfQueries() +
            ", price=" + getPrice() +
            ", periode='" + getPeriode() + "'" +
            ", actif='" + getActif() + "'" +
            "}";
    }
}
