/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.srg.securitymock;

import java.util.Set;
import org.glassfish.jersey.server.ResourceConfig;
import org.pac4j.core.config.Config;
import org.pac4j.core.credentials.TokenCredentials;
import org.pac4j.http.authorization.authorizer.IpRegexpAuthorizer;
import org.pac4j.http.client.direct.IpClient;
import org.pac4j.http.client.direct.ParameterClient;
import org.pac4j.http.credentials.authenticator.IpRegexpAuthenticator;
import org.pac4j.http.credentials.extractor.IpExtractor;
import org.pac4j.jax.rs.features.Pac4JSecurityFeature;
import org.pac4j.jax.rs.jersey.features.Pac4JValueFactoryProvider;
import org.pac4j.jax.rs.servlet.features.ServletJaxRsContextFactoryProvider;
import org.pac4j.jwt.credentials.authenticator.JwtAuthenticator;

/**
 *
 * @author srgarcia
 */
@javax.ws.rs.ApplicationPath("rest")
public class ApplicationConfig extends ResourceConfig {

    public ApplicationConfig() {
        packages("com.srg");
        Config config = this.initConfigSecurity();
        this.register(new ServletJaxRsContextFactoryProvider(config))
                .register(new Pac4JSecurityFeature(config))
                .register(new Pac4JValueFactoryProvider.Binder());
    }

    public Config initConfigSecurity() {
        
        JwtAuthenticator jwtAuthenticator = new JwtAuthenticator();
        
        
        ParameterClient parameterClient = new ParameterClient("token", jwtAuthenticator);
        parameterClient.setSupportGetRequest(true);
        parameterClient.setSupportPostRequest(false);

        
        
        Config config = new Config(parameterClient);
        
        return config;
    }

    /*@Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }*/
    /**
     * Do not modify addRestResourceClasses() method. It is automatically
     * populated with all resources defined in the project. If required, comment
     * out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.srg.securitymock.Test.class);
    }

}
