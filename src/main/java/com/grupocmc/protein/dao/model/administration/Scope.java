package com.grupocmc.protein.dao.model.administration;

import com.grupocmc.protein.config.Constantes;
import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "SCOPE")
@Where(clause = Constantes.SOFT_DELETE_CLAUSE)
public class Scope extends BaseEntity  {

    private String name;
    private String description;
    private String slug;

    public Scope(){}

    public Scope(String slug){
        this.slug = slug;
    }

    @Column(name="SLUG")
    public String getSlug() {return slug;}
    public void setSlug(String slug) {this.slug = slug;}

    @Column(name="CODE_NAME")
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    @Column(name="CODE_DESC")
    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}
}
