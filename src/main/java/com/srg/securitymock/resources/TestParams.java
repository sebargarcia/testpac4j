/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.srg.securitymock.resources;

import javax.validation.constraints.NotNull;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

/**
 *
 * @author srgarcia
 */
public class TestParams {

    @QueryParam(value = "param1")
    private String param1;

    @QueryParam(value = "param2")
    private Integer param2;

    public TestParams() {
    }

    public TestParams(String param1, Integer param2) {
        this.param1 = param1;
        this.param2 = param2;
    }

    //********GETS Y SETS*******************
    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    public Integer getParam2() {
        return param2;
    }

    public void setParam2(Integer param2) {
        this.param2 = param2;
    }

}
