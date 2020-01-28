package com.grupocmc.protein.pojos.webservices.response.typereward;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.grupocmc.protein.dao.model.administration.RewardType;
import com.grupocmc.protein.pojos.webservices.response.ResponseBaseAdministrationWebService;
import com.grupocmc.protein.pojos.webservices.response.ResponseBaseWebService;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@NoArgsConstructor
@JsonInclude(NON_NULL)
public class TypeRewardResponseWebService extends ResponseBaseAdministrationWebService {

    private static final long serialVersionUID = -3970600099062422465L;

    @JsonProperty("value")
    private Integer value;

    @JsonProperty("percentage")
    private Double percentage;

    @JsonProperty("symbol")
    private String symbol;


    public TypeRewardResponseWebService buildRespuestaFromEntity (RewardType entity) {
        this.setCode(entity.getSlug());
        this.setDescription(entity.getDescription());
        this.setName(entity.getName());
        this.setPercentage(entity.getPercentage());
        this.setSymbol(entity.getSymbol());
        this.setValue(entity.getValue());
        this.setId(entity.getId());
        return this;
    }


}
