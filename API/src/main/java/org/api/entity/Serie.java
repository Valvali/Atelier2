/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.api.entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author bertrand
 */
@Entity
public class Serie {
    
    @Id
    private String id;
    private String lieu;
    @OneToMany
    private List<Point> points;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String nom) {
        this.lieu = nom;
    }
    
    /**
     * @return the points
     */
    public List<Point> getPoints() {
        return points;
    }
    
}
