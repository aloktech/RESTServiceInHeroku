/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imos.rest.test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author p
 */
public class RESTInvalidTesting {

    private static WebTarget target;
    private static Client client;

    @BeforeAll
    public static void setUp() throws Exception {
        client = ClientBuilder.newBuilder().register(MultiPartFeature.class).build();
    }

    @BeforeEach
    public void preTest() {
        target = client.target("http://localhost:8083/RESTServiceInHeroku/others");
    }

    @Test
    public void testGET() {
        Builder builder = target.request(MediaType.APPLICATION_JSON);
        Response response = builder.get();
        Assertions.assertNotNull(response);
        Assertions.assertEquals(404, response.getStatus());
    }
}
