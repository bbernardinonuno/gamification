package com.grupocmc.protein.mvc.controller.administration;


import com.grupocmc.protein.pojos.webservices.response.ResponseBaseWebService;
import com.grupocmc.protein.pojos.webservices.request.scope.ScopeRequestWebService;
import com.grupocmc.protein.pojos.webservices.response.scope.ListScopeResponseWebService;
import com.grupocmc.protein.pojos.webservices.response.scope.ScopeResponseWebService;
import com.grupocmc.protein.service.administration.scope.ScopeServiceOps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping({ "/administration/scope" })
public class ScopeController {

    private ScopeServiceOps scopeService;

    @Autowired
    public ScopeController(ScopeServiceOps scopeServiceOps){
        this.scopeService = scopeServiceOps;
    }

    @RequestMapping(value = "/", method = {RequestMethod.POST}, headers = "Accept=application/xml, application/json")
    public @ResponseBody
    ResponseBaseWebService createScope(@RequestBody @Valid ScopeRequestWebService catalogRequest,
                                       @RequestHeader HttpHeaders headers) {

        ResponseBaseWebService respuesta = scopeService.create(catalogRequest);
        return respuesta;
    }


    @RequestMapping(value = "/{slug}/", method = {RequestMethod.PUT}, headers = "Accept=application/xml, application/json")
    public @ResponseBody
    ScopeResponseWebService updateScope (@PathVariable("slug") String code,
                                         @RequestBody @Valid ScopeRequestWebService catalogRequest,
                                         @RequestHeader HttpHeaders headers) {
        catalogRequest.setCode(code);
        ScopeResponseWebService respuesta = scopeService.update(catalogRequest);
        return respuesta;
    }

    @RequestMapping(value = "/{idCatalog}/", method = {RequestMethod.DELETE}, headers = "Accept=application/xml, application/json")
    public ResponseBaseWebService deleteScope (@PathVariable("idCatalog") Long idCatalog,
                                               @RequestHeader HttpHeaders headers) {

        ResponseBaseWebService respuesta = scopeService.delete(idCatalog);
        return respuesta;
    }

    @RequestMapping(value = "/{idCatalog}/", method = {RequestMethod.GET}, headers = "Accept=application/xml, application/json")
    public @ResponseBody
    ScopeResponseWebService getScope (@PathVariable("idCatalog") Long idCatalog,
                                      @RequestHeader HttpHeaders headers) {
        ScopeResponseWebService respuesta = scopeService.read(idCatalog);
        return respuesta;
    }


    @RequestMapping(value = "/", method = {RequestMethod.GET}, headers = "Accept=application/xml, application/json")
    public @ResponseBody
    ListScopeResponseWebService readAllScopes (Long[] uuids, @RequestHeader HttpHeaders headers){
        ListScopeResponseWebService respuesta = scopeService.readAll( uuids);
        return respuesta;
    }
}
