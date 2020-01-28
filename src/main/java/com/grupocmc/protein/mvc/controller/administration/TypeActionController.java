package com.grupocmc.protein.mvc.controller.administration;

import com.grupocmc.protein.pojos.webservices.request.typeaction.TypeActionRequestWebService;
import com.grupocmc.protein.pojos.webservices.response.ResponseBaseWebService;
import com.grupocmc.protein.pojos.webservices.response.typeaction.ListTypeActionResponseWebService;
import com.grupocmc.protein.pojos.webservices.response.typeaction.TypeActionResponseWebService;
import com.grupocmc.protein.service.administration.typeaction.TypeActionServiceOps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping({ "/administration/typeAction" })
public class TypeActionController {

    private TypeActionServiceOps typeActionService;

    @Autowired
    public TypeActionController(TypeActionServiceOps typeActionService) {
        this.typeActionService = typeActionService;
    }


    @RequestMapping(value = "/", method = {RequestMethod.POST}, headers = "Accept=application/xml, application/json")
    public @ResponseBody
    ResponseBaseWebService createTypeAction (@RequestBody @Valid TypeActionRequestWebService typeActionPeticion,
                                             @RequestHeader HttpHeaders headers) {
        ResponseBaseWebService respuesta =typeActionService.create(typeActionPeticion);
        return respuesta;
    }


    @RequestMapping(value = "/{idTypeAction}/", method = {RequestMethod.PUT}, headers = "Accept=application/xml, application/json")
    public @ResponseBody
    TypeActionResponseWebService updateTypeAction (@PathVariable("idTypeAction") Long idTypeAction,
                                                   @RequestBody @Valid TypeActionRequestWebService typeActionPeticion,
                                                   @RequestHeader HttpHeaders headers) {

        typeActionPeticion.setIdTypeAction(idTypeAction);
        TypeActionResponseWebService respuesta =typeActionService.update(typeActionPeticion);
        return respuesta;
    }

    @RequestMapping(value = "/{idTypeAction}/", method = {RequestMethod.DELETE}, headers = "Accept=application/xml, application/json")
    public ResponseBaseWebService deleteTypeAction (@PathVariable("idTypeAction") Long idTypeAction,
                                                    @RequestHeader HttpHeaders headers) {
        ResponseBaseWebService respuesta =typeActionService.delete(idTypeAction);
        return respuesta;
    }

    @RequestMapping(value = "/{idTypeAction}/", method = {RequestMethod.GET}, headers = "Accept=application/xml, application/json")
    public @ResponseBody
    TypeActionResponseWebService getTypeAction (@PathVariable("idTypeAction") Long idTypeAction,
                                                @RequestHeader HttpHeaders headers) {
        TypeActionResponseWebService respuesta =typeActionService.read(idTypeAction);
        return respuesta;
    }


    @RequestMapping(value = "/", method = {RequestMethod.GET}, headers = "Accept=application/xml, application/json")
    public @ResponseBody
    ListTypeActionResponseWebService readAllTypeActions (Long[] uuids, @RequestHeader HttpHeaders headers){
        ListTypeActionResponseWebService respuesta = typeActionService.readAll(uuids);
        return respuesta;
    }
}



