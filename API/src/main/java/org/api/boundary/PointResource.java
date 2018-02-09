
package org.api.boundary;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.api.entity.Point;
import org.api.entity.Serie;
import org.provider.Secured;

@Stateless
@Path("point")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PointResource {
    @Inject
    PointManager pm;
    
    @Inject
    SerieManager sm;
    
    @PersistenceContext
    EntityManager em;
    
    @GET
    public Response getPoints() {
//        JsonObject json = Json.createObjectBuilder()
//                .add("type", "collection")
//                .add("categories", getCategorieList())
//                .build();
//        return Response.ok(json).build();
        GenericEntity<List<Point>> liste = new GenericEntity<List<Point>>(this.pm.findAll()) {
        };
        return Response.ok(liste).build();
    }
    
    @GET
    @Path("{id}")
    public Response getOnePoint(@PathParam("id") long id, @Context UriInfo uriInfo) {
        return Optional.ofNullable(pm.findById(id))
                //.map(c -> Response.ok(categorie2Json(c)).build())
                .map(p -> Response.ok(p).build())
                //.orElseThrow(() -> new CategorieNotFound("Ressource non disponible "+ uriInfo.getPath()));
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }
    
    @POST
    @Secured
    @Path("{serie}")
    public Response newPoint(@Valid Point p, @PathParam("serie") String s, @Context UriInfo uriInfo) {
        
        Serie serie = sm.findByName(s);
        em.detach(serie);
        serie.getPoints().add(p);
        
        
//        Point newOne = this.pm.save(p);
        this.sm.save(serie);
        
        
//        String id = newOne.getId();
//        URI uri = uriInfo.getAbsolutePathBuilder().path("/"+id).build();
        return Response.created(/*uri*/null).build();
    }
    
    @DELETE
    @Secured
    @Path("{id}")
    public Response suppression(@PathParam("id") long id) {
        this.pm.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    
}
