/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imos.rest.test;

import java.io.File;
import java.io.IOException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author p
 */
public class RESTTesting {

    private static WebTarget target;
    private static Client client;

    @BeforeAll
    public static void setUp() throws Exception {
        client = ClientBuilder.newBuilder().register(MultiPartFeature.class).build();
    }

    @BeforeEach
    public void preTest() {
        target = client.target("http://localhost:8083/RESTServiceInHeroku/rest");
    }

    @Test
    public void testGET() {
        Builder builder = target.request(MediaType.APPLICATION_JSON);
        String responseMsg = builder.get(String.class);
        System.out.println(responseMsg);
    }

    @Test
    public void testStringPOST() throws IOException {
        target = target.path("string");

        final FormDataMultiPart mp = new FormDataMultiPart();
        final FormDataBodyPart p = new FormDataBodyPart(FormDataContentDisposition.name("part").fileName("part").build(), "CONTENT");
        mp.bodyPart(p);

        final String s = target.request().post(Entity.entity(mp, MediaType.MULTIPART_FORM_DATA_TYPE), String.class);
        System.out.println(s);
    }

    @Test
    public void testFilePOST() throws IOException {
        target = target.path("file");
        Builder builder = target.request();
        FileDataBodyPart filePart = new FileDataBodyPart("file",
                new File("src/main/resources/SampleImage.jpg"));
        filePart.setContentDisposition(
                FormDataContentDisposition.name("file")
                        .fileName("SampleImage.jpg").build());
        MultiPart multipartEntity = new FormDataMultiPart()
                .bodyPart(filePart);
        Response res = builder.post(Entity.entity(multipartEntity, multipartEntity.getMediaType()));
        System.out.println(res.readEntity(String.class));
        
        builder = target.request();
        filePart = new FileDataBodyPart("file",
                new File("src/main/resources/SamplePdf.pdf"));
        filePart.setContentDisposition(
                FormDataContentDisposition.name("file")
                        .fileName("SamplePdf.pdf").build());
        multipartEntity = new FormDataMultiPart()
                .bodyPart(filePart);
        res = builder.post(Entity.entity(multipartEntity, multipartEntity.getMediaType()));
        System.out.println(res.readEntity(String.class));
    }
}
