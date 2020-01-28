package com.grupocmc.protein.service.administration.level;

import com.grupocmc.protein.pojos.webservices.request.level.LevelRequestWebService;
import com.grupocmc.protein.pojos.webservices.response.ResponseBaseWebService;
import com.grupocmc.protein.pojos.webservices.response.level.LevelResponseWebService;
import com.grupocmc.protein.pojos.webservices.response.level.ListLevelResponseWebService;

public interface LevelServiceOps {

    ResponseBaseWebService create (LevelRequestWebService peticionBean);

    LevelResponseWebService update (LevelRequestWebService peticionBean);

    LevelResponseWebService read (Long idCatalog);

    ResponseBaseWebService delete (Long idCatalog);

    ListLevelResponseWebService readAll (Long[] ids);
}
