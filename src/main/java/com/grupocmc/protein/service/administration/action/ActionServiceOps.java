package com.grupocmc.protein.service.administration.action;

import com.grupocmc.protein.pojos.webservices.response.ResponseBaseWebService;
import com.grupocmc.protein.pojos.webservices.request.action.ActionRequestWebService;
import com.grupocmc.protein.pojos.webservices.response.action.ActionResponseWebService;
import com.grupocmc.protein.pojos.webservices.response.action.ListActionsResponseWebService;


public interface ActionServiceOps {

    ResponseBaseWebService create (ActionRequestWebService peticion);

    ActionResponseWebService update (ActionRequestWebService peticion);

    ActionResponseWebService read (String slugAction );

    ResponseBaseWebService delete (String slugAction );

    ListActionsResponseWebService readAll (Long[] ids);
}
