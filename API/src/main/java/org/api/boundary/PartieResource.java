/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.api.boundary;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.api.entity.Point;

/**
 *
 * @author bertrand
 */

@Stateless
@Path("partie")
public class PartieResource {
//    @GET
//    public Response getSeries() {
//        JsonObject pointJSON = Json.createObjectBuilder()
//                .add("hello", "hello")
//                .add("world", "world")
//                .build();
//        return Response.ok(pointJSON).build();
//    }
    @GET
    @Path("{nom}")
    public Response getPartie(@PathParam("nom") String nom, @Context UriInfo uriInfo) {
        JsonObjectBuilder ret = Json.createObjectBuilder();
        JsonArrayBuilder pointsJSON = Json.createArrayBuilder();
        Collection<Point> points = new ArrayList<>(); // TODO
        for (Point p : points) {
            JsonObjectBuilder pointJSON = Json.createObjectBuilder();
            pointJSON.add("lat", p.getLat());
            pointJSON.add("lng", p.getLng());
            pointJSON.add("img", p.getImg());
            pointJSON.add("description", p.getDescription());
            pointJSON.add("difficulte", p.getDifficulte());
            pointsJSON.add(pointJSON);
        }
        // randomUUID() utilise SecureRandom et 122bits de random, ce qui est suffisant
        ret.add("token", UUID.randomUUID().toString());
        ret.add("points", pointsJSON);
        return Response.ok(ret.build()).build();
    }
}
