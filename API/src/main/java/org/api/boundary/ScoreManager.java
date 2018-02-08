/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.api.boundary;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.CacheStoreMode;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.api.entity.Score;
/**
 *
 * @author vali
 */
@Stateless
public class ScoreManager {

    @PersistenceContext
    EntityManager em;

    public Score findById(long id) {
        return this.em.find(Score.class, id);
    }

    public List<Score> findAll() {
        Query q = this.em.createNamedQuery("Score.findAll", Score.class);
        q.setHint("javax.persistence.cache.storeMode", CacheStoreMode.REFRESH);
        return q.getResultList();
    }

    public Score save(Score s) {
        return this.em.merge(s);
    }

    public void delete(long id) {
        try {
            Score ref = this.em.getReference(Score.class, id);
            this.em.remove(ref);
        } catch (EntityNotFoundException enfe) {
            // rien à faire   
        }
    }    
}
