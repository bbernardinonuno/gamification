package com.grupocmc.protein.pojos.webservices.request.typeaction;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.grupocmc.protein.config.Constantes;
import com.grupocmc.protein.dao.model.administration.ActionType;
import com.grupocmc.protein.dao.model.administration.ActionTypeMin;
import com.grupocmc.protein.dao.model.administration.ActionTypeTemp;
import com.grupocmc.protein.utils.FormatDate;
import com.grupocmc.protein.validators.ValidUtc;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static org.joda.time.format.DateTimeFormat.forPattern;
import static org.springframework.util.StringUtils.hasText;

@Data
@NoArgsConstructor
public class TypeActionRequestWebService   {

    @Size(max = 255, message = "error.validation.size")
    @NotNull(message = "error.validation.required")
    @JsonProperty("name")
    private String name;

    @Size(max = 255, message = "error.validation.size")
    @JsonProperty("desc")
    private String description;

    @JsonProperty("rule")
    private String rule;

    @JsonProperty("expresionRule")
    private String expresionRule;

    @JsonProperty("numIterations")
    private Integer numIterations;

    @JsonProperty("value")
    private Integer value;

    @JsonProperty("initDate")
    @ValidUtc
    private String startDate;

    @JsonProperty("endDate")
    @ValidUtc
    private String endDate;

    @JsonProperty("numDays")
    private Integer numDays;

    private Long idTypeAction;

    public ActionType buildEntityFromRequest(ActionType actionTypeEntity) {

        if (hasText(this.getStartDate())  && hasText(this.getEndDate()) && this.getValue() != null)
           actionTypeEntity = new ActionType();

        else  if (hasText(this.getStartDate())  && hasText(this.getEndDate())  ){
            actionTypeEntity = new ActionTypeTemp();
            ((ActionTypeTemp) actionTypeEntity).setStartDate(FormatDate.formatUTC(this.getStartDate()));
            ((ActionTypeTemp) actionTypeEntity).setEndDate(FormatDate.formatUTC(this.getEndDate()));
            ((ActionTypeTemp) actionTypeEntity).setNumDays(this.getNumDays());
        }else if (this.getValue() != null ) {
            actionTypeEntity = new ActionTypeMin();
            ((ActionTypeMin) actionTypeEntity).setValue(this.getValue());
        }

        actionTypeEntity.setName(this.getName());
        actionTypeEntity.setDescription(this.getDescription());
        actionTypeEntity.setRule(this.getRule());
        actionTypeEntity.setExpresionRule(this.getExpresionRule());
        actionTypeEntity.setNumIterations(this.getNumIterations());

        return actionTypeEntity;
    }


}
