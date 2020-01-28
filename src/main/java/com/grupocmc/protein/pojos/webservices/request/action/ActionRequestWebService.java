package com.grupocmc.protein.pojos.webservices.request.action;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.grupocmc.protein.dao.model.administration.Action;
import com.grupocmc.protein.dao.model.administration.ActionType;
import com.grupocmc.protein.dao.model.administration.Scope;
import com.grupocmc.protein.pojos.webservices.request.BaseAdministrationRequestWebService;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class ActionRequestWebService extends BaseAdministrationRequestWebService {

    @NotNull(message = "error.validation.required")
    @JsonProperty("points")
    private Integer points;

    @JsonProperty("imagen")
    private byte[] image;

    @JsonProperty("message")
    private String message;

    @NotNull(message = "error.validation.required")
    @JsonProperty("actionType")
    private Long actionType;

    @NotNull(message = "error.validation.required")
    @JsonProperty("scope")
    private String scope;

    @JsonProperty("isParent")
    private boolean isFather;

    @JsonProperty("codeParent")
    private String codeParent;

    @JsonProperty("slugReward")
    private String slugReward;

    public Action buildEntityFromRequest() {
        Action actionEntity = new Action();
        actionEntity.setName(this.getName());
        actionEntity.setDescription(this.getDescription());
        actionEntity.setSlug(this.getCode());
        actionEntity.setPoints(this.getPoints());
        actionEntity.setMessage(this.getMessage());
        actionEntity.setImage(this.getImage());
        actionEntity.setActionType(new ActionType(this.actionType));
        actionEntity.setScope(new Scope(this.getScope()));
        actionEntity.setIsParent(this.isFather());
        actionEntity.setActionParent(new Action(this.getCodeParent()));

        return actionEntity;
    }
}
