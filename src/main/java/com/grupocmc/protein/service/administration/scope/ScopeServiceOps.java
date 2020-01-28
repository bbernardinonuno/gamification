package com.grupocmc.protein.service.administration.scope;


import com.grupocmc.protein.pojos.webservices.response.ResponseBaseWebService;
import com.grupocmc.protein.pojos.webservices.request.scope.ScopeRequestWebService;
import com.grupocmc.protein.pojos.webservices.response.scope.ListScopeResponseWebService;
import com.grupocmc.protein.pojos.webservices.response.scope.ScopeResponseWebService;

public interface ScopeServiceOps {

    ResponseBaseWebService create (ScopeRequestWebService rewardPeticionBean);

    ScopeResponseWebService update (ScopeRequestWebService peticion);

    ScopeResponseWebService read (Long idCatalog);

    ResponseBaseWebService delete (Long idCatalog);

    ListScopeResponseWebService readAll (Long[] ids);
}
