/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.api.boundary;

import java.util.List;
import java.util.UUID;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.api.entity.Point;
import org.api.entity.Serie;

/**
 *
 * @author bertrand
 */
class SerieManager {
    
    @PersistenceContext 
    EntityManager em;
    
    @Inject
    PointManager pm;

    public Serie findByName(String lieu) {
        try {
            return (Serie) em.createQuery("SELECT s FROM Serie s where s.lieu = :lieu")
                        .setParameter("lieu", lieu).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    public List<Serie> getAllSeries() {
        return em.createQuery("SELECT s FROM Serie s").getResultList();
    }

    public Serie save(Serie s) {
        for (Point p: s.getPoints()) {
            pm.save(p);
        }
        s.setId(UUID.randomUUID().toString());
        return this.em.merge(s);
    }
    
}
