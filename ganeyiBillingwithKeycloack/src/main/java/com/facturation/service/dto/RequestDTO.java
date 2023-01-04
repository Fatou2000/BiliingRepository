package com.facturation.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link com.facturation.domain.Request} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RequestDTO implements Serializable {

    private String id;

    private Integer duration;

    private String status;

    private LocalDate requestDate;

    private ResultatDTO resultat;

    private ForfaitDTO forfait;

    private ClientDTO client;

    private ApiDTO api;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public ResultatDTO getResultat() {
        return resultat;
    }

    public void setResultat(ResultatDTO resultat) {
        this.resultat = resultat;
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

    public ApiDTO getApi() {
        return api;
    }

    public void setApi(ApiDTO api) {
        this.api = api;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RequestDTO)) {
            return false;
        }

        RequestDTO requestDTO = (RequestDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, requestDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RequestDTO{" +
            "id='" + getId() + "'" +
            ", duration=" + getDuration() +
            ", status='" + getStatus() + "'" +
            ", requestDate='" + getRequestDate() + "'" +
            ", resultat=" + getResultat() +
            ", forfait=" + getForfait() +
            ", client=" + getClient() +
            ", api=" + getApi() +
            "}";
    }
}
