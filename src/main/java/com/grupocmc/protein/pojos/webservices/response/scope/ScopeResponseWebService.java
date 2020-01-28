package com.grupocmc.protein.pojos.webservices.response.scope;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.grupocmc.protein.dao.model.administration.Scope;
import com.grupocmc.protein.pojos.webservices.response.ResponseBaseAdministrationWebService;
import com.grupocmc.protein.pojos.webservices.response.ResponseBaseWebService;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@NoArgsConstructor
@JsonInclude(NON_NULL)
public class ScopeResponseWebService extends ResponseBaseAdministrationWebService {

        private static final long serialVersionUID = -3970600099062490865L;

        public ScopeResponseWebService buildRespuestaFromEntity (Scope entity) {
            this.setCode(entity.getSlug());
            this.setName(entity.getName());
            this.setDescription(entity.getDescription());
            this.setId(entity.getId());
            return this;
        }
}
