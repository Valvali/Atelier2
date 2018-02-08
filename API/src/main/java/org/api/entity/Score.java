/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.api.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

/**
 *
 * @author vali
 */
@Entity
@NamedQuery(name="Score.findAll",query="SELECT s FROM Score s")
public class Score implements Serializable {
    @Id
    private String id;
    
    private int score;
    private String nom;
    private int difficulte;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "")
    private Serie serie;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the difficulte
     */
    public int getDifficulte() {
        return difficulte;
    }

    /**
     * @param difficulte the difficulte to set
     */
    public void setDifficulte(int difficulte) {
        this.difficulte = difficulte;
    }

    /**
     * @return the serie
     */
    public Serie getSerie() {
        return serie;
    }

    /**
     * @param serie the serie to set
     */
    public void setSerie(Serie serie) {
        this.serie = serie;
    }
    
    
}
