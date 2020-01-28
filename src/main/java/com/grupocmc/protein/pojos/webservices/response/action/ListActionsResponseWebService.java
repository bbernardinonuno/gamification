package com.grupocmc.protein.pojos.webservices.response.action;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.grupocmc.protein.pojos.webservices.response.ResponseBaseWebService;
import lombok.Data;

import java.util.List;

@Data
public class ListActionsResponseWebService extends ResponseBaseWebService {

    private static final long serialVersionUID = 1L;

    @JsonProperty("actions")
    List<ActionResponseWebService> listActions;
}
