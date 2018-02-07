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
import org.api.entity.User;
/**
 *
 * @author vali
 */
@Stateless
public class UserManager {
     @PersistenceContext
    EntityManager em;

    public User findById(String id) {
        return this.em.find(User.class, id);
    }

    public List<User> findAll() {
        Query q = this.em.createNamedQuery("User.findAll", User.class);
        q.setHint("javax.persistence.cache.storeMode", CacheStoreMode.REFRESH);
        return q.getResultList();
    }

    public User save(User u) {
        return this.em.merge(u);
    }

    public void delete(String id) {
        try {
            User ref = this.em.getReference(User.class, id);
            this.em.remove(ref);
        } catch (EntityNotFoundException enfe) {
            // rien à faire   
        }
    }
}
