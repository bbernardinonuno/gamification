package com.grupocmc.protein.pojos.webservices.response.reward;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.grupocmc.protein.pojos.webservices.response.ResponseBaseAdministrationWebService;
import com.grupocmc.protein.pojos.webservices.response.ResponseBaseWebService;
import lombok.Data;

import java.util.List;

@Data
public class ListRewardsResponseWebService extends ResponseBaseAdministrationWebService {

    private static final long serialVersionUID = 1L;

    @JsonProperty("rewards")
    List<RewardResponseWebService> listRewards;

}
