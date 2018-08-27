/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.srg.securitymock.config;

import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;
import org.pac4j.core.config.Config;
import org.pac4j.jax.rs.features.JaxRsConfigProvider;
import org.pac4j.jax.rs.features.Pac4JSecurityFeature;
import org.pac4j.jax.rs.jersey.features.Pac4JValueFactoryProvider;
import org.pac4j.jax.rs.servlet.features.ServletJaxRsContextFactoryProvider;

@Provider
public class Pac4JFeature implements Feature {

    @Override
    public boolean configure(FeatureContext context) {
        ConfigSecurity configSecurity = new ConfigSecurity();
        Config config = configSecurity.getConfig();
        
        context
            .register(new JaxRsConfigProvider(config))
            .register(new Pac4JSecurityFeature())
            .register(new Pac4JValueFactoryProvider.Binder()) // only with Jersey            
            .register(new ServletJaxRsContextFactoryProvider());
        
        return true;
    }
}
