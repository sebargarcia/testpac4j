/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.srg.securitymock.config;


import org.pac4j.core.config.Config;
import org.pac4j.core.credentials.authenticator.Authenticator;
import org.pac4j.http.client.direct.HeaderClient;
import org.pac4j.http.client.direct.IpClient;
import org.pac4j.http.client.direct.ParameterClient;
import org.pac4j.jwt.config.signature.SecretSignatureConfiguration;
import org.pac4j.jwt.credentials.authenticator.JwtAuthenticator;
import org.pac4j.http.credentials.authenticator.IpRegexpAuthenticator;

/**
 *
 * @author Seba
 */
public class ConfigSecurity {

    public Config getConfig() {
        IpClient ipClient = new IpClient(this.initIpAuthenticator()); 

        ParameterClient paramClient = new ParameterClient();
        paramClient.setSupportGetRequest(true);
        paramClient.setSupportPostRequest(true);
        paramClient.setParameterName("token");
        paramClient.setAuthenticator(this.initJWTAuthenticator());
        
        HeaderClient headerClient = new HeaderClient();
        headerClient.setAuthenticator(this.initJWTAuthenticator());
        headerClient.setHeaderName("Auth");
        

        Config config = new Config(ipClient, paramClient, headerClient);
        return config;
    } 
    
    public Authenticator initIpAuthenticator(){
        return new IpRegexpAuthenticator("0:0:0:0:0:0:0:1");
    }
    

    public Authenticator initJWTAuthenticator() {
        JwtAuthenticator jwtAuthenticator = new JwtAuthenticator();
        jwtAuthenticator.addSignatureConfiguration(new SecretSignatureConfiguration("YHp5kzYPnsMl5uBlBKDgfqF4q9Ux83UT"));
        return jwtAuthenticator;
    }   

}
