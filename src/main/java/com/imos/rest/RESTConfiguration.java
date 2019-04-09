/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imos.rest;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author p
 */
@ApplicationPath("rest")
public class RESTConfiguration extends ResourceConfig {

    public RESTConfiguration() {
        packages("com.imos.rest");
        register(MultiPartFeature.class);
    }
    
}
