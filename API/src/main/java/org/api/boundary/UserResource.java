/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.api.boundary;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.api.entity.User;

/**
 *
 * @author vali
 */
@Stateless
@Path("user")
public class UserResource {
    
    @Inject
    UserManager um;
    
    @GET
    public Response GetUsers() {
        GenericEntity<List<User>> liste = new GenericEntity<List<User>>(this.um.findAll()) {
        };
        return Response.ok(liste).build();
    }
    
    @GET
    @Path("{id}")
    public Response getOneUser(@PathParam("id") String id, @Context UriInfo uriInfo) {
        return Optional.ofNullable(um.findById(id))
                //.map(c -> Response.ok(categorie2Json(c)).build())
                .map(u -> Response.ok(u).build())
                //.orElseThrow(() -> new CategorieNotFound("Ressource non disponible "+ uriInfo.getPath()));
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }
        
    @POST
    public Response newScore(@Valid User u, @Context UriInfo uriInfo) {
        User newOne = this.um.save(u);
        String id = newOne.getId();
        URI uri = uriInfo.getAbsolutePathBuilder().path("/"+id).build();
        return Response.created(uri).build();
    }
    
    @DELETE
    @Path("{id}")
    public Response suppression(@PathParam("id") String id) {
        this.um.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
