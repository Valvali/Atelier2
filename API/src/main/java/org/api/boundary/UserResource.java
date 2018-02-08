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
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
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

import org.api.entity.User;

/**
 *
 * @author vali
 */
@Stateless
@Path("user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {
    
    @Inject
    UserManager um;
    
    @GET
    public Response GetUsers() {
        JsonArrayBuilder jab = Json.createArrayBuilder();
        for (User u: this.um.findAll()) {
            jab.add(u.getNom());
            jab.add(u.getPrenom());
            jab.add(u.getMail());
        }
        return Response.ok(jab.build()).build();
    }
    
    @GET
    @Path("{id}")
    public Response getOneUser(@PathParam("id") String id, @Context UriInfo uriInfo) {
        User u = um.findById(id);
        JsonObjectBuilder job = Json.createObjectBuilder();
        job.add("nom", u.getNom());
        job.add("prenom", u.getPrenom());
        job.add("mail", u.getMail());
        return Response.ok(job.build()).build();
    }
        
    @POST
    public Response newUser(@Valid User u, @Context UriInfo uriInfo) {
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
