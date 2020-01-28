package com.grupocmc.protein.service.gamification;

import com.grupocmc.protein.config.Constantes;
import com.grupocmc.protein.dao.model.administration.Action;
import com.grupocmc.protein.dao.model.administration.ActionRewardUser;
import com.grupocmc.protein.dao.model.administration.User;
import com.grupocmc.protein.dao.repository.administration.ActionRepository;
import com.grupocmc.protein.dao.repository.administration.ActionRewardUserRepository;
import com.grupocmc.protein.dao.repository.administration.UserRepository;
import com.grupocmc.protein.mvc.exceptions.NotFoundException;
import com.grupocmc.protein.mvc.exceptions.ValidationException;
import com.grupocmc.protein.pojos.webservices.request.BaseRequestWebService;
import com.grupocmc.protein.pojos.webservices.response.action.ListActionsResponseWebService;
import com.grupocmc.protein.pojos.webservices.response.gamification.ActionsUsersResponseWebService;
import com.grupocmc.protein.pojos.webservices.response.gamification.HowToDoReponseWebService;
import com.grupocmc.protein.service.administration.action.ActionService;
import com.grupocmc.protein.service.administration.action.ActionServiceOps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class GamificationService implements GamificationServiceOps {

    private ActionRepository actionRepository;
    private ActionRewardUserRepository actionRewardUserRepository;
    private UserRepository userRepository;

    @Autowired
    public GamificationService (ActionRepository actionRepository,
                                ActionRewardUserRepository actionRewardUserRepository,
                                UserRepository userRepository){
        this.actionRepository = actionRepository;
        this.actionRewardUserRepository = actionRewardUserRepository;
        this.userRepository =userRepository;
    }

    public HowToDoReponseWebService howToGamification(){

        HowToDoReponseWebService howtodo= new HowToDoReponseWebService();
        actionRepository.findByActionParentIsNull().forEach(actionEntity ->
                howtodo.createHowToDo(actionEntity,actionRepository.findByActionParent(actionEntity)));
        return howtodo;
    }

    @Override
    public ActionsUsersResponseWebService getActions(BaseRequestWebService peticion) {
        User user = Optional.ofNullable(userRepository.findByUsername(peticion.getUser()))
                .orElseThrow(()-> new NotFoundException(Constantes.RETORNO_USER_NOT_EXISTS));

        return buildResponseForEntity( Optional.ofNullable(this.actionRewardUserRepository.findByUserOrderByStartDateAsc(user))
                    .orElse(new ArrayList<>()), user);

    }

    private ActionsUsersResponseWebService buildResponseForEntity(List<ActionRewardUser> acciones, User user ){
        ActionsUsersResponseWebService actionsByUser = new ActionsUsersResponseWebService().createBeanResponse(user);

        if (acciones.isEmpty())
            return actionsByUser;

        acciones.forEach(actionRewardUser -> actionsByUser.createActionUser(actionRewardUser));
        return actionsByUser;
    }
}
