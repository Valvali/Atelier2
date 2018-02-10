package org.provider;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;

@Provider
@PreMatching
public class CORSResponseFilter implements ContainerResponseFilter {

    private final static Logger log = Logger.getLogger(CORSResponseFilter.class.getName());

    @Override
    public void filter(ContainerRequestContext requestCtx, ContainerResponseContext responseCtx)
            throws IOException {
        responseCtx.getHeaders().add("Access-Control-Allow-Headers", "Content-Type, Origin, Accept");
        responseCtx.getHeaders().add("Access-Control-Allow-Origin", "*");
        responseCtx.getHeaders().add("Access-Control-Allow-Credentials", "true");
        responseCtx.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE ");
        responseCtx.getHeaders().add("Access-Control-Allow-Headers", "Content-Type, Origin, Accept, authorization, cache-control, x-requested-with");
        responseCtx.getHeaders().add("Access-Control-Allow-Credentials", "true");
        responseCtx.getHeaders().add("Access-Control-Expose-Headers", "authorization");
    }

}
