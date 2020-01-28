package com.grupocmc.protein.pojos.webservices.response.level;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.grupocmc.protein.pojos.webservices.response.ResponseBaseWebService;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ListLevelResponseWebService extends ResponseBaseWebService {

    private static final long serialVersionUID = -3970600099062422855L;

    @JsonProperty("levelList")
    List<LevelResponseWebService> listLevels;
}
