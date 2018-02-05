/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.api.boundary;

import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 *
 * @author bertrand
 */

@Stateless
@Path("serie")
public class SerieResource {
    @GET
    public Response getSeries() {
        JsonObject json = Json.createObjectBuilder()
                .add("hello", "hello")
                .add("world", "world")
                .build();
        return Response.ok(json).build();
    }
}
