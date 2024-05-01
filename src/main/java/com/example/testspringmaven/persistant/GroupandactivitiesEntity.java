package com.example.testspringmaven.persistant;

import jakarta.persistence.*;

@Entity
@Table(name = "groupandactivities", schema = "payaya")
public class GroupandactivitiesEntity {
    @Basic
    @Column(name = "idGroup", nullable = false)
    private int idGroup;
    @Basic
    @Column(name = "idActivity", nullable = false)
    private int idActivity;

    @Basic
    @Column(name = "note")
    private int note;

    @Id
    private Long id;

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
        if (note != that.note) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idGroup;
        result = 31 * result + idActivity;
        return result;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }
}
