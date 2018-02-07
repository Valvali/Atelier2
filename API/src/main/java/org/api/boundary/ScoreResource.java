/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.api.boundary;

import java.net.URI;
import java.util.List;
import java.util.UUID;
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

import org.api.entity.Score;



/**
 *
 * @author vali
 */

@Stateless
@Path("score")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ScoreResource {
    final int TOP = 10; // top 10
    @Inject
    ScoreManager sm;
    
    @Inject
    SerieManager serieManager;
    
    @GET
    public Response getScore() {
        JsonArrayBuilder jab = Json.createArrayBuilder();
        List<Score> allScores = this.sm.findAll();
        allScores.sort((s1, s2) -> {
            return s2.getScore() - s1.getScore();
        });
        for (Score s: allScores.subList(0, TOP)) {
            JsonObjectBuilder job = Json.createObjectBuilder();
            job.add("nom", s.getNom());
            job.add("score", s.getScore());
            jab.add(job);
        }
        return Response.ok(jab.build()).build();
    }
    
    @POST
    @Path("{token}/{serie}")
    public Response newScore(@Valid Score s, @PathParam("token") String token, @PathParam("serie") String serie, @Context UriInfo uriInfo) {
        s.setSerie(serieManager.findByName(serie));
        s.setId(UUID.randomUUID().toString());
        Score newOne = this.sm.save(s);
        String id = newOne.getId();
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
