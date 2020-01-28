package com.grupocmc.protein.service.administration.typeaction;

import com.grupocmc.protein.pojos.webservices.request.typeaction.TypeActionRequestWebService;
import com.grupocmc.protein.pojos.webservices.response.ResponseBaseWebService;
import com.grupocmc.protein.pojos.webservices.response.typeaction.ListTypeActionResponseWebService;
import com.grupocmc.protein.pojos.webservices.response.typeaction.TypeActionResponseWebService;

public interface TypeActionServiceOps {

    ResponseBaseWebService create (TypeActionRequestWebService peticion);

    TypeActionResponseWebService update (TypeActionRequestWebService peticion);

    TypeActionResponseWebService read (Long idAction);

    ResponseBaseWebService delete (Long idAction);

    ListTypeActionResponseWebService readAll (Long[] ids);
}
