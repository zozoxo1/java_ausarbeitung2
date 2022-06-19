package de.fhswf.fbin.javafx.samegame;
	
import static de.fhswf.fbin.javafx.samegame.common.Constants.SCREEN_DIMENSION;

import de.fhswf.fbin.javafx.samegame.logger.Log;
import de.fhswf.fbin.javafx.samegame.pane.SameGamePane;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


/**
 *
 * Startet die Application und zeigt das Fenster an. Prüft beim starten auf Fehler.
 *
 * @author Zoe Günther
 * @version 1.0
 *
 */
public class Main extends Application {
   
	/**
	 * Zeigt das Application Fenster an.
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new SameGamePane();
			Scene scene = new Scene(root,SCREEN_DIMENSION[0], SCREEN_DIMENSION[1] - 15);
			
			primaryStage.setTitle("The Same Game");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
		   Log.printToLogFile(e, getClass().getSimpleName());
	       Log.showErrorAlert("Es ist ein Fehler beim erstellen des Fensters aufgetreten");
	       Log.closePlatform(getClass().getSimpleName());
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
