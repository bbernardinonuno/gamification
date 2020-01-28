package com.grupocmc.protein.dao.model.administration;

import lombok.Data;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name="ACTION_REWARD_USER")
public class ActionRewardUser  extends BaseEntity implements Serializable {


    private Action action;
    private Reward reward;
    private User user;
    private DateTime startDate;
    private DateTime endDate;
    private Integer numIteration;
    private Integer points;
    public ActionRewardUser(){}


    @ManyToOne
    @JoinColumn(name = "ID_ACTION")
    public Action getAction() {
        return action;
    }
    public void setAction(Action action) {
        this.action = action;
    }

    @ManyToOne
    @JoinColumn(name = "ID_REWARD")
    public Reward getReward() {
        return reward;
    }
    public void setReward(Reward reward) {
        this.reward = reward;
    }

    @ManyToOne
    @JoinColumn(name = "ID_USER")
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    @Column(name="STARTDATE")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    public DateTime getStartDate() {
        return startDate;
    }
    public void setStartDate(DateTime startDate) {
        this.startDate = startDate;
    }

    @Column(name="ENDDDATE")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    public DateTime getEndDate() {
        return endDate;
    }
    public void setEndDate(DateTime endDate) {
        this.endDate = endDate;
    }

    @Column(name="POINTS")
    public Integer getPoints() {return points;}
    public void setPoints(Integer points) {this.points = points;}

    @Column(name="ITERATION")
    public Integer getNumIteration() {return numIteration;}
    public void setNumIteration(Integer numIteration) {this.numIteration = numIteration;}
}
