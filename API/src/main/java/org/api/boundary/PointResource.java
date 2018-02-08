
package org.api.boundary;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.inject.Inject;
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

@Stateless
@Path("point")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PointResource {
    @Inject
    PointManager pm;
    
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
    public Response newPoint(@Valid Point p, @Context UriInfo uriInfo) {
        Point newOne = this.pm.save(p);
        String id = newOne.getId();
        URI uri = uriInfo.getAbsolutePathBuilder().path("/"+id).build();
        return Response.created(uri).build();
    }
    
    @DELETE
    @Path("{id}")
    public Response suppression(@PathParam("id") long id) {
        this.pm.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    
}
