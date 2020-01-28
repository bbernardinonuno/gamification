package com.grupocmc.protein.service.administration.reward;

import com.grupocmc.protein.pojos.webservices.response.ResponseBaseWebService;
import com.grupocmc.protein.pojos.webservices.request.reward.RewardRequestWebService;
import com.grupocmc.protein.pojos.webservices.response.reward.ListRewardsResponseWebService;
import com.grupocmc.protein.pojos.webservices.response.reward.RewardResponseWebService;

public interface RewardServiceOps {

    ResponseBaseWebService create (RewardRequestWebService rewardPeticionBean);

    RewardResponseWebService update (RewardRequestWebService peticion);

    RewardResponseWebService read (String slug);

    ResponseBaseWebService delete (String slug);

    ListRewardsResponseWebService readAll (Long[] ids);
}
