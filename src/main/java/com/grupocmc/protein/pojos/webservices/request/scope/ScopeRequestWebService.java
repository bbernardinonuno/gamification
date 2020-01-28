package com.grupocmc.protein.pojos.webservices.request.scope;

import com.grupocmc.protein.dao.model.administration.Scope;
import com.grupocmc.protein.pojos.webservices.request.BaseAdministrationRequestWebService;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ScopeRequestWebService extends BaseAdministrationRequestWebService {

    public Scope buildEntityFromRequest (Scope entity) {
        entity.setDescription(this.getDescription());
        entity.setName(this.getName());
        entity.setSlug(this.getCode());
        return entity;
    }
}


