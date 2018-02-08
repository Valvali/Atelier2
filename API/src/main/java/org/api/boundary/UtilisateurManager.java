/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.api.boundary;

import java.util.List;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.persistence.CacheStoreMode;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.api.entity.Utilisateur;
/**
 *
 * @author vali
 */
@Stateless
public class UtilisateurManager {
     @PersistenceContext
    EntityManager em;

    public Utilisateur findById(String id) {
        return this.em.find(Utilisateur.class, id);
    }

    public List<Utilisateur> findAll() {
        Query q = this.em.createNamedQuery("User.findAll", Utilisateur.class);
        q.setHint("javax.persistence.cache.storeMode", CacheStoreMode.REFRESH);
        return q.getResultList();
    }

    public Utilisateur save(Utilisateur u) {
        u.setId(UUID.randomUUID().toString());
        return this.em.merge(u);
    }

    public void delete(String id) {
        try {
            Utilisateur ref = this.em.getReference(Utilisateur.class, id);
            this.em.remove(ref);
        } catch (EntityNotFoundException enfe) {
            // rien à faire   
        }
    }
}
