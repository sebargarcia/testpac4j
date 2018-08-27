/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.srg.securitymock.resources;

import com.srg.securitymock.resources.responses.GenericErrorResponse;
import com.srg.securitymock.resources.responses.ProfileResponse;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.pac4j.http.profile.IpProfile;
import org.pac4j.jax.rs.annotations.Pac4JProfile;
import org.pac4j.jax.rs.annotations.Pac4JSecurity;
import org.pac4j.jwt.config.signature.SecretSignatureConfiguration;
import org.pac4j.jwt.credentials.authenticator.JwtAuthenticator;
import org.pac4j.jwt.profile.JwtGenerator;

/**
 * REST Web Service
 *
 * @author srgarcia
 */
@Path("auth")
public class TestResource {

    @Context
    private UriInfo context;

    public TestResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Pac4JSecurity(clients = "IpClient")
    public Response getTokenWithIpClient(@Pac4JProfile IpProfile profile) {
        System.out.println("Prfile: " + profile.toString());
        ProfileResponse r = generateToken(profile);
        return Response.status(200).entity(r).build();
    }

    @GET()
    @Path("profile")
    @Produces(MediaType.APPLICATION_JSON)
    @Pac4JSecurity(clients = "ParameterClient, HeaderClient")
    public ProfileResponse getProfile(@Pac4JProfile IpProfile profile) {
        ProfileResponse p = this.generateToken(profile);
        return p;
    }

    public ProfileResponse generateToken(IpProfile profile) {
        //Tenemos el profile del usuario por lo que podemos generar el token.         
        JwtGenerator<IpProfile> generator = new JwtGenerator<>();
        generator.setSignatureConfiguration(new SecretSignatureConfiguration("YHp5kzYPnsMl5uBlBKDgfqF4q9Ux83UT"));

        String token = generator.generate(profile);
        //Generamos la respose y mandamos el token       
        ProfileResponse r = new ProfileResponse();
        r.setId(profile.getId());
        r.setToken(token);
        r.setFechaCreacion(new Date());
        return r;
    }

}
