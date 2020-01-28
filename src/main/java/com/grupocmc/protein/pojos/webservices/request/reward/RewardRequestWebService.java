package com.grupocmc.protein.pojos.webservices.request.reward;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.grupocmc.protein.dao.model.administration.*;
import com.grupocmc.protein.pojos.webservices.request.BaseAdministrationRequestWebService;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class RewardRequestWebService extends BaseAdministrationRequestWebService {

   @JsonProperty("imagen")
    private byte[] image;

    @JsonProperty("message")
    private String message;

    @JsonProperty("typeReward")
    private String codTypeReward;

    public Reward buildEntityFromRequest(Reward entity) {
        entity.setName(this.getName());
        entity.setDescription(this.getDescription());
        entity.setSlug(this.getCode());
        entity.setMessage(this.getMessage());
        entity.setImage(this.getImage());
        entity.setRewardType(new RewardType(this.getCodTypeReward()));
        return entity;
    }

}
