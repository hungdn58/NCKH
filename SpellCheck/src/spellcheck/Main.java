/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spellcheck;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.text.BadLocationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import DiacriticsRestoration.*;

import javax.annotation.Resource;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author TUNG
 */
@Path("ConversionService")
public class Main {

	private ServletContext context; 
	static HttpServletRequest req;
	public SpellChecker sp = null;
    public Restoration res = null;
	
    private double runningTime = 0;
    private boolean True;
    
    @Context
    public void setServletContext(ServletContext context) {
	    System.out.println("servlet context set here");
	    this.context = context;
	    sp = (SpellChecker) context.getAttribute("sp");
	    res = (Restoration) context.getAttribute("res");
    }
	
	@GET
    @Path("/checkSpelling/{i}")
     @Produces(MediaType.TEXT_XML)
     public String checkSpelling(@PathParam("i") String in) throws BadLocationException {
		if(sp == null) {
			System.out.println("null ma ...");
		}
		String inputText = in;
		String result = "";
		String output = "";

        if (inputText.length() > 0) {
            //System.out.println(inputText);
            String[] lines = inputText.split("\n");
//            System.out.println("lines length " + lines.length);
            for (int i = 0; i < lines.length; i++) {
//                System.out.println(lines[0]);
                try {
                    long start = System.currentTimeMillis();
                    //lines[i] = res.restoration(lines[i]);
                    lines[i] = res.restoration(lines[i]);
                    result += sp.processSentence(lines[i]) + "\n";
                    System.out.println("Result ..." + result);
                    long end = System.currentTimeMillis();
                    this.runningTime = (end - start)*1.0/1000;
                    System.out.println("runningTime ..." + runningTime);
                } catch (Exception ex) {
                    Logger.getLogger(SpellcheckUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            output = "<CheckSpelling>"
			        + "<Result>" + result + "</Result>"
			        + "<ProcessTime>" + Double.toString(runningTime) + "</ProcessTime>"
			        + "</CheckSpelling>";
        } else {
//            System.out.println("empty input");
        	output = "k biet loi gi!";
        }
        
        return output;
     }
	
	@Path("/check")
	@POST
    @Produces(MediaType.TEXT_XML)
	 public String checkGrammar(String word)
	 {
		String inputText = word;
		String result = "";
		String output = "";

        if (inputText.length() > 0) {
            //System.out.println(inputText);
            String[] lines = inputText.split("\n");
//            System.out.println("lines length " + lines.length);
            for (int i = 0; i < lines.length; i++) {
//                System.out.println(lines[0]);
                try {
                    long start = System.currentTimeMillis();
                    //lines[i] = res.restoration(lines[i]);
                    lines[i] = res.restoration(lines[i]);
                    result += sp.processSentence(lines[i]) + "\n";
                    long end = System.currentTimeMillis();
                    this.runningTime = (end - start)*1.0/1000;
                } catch (Exception ex) {
                    Logger.getLogger(SpellcheckUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            output = "<CheckSpelling>"
			        + "<Result>" + result + "</Result>"
			        + "<ProcessTime>" + Double.toString(runningTime) + "</ProcessTime>"
			        + "</CheckSpelling>";
        } else {
//            System.out.println("empty input");
        	output = "k biet loi gi!";
        }
        
        return output;
	 }

     @Path("/FeetToInch/{f}")
     @GET
     @Produces(MediaType.TEXT_XML)
     public String convertFeetToInch(@PathParam("f") int f) {
      int inch=0;
         int feet = f;
         inch = 12*feet;
  
         return "<FeetToInchService>"
           + "<Feet>" + feet + "</Feet>"
           + "<Inch>" + inch + "</Inch>"
           + "</FeetToInchService>";
     }

    /**
     * @param args the command line arguments
     */
    static void funcTest(StringBuilder input) {
//        input.replace(0,input.length(),"good");
        StringBuilder t = new StringBuilder("good");
        input = t;
    }

    static void listTest(List<String> list) {
        list.add("ha");
        list.add("haha");
        list.add("hahaha");
    }

    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        Runtime runtime = Runtime.getRuntime();

        long startTime = System.currentTimeMillis();


        try {

            SpellcheckUI ui = new SpellcheckUI();
            ui.setTitle("Vietnamese spell checking");
            ui.setVisible(true);

            long endTime = System.currentTimeMillis();
            long totalTime = endTime - startTime;
//        System.out.println("memory usage: "+allocatedMemory/1024);
            //System.out.println(totalTime + " ms");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
 
    }
}
