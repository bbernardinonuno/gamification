package com.grupocmc.protein.dao.model.administration;

import javax.persistence.*;

import com.grupocmc.protein.config.Constantes;
import lombok.Data;
import org.hibernate.annotations.Where;

import java.io.Serializable;

@Data
@Entity
@Table(name = "LEVEL")
@Where(clause = Constantes.SOFT_DELETE_CLAUSE)
public class Level extends BaseEntity implements Serializable {


    private String slug;
    private String name;
    private String description;
    private Integer points;

    public Level(){}

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

    @Column(name="POINTS")
    public Integer getPoints() {
        return points;
    }
    public void setPoints(Integer points) {
        this.points = points;
    }


}
