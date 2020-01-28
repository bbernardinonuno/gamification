package com.grupocmc.protein.pojos.webservices.response.action;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.grupocmc.protein.dao.model.administration.Action;
import com.grupocmc.protein.pojos.webservices.response.ResponseBaseAdministrationWebService;
import com.grupocmc.protein.pojos.webservices.response.ResponseBaseWebService;
import com.grupocmc.protein.pojos.webservices.response.typeaction.TypeActionResponseWebService;
import com.grupocmc.protein.pojos.webservices.response.typereward.TypeRewardResponseWebService;
import lombok.Data;
import lombok.NoArgsConstructor;



import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@NoArgsConstructor
@JsonInclude(NON_NULL)
public class ActionResponseWebService extends ResponseBaseAdministrationWebService {
    private static final long serialVersionUID = -3970600099062490865L;

    @JsonProperty("points")
    private Integer points;

    @JsonProperty("imagen")
    private byte[] image;

    @JsonProperty("message")
    private String message;

    @JsonProperty("scope")
    private ResponseBaseWebService scope;

    @JsonProperty("actionType")
    private TypeActionResponseWebService actionType;

    @JsonProperty("slugActionParent")
    private String codActionParent;


    @JsonProperty("isParent")
    private Boolean isParent;


    public ActionResponseWebService buildRespuestaFromEntity(Action entidad) {

        this.setActionType(new TypeActionResponseWebService().buildRespuestaFromEntity(entidad.getActionType()));
        this.setCode(entidad.getSlug());
        this.setDescription(entidad.getDescription());
        this.setImage(entidad.getImage());
        this.setMessage(entidad.getMessage());
        this.setName(entidad.getName());
        this.setPoints(entidad.getPoints());
        ResponseBaseAdministrationWebService scope = new ResponseBaseAdministrationWebService();
        scope.setId(entidad.getScope().getId());
        scope.setName(entidad.getScope().getName());
        scope.setCode(entidad.getScope().getSlug());
        scope.setDescription(entidad.getScope().getDescription());
        this.setScope(scope);
        this.setId(entidad.getId());
        this.setCodActionParent(entidad.getActionParent() == null ? null : entidad.getActionParent().getSlug());
        this.setIsParent(entidad.getIsParent());
        return this;
    }


}
