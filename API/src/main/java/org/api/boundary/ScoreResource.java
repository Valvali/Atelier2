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
import org.control.JsonError;

import org.api.entity.Score;
import org.provider.Secured;



/**
 *
 * @author vali
 */

@Stateless
@Path("score")
@Api(value = "API RESTful")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ScoreResource {
    final int TOP = 10; // top 10
    @Inject
    ScoreManager sm;
    
    @Inject
    SerieManager serieManager;
    @Inject
    PartieManager pm;
    
    @GET
    @ApiOperation(value = "Accès à la liste des scores")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")})
    public Response getScore() {
        JsonArrayBuilder jab = Json.createArrayBuilder();
        List<Score> allScores = this.sm.findAll();
        allScores.sort((s1, s2) -> {
            return s2.getScore() - s1.getScore();
        });
        int size = allScores.size();
        int top = size < TOP ? size : TOP;
        for (Score s: allScores.subList(0, top)) {
            JsonObjectBuilder job = Json.createObjectBuilder();
            job.add("nom", s.getNom());
            job.add("score", s.getScore());
            jab.add(job);
        }
        return Response.ok(jab.build()).build();
    }
    
    @POST
    @ApiOperation(value = "Publication d'un score apres une partie")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 401, message = "Unauthorized")})
    @Path("{token}/{serie}")
    public Response newScore(@Valid Score s, @PathParam("token") String token, @PathParam("serie") String serie, @Context UriInfo uriInfo) {
        if (! pm.isTokenValid(token, serie)) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(JsonError.error("token invalide pour cette série")).build();
        }
        s.setSerie(serieManager.findByName(serie));
        s.setId(UUID.randomUUID().toString());
        Score newOne = this.sm.save(s);
        String id = newOne.getId();
        URI uri = uriInfo.getAbsolutePathBuilder().path("/"+id).build();
        return Response.created(uri).build();
    }
    
    @DELETE
    @Secured
    @Path("{id}")
    public Response suppression(@PathParam("id") long id) {
        this.sm.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    
}
