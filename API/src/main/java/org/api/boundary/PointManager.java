package org.api.boundary;

import java.util.List;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.persistence.CacheStoreMode;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.api.entity.Point;

@Stateless
public class PointManager {

    @PersistenceContext
    EntityManager em;

    public Point findById(long id) {
        return this.em.find(Point.class, id);
    }

    public List<Point> findAll() {
        Query q = this.em.createNamedQuery("Point.findAll", Point.class);
        q.setHint("javax.persistence.cache.storeMode", CacheStoreMode.REFRESH);
        return q.getResultList();
    }

    public Point save(Point p) {
        p.setId(UUID.randomUUID().toString());
        return this.em.merge(p);
    }

    public void delete(long id) {
        try {
            Point ref = this.em.getReference(Point.class, id);
            this.em.remove(ref);
        } catch (EntityNotFoundException enfe) {
            // rien à faire   
        }
    }
}
