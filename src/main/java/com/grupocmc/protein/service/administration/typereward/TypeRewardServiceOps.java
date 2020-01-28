package com.grupocmc.protein.service.administration.typereward;

import com.grupocmc.protein.pojos.webservices.response.ResponseBaseWebService;
import com.grupocmc.protein.pojos.webservices.request.typereward.TypeRewardRequestWebService;
import com.grupocmc.protein.pojos.webservices.response.typereward.ListTypeRewardResponseWebService;
import com.grupocmc.protein.pojos.webservices.response.typereward.TypeRewardResponseWebService;

public interface TypeRewardServiceOps {

    ResponseBaseWebService create (TypeRewardRequestWebService rewardPeticionBean);

    TypeRewardResponseWebService update (TypeRewardRequestWebService peticion);

    TypeRewardResponseWebService read (Long idAction);

    ResponseBaseWebService delete (Long idAction);

    ListTypeRewardResponseWebService readAll (Long[] ids);
}
