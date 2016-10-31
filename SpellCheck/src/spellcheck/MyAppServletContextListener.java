package spellcheck;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import DiacriticsRestoration.Restoration;

public class MyAppServletContextListener implements ServletContextListener{
	static SpellChecker sp;
    static Restoration res;
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
		System.out.println("ServletContextListener destroyed");
	}

        //Run this before web application is started
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("ServletContextListener started");
		ServletContext cntxt = arg0.getServletContext();
		try {
			sp = new SpellChecker("E:/ngramdict/");
			res = new Restoration();
			
			cntxt.setAttribute("sp", sp);
			cntxt.setAttribute("res", res);
			
			System.out.println("initial data ...");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
