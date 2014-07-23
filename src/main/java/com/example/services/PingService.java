package com.example.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/ping")
public class PingService {

    @GET
    public Response get() {

        return Response.ok().build();
    }


}

