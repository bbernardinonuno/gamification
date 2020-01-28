package com.grupocmc.protein.pojos.webservices.request.typereward;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.grupocmc.protein.dao.model.administration.RewardType;
import com.grupocmc.protein.pojos.webservices.request.BaseAdministrationRequestWebService;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class TypeRewardRequestWebService extends BaseAdministrationRequestWebService {

    @JsonProperty("value")
    private Integer value;

    @JsonProperty("symbol")
    private String symbol;

    @JsonProperty("percentage")
    private Double percentage;

    public RewardType buildEntityFromRequest (RewardType entity){

        entity.setDescription(this.getDescription());
        entity.setName(this.getName());
        entity.setPercentage(this.getPercentage());
        entity.setSlug(this.getCode());
        entity.setSymbol(this.getSymbol());
        entity.setValue(this.getValue());
        return entity;
    }
}
