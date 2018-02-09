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
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.api.entity.Utilisateur;
import org.control.PasswordManagement;
import org.mindrot.jbcrypt.BCrypt;
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
    
    public boolean checkCredentials(String mail, String password) {
        Query q = this.em.createQuery("SELECT u FROM Utilisateur u WHERE u.mail = :usermail")
        .setParameter("usermail", mail);
        try {
           Utilisateur u = (Utilisateur) q.getSingleResult();
           return BCrypt.checkpw(password, u.getPassword());
        } catch(NoResultException e) {
            return false;
        }
    }

    public Utilisateur save(Utilisateur u) {
        u.setId(UUID.randomUUID().toString());
        u.setPassword(PasswordManagement.digestPassword(u.getPassword()));
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
