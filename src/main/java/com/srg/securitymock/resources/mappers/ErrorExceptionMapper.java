/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.srg.securitymock.resources.mappers;


import com.srg.securitymock.resources.responses.GenericErrorResponse;
import java.util.Arrays;
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Variant;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.pac4j.core.exception.TechnicalException;

/**
 *
 * @author Seba
 */
@Provider
public class ErrorExceptionMapper implements ExceptionMapper<Exception> {

    @Context
    private javax.inject.Provider<Request> request;

    @Override
    public Response toResponse(Exception exception) {
        exception.printStackTrace();
        
        Response.ResponseBuilder response = Response.status(Response.Status.INTERNAL_SERVER_ERROR);
        //Generic response created for us.
        GenericErrorResponse rc = new GenericErrorResponse();
        
        if (exception instanceof TechnicalException) {
            response = Response.status(Response.Status.UNAUTHORIZED);
            rc.setStatus(Response.Status.UNAUTHORIZED.getStatusCode());
            rc.setStatusDescription(Response.Status.UNAUTHORIZED.name());
        } else if (exception instanceof BadRequestException) {
            response = Response.status(Response.Status.BAD_REQUEST);
            rc.setStatus(Response.Status.BAD_REQUEST.getStatusCode());
            rc.setStatusDescription(Response.Status.BAD_REQUEST.name());
        } else if (exception instanceof NotAuthorizedException) {
            response = Response.status(Response.Status.UNAUTHORIZED);
            rc.setStatus(Response.Status.UNAUTHORIZED.getStatusCode());
            rc.setStatusDescription(Response.Status.UNAUTHORIZED.name());
        } else if (exception instanceof NotFoundException) {
            response = Response.status(Response.Status.NOT_FOUND);
            rc.setStatus(Response.Status.NOT_FOUND.getStatusCode());
            rc.setStatusDescription(Response.Status.NOT_FOUND.name());
        } else {
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR);
            rc.setStatus(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
            rc.setStatusDescription(Response.Status.INTERNAL_SERVER_ERROR.name());
        }

        // Entity
        final List<Variant> variants = Variant.mediaTypes(
                MediaType.APPLICATION_JSON_TYPE,
                MediaType.APPLICATION_XML_TYPE
        ).build();
        final Variant variant = request.get().selectVariant(variants);
        if (variant != null) {
            response.type(variant.getMediaType());
        } else {
            /*
                 * default media type which will be used only when none media type from {@value variants} is in
                 * accept header of original request.
             */
            response.type(MediaType.TEXT_PLAIN_TYPE);
        }

        if (exception instanceof TechnicalException && exception.getMessage()
                .equals("Cannot decrypt / verify JWT")) {
            rc.setErrors(Arrays.asList("Token inválido. No se pudo verificar el token"));
        } else {
            rc.setErrors(Arrays.asList(exception.getMessage()));
        }

        response.entity(
                new GenericEntity<GenericErrorResponse>(
                        rc,
                        new GenericType<GenericErrorResponse>() {
                        }.getType()
                )
        );
        return response.build();
    }

}
