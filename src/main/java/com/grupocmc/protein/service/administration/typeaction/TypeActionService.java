package com.grupocmc.protein.service.administration.typeaction;

import com.google.common.collect.Lists;
import com.grupocmc.protein.config.Constantes;
import com.grupocmc.protein.dao.model.administration.Action;
import com.grupocmc.protein.dao.model.administration.ActionType;
import com.grupocmc.protein.dao.model.administration.ActionTypeMin;
import com.grupocmc.protein.dao.model.administration.ActionTypeTemp;
import com.grupocmc.protein.dao.repository.administration.ActionTypeRepository;
import com.grupocmc.protein.mvc.exceptions.NotFoundException;
import com.grupocmc.protein.mvc.exceptions.ValidationException;
import com.grupocmc.protein.pojos.webservices.request.typeaction.TypeActionRequestWebService;
import com.grupocmc.protein.pojos.webservices.response.ResponseBaseWebService;
import com.grupocmc.protein.pojos.webservices.response.typeaction.ListTypeActionResponseWebService;
import com.grupocmc.protein.pojos.webservices.response.typeaction.TypeActionResponseWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class TypeActionService implements TypeActionServiceOps {

    private ActionTypeRepository actionTypeRepository;

    @Autowired
    public TypeActionService (ActionTypeRepository actionTypeRepository){
        this.actionTypeRepository = actionTypeRepository;
    }

    @Override
    public ResponseBaseWebService create(TypeActionRequestWebService peticion) {
        final ActionType actionType = peticion.buildEntityFromRequest(new ActionType());

        Optional.ofNullable(actionType).orElseThrow(()-> new ValidationException(Constantes.RETORNO_TYPEACTION_NOT_INSTANCIATED));

        final ActionType actionTypeCreated = actionTypeRepository.save(actionType);
        final ResponseBaseWebService respuesta =  new ResponseBaseWebService();
        respuesta.setId(actionTypeCreated.getId());
        respuesta.setCodigoRespuesta(Constantes.RETORNO_OK);
        respuesta.setError(false);
        return respuesta;
    }

    @Override
    public TypeActionResponseWebService update(TypeActionRequestWebService peticion) {
        final Optional<ActionType> oldActionType = Optional.ofNullable(actionTypeRepository.findById(peticion.getIdTypeAction()))
                .orElseThrow(() -> new NotFoundException(Constantes.RETORNO_TYPEREWARD_NO_EXISTE));
        if (!oldActionType.isPresent()){
            throw  new NotFoundException(Constantes.RETORNO_TYPEACTION_NO_EXISTE);
        }

        final ActionType modified = peticion.buildEntityFromRequest(oldActionType.get());
        Optional.ofNullable(modified).orElseThrow(()-> new ValidationException(Constantes.RETORNO_TYPEACTION_NOT_INSTANCIATED));

        modified.setState(oldActionType.get().getState());
        modified.setId(oldActionType.get().getId());

        if (!modified.showInstance().equals(oldActionType.get().showInstance())){
            delete(oldActionType.get().getId());
        }
        actionTypeRepository.save(modified);
        return getBeanFromEntity(modified);
    }

    @Override
    public TypeActionResponseWebService read(Long idActionType) {
        Optional<ActionType> actionType = Optional.ofNullable(actionTypeRepository.findById(idActionType))
                .orElseThrow(() -> new NotFoundException(Constantes.RETORNO_TYPEACTION_NO_EXISTE));

        if (!actionType.isPresent()){
            throw  new NotFoundException(Constantes.RETORNO_TYPEACTION_NO_EXISTE);
        }
        return getBeanFromEntity(actionType.get());
    }

    @Override
    public ResponseBaseWebService delete(Long idActionType) {
        Optional<ActionType> actionType = Optional.ofNullable(actionTypeRepository.findById(idActionType))
                .orElseThrow(() -> new NotFoundException(Constantes.RETORNO_TYPEACTION_NO_EXISTE));

        if (actionType.isPresent()){
            ActionType actionTypeDeleted = actionType.get();
            actionTypeDeleted.setState(false);
            actionTypeRepository.save(actionTypeDeleted);
        }
        return new ResponseBaseWebService();
    }

    @Override
    public ListTypeActionResponseWebService readAll(Long[] ids) {
        final ListTypeActionResponseWebService beanRespuesta = new ListTypeActionResponseWebService();
        beanRespuesta.setListTypeActions(getActionsType(ids)
                .stream()
                .map(entity-> getBeanFromEntity(entity))
                .collect(Collectors.toList()));
        return beanRespuesta;
    }

    public List<ActionType> getActionsType (Long[] listIds){
        return (Optional.ofNullable(listIds)
                .map(ids -> actionTypeRepository.findByIdIn(Lists.newArrayList(ids)))
                .orElse(actionTypeRepository.findAll())
                .stream()
                .sorted(Comparator.comparing(ActionType::getName))
                .collect(Collectors.toList()));
    }

    private TypeActionResponseWebService getBeanFromEntity(ActionType entidad) {
        return Optional.ofNullable(entidad).map(actionType -> new TypeActionResponseWebService().buildRespuestaFromEntity(actionType))
                .orElse(new TypeActionResponseWebService());
    }

}
