package com.grupocmc.protein.service.administration.action;

import com.google.common.collect.Lists;
import com.grupocmc.protein.config.Constantes;
import com.grupocmc.protein.dao.model.administration.Action;
import com.grupocmc.protein.dao.model.administration.ActionType;
import com.grupocmc.protein.dao.model.administration.Reward;
import com.grupocmc.protein.dao.repository.administration.ActionTypeRepository;
import com.grupocmc.protein.dao.repository.administration.RewardRepository;
import com.grupocmc.protein.dao.repository.administration.ScopeRepository;
import com.grupocmc.protein.mvc.exceptions.NotFoundException;
import com.grupocmc.protein.mvc.exceptions.ValidationException;
import com.grupocmc.protein.pojos.webservices.response.ResponseBaseWebService;
import com.grupocmc.protein.pojos.webservices.request.action.ActionRequestWebService;
import com.grupocmc.protein.pojos.webservices.response.action.ActionResponseWebService;
import com.grupocmc.protein.pojos.webservices.response.action.ListActionsResponseWebService;
import com.grupocmc.protein.dao.repository.administration.ActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Service
@Transactional(rollbackFor = Exception.class)
public class ActionService implements ActionServiceOps {

    private ActionRepository actionRepository;
    private ScopeRepository scopeRepository;
    private ActionTypeRepository actionTypeRepository;
    private RewardRepository rewardRepository;

    @Autowired
    public ActionService(ActionRepository actionRepository, ScopeRepository scopeRepository, ActionTypeRepository actionTypeRepository,
                         RewardRepository rewardRepository){
        this.actionRepository = actionRepository;
        this.scopeRepository = scopeRepository;
        this.actionTypeRepository = actionTypeRepository;
        this.rewardRepository = rewardRepository;
    }
    @Override
    public ResponseBaseWebService create (ActionRequestWebService peticion){

        if (actionRepository.findBySlug(peticion.getCode()) != null) {
            throw new ValidationException(Constantes.RETORNO_ACTION_CODE_YA_EXISTE);
        }

        final Action  action = peticion.buildEntityFromRequest();

        if (peticion.getSlugReward() != null) {
            final Reward reward = Optional.ofNullable(
                    rewardRepository.findBySlug(peticion.getSlugReward()))
                    .orElseThrow(() -> new ValidationException(Constantes.RETORNO_REWARD_ACTION_NO_EXISTE));
            action.addReward(reward);
        }
        if (peticion.getScope() != null) {
            action.setScope(Optional.ofNullable(
                    scopeRepository.findBySlug(peticion.getScope()))
                    .orElseThrow(() -> new ValidationException(Constantes.RETORNO_SCOPE_ACTION_NO_EXISTE)));
        }
        Optional<ActionType> actionType = Optional.ofNullable(actionTypeRepository.findById(peticion.getActionType())
                        .orElseThrow(() -> new ValidationException(Constantes.RETORNO_TYPEACTION_ACTION_NO_EXISTE)));
        action.setActionType(actionType.get());

        if (peticion.getCodeParent() != null){
            action.setActionParent(
                    Optional.ofNullable(actionRepository.findBySlug(peticion.getCodeParent()))
                            .orElseThrow(() -> new ValidationException(Constantes.RETORNO_ACTION_PADRE_ACTION_NO_EXISTE)));
        } else {
            action.setActionParent(null);
        }
        final Action actionCreated = actionRepository.save(action);
        final ResponseBaseWebService respuesta =  new ResponseBaseWebService();

        respuesta.setId(actionCreated.getId());
        respuesta.setCodigoRespuesta(Constantes.RETORNO_OK);
        respuesta.setError(false);
        return respuesta;
    }

    @Override
    public ActionResponseWebService update (ActionRequestWebService peticion){

        final Action oldAction = Optional.ofNullable(actionRepository.findBySlug(peticion.getCode()))
                .orElseThrow(() -> new NotFoundException(Constantes.RETORNO_ACTION_NO_EXISTE));

        final Action modified = peticion.buildEntityFromRequest();
        modified.setState(oldAction.getState());
        modified.setId(oldAction.getId());


        if (peticion.getScope() != null) {
            modified.setScope(Optional.ofNullable(
                    scopeRepository.findBySlug(peticion.getScope()))
                    .orElseThrow(() -> new ValidationException(Constantes.RETORNO_SCOPE_ACTION_NO_EXISTE)));
        } else {
            modified.setScope(oldAction.getScope());
        }
        Optional<ActionType> actionType = Optional.ofNullable(actionTypeRepository.findById(peticion.getActionType())
                .orElseThrow(() -> new ValidationException(Constantes.RETORNO_TYPEACTION_ACTION_NO_EXISTE)));
        modified.setActionType(actionType.get());

        if (peticion.getCodeParent() != null){
            modified.setActionParent(
                    Optional.ofNullable(actionRepository.findBySlug(peticion.getCodeParent()))
                            .orElseThrow(() -> new ValidationException(Constantes.RETORNO_ACTION_PADRE_ACTION_NO_EXISTE)));
        } else {
            modified.setActionParent(oldAction.getActionParent());
        }

        if (peticion.getSlugReward() != null) {
            modified.removeReward(oldAction.obtenerReward());
            final Reward reward = Optional.ofNullable(
                    rewardRepository.findBySlug(peticion.getSlugReward()))
                    .orElseThrow(() -> new ValidationException(Constantes.RETORNO_REWARD_ACTION_NO_EXISTE));
            modified.addReward(reward);
        }
        actionRepository.save(modified);
        return getBeanFromEntity(modified);
    }

    @Override
    public ActionResponseWebService read (String slugAction ){
        Action action = ofNullable(actionRepository.findBySlug(slugAction))
                        .orElseThrow(() -> new NotFoundException(Constantes.RETORNO_ACTION_NO_EXISTE));

        return  getBeanFromEntity(action);
    }
    @Override
    public ListActionsResponseWebService readAll (Long[] ids){
        final ListActionsResponseWebService beanRespuesta = new ListActionsResponseWebService();
        beanRespuesta.setListActions(getActions(ids)
                .stream()
                .map(actionEntity-> getBeanFromEntity(actionEntity))
                .collect(Collectors.toList()));


        return beanRespuesta;
    }
    private ActionResponseWebService getBeanFromEntity(Action entidad) {
        return Optional.ofNullable(entidad).map(action -> new ActionResponseWebService().buildRespuestaFromEntity(action))
                .orElse(new ActionResponseWebService());
    }

    private List<Action> getActions (Long[] listIds) {

        return (Optional.ofNullable(listIds)
                .map(ids -> actionRepository.findByIdIn(Lists.newArrayList(ids)))
                .orElse(actionRepository.findAll())
                .stream()
                .sorted(Comparator.comparing(Action::getSlug).thenComparing(Action::getName))
                .collect(Collectors.toList()));

    }
    @Override
    public ResponseBaseWebService delete (String slugAction ) {
        Action actionDeleted = ofNullable(actionRepository.findBySlug(slugAction))
                .orElseThrow(() -> new NotFoundException(Constantes.RETORNO_ACTION_NO_EXISTE));

        actionDeleted.setState(false);
        actionDeleted.setRewards(null);
        actionRepository.save(actionDeleted);
        return new ResponseBaseWebService();
    }


}
