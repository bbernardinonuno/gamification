package com.grupocmc.protein.mvc.controller.administration;

import com.grupocmc.protein.pojos.webservices.response.ResponseBaseWebService;
import com.grupocmc.protein.pojos.webservices.request.reward.RewardRequestWebService;
import com.grupocmc.protein.pojos.webservices.response.reward.ListRewardsResponseWebService;
import com.grupocmc.protein.pojos.webservices.response.reward.RewardResponseWebService;
import com.grupocmc.protein.service.administration.reward.RewardServiceOps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping({ "/administration/rewards" })
public class RewardController {

    private RewardServiceOps rewardService;

    @Autowired
    public RewardController (RewardServiceOps rewardService){
        this.rewardService = rewardService;
    }


    @RequestMapping(value = "/", method = {RequestMethod.POST}, headers = "Accept=application/xml, application/json")
    public @ResponseBody
    ResponseBaseWebService createReward (@RequestBody @Valid RewardRequestWebService rewardPeticionBean,
                                         @RequestHeader HttpHeaders headers) {
        ResponseBaseWebService respuesta =rewardService.create(rewardPeticionBean);
        return respuesta;
    }


    @RequestMapping(value = "/{slug}/", method = {RequestMethod.PUT}, headers = "Accept=application/xml, application/json")
    public @ResponseBody RewardResponseWebService updateReward (@PathVariable("slug") String code,
                                           @RequestBody @Valid RewardRequestWebService rewardPeticionBean,
                                           @RequestHeader HttpHeaders headers) {
        rewardPeticionBean.setCode(code);
        RewardResponseWebService respuesta =rewardService.update(rewardPeticionBean);
        return respuesta;
    }

    @RequestMapping(value = "/{slug}/", method = {RequestMethod.DELETE}, headers = "Accept=application/xml, application/json")
    public ResponseBaseWebService deleteReward (@PathVariable("slug") String slug,
                                                @RequestHeader HttpHeaders headers) {
        ResponseBaseWebService respuesta =rewardService.delete(slug);
        return respuesta;
    }

    @RequestMapping(value = "/{slug}/", method = {RequestMethod.GET}, headers = "Accept=application/xml, application/json")
    public @ResponseBody
    RewardResponseWebService getReward (@PathVariable("slug") String slug,
                                        @RequestHeader HttpHeaders headers) {
        RewardResponseWebService respuesta =rewardService.read(slug);
        return respuesta;
    }


    @RequestMapping(value = "/", method = {RequestMethod.GET}, headers = "Accept=application/xml, application/json")
    public @ResponseBody
    ListRewardsResponseWebService readAllRewards (Long[] uuids, @RequestHeader HttpHeaders headers){
        ListRewardsResponseWebService respuesta = rewardService.readAll(uuids);
        return respuesta;
    }
}

