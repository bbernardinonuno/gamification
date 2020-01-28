package com.grupocmc.protein.dao.model.administration;


import com.grupocmc.protein.config.Constantes;
import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name="ACTION_TYPE_MIN_VALUE")
@PrimaryKeyJoinColumn(referencedColumnName="ID")
@DiscriminatorValue("MIN")
@Where(clause = Constantes.SOFT_DELETE_CLAUSE)
public class ActionTypeMin extends ActionType implements Serializable {

    private Integer value;

    @Column(name="VALUE")
    public Integer getValue() {
        return value;
    }
    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String showInstance(){
        return "MIN";
    }
}
