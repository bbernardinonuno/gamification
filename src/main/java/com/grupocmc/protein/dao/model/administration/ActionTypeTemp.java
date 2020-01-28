package com.grupocmc.protein.dao.model.administration;

import com.grupocmc.protein.config.Constantes;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name="ACTION_TYPE_TEMP")
@PrimaryKeyJoinColumn(referencedColumnName="ID")
@DiscriminatorValue("TEMP")
@Where(clause = Constantes.SOFT_DELETE_CLAUSE)
public class ActionTypeTemp extends ActionType implements Serializable {

    private DateTime startDate;
    private DateTime endDate;
    private Integer numDays;


    @Column(name="STARTDATE")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    public DateTime getStartDate() {
        return startDate;
    }
    public void setStartDate(DateTime startDate) {
        this.startDate = startDate;
    }

    @Column(name="ENDDATE")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    public DateTime getEndDate() {
        return endDate;
    }
    public void setEndDate(DateTime endDate) {
        this.endDate = endDate;
    }

    @Column(name="NUM_DAYS")
    public Integer getNumDays() {
        return numDays;
    }
    public void setNumDays(Integer numDays) {
        this.numDays = numDays;
    }

    @Override
    public String showInstance(){
        return "TEMP";
    }
}
