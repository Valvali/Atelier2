/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.api.boundary;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

import org.api.entity.Utilisateur;
import org.provider.Secured;

/**
 *
 * @author vali
 */
@Stateless
@Path("user")
@Api(value = "API RESTful")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class UtilisateurResource {
    
    @Inject
    UtilisateurManager um;
    
    @GET
    @Secured
    public Response GetUsers() {
        JsonArrayBuilder jab = Json.createArrayBuilder();
        for (Utilisateur u: this.um.findAll()) {
            jab.add(u.getNom());
            jab.add(u.getMail());
        }
        return Response.ok(jab.build()).build();
    }
    
    @GET
    @Secured
    @Path("{id}")
    public Response getOneUser(@PathParam("id") String id, @Context UriInfo uriInfo) {
        Utilisateur u = um.findById(id);
        JsonObjectBuilder job = Json.createObjectBuilder();
        job.add("nom", u.getNom());
        job.add("mail", u.getMail());
        return Response.ok(job.build()).build();
    }
        
    @POST
    @ApiOperation(value = "S'inscrire")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")})
    public Response newUtilisateur(@Valid Utilisateur u, @Context UriInfo uriInfo) {
        Utilisateur newOne = this.um.save(u);

        String id = newOne.getId();
        URI uri = uriInfo.getAbsolutePathBuilder().path("/"+id).build();
        return Response.created(uri).build();
    }
        
    @DELETE
    @Secured
    @Path("{id}")
    public Response suppression(@PathParam("id") String id) {
        this.um.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
