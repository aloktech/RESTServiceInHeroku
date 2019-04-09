/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imos.rest;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.JSONObject;

/**
 *
 * @author p
 */
@Path("")
public class RESTEndPoint {

    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("dd/MMM/yyyy hh:mm:ss a");
    
    @Inject
    private RESTService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getMessage() {
        JSONObject json = new JSONObject();
        json.put("time", LocalDateTime.now().format(TIME_FORMAT));
        return json.toString(4);
    }

    @POST
    @Path("string")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public String postString(
            @FormDataParam("part") String s,
            @FormDataParam("part") FormDataContentDisposition d) {
        return s + ":" + d.getFileName();
    }

    @POST
    @Path("file")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public String postFile(
            @FormDataParam("file") InputStream inputStream,
            @FormDataParam("file") FormDataContentDisposition d) {
        service.saveFile(d, inputStream);
        return d.getName() + ":" + d.getFileName();
    }
}
