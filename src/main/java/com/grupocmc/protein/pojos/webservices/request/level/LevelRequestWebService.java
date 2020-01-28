package com.grupocmc.protein.pojos.webservices.request.level;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.grupocmc.protein.dao.model.administration.Level;
import com.grupocmc.protein.pojos.webservices.request.BaseAdministrationRequestWebService;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LevelRequestWebService extends BaseAdministrationRequestWebService {

    @JsonProperty("points")
    private Integer points;

    public Level buildEntityFromRequest (Level entity){
        entity.setDescription(this.getDescription());
        entity.setName(this.getName());
        entity.setSlug(this.getCode());
        entity.setPoints(this.getPoints());
        return entity;
    }
}
