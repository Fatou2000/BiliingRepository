package com.facturation.service.dto;

import com.facturation.domain.enumeration.FactureStatus;
import com.facturation.domain.enumeration.TypeFacturation;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link com.facturation.domain.Facture} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class FactureDTO implements Serializable {

    private String id;

    private Double rabais;

    private Double tva;

    private Double sousTotal;

    private Double total;

    private TypeFacturation typeFacturation;

    private FactureStatus status;

    private String reference;

    private LocalDate date;

    private String numero;

    private ForfaitDTO forfait;

    private ClientDTO client;

    private Set<ProductDTO> products = new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getRabais() {
        return rabais;
    }

    public void setRabais(Double rabais) {
        this.rabais = rabais;
    }

    public Double getTva() {
        return tva;
    }

    public void setTva(Double tva) {
        this.tva = tva;
    }

    public Double getSousTotal() {
        return sousTotal;
    }

    public void setSousTotal(Double sousTotal) {
        this.sousTotal = sousTotal;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public TypeFacturation getTypeFacturation() {
        return typeFacturation;
    }

    public void setTypeFacturation(TypeFacturation typeFacturation) {
        this.typeFacturation = typeFacturation;
    }

    public FactureStatus getStatus() {
        return status;
    }

    public void setStatus(FactureStatus status) {
        this.status = status;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public ForfaitDTO getForfait() {
        return forfait;
    }

    public void setForfait(ForfaitDTO forfait) {
        this.forfait = forfait;
    }

    public ClientDTO getClient() {
        return client;
    }

    public void setClient(ClientDTO client) {
        this.client = client;
    }

    public Set<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductDTO> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FactureDTO)) {
            return false;
        }

        FactureDTO factureDTO = (FactureDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, factureDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FactureDTO{" +
            "id='" + getId() + "'" +
            ", rabais=" + getRabais() +
            ", tva=" + getTva() +
            ", sousTotal=" + getSousTotal() +
            ", total=" + getTotal() +
            ", typeFacturation='" + getTypeFacturation() + "'" +
            ", status='" + getStatus() + "'" +
            ", reference='" + getReference() + "'" +
            ", date='" + getDate() + "'" +
            ", numero='" + getNumero() + "'" +
            ", forfait=" + getForfait() +
            ", client=" + getClient() +
            ", products=" + getProducts() +
            "}";
    }
}
