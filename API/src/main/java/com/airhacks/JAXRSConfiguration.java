package com.airhacks;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.api.boundary.AuthentificationBoundary;
import org.api.boundary.PartieResource;
import org.api.boundary.PointResource;
import org.api.boundary.ScoreResource;
import org.api.boundary.SerieResource;
import org.api.boundary.FileResource;
import org.api.boundary.UtilisateurResource;
import org.provider.CORSRequestFilter;
import org.provider.CORSResponseFilter;

/**
 * Configures a JAX-RS endpoint. Delete this class, if you are not exposing
 * JAX-RS resources in your application.
 *
 * @author airhacks.com
 */
@ApplicationPath("api")
public class JAXRSConfiguration extends Application {
        @Override
        public Set<Class<?>> getClasses() {
        Set<Class<?>> s = new HashSet<>();
        s.add(com.github.phillipkruger.apiee.ApieeService.class);
        s.add(CORSRequestFilter.class);
        s.add(CORSResponseFilter.class);
        s.add(PartieResource.class);
        s.add(PointResource.class);
        s.add(ScoreResource.class);
        s.add(SerieResource.class);
        s.add(FileResource.class);
        s.add(UtilisateurResource.class);
        s.add(AuthentificationBoundary.class);
        return s;
    }
}
