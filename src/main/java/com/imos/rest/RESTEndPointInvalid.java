/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imos.rest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.json.JSONObject;

/**
 *
 * @author p
 */
@Path("others")
public class RESTEndPointInvalid {

    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("dd/MMM/yyyy hh:mm:ss a");

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getMessage() {
        JSONObject json = new JSONObject();
        json.put("time", LocalDateTime.now().format(TIME_FORMAT));
        return json.toString(4);
    }
}
