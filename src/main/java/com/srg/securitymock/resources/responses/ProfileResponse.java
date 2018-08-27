/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.srg.securitymock.resources.responses;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Seba
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ProfileResponse implements Serializable{
   
    private String id;
    private String token;           
    private Date fechaCreacion;     
    
    //******GETS y SETS********

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }   
    
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
  
}
