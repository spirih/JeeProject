package com.example.testspringmaven.persistant;

import com.example.testspringmaven.repository.ActivitiesRepository;
import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
@Table(name = "activities", schema = "payaya")

public class ActivitiesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "name", nullable = false, length = -1)
    private String name;
    @Basic
    @Column(name = "description", nullable = false, length = -1)
    private String description;
    @Basic
    @Column(name = "discipline", nullable = false, length = -1)
    private String discipline;
    @Basic
    @Column(name = "pathologie", nullable = false)
    private String pathologie;
    @Basic
    @Column(name = "url", nullable = false)
    private String url;
    @Basic
    @Column(name = "lat", nullable = false, precision = 0)
    private double lat;
    @Basic
    @Column(name = "lng", nullable = false, precision = 0)
    private double lng;
    @Basic
    @Column(name = "address", nullable = false, length = -1)
    private String address;

    private float note = 0;


    public ActivitiesEntity(String name, String description, String discipline, String pathologie, String url, double lat, double lng, String address) {
        this.name = name;
        this.description = description;
        this.discipline = discipline;
        this.pathologie = pathologie;
        this.url = url;
        this.lat = lat;
        this.lng = lng;
        this.address = address;
    }

    public ActivitiesEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public String getPathologie() {
        return pathologie;
    }

    public void setPathologie(String pathologie) {
        this.pathologie = pathologie;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ActivitiesEntity that = (ActivitiesEntity) o;

        if (id != that.id) return false;
        if (pathologie != that.pathologie) return false;
        if (url != that.url) return false;
        if (Double.compare(that.lat, lat) != 0) return false;
        if (Double.compare(that.lng, lng) != 0) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (discipline != null ? !discipline.equals(that.discipline) : that.discipline != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (discipline != null ? discipline.hashCode() : 0);
        result = 31 * result + (pathologie != null ? pathologie.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        temp = Double.doubleToLongBits(lat);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(lng);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }


    public float tellNote(){
        float note = 0;
        return note;
    }
}
