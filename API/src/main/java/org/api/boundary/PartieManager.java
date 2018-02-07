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
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.api.entity.Partie;
import org.api.entity.Point;
import org.provider.CORSRequestFilter;

/**
 *
 * @author bertrand
 */
public class PartieManager {
    @PersistenceContext 
    EntityManager em;
    
    @Inject
    SerieManager sm;
    
    private final static Logger log = Logger.getLogger(PartieManager.class.getName());

    
    public List<Point> nouvellePartie(String lieu, int difficulte) {
        final int NBPOINTS = 10;
        Random r = ThreadLocalRandom.current();
        List points = sm.findByName(lieu).getPoints().stream()
                .filter(p -> p.getDifficulte() <= difficulte)
                .collect(Collectors.toList());
        if (points == null) {
            log.log(Level.WARNING, "{0}non trouv\u00e9", lieu);
            return null;
        }
        int length = points.size();

        if (length < NBPOINTS) {
            log.log(Level.WARNING, "{0} a seulement {1} points, {2} n\u00e9c\u00e9ssaires", new Object[]{lieu, length, NBPOINTS});
            return null;
        };

        for (int i = length - 1; i >= length - NBPOINTS; --i)
        {
            Collections.swap(points, i , r.nextInt(i + 1));
        }
        return points.subList(length - NBPOINTS, length);
    }
}
