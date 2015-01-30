/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import javax.faces.bean.ManagedBean;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
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
        String reportName = "C:\\Users\\bk-laptop\\Documents\\NetBeansProjects\\Outgoings\\web\\reports\\Outgoings.jrxml";
        // URL url = this.getClass().getResource("Outgoings.jrxml");
        // System.out.println(url);
        JasperDesign jasperDesign = null;
        try {
            jasperDesign = JRXmlLoader.load(reportName);
            //jasperDesign.setQuery(null);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, conn);
            File file = new File("C:\\Users\\bk-laptop\\Desktop\\", "bob.pdf");
            FileOutputStream out = new FileOutputStream(file);
            // JasperRunManager.runReportToHtmlFile(reportName, null, conn);
            //  out.flush();    
            //  out.close();  
            JasperExportManager.exportReportToPdfStream(jasperPrint, out);
            JasperExportManager.exportReportToHtmlFile(jasperPrint, "C:\\Users\\bk-laptop\\Desktop\\bob.html");
              try{

        if ((new File("C:\\Users\\bk-laptop\\Desktop\\bob.html")).exists()) {

           /* Process p = Runtime
               .getRuntime()
               .exec("rundll32 url.dll,FileProtocolHandler C:\\Users\\bk-laptop\\Desktop\\bob.html");
            p.waitFor();*/
            File htmlFile = new File("C:\\Users\\bk-laptop\\Desktop\\bob.html");
               //Desktop.getDesktop().browse(htmlFile.toURI());
       displayURL("C:/Users/bk-laptop/Desktop/bob.html");
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
