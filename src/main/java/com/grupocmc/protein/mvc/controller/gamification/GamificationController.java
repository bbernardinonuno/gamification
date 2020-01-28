package com.grupocmc.protein.mvc.controller.gamification;

import com.grupocmc.protein.dao.model.administration.User;
import com.grupocmc.protein.pojos.webservices.request.BaseRequestWebService;
import com.grupocmc.protein.pojos.webservices.response.gamification.ActionsUsersResponseWebService;
import com.grupocmc.protein.pojos.webservices.response.gamification.HowToDoReponseWebService;
import com.grupocmc.protein.service.gamification.GamificationServiceOps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({ "/gamify" })
public class GamificationController {

    private GamificationServiceOps gamificationService;

    @Autowired
    public GamificationController (GamificationServiceOps gamificationService){
        this.gamificationService = gamificationService;
    }

    @RequestMapping(value = "/howtodo/", method = {RequestMethod.GET}, headers = "Accept=application/xml, application/json")
    public @ResponseBody
    HowToDoReponseWebService howToGamification(@RequestHeader HttpHeaders headers) {
        return gamificationService.howToGamification();
    }

    @RequestMapping(value = "/actions/", method = {RequestMethod.GET}, headers = "Accept=application/xml, application/json")
    public @ResponseBody
    ActionsUsersResponseWebService getActions(@RequestHeader HttpHeaders headers) {
        return gamificationService.getActions(new BaseRequestWebService().withHeader(headers));
    }
}
