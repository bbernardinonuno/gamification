package com.grupocmc.protein.pojos.webservices.response.gamification;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.grupocmc.protein.dao.model.administration.Action;
import com.grupocmc.protein.dao.model.administration.Reward;
import com.grupocmc.protein.pojos.webservices.response.ResponseBaseWebService;
import com.grupocmc.protein.pojos.webservices.response.action.ActionResponseWebService;
import com.grupocmc.protein.pojos.webservices.response.reward.RewardResponseWebService;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@NoArgsConstructor
@JsonInclude(NON_NULL)
public class HowToDoReponseWebService extends ResponseBaseWebService implements Serializable {

    @JsonProperty("howToDoList")
    private List<HowToDo> howToDoList;

    public void createHowToDo (Action action, List<Action> childrens) {
        HowToDo howToDo = new HowToDo(action,childrens);
        addHowToDoList(howToDo);

    }

    private void addHowToDoList(HowToDo howtodo){
        if (howToDoList == null)
            howToDoList  = new ArrayList<>();

        if (howtodo != null)
            howToDoList.add(howtodo);
    }


    @Data
    @JsonInclude(NON_NULL)
    protected class HowToDo {


        @JsonProperty("action_name")
        private String action;

        @JsonProperty("action_desc")
        private String descriptionAction;

        @JsonProperty("points")
        private Integer points;

        @JsonProperty("childrens")
        private List<HowToDo> childrens;

        @JsonProperty("achievement_name")
        private String reward;

        @JsonProperty("achievement_desc")
        private String descriptionReward;


        public HowToDo(Action actionEntity,List<Action> childs) {
            setAction(actionEntity.getName());
            setDescriptionAction(actionEntity.getDescription());
            setPoints(actionEntity.getPoints());
            if (actionEntity.getRewards() != null && actionEntity.getRewards().size() > 0 ){
                Reward reward  = actionEntity.getRewards().iterator().next();
                setReward(reward.getName());
                setDescriptionReward(reward.getDescription());
            }
            if (childs != null){
                this.setChildrens(this.getChildrens() == null? new ArrayList<>() : getChildrens());
                childs.forEach(child -> this.getChildrens().add(new HowToDo(child, null)));
            }
        }
    }
}
