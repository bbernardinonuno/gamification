package com.grupocmc.protein.pojos.webservices.response.typeaction;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.grupocmc.protein.dao.model.administration.ActionType;
import com.grupocmc.protein.dao.model.administration.ActionTypeMin;
import com.grupocmc.protein.dao.model.administration.ActionTypeTemp;
import com.grupocmc.protein.mvc.deserializers.JodaTimeDeserializer;
import com.grupocmc.protein.pojos.webservices.response.ResponseBaseAdministrationWebService;
import com.grupocmc.protein.pojos.webservices.response.ResponseBaseWebService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@NoArgsConstructor
@JsonInclude(NON_NULL)
public class TypeActionResponseWebService  extends ResponseBaseAdministrationWebService {

    private static final long serialVersionUID = -6520600099062422465L;

    @JsonProperty("rule")
    private String rule;

    @JsonProperty("expresionRule")
    private String expresionRule;

    @JsonProperty("numIterations")
    private Integer numIterations;

    @JsonProperty("value")
    private Integer value;

    @JsonProperty("initDate")
    @JsonSerialize(using = JodaTimeDeserializer.class)
    private DateTime startDate;

    @JsonProperty("endDate")
    @JsonSerialize(using = JodaTimeDeserializer.class)
    private DateTime endDate;

    @JsonProperty("numDays")
    private Integer numDays;


    public TypeActionResponseWebService buildRespuestaFromEntity (ActionType entity) {

        this.setRule(entity.getRule());
        this.setExpresionRule(entity.getExpresionRule());
        this.setNumIterations(entity.getNumIterations());
        this.setId(entity.getId());

        if (entity instanceof ActionTypeTemp){
            this.setNumDays(((ActionTypeTemp) entity).getNumDays());
            this.setEndDate(((ActionTypeTemp) entity).getEndDate());
            this.setStartDate(((ActionTypeTemp) entity).getStartDate());
        }

        if (entity instanceof ActionTypeMin){
            this.setValue(((ActionTypeMin) entity).getValue());
        }

        return this;
    }
}
