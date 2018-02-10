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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.api.entity.Partie;
import org.api.entity.Point;
import org.api.entity.Serie;
import org.control.JsonError;

/**
 *
 * @author bertrand
 */

@Stateless
@Path("partie")
@Api(value = "API RESTful")
public class PartieResource {

    @Inject
    PartieManager pm;
    
    @Inject
    SerieManager sm;
    
    
    @POST
    @ApiOperation(value = "Rêcupêrer une partie")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 401, message = "Unauthorized")})
    @Path("{serie}/{difficulte}")
    public Response getPartie(@PathParam("serie") String nomSerie, @PathParam("difficulte") int difficulte, @Context UriInfo uriInfo) {
        
        // randomUUID() utilise SecureRandom et 122bits de random, ce qui est suffisant
        String token = UUID.randomUUID().toString();
        
        Serie serie = sm.findByName(nomSerie);
        if (serie == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(JsonError.error("Cette série n'existe pas")).build();

        }
        
        JsonObjectBuilder ret = Json.createObjectBuilder();
        JsonArrayBuilder pointsJSON = Json.createArrayBuilder();
        List<Point> points = pm.nouvellePartie(nomSerie, difficulte);
        if (points == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(JsonError.error("Pas assez de POI pour cette difficultê")).build();
        }
        for (Point p : points) {
            JsonObjectBuilder pointJSON = Json.createObjectBuilder();
            pointJSON.add("lat", p.getLat());
            pointJSON.add("lng", p.getLng());
            pointJSON.add("img", p.getImg());
            pointJSON.add("description", p.getDescription());
            pointJSON.add("difficulte", p.getDifficulte());
            pointsJSON.add(pointJSON);
        }
        ret.add("token", token);
        ret.add("points", pointsJSON);
        
        Partie partie = new Partie();
        partie.setDifficulte(difficulte);
        partie.setScore(-1);
        partie.setSerie(serie);
        partie.setToken(token);
        
        pm.save(partie);
        
        return Response.ok(ret.build()).build();
    }
}
