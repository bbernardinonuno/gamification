package com.grupocmc.protein.dao.model.administration;

import com.grupocmc.protein.config.Constantes;
import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "REWARD_TYPE")
@Where(clause = Constantes.SOFT_DELETE_CLAUSE)
public class RewardType extends BaseEntity implements Serializable {

    private String slug;
    private String name;
    private String description;
    private Integer value;
    private Double percentage;
    private String symbol;

    public RewardType(){}

    public RewardType(String code){
        this.slug =code;
    }

    @Column(name="SLUG")
    public String getSlug() {return slug;}
    public void setSlug(String slug) { this.slug = slug;}

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

    @Column(name="VALUE")
    public Integer getValue() {
        return value;
    }
    public void setValue(Integer value) {
        this.value = value;
    }

    @Column(name="PERCENTAGE")
    public Double getPercentage() {
        return percentage;
    }
    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    @Column(name="SYMBOL")
    public String getSymbol() {
        return symbol;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
