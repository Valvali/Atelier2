/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.api.boundary;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.api.entity.Partie;
import org.api.entity.Serie;

/**
 *
 * @author bertrand
 */
public class PartieManager {
    @PersistenceContext 
    EntityManager em;
    SerieManager sm;
    
    public List<Partie> nouvellePartie(String lieu, int difficulte) {
        final int NBPOINTS = 10;
        Random r = ThreadLocalRandom.current();
        List points = sm.findByName(lieu).getPoints();
        if (points == null) {
            return null;
            // TODO
        }
        int length = points.size();

        if (length < NBPOINTS) return null; // TODO

        for (int i = length - 1; i >= length - NBPOINTS; --i)
        {
            Collections.swap(points, i , r.nextInt(i + 1));
        }
        return points.subList(length - NBPOINTS, length);
    }
}
