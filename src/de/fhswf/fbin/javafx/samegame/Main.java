package de.fhswf.fbin.javafx.samegame;
	
import static de.fhswf.fbin.javafx.samegame.common.Constants.SCREEN_DIMENSION;

import de.fhswf.fbin.javafx.samegame.pane.SameGamePane;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new SameGamePane();
			Scene scene = new Scene(root,SCREEN_DIMENSION[0], SCREEN_DIMENSION[1] - 15);
			
			primaryStage.setTitle("The Same Game");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// TODO alle sachen aus pane rausnehmen.
	// eine klasse eine abhängigkeit
	// try catch bei den listenern
	// try catch bei platform exit
	
	public static void main(String[] args) {
		launch(args);
	}
}
