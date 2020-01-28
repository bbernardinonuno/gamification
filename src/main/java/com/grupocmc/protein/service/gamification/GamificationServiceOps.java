package com.grupocmc.protein.service.gamification;


import com.grupocmc.protein.pojos.webservices.request.BaseRequestWebService;
import com.grupocmc.protein.pojos.webservices.response.gamification.ActionsUsersResponseWebService;
import com.grupocmc.protein.pojos.webservices.response.gamification.HowToDoReponseWebService;

public interface GamificationServiceOps {

    HowToDoReponseWebService howToGamification();

    ActionsUsersResponseWebService getActions(BaseRequestWebService peticion);
}
