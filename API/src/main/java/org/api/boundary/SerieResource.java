/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.api.boundary;

import java.net.URI;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.api.entity.Serie;

/**
 *
 * @author bertrand
 */
@Stateless
@Path("serie")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SerieResource {
    
    @Inject
    SerieManager sm;
    
    @GET
    public Response getSeriesNames() {
        JsonArrayBuilder jab = Json.createArrayBuilder();
        for (Serie s: this.sm.getAllSeries()) {
            jab.add(s.getLieu());
        }
        return Response.ok(jab.build()).build();
    }
    
    @POST
    // TODO protéger
    public Response newSerie(@Valid Serie s, @Context UriInfo uriInfo) {
        String id = this.sm.save(s).getId();
        URI uri = uriInfo.getAbsolutePathBuilder().path("/" + id).build();
        return Response.created(uri).build();
    }
}
