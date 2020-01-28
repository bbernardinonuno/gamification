package com.grupocmc.protein.pojos.webservices.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
@Data
public class ResponseBaseAdministrationWebService extends ResponseBaseWebService  implements Serializable {

    @JsonProperty("slug")
    private String code;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;
}
