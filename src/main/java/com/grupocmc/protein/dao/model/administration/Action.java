package com.grupocmc.protein.dao.model.administration;

import javax.persistence.*;

import com.grupocmc.protein.config.Constantes;
import lombok.Data;
import org.hibernate.annotations.Where;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "ACTIONS")
@Where(clause = Constantes.SOFT_DELETE_CLAUSE)
public class Action extends BaseEntity implements Serializable{

    private String slug;
    private String name;
    private String description;
    private Integer points;
    private byte[] image;
    private String message;
    private Boolean isParent;
    private Action actionParent;
    private ActionType actionType;
    private Scope scope;
    private Set<Reward> rewards = new HashSet<>();

    public Action(){}

    public Action(String slug){
        this.slug = slug;
    }

    @Column(name="CODE_NAME")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(name="CODE_DESC")
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name="POINTS")
    public Integer getPoints() {
        return points;
    }
    public void setPoints(Integer points) {
        this.points = points;
    }

    @Column(name="IMAGE")
    public byte[] getImage() {return image;}
    public void setImage(byte[] image) {this.image = image;}

    @Column(name="SLUG")
    public String getSlug() {
        return slug;
    }
    public void setSlug(String slug) {
        this.slug = slug;
    }

    @Column(name="MESSAGE")
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }


    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name = "ID_ACTION_TYPE")
    public ActionType getActionType() { return actionType;}
    public void setActionType(ActionType actionType) {this.actionType = actionType;}

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name = "ID_SCOPE")
    public Scope getScope() { return scope;}
    public void setScope(Scope scope) { this.scope = scope;}


    @Column(name = "IS_FATHER")
    public Boolean getIsParent() {return isParent;}
    public void setIsParent(Boolean isParent) {this.isParent = isParent;}

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ACTION_FATHER")
    public Action getActionParent() {return actionParent;}
    public void setActionParent(Action actionParent) {this.actionParent = actionParent;}



    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name = "ACTIONS_REWARDS",
            joinColumns = { @JoinColumn(name = "ID_ACTION") },
            inverseJoinColumns = { @JoinColumn(name = "ID_REWARD") })
    public Set<Reward> getRewards() { return rewards;}
    public void setRewards(Set<Reward> rewards) {this.rewards = rewards; }


    public void addReward(Reward reward){
        if (this.rewards == null)
            this.rewards = new HashSet<>();
        this.rewards.add(reward);
    }

    public void removeReward(Reward reward){
        this.rewards.remove(reward);

    }
    public Reward obtenerReward(){
        if (this.rewards == null ||this.rewards.isEmpty())
             return null;
        return this.rewards.iterator().next();
    }


}
