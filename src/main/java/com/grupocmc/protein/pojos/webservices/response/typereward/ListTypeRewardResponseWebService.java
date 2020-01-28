package com.grupocmc.protein.pojos.webservices.response.typereward;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.grupocmc.protein.pojos.webservices.response.ResponseBaseAdministrationWebService;
import com.grupocmc.protein.pojos.webservices.response.ResponseBaseWebService;
import lombok.Data;

import java.util.List;

@Data
public class ListTypeRewardResponseWebService extends ResponseBaseWebService {

    private static final long serialVersionUID = 1L;

    @JsonProperty("typesRewardsList")
    List<TypeRewardResponseWebService> listTypesRewards;


}
