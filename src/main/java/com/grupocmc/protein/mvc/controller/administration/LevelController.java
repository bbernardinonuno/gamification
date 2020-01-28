package com.grupocmc.protein.mvc.controller.administration;

import com.grupocmc.protein.pojos.webservices.request.level.LevelRequestWebService;
import com.grupocmc.protein.pojos.webservices.response.ResponseBaseWebService;
import com.grupocmc.protein.pojos.webservices.response.level.LevelResponseWebService;
import com.grupocmc.protein.pojos.webservices.response.level.ListLevelResponseWebService;
import com.grupocmc.protein.service.administration.level.LevelServiceOps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping({ "/administration/levels" })
public class LevelController {

    private LevelServiceOps levelService;

    @Autowired
    public LevelController(LevelServiceOps levelService){
        this.levelService = levelService;
    }

    @RequestMapping(value = "/", method = {RequestMethod.POST}, headers = "Accept=application/xml, application/json")
    public @ResponseBody
    ResponseBaseWebService createLevel(@RequestBody @Valid LevelRequestWebService levelPeticion,
                                       @RequestHeader HttpHeaders headers) {

        ResponseBaseWebService respuesta = levelService.create(levelPeticion);
        return respuesta;
    }


    @RequestMapping(value = "/{slug}/", method = {RequestMethod.PUT}, headers = "Accept=application/xml, application/json")
    public @ResponseBody
    LevelResponseWebService updateLevel (@PathVariable("slug") String code,
                                         @RequestBody @Valid LevelRequestWebService levelPeticion,
                                         @RequestHeader HttpHeaders headers) {
        levelPeticion.setCode(code);
        LevelResponseWebService respuesta = levelService.update(levelPeticion);
        return respuesta;
    }

    @RequestMapping(value = "/{idLevel}/", method = {RequestMethod.DELETE}, headers = "Accept=application/xml, application/json")
    public ResponseBaseWebService deleteLevel (@PathVariable("idLevel") Long idLevel,
                                               @RequestHeader HttpHeaders headers) {

        ResponseBaseWebService respuesta = levelService.delete(idLevel);
        return respuesta;
    }

    @RequestMapping(value = "/{idLevel}/", method = {RequestMethod.GET}, headers = "Accept=application/xml, application/json")
    public @ResponseBody
    LevelResponseWebService getLevel (@PathVariable("idLevel") Long idLevel,
                                      @RequestHeader HttpHeaders headers) {
        LevelResponseWebService respuesta = levelService.read(idLevel);
        return respuesta;
    }


    @RequestMapping(value = "/", method = {RequestMethod.GET}, headers = "Accept=application/xml, application/json")
    public @ResponseBody
    ListLevelResponseWebService readAllLevels (Long[] uuids, @RequestHeader HttpHeaders headers){
        ListLevelResponseWebService respuesta = levelService.readAll( uuids);
        return respuesta;
    }
}

