package de.fhswf.fbin.javafx.samegame.logger;

import java.io.File;
import java.io.PrintStream;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public abstract class Log
{

   public static void printToLogFile(Exception ex, String className) {
      try {
         String logFile = System.getProperty("user.home")
               + File.separatorChar + className + ".log";
         ex.printStackTrace(new PrintStream(logFile));
      } catch(Exception e1) {
         System.out.println("Schreiben in Logfile nicht möglich");
      }
   }
   
   public static void showErrorAlert(String errorMessage) {
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Etwas ist schiefgelaufen!");
      alert.setHeaderText("Es ist ein Fehler aufgetreten.");
      alert.setContentText(errorMessage);
      alert.showAndWait();
   }
   
   public static void closePlatform(String className) {
      try {
         Platform.exit();
      } catch(Exception ex) {
         Log.printToLogFile(ex, className);
         Log.showErrorAlert("Es ist ein Fehler beim schließen der Anwendung aufgetreten!");
      }
   }
   
}
