package com.grupocmc.protein.dao.model.administration;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "USER")
public class User extends BaseEntity implements Serializable {

    private String username;
    private Integer points;
    private Level level;
    private String role;

    @Column(name="USERNAME")
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name="POINTS")
    public Integer getPoints() {
        return points;
    }
    public void setPoints(Integer points) {
        this.points = points;
    }

    @ManyToOne
    @JoinColumn(name = "ID_LEVEL")
    public Level getLevel() {
        return level;
    }
    public void setLevel(Level level) {
        this.level = level;
    }

    @Column(name="ROLE")
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
}
