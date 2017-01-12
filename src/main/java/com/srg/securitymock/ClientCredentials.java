/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.srg.securitymock;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author srgarcia
 */
@XmlRootElement
public class ClientCredentials {
    private String client_id = "";    
    private String secret_key = "";
    private String token = "";

    public ClientCredentials() {
        client_id = generateClientId();
        secret_key = generateClientSecret();
    }
    
    private String generateClientId() {
        return RandomGenerator.generateShortRandomString();
    }

    private String generateClientSecret() {
        return RandomGenerator.generateRandomString();
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getSecret_key() {
        return secret_key;
    }

    public void setSecret_key(String secret_key) {
        this.secret_key = secret_key;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
    
}
