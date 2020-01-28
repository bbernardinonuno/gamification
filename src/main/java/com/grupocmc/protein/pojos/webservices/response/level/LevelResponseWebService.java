package com.grupocmc.protein.pojos.webservices.response.level;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.grupocmc.protein.dao.model.administration.Level;
import com.grupocmc.protein.pojos.webservices.response.ResponseBaseAdministrationWebService;
import com.grupocmc.protein.pojos.webservices.response.ResponseBaseWebService;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@NoArgsConstructor
@JsonInclude(NON_NULL)
public class LevelResponseWebService  extends ResponseBaseAdministrationWebService {

        private static final long serialVersionUID = -3970600099062412345L;

        @JsonProperty("points")
        private Integer points;

        public LevelResponseWebService buildRespuestaFromEntity (Level entity) {
            this.setCode(entity.getSlug());
            this.setName(entity.getName());
            this.setDescription(entity.getDescription());
            this.setId(entity.getId());
            this.setPoints(entity.getPoints());
            return this;
        }
}
