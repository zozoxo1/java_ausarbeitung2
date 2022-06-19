package de.fhswf.fbin.javafx.samegame.logger;

import java.io.File;
import java.io.PrintStream;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * Klasse zum Loggen von Fehlern.
 *
 * @author Zoe Günther
 * @version 1.0
 *
 */
public abstract class Log
{

   /**
    * Schreibt den Fehler einer Exception in ein Log File.<br>
    * File liegt im User Verzeichnis
    * 
    * @param ex Exception welche geworfen wird
    * @param className Name der Klasse welche den Fehler wirft. Wird zum benennen der Datei verwendet
    */
   public static void printToLogFile(Exception ex, String className) {
      try {
         String logFile = System.getProperty("user.home")
               + File.separatorChar + className + ".log";
         ex.printStackTrace(new PrintStream(logFile));
      } catch(Exception e1) {
         System.out.println("Schreiben in Logfile nicht möglich");
      }
   }
   
   /**
    * Zeigt einen Error Alert an.
    * 
    * @param errorMessage Message welche angezeigt wird
    */
   public static void showErrorAlert(String errorMessage) {
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Etwas ist schiefgelaufen!");
      alert.setHeaderText("Es ist ein Fehler aufgetreten.");
      alert.setContentText(errorMessage);
      alert.showAndWait();
   }
   
   /**
    * Beendet die Application und prüft beim schließen ob das schließen erfolgreich ist.
    * 
    * @param className Klassenname welcher zum Loggen verwendet wird
    */
   public static void closePlatform(String className) {
      try {
         Platform.exit();
      } catch(Exception ex) {
         Log.printToLogFile(ex, className);
         Log.showErrorAlert("Es ist ein Fehler beim schließen der Anwendung aufgetreten!");
      }
   }
   
}
