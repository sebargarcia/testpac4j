/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.srg.securitymock.config;

import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author srgarcia
 */
@javax.ws.rs.ApplicationPath("rest")
public class ApplicationConfig extends ResourceConfig {

    public ApplicationConfig() {
        packages("com.srg");        
    }
}
