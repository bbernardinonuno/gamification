package com.grupocmc.protein.pojos.webservices.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Splitter;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

import static org.springframework.http.HttpHeaders.ACCEPT_LANGUAGE;


@Data
@NoArgsConstructor
public class BaseAdministrationRequestWebService extends  BaseRequestWebService implements Serializable {
    private static final long serialVersionUID = 1L;

    @Size(max = 255, message = "error.validation.size")
    @NotNull(message = "error.validation.required")
    @JsonProperty("slug")
    private String code;

    @Size(max = 255, message = "error.validation.size")
    @NotNull(message = "error.validation.required")
    @JsonProperty("name")
    private String name;

    @Size(max = 255, message = "error.validation.size")
    @JsonProperty("desc")
    private String description;

}
