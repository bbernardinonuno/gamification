package com.grupocmc.protein.pojos.webservices.response.gamification;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.grupocmc.protein.dao.model.administration.ActionRewardUser;
import com.grupocmc.protein.dao.model.administration.User;
import com.grupocmc.protein.mvc.deserializers.JodaTimeDeserializer;
import com.grupocmc.protein.pojos.webservices.response.ResponseBaseWebService;

import com.grupocmc.protein.pojos.webservices.response.level.LevelResponseWebService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data

@NoArgsConstructor
@JsonInclude(NON_NULL)
public class ActionsUsersResponseWebService extends ResponseBaseWebService implements Serializable {

    @JsonProperty("actions")
    List<ActionsDate> actionsList;

    @JsonProperty("points")
    Integer totalPoints;

    @JsonProperty("level")
    LevelResponseWebService level;

    public ActionsUsersResponseWebService createBeanResponse(User user){
        this.setTotalPoints(user.getPoints());
        this.setLevel(new LevelResponseWebService().buildRespuestaFromEntity(user.getLevel()));
        return this;
    }

    public void createActionUser(ActionRewardUser actionUser){
        addActionsDate(new ActionsDate(actionUser));
    }

    private void addActionsDate(ActionsDate actionsDate){
        if (this.actionsList == null)
            this.actionsList  = new ArrayList<>();

        this.actionsList.add(actionsDate);
    }


    @Data
    @JsonInclude(NON_NULL)
    protected class ActionsDate {
        @JsonProperty("action")
        String actionName;

        @JsonProperty("points")
        Integer pointsAction;

        @JsonProperty("date")
        @JsonSerialize(using = JodaTimeDeserializer.class)
        DateTime dateAction;

        public ActionsDate (ActionRewardUser action ){

            this.setActionName(action.getAction().getName());
            this.setPointsAction(action.getPoints());
            this.setDateAction(action.getStartDate());
        }
    }

}
