/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.api.boundary;

import java.net.URI;
import java.util.List;
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

import org.api.entity.Score;


/**
 *
 * @author vali
 */

@Stateless
@Path("scores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ScoreResource {
    @Inject
    ScoreManager sm;
    
    @GET
    public Response getPoints() {

        GenericEntity<List<Score>> liste = new GenericEntity<List<Score>>(this.sm.findAll()) {
        };
        return Response.ok(liste).build();
    }
    
    @POST
    public Response newPoint(@Valid Score s, @Context UriInfo uriInfo) {
        Score newOne = this.sm.save(s);
        long id = newOne.getId();
        URI uri = uriInfo.getAbsolutePathBuilder().path("/"+id).build();
        return Response.created(uri).build();
    }
    
    @DELETE
    @Path("{id}")
    public Response suppression(@PathParam("id") long id) {
        this.sm.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    
}
