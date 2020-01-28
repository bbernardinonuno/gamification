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
@Table(name = "REWARDS")
@Where(clause = Constantes.SOFT_DELETE_CLAUSE)
public class Reward extends BaseEntity implements Serializable {

    private String slug;
    private String name;
    private String description;
    private byte[] image;
    private String message;
    private RewardType rewardType;

    public Reward(){}

    public Reward(String slug){
        this.slug = slug;
    }

    @Column(name="SLUG")
    public String getSlug() {return slug;}
    public void setSlug(String slug) {this.slug = slug;}

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

    @Column(name="IMAGE")
    public byte[] getImage() {
        return image;
    }
    public void setImage(byte[] image) {
        this.image = image;
    }

    @Column(name="MESSAGE")
    public String getMessage() {return message;}
    public void setMessage(String message) {this.message = message;}

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_REWARD_TYPE")
    public RewardType getRewardType() {return rewardType;}
    public void setRewardType(RewardType rewardType) {this.rewardType = rewardType;}



}
