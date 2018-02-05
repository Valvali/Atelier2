package org.api.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@NamedQuery(name="Point.findAll",query="SELECT p FROM Point p")
public class Point implements Serializable {
    @Id
    private long id;
    
    @NotNull
    private double lat;
    @NotNull
    private double lng;
    @NotNull
    private String img;
    
    private String description;
    
    private int difficulte;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDifficulte() {
        return difficulte;
    }

    public void setDifficulte(int difficulte) {
        this.difficulte = difficulte;
    }
    
    
}
