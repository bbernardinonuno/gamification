package com.grupocmc.protein.pojos.webservices.response.scope;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.grupocmc.protein.pojos.webservices.response.ResponseBaseWebService;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ListScopeResponseWebService extends ResponseBaseWebService {

    private static final long serialVersionUID = -3970600099062422897L;

    @JsonProperty("scopeList")
    List<ScopeResponseWebService> listScopes;
}
