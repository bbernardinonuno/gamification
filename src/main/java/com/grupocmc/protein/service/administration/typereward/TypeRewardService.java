package com.grupocmc.protein.service.administration.typereward;

import com.google.common.collect.Lists;
import com.grupocmc.protein.config.Constantes;
import com.grupocmc.protein.dao.model.administration.RewardType;
import com.grupocmc.protein.dao.repository.administration.RewardTypeRepository;
import com.grupocmc.protein.mvc.exceptions.NotFoundException;
import com.grupocmc.protein.mvc.exceptions.ValidationException;
import com.grupocmc.protein.pojos.webservices.response.ResponseBaseWebService;
import com.grupocmc.protein.pojos.webservices.request.typereward.TypeRewardRequestWebService;
import com.grupocmc.protein.pojos.webservices.response.typereward.ListTypeRewardResponseWebService;
import com.grupocmc.protein.pojos.webservices.response.typereward.TypeRewardResponseWebService;
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
public class TypeRewardService implements TypeRewardServiceOps {

    private RewardTypeRepository rewardTypeRepository;

    @Autowired
    public  TypeRewardService(RewardTypeRepository rewardTypeRepository){
        this.rewardTypeRepository = rewardTypeRepository;
    }


    @Override
    public ResponseBaseWebService create(TypeRewardRequestWebService rewardTypePeticionBean) {

        if (rewardTypeRepository.findBySlug(rewardTypePeticionBean.getCode()) != null) {
            throw new ValidationException(Constantes.RETORNO_TYPEREWARD_CODE_YA_EXISTE);
        }
        final RewardType rewardType = rewardTypePeticionBean.buildEntityFromRequest(new RewardType());
        final RewardType rewardTypeCreated = rewardTypeRepository.save(rewardType);
        final ResponseBaseWebService respuesta =  new ResponseBaseWebService();
        respuesta.setId(rewardTypeCreated.getId());
        respuesta.setCodigoRespuesta(Constantes.RETORNO_OK);
        respuesta.setError(false);
        return respuesta;
    }

    @Override
    public TypeRewardResponseWebService update(TypeRewardRequestWebService peticion) {
        final RewardType oldRewardType = Optional.ofNullable(rewardTypeRepository.findBySlug(peticion.getCode()))
                .orElseThrow(() -> new NotFoundException(Constantes.RETORNO_TYPEREWARD_NO_EXISTE));

        final RewardType modified = peticion.buildEntityFromRequest(oldRewardType);
        modified.setState(oldRewardType.getState());
        modified.setId(oldRewardType.getId());
        rewardTypeRepository.save(modified);
        return getBeanFromEntity(modified);
    }

    @Override
    public TypeRewardResponseWebService read(Long idTypeReward) {
        Optional<RewardType> rewardType = ofNullable(rewardTypeRepository.findById(idTypeReward))
                .orElseThrow(() -> new NotFoundException(Constantes.RETORNO_TYPEREWARD_NO_EXISTE));
        if (!rewardType.isPresent())
            throw new NotFoundException(Constantes.RETORNO_TYPEREWARD_NO_EXISTE) ;

        return  getBeanFromEntity(rewardType.get());
    }


    @Override
    public ResponseBaseWebService delete(Long idTypeReward) {
        Optional<RewardType> rewardType = ofNullable(rewardTypeRepository.findById(idTypeReward))
                .orElseThrow(() -> new NotFoundException(Constantes.RETORNO_TYPEREWARD_NO_EXISTE));

        if (rewardType.isPresent()){
            RewardType rewardTypeDeleted = rewardType.get();
            rewardTypeDeleted.setState(false);
            rewardTypeRepository.save(rewardTypeDeleted);
        }
        return new ResponseBaseWebService();
    }

    @Override
    public ListTypeRewardResponseWebService readAll(Long[] ids) {
        final ListTypeRewardResponseWebService beanRespuesta = new ListTypeRewardResponseWebService();
        beanRespuesta.setListTypesRewards(getTypeRewards(ids)
                .stream()
                .map(entity-> getBeanFromEntity(entity))
                .collect(Collectors.toList()));
        return beanRespuesta;
    }

    private List<RewardType> getTypeRewards (Long[] listIds) {
        return (Optional.ofNullable(listIds)
                .map(ids -> rewardTypeRepository.findByIdIn(Lists.newArrayList(ids)))
                .orElse(rewardTypeRepository.findAll())
                .stream()
                .sorted(Comparator.comparing(RewardType::getSlug).thenComparing(RewardType::getName))
                .collect(Collectors.toList()));

    }
    private TypeRewardResponseWebService getBeanFromEntity(RewardType entidad) {
        return Optional.ofNullable(entidad).map(typeReward -> new TypeRewardResponseWebService().buildRespuestaFromEntity(typeReward))
                .orElse(new TypeRewardResponseWebService());
    }
}
