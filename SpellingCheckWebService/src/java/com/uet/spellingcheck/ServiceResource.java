/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uet.spellingcheck;

import DiacriticsRestoration.Restoration;
import com.uet.spellingcheck.spellcheck.SpellChecker;
import com.uet.spellingcheck.spellcheck.SpellcheckUI;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.BadLocationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * REST Web Service
 *
 * @author hoang
 */
@Path("service")
public class ServiceResource {
    Runtime runtime = Runtime.getRuntime();

    long startTime = System.currentTimeMillis();
    static SpellChecker sp;
    static Restoration res;


    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ServiceResource
     */
    public ServiceResource() {
        try {
            sp = new SpellChecker("E:/ngramdict/");
//            res = new Restoration();
        } catch (Exception ex) {
            Logger.getLogger(ServiceResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Retrieves representation of an instance of com.uet.spellingcheck.ServiceResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getHtml() {
        String text = "";
        try {
            //TODO return proper representation object
            Document doc = Jsoup.connect("http://jsoup.org").get();
            text = doc.body().text();
        } catch (IOException ex) {
            Logger.getLogger(ServiceResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return text;
//        throw new UnsupportedOperationException();
    }   

    /**
     * PUT method for updating or creating an instance of ServiceResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.TEXT_HTML)
    public void putHtml(String content) {
    }
    
    @Path("check")
    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Produces("text/plain")
    public String postHandler(@FormParam("url") String url) {
        String text = "";
        
        try {
            //TODO return proper representation object

            Document doc = Jsoup.connect(url).get();
            text = doc.body().text();
      
        } catch (IOException ex) {
            Logger.getLogger(ServiceResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return checkSpelling(text);
    }
    
    private String checkSpelling(String content) {                                         
        // TODO add your handling code here:
        //String inputText = this.jTextArea1.getText();
        String output = "";
        double runningTime = 0.0;
        if (content.length() > 0) {
            //System.out.println(inputText);
            String[] lines = content.split("\n");
//            System.out.println("lines length " + lines.length);
            String result = "";
            for (int i = 0; i < lines.length; i++) {
//                System.out.println(lines[0]);
                try {
                    long start = System.currentTimeMillis();
                    //lines[i] = res.restoration(lines[i]);
//                    if(this.jRadioButton2.isSelected()){
//                        lines[i] = res.restoration(lines[i]);
//                    }
                    result += sp.processSentence(lines[i]) + "\n";
                    long end = System.currentTimeMillis();
                    runningTime = (end - start)*1.0/1000;
                } catch (Exception ex) {
                    Logger.getLogger(SpellcheckUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            output = result + " - " + runningTime;
            //System.out.println(result);
        } else {
            output = "empty input";
        }
//        String[] split = inputText.split("\n");
//        for(int i = 0;i < split.length;i++)
//            System.out.println(split[i]);
//        System.out.println(inputText);
        return output;
    }  
}
