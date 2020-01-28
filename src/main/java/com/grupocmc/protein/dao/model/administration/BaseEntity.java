package com.grupocmc.protein.dao.model.administration;


import com.grupocmc.protein.config.Constantes;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@EntityListeners({AuditingEntityListener.class})
@MappedSuperclass
@Where(clause = Constantes.SOFT_DELETE_CLAUSE)
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Boolean state;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", unique = true, nullable = false)
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    @Column(
            name = "STATE",
            nullable = false,
            columnDefinition = "int(1) DEFAULT 1")
    public Boolean getState() {
        return state;
    }
    public void setState(Boolean state) {
        this.state = state;
    }

    @PrePersist
    public void prePersist() {
        if (getState() == null) setState(Boolean.TRUE);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseEntity)) return false;
        BaseEntity that = (BaseEntity) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getState(), that.getState());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getState());
    }
}