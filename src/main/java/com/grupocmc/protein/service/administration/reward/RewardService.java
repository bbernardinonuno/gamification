package com.grupocmc.protein.service.administration.reward;

import com.google.common.collect.Lists;
import com.grupocmc.protein.config.Constantes;
import com.grupocmc.protein.dao.model.administration.Reward;
import com.grupocmc.protein.dao.repository.administration.RewardRepository;
import com.grupocmc.protein.dao.repository.administration.RewardTypeRepository;
import com.grupocmc.protein.mvc.exceptions.NotFoundException;
import com.grupocmc.protein.mvc.exceptions.ValidationException;
import com.grupocmc.protein.pojos.webservices.response.ResponseBaseWebService;
import com.grupocmc.protein.pojos.webservices.request.reward.RewardRequestWebService;
import com.grupocmc.protein.pojos.webservices.response.reward.ListRewardsResponseWebService;
import com.grupocmc.protein.pojos.webservices.response.reward.RewardResponseWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Service
@Transactional
public class RewardService implements  RewardServiceOps {

    private RewardRepository rewardRepository;
    private RewardTypeRepository rewardTypeRepository;

    @Autowired
    public RewardService (RewardRepository rewardRepository, RewardTypeRepository rewardTypeRepository){
        this.rewardRepository = rewardRepository;
        this.rewardTypeRepository = rewardTypeRepository;
    }

    @Override
    public ResponseBaseWebService create(RewardRequestWebService peticion) {

        if (rewardRepository.findBySlug(peticion.getCode()) != null) {
            throw new ValidationException(Constantes.RETORNO_REWARD_CODE_YA_EXISTE);
        }
        final Reward reward = peticion.buildEntityFromRequest(new Reward());

        reward.setRewardType(rewardTypeRepository.findBySlug(peticion.getCodTypeReward()));

        final Reward rewardCreated = rewardRepository.save(reward);
        final ResponseBaseWebService respuesta =  new ResponseBaseWebService();
        respuesta.setId(rewardCreated.getId());
        respuesta.setCodigoRespuesta(Constantes.RETORNO_OK);
        respuesta.setError(false);
        return respuesta;
    }

    @Override
    public RewardResponseWebService update(RewardRequestWebService peticion) {
        final Reward oldReward = Optional.ofNullable(rewardRepository.findBySlug(peticion.getCode()))
                .orElseThrow(() -> new NotFoundException(Constantes.RETORNO_REWARD_NO_EXISTE));

        final Reward modified = peticion.buildEntityFromRequest(oldReward);
        modified.setState(oldReward.getState());
        modified.setId(oldReward.getId());

        if (peticion.getCodTypeReward() != null)
            modified.setRewardType(rewardTypeRepository.findBySlug(peticion.getCodTypeReward()));

        rewardRepository.save(modified);
        return getBeanFromEntity(modified);
    }

    @Override
    public RewardResponseWebService read(String slug) {
        Reward reward = ofNullable(rewardRepository.findBySlug(slug))
                .orElseThrow(() -> new NotFoundException(Constantes.RETORNO_REWARD_NO_EXISTE));

        return  getBeanFromEntity(reward);
    }

    @Override
    public ResponseBaseWebService delete(String slug) {
        Reward rewardDeleted = ofNullable(rewardRepository.findBySlug(slug))
                .orElseThrow(() -> new NotFoundException(Constantes.RETORNO_REWARD_NO_EXISTE));

        rewardDeleted.setState(false);
        rewardRepository.save(rewardDeleted);
        return new ResponseBaseWebService();
    }

    @Override
    public ListRewardsResponseWebService readAll(Long[] ids) {
        final ListRewardsResponseWebService beanRespuesta = new ListRewardsResponseWebService();
        beanRespuesta.setListRewards(getRewards(ids)
                .stream()
                .map(entity-> getBeanFromEntity(entity))
                .collect(Collectors.toList()));


        return beanRespuesta;
    }

    private List<Reward> getRewards (Long[] listIds) {
        return (Optional.ofNullable(listIds)
                .map(ids -> rewardRepository.findByIdIn(Lists.newArrayList(ids)))
                .orElse(rewardRepository.findAll())
                .stream()
                .sorted(Comparator.comparing(Reward::getSlug).thenComparing(Reward::getName))
                .collect(Collectors.toList()));

    }
    private RewardResponseWebService getBeanFromEntity(Reward entidad) {
        return Optional.ofNullable(entidad).map(action -> new RewardResponseWebService().buildRespuestaFromEntity(action))
                .orElse(new RewardResponseWebService());
    }
}
