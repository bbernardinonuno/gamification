package com.grupocmc.protein.pojos.webservices.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
@Data
public class ResponseBaseWebService implements Serializable {

    private static final long serialVersionUID = 1L;
    @JsonProperty(value = "responseCode")
    private String codigoRespuesta;

    private Boolean error;
    @JsonProperty(value = "responseMessage")
    private String mensajeRespuesta;

    @JsonProperty(value = "id")
    private Long id;


    public ResponseBaseWebService() {

    }

    public ResponseBaseWebService(String codigoRespuesta, Boolean error) {
        super();
        this.codigoRespuesta = codigoRespuesta;
        this.error = error;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ResponseBaseWebService that = (ResponseBaseWebService) o;

        return new EqualsBuilder()
                .append(codigoRespuesta, that.codigoRespuesta)
                .append(error, that.error)
                .append(mensajeRespuesta, that.mensajeRespuesta)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(codigoRespuesta)
                .append(error)
                .append(mensajeRespuesta)
                .toHashCode();
    }
}