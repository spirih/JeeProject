package com.example.testspringmaven.object;

import com.example.testspringmaven.persistant.ActivitiesEntity;
import com.example.testspringmaven.repository.ActivitiesRepository;

public class Activity {
    private String name;
    private String description;
    private String discipline;
    private String pathologies;
    private int id;
    private String url;
    private float lat;
    private float lng;
    private String address;

    public Activity() {
    }

    public Activity(String name, String description, String discipline, String pathologies, int id, String url, float lat, float lng, String address) {
        this.name = name;
        this.description = description;
        this.discipline = discipline;
        this.pathologies = pathologies;
        this.id = id;
        this.url = url;
        this.lat = lat;
        this.lng = lng;
        this.address = address;
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

    public String getPathologies() {
        return pathologies;
    }

    public void setPathologies(String pathologies) {
        this.pathologies = pathologies;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ActivitiesEntity generateRepository() {
        return new ActivitiesEntity(name,description,discipline,pathologies,url,lat,lng,address,-1);
    }
}
