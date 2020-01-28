package com.grupocmc.protein.mvc.controller.administration;

import com.grupocmc.protein.pojos.webservices.response.ResponseBaseWebService;
import com.grupocmc.protein.pojos.webservices.request.action.ActionRequestWebService;
import com.grupocmc.protein.pojos.webservices.response.action.ActionResponseWebService;
import com.grupocmc.protein.pojos.webservices.response.action.ListActionsResponseWebService;
import com.grupocmc.protein.service.administration.action.ActionServiceOps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping({ "/administration/actions" })
public class ActionController {

    ActionServiceOps actionService;
    @Autowired
    public ActionController (ActionServiceOps actionService){
        this.actionService = actionService;
    }

    @RequestMapping(value = "/", method = {RequestMethod.POST}, headers = "Accept=application/xml, application/json")
    public @ResponseBody ResponseBaseWebService createAction (@RequestBody @Valid ActionRequestWebService actionPeticionBean,
                                                   @RequestHeader HttpHeaders headers) {

        ResponseBaseWebService respuesta =actionService.create(actionPeticionBean);
        return respuesta;
    }

    @RequestMapping(value = "/{codeAction}/", method = {RequestMethod.PUT}, headers = "Accept=application/xml, application/json")
    public @ResponseBody
    ActionResponseWebService updateAction (@PathVariable("codeAction") String code,
                                           @RequestBody @Valid ActionRequestWebService actionPeticionBean,
                                           @RequestHeader HttpHeaders headers) {

        actionPeticionBean.setCode(code);
        ActionResponseWebService respuesta =actionService.update(actionPeticionBean);
        return respuesta;
    }
    @RequestMapping(value = "/{slugAction}/", method = {RequestMethod.DELETE}, headers = "Accept=application/xml, application/json")
    public ResponseBaseWebService deleteAction (@PathVariable("slugAction") String slugAction,
                                                @RequestHeader HttpHeaders headers) {

        ResponseBaseWebService respuesta =actionService.delete(slugAction);
        return respuesta;
    }

    @RequestMapping(value = "/{slugAction}/", method = {RequestMethod.GET}, headers = "Accept=application/xml, application/json")
    public @ResponseBody
    ActionResponseWebService getAction (@PathVariable("slugAction") String slugAction,
                                        @RequestHeader HttpHeaders headers) {

        ActionResponseWebService respuesta =actionService.read(slugAction);
        return respuesta;
    }


    @RequestMapping(value = "/", method = {RequestMethod.GET}, headers = "Accept=application/xml, application/json")
    public @ResponseBody
    ListActionsResponseWebService readAllActions (Long[] uuids,
                                                  @RequestHeader HttpHeaders headers){

        ListActionsResponseWebService respuesta = actionService.readAll(uuids);
        return respuesta;
    }
}
