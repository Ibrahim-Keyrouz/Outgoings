/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;


/**
 *
 * @author bk-laptop
 */
@ManagedBean(name = "reporting")
public class Reporting {

    HashMap parameters = null;
    private Connection conn = null;

    public void initConnection() {

        String HOST = "jdbc:oracle:thin:@localhost:1521:XE";
        String USERNAME = "hr";
        String PASSWORD = "remoteusers";
        try {
            Class.forName("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        try {
            conn = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
  
    
     public String showReport() throws JRException, FileNotFoundException, IOException {
      
        initConnection();
        String path = new File(".").getCanonicalPath();
        System.out.println(path);
        String reportName = "/home/oracle/NetBeansProjects/Outgoings/web/reports/Outgoings.jrxml";
        // URL url = this.getClass().getResource("Outgoings.jrxml");
        // System.out.println(url);
        JasperDesign jasperDesign = null;
        try {
              System.out.println("bob10");
            jasperDesign = JRXmlLoader.load(reportName);
              System.out.println("bob100");
            //jasperDesign.setQuery(null);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
              System.out.println("bob1000");
              
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, conn);
              System.out.println("bob1000,1");
            File file = new File("/home/oracle/Desktop/", "bob.pdf");
             System.out.println("bob1001");
            FileOutputStream out = new FileOutputStream(file);
             System.out.println("bob1002");
              String reportName1 = "/home/oracle/NetBeansProjects/Outgoings/web/reports/Outgoings.jasper";
            JasperRunManager.runReportToHtmlFile(reportName1, null, conn);
            //  out.flush();    
            //  out.close();  
            JasperExportManager.exportReportToPdfStream(jasperPrint, out);
             System.out.println("bob1003");
           
            JasperExportManager.exportReportToHtmlFile(jasperPrint, "/home/oracle/Desktop/bob.html");
              System.out.println("bob1004");
              try{

        if ((new File("/home/oracle/Desktop/bob.html")).exists()) {

           /* Process p = Runtime
               .getRuntime()
               .exec("rundll32 url.dll,FileProtocolHandler C:\\Users\\bk-laptop\\Desktop\\bob.html");
            p.waitFor();*/
            File htmlFile = new File("/home/oracle/Desktop/bob.html");
               //Desktop.getDesktop().browse(htmlFile.toURI());
       displayURL("/home/oracle/Desktop/bob.html");
      //    BrowserControl.displayURL("http://www.java-tips.org");
            
        } else {

            System.out.println("File does not exist");

        }

      } catch (Exception ex) {
        ex.printStackTrace();
      }
            /////////// Enable this code to let your default printer print this report 
            /////////////////  JasperPrintManager.printReport(jasperPrint, false);
            /////////////////////// 
            try {
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (JRException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
   
       /* String line;
		try 
		{ 
			URL url = new URL( "http://127.0.0.1:8080/Outgoings/Report" ); 
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream())); 
			line = in.readLine(); 

			System.out.println( line );	

			in.close(); 
		}
		catch (Exception e)
		{ 
			e.printStackTrace(); 
		}*/

 //   p.addParameter("TEST", "VALUE");

  //  System.out.println(p);

   /* URL gwtServlet = null;
    try {


        gwtServlet = new URL("http://localhost:8080/Outgoings/Report");
        HttpURLConnection servletConnection = (HttpURLConnection) gwtServlet.openConnection();
        servletConnection.setRequestMethod("GET");
        servletConnection.setDoOutput(true);
        
        ObjectOutputStream objOut = new ObjectOutputStream(servletConnection.getOutputStream());
      //  objOut.writeObject(p);
          
        objOut.flush();
       objOut.close();
        InputStream response = servletConnection.getInputStream();
        
    } catch (MalformedURLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }*/
        
     /*    String sessionCookie = null;
          System.out.println(1);
    URL url = new java.net.URL("http://localhost:8080/Outgoings/Report");
     System.out.println(2);
      URLConnection con = url.openConnection();
      if (con == null) {
           System.out.println(69);
      }
     System.out.println(3);
    if (sessionCookie != null) {
         System.out.println(4);
      con.setRequestProperty("cookie", sessionCookie);
    }
     System.out.println(5);
    con.setUseCaches(false);
    con.setDoOutput(true);
    con.setDoInput(true);
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
     System.out.println(6);
    DataOutputStream out = new DataOutputStream(byteOut);
    out.flush();
     System.out.println(7);
    byte buf[] = byteOut.toByteArray();
    con.setRequestProperty("Content-type", "application/octet-stream");
    con.setRequestProperty("Content-length", "" + buf.length);
    DataOutputStream dataOut = new DataOutputStream(con.getOutputStream());
     System.out.println(8);
    dataOut.write(buf);
    dataOut.flush();
    dataOut.close();
     System.out.println(9);
    if (con == null) {
           System.out.println(69);
      }
    DataInputStream in = new DataInputStream(con.getInputStream());
     System.out.println(in);
    int count = in.readInt();
     System.out.println(12);
    in.close();
     System.out.println(10);
    if (sessionCookie == null) {
      String cookie = con.getHeaderField("set-cookie");
       System.out.println(11);
      if (cookie != null) {
        sessionCookie = parseCookie(cookie);
        System.out.println("Setting session ID=" + sessionCookie);
      }
    }

    System.out.println(count);*/
        
   /*     URL servlet;
try {
servlet = new URL("http://localhost:8080/Outgoings/Report");
HttpURLConnection servletConnection = (HttpURLConnection) servlet.openConnection();
System.out.println(1);
URLConnection conn;
conn = servlet.openConnection();
System.out.println(2);
servletConnection.setDoOutput(true);
 System.out.println(3);
BufferedWriter out = new BufferedWriter(new OutputStreamWriter(servletConnection.getOutputStream()));
System.out.println(4);
out.write("xml=first cut");
 System.out.println(5);
InputStreamReader isr = new InputStreamReader(servletConnection.getInputStream());
System.out.println(6);
BufferedReader br = new BufferedReader(isr);
System.out.println(7);
String str = new String();
System.out.println(str);
str = br.readLine();
System.out.println(str);
InputStream response = servletConnection.getInputStream();
response.read();
} catch (MalformedURLException ex) {
ex.printStackTrace();
} catch (IOException ex) {
ex.printStackTrace();
}*/
   
        
 public void showReport1() throws MalformedURLException, IOException {

FacesContext context = FacesContext.getCurrentInstance();
try{
ExternalContext exContext = context.getExternalContext();
exContext.redirect("http://192.168.10.107:8080/Outgoings/Report");

}catch
(Exception e) {
e.printStackTrace();
}finally{
context.responseComplete();
}


}
    
    /* public static String parseCookie(String raw) {
    String c = raw;

    if (raw != null) {
      int endIndex = raw.indexOf(";");
      if (endIndex >= 0) {
        c = raw.substring(0, endIndex);
      }
    }
    return c;
  }*/
    
    
    
    // Used to identify the windows platform.
    private static final String WIN_ID = "Windows";
    // The default system browser under windows.
    private static final String WIN_PATH = "rundll32";
    // The flag to display a url.
    private static final String WIN_FLAG = "url.dll,FileProtocolHandler";
    // The default browser under unix.
    private static final String UNIX_PATH = "netscape";
    // The flag to display a url.
    private static final String UNIX_FLAG = "-remote openURL";
    
    
       
    
    /**
     * Display a file in the system browser.  If you want to display a
     * file, you must include the absolute path name.
     *
     * @param url the file's url (the url must start with either "http://"
     * or
     * "file://").
     */
    public static void displayURL(String url) {
        boolean windows = isWindowsPlatform();
        String cmd = null;
        try {
            if (windows) {
                // cmd = 'rundll32 url.dll,FileProtocolHandler http://...'
                cmd = WIN_PATH + " " + WIN_FLAG + " " + url;
                Process p = Runtime.getRuntime().exec(cmd);
            } else {
                // Under Unix, Netscape has to be running for the "-remote"
                // command to work.  So, we try sending the command and
                // check for an exit value.  If the exit command is 0,
                // it worked, otherwise we need to start the browser.
                // cmd = 'netscape -remote openURL(http://www.java-tips.org)'
                cmd = UNIX_PATH + " " + UNIX_FLAG + "(" + url + ")";
                Process p = Runtime.getRuntime().exec(cmd);
                try {
                    // wait for exit code -- if it's 0, command worked,
                    // otherwise we need to start the browser up.
                    int exitCode = p.waitFor();
                    if (exitCode != 0) {
                        // Command failed, start up the browser
                        // cmd = 'netscape http://www.java-tips.org'
                        cmd = UNIX_PATH + " "  + url;
                        p = Runtime.getRuntime().exec(cmd);
                    }
                } catch(InterruptedException x) {
                    System.err.println("Error bringing up browser, cmd='" +
                            cmd + "'");
                    System.err.println("Caught: " + x);
                }
            }
        } catch(IOException x) {
            // couldn't exec browser
            System.err.println("Could not invoke browser, command=" + cmd);
            System.err.println("Caught: " + x);
        }
    }
    /**
     * Try to determine whether this application is running under Windows
     * or some other platform by examing the "os.name" property.
     *
     * @return true if this application is running under a Windows OS
     */
    public static boolean isWindowsPlatform() {
        String os = System.getProperty("os.name");
        if ( os != null && os.startsWith(WIN_ID))
            return true;
        else
            return false;
        
    }
    
   
    
    

}
