package com.example.testspringmaven.persistant;

import jakarta.persistence.*;

@Entity
@Table(name = "activities", schema = "payaya", catalog = "")

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
    private int pathologie;
    @Basic
    @Column(name = "url", nullable = false)
    private int url;
    @Basic
    @Column(name = "lat", nullable = false, precision = 0)
    private double lat;
    @Basic
    @Column(name = "lng", nullable = false, precision = 0)
    private double lng;
    @Basic
    @Column(name = "address", nullable = false, length = -1)
    private String address;
    @Basic
    @Column(name = "note", nullable = false)
    private int note;

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

    public int getPathologie() {
        return pathologie;
    }

    public void setPathologie(int pathologie) {
        this.pathologie = pathologie;
    }

    public int getUrl() {
        return url;
    }

    public void setUrl(int url) {
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
        result = 31 * result + pathologie;
        result = 31 * result + url;
        temp = Double.doubleToLongBits(lat);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(lng);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }
}
