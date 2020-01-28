package com.grupocmc.protein.dao.model.administration;

import com.grupocmc.protein.config.Constantes;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "ACTION_TYPE")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="DISC", discriminatorType=DiscriminatorType.STRING, length=20)
@DiscriminatorValue("COMUN")
@Where(clause = Constantes.SOFT_DELETE_CLAUSE)
public class ActionType extends BaseEntity implements Serializable {


    private String name;
    private String description;
    private String rule;
    private String expresionRule;
    private Integer numIterations;

    public ActionType(){}

    public ActionType(Long id){
        setId(id);
    }

    @Column(name = "CODE_NAME")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "CODE_DESC")
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "RULE")
    public String getRule() {
        return rule;
    }
    public void setRule(String rule) {
        this.rule = rule;
    }

    @Column(name = "RULE_EXP")
    public String getExpresionRule() {
        return expresionRule;
    }
    public void setExpresionRule(String expresionRule) {
        this.expresionRule = expresionRule;
    }

    @Column(name = "NUM_ITER")
    public Integer getNumIterations() {
        return numIterations;
    }
    public void setNumIterations(Integer numIterations) {
        this.numIterations = numIterations;
    }

    public String showInstance(){
        return "COMUN";
    }
}
