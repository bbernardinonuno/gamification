package com.grupocmc.protein.mvc.controller.administration;

import com.grupocmc.protein.pojos.webservices.response.ResponseBaseWebService;
import com.grupocmc.protein.pojos.webservices.request.typereward.TypeRewardRequestWebService;
import com.grupocmc.protein.pojos.webservices.response.typereward.ListTypeRewardResponseWebService;
import com.grupocmc.protein.pojos.webservices.response.typereward.TypeRewardResponseWebService;
import com.grupocmc.protein.service.administration.typereward.TypeRewardServiceOps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping({ "/administration/typeRewards" })
public class TypeRewardController {

    private TypeRewardServiceOps typeRewardService;

    @Autowired
    public TypeRewardController (TypeRewardServiceOps typeRewardService){
        this.typeRewardService = typeRewardService;
    }


    @RequestMapping(value = "/", method = {RequestMethod.POST}, headers = "Accept=application/xml, application/json")
    public @ResponseBody
    ResponseBaseWebService createTypeReward (@RequestBody @Valid TypeRewardRequestWebService rewardPeticionBean,
                                         @RequestHeader HttpHeaders headers) {
        ResponseBaseWebService respuesta =typeRewardService.create(rewardPeticionBean);
        return respuesta;
    }


    @RequestMapping(value = "/", method = {RequestMethod.PUT}, headers = "Accept=application/xml, application/json")
    public @ResponseBody
    TypeRewardResponseWebService updateTypeReward (@PathVariable("codeReward") String code,
                                                   @RequestBody @Valid TypeRewardRequestWebService typeRewardPeticion,
                                                   @RequestHeader HttpHeaders headers) {
        typeRewardPeticion.setCode(code);
        TypeRewardResponseWebService respuesta =typeRewardService.update(typeRewardPeticion);
        return respuesta;
    }

    @RequestMapping(value = "/{idReward}/", method = {RequestMethod.DELETE}, headers = "Accept=application/xml, application/json")
    public ResponseBaseWebService deleteTypeReward (@PathVariable("idReward") Long idReward,
                                                @RequestHeader HttpHeaders headers) {
        ResponseBaseWebService respuesta =typeRewardService.delete(idReward);
        return respuesta;
    }

    @RequestMapping(value = "/{idReward}/", method = {RequestMethod.GET}, headers = "Accept=application/xml, application/json")
    public @ResponseBody
    TypeRewardResponseWebService getTypeReward (@PathVariable("idReward") Long idReward,
                                        @RequestHeader HttpHeaders headers) {
        TypeRewardResponseWebService respuesta =typeRewardService.read(idReward);
        return respuesta;
    }


    @RequestMapping(value = "/", method = {RequestMethod.GET}, headers = "Accept=application/xml, application/json")
    public @ResponseBody
    ListTypeRewardResponseWebService readAllRewards (Long[] uuids, @RequestHeader HttpHeaders headers){
        ListTypeRewardResponseWebService respuesta = typeRewardService.readAll(uuids);
        return respuesta;
    }
}


