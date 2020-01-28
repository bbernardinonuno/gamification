package com.grupocmc.protein.pojos.webservices.response.reward;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.grupocmc.protein.dao.model.administration.Reward;
import com.grupocmc.protein.pojos.webservices.response.ResponseBaseAdministrationWebService;
import com.grupocmc.protein.pojos.webservices.response.ResponseBaseWebService;
import com.grupocmc.protein.pojos.webservices.response.typereward.TypeRewardResponseWebService;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@NoArgsConstructor
@JsonInclude(NON_NULL)
public class RewardResponseWebService extends ResponseBaseAdministrationWebService {
    private static final long serialVersionUID = -3970600099062490865L;

    @JsonProperty("message")
    private String message;

    @JsonProperty("imagen")
    private byte[] image;

    @JsonProperty("typeReward")
    private TypeRewardResponseWebService typeReward;

    public RewardResponseWebService buildRespuestaFromEntity(Reward entity) {
        this.setName(entity.getName());
        this.setDescription(entity.getDescription());
        this.setCode(entity.getSlug());
        this.setMessage(entity.getMessage());
        this.setImage(entity.getImage());
        this.setTypeReward(new TypeRewardResponseWebService().buildRespuestaFromEntity(entity.getRewardType()));
        this.setId(entity.getId());
        return this;
    }

}
