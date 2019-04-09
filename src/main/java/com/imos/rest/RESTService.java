/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imos.rest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

/**
 *
 * @author p
 */
@ApplicationScoped
public class RESTService {

    public void saveFile(FormDataContentDisposition d, InputStream inputStream) {
        String filePath = System.getProperty("user.dir") + File.separator + ".." + File.separator + "temp" + File.separator + d.getFileName();
        File file = new File(filePath);
        try (FileOutputStream stream = new FileOutputStream(file)) {
            byte[] data = new byte[1024];
            while (inputStream.read(data) != -1) {
                stream.write(data);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RESTEndPoint.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RESTEndPoint.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
