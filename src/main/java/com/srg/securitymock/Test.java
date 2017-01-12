/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.srg.securitymock;

import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import org.pac4j.jax.rs.annotations.Pac4JSecurity;
import org.pac4j.jwt.credentials.authenticator.JwtAuthenticator;
import org.pac4j.jwt.profile.JwtGenerator;

/**
 * REST Web Service
 *
 * @author srgarcia
 */
@Path("test")
public class Test {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Test
     */
    public Test() {
    }

    /**
     * Retrieves representation of an instance of com.srg.securitymock.Test
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Pac4JSecurity(clients = "ParameterClient")
    public String getJson() {
        return "ok get";
    }

    @GET()
    @Path("credentials")
    @Produces(MediaType.APPLICATION_JSON)
    public ClientCredentials getCredentials() {
        ClientCredentials c = new ClientCredentials();
        return c;
    }

    @GET()
    @Path("token")
    @Produces(MediaType.APPLICATION_JSON)
    public ClientCredentials getToken() {
        //Aca deberiamos recuperar las credenciales desde los param
        ClientCredentials c = new ClientCredentials();        
        JwtGenerator generator = new JwtGenerator();
        Map<String, String> credentials = new HashMap<>();
        credentials.put("id", c.getClient_id());
        credentials.put("secret", c.getSecret_key());
        String token = generator.generate(credentials);
        c.setToken(token);
        
        JwtAuthenticator jwtAuthenticator = new JwtAuthenticator();
        System.out.println("Profile: " + jwtAuthenticator.validateToken(token));
        
        return c;
    }

    /**
     * PUT method for updating or creating an instance of Test
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String putJson(String content) {
        return "ok post";
    }
}
