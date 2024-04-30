package com.example.testspringmaven.persistant;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "groupandactivities", schema = "payaya", catalog = "")
public class GroupandactivitiesEntity {
    @Basic
    @Column(name = "idGroup", nullable = false)
    private int idGroup;
    @Basic
    @Column(name = "idActivity", nullable = false)
    private int idActivity;

    public int getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(int idGroup) {
        this.idGroup = idGroup;
    }

    public int getIdActivity() {
        return idActivity;
    }

    public void setIdActivity(int idActivity) {
        this.idActivity = idActivity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupandactivitiesEntity that = (GroupandactivitiesEntity) o;

        if (idGroup != that.idGroup) return false;
        if (idActivity != that.idActivity) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idGroup;
        result = 31 * result + idActivity;
        return result;
    }
}
