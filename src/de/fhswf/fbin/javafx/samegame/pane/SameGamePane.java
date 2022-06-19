package de.fhswf.fbin.javafx.samegame.pane;

import de.fhswf.fbin.javafx.samegame.listener.SameGameMouseListener;

import java.util.Optional;

import de.fhswf.fbin.javafx.samegame.listener.SameGameHeightResizeListener;
import de.fhswf.fbin.javafx.samegame.listener.SameGameWidthResizeListener;
import de.fhswf.fbin.javafx.samegame.logger.Log;
import de.fhswf.fbin.javafx.samegame.model.SameGameBoard;
import de.fhswf.fbin.javafx.samegame.model.SameGameCanvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

/**
 *
 * Klasse zur implementierung einer BorderPane, worauf das Game stattfindet
 *
 * @author Zoe G�nther
 * @version 1.0
 *
 */
public class SameGamePane extends BorderPane
{

   private final SameGameCanvas sameGameCanvas;
   private final SameGameBoard sameGameBoard;
   private final Label blockCountLabel = new Label();
   
   /**
    * Konstruktor zur implementierung des Games auf dem Pane.
    * Initialisiert die Listener.
    * 
    * @throws IllegalArgumentException Wenn ein Parameter einer Funktion null ist
    */
   public SameGamePane() throws IllegalArgumentException
   {
      sameGameBoard = new SameGameBoard();
      sameGameCanvas = new SameGameCanvas(sameGameBoard);
      
      updateLabel();
      
      setOnMouseClicked(new SameGameMouseListener(this));
      
      heightProperty().addListener(new SameGameHeightResizeListener(sameGameCanvas));
      widthProperty().addListener(new SameGameWidthResizeListener(sameGameCanvas));
      
      setCenter(sameGameCanvas);
      setBottom(blockCountLabel);
      
      sameGameCanvas.renderBoard();
   }
   
   /**
    * Gibt das SameGameCanvas zur�ck
    * 
    * @return SameGameCanvas
    */
   public SameGameCanvas getSameGameCanvas()
   {
      return sameGameCanvas;
   }


   /**
    * Gibt das SameGameBoard zur�ck
    * 
    * @return SameGameBoard
    */
   public SameGameBoard getSameGameBoard()
   {
      return sameGameBoard;
   }


   /**
    * Updated den Label Text f�r die restlichen vorhandenen Bl�cke
    */
   public void updateLabel()
   {
      blockCountLabel.setText("Bl�cke vorhanden: " + sameGameBoard.getRemaining());
   }
   
   /**
    * Zeigt einen GameOver alert und bei best�tigen eines neuen Spiels wird das Game neu gestartet.
    * Bei nicht best�tigen eines neuen Spiels wird die Application beendet.
    * 
    * @throws IllegalArgumentException board oder canvas ist null
    */
   public void gameOver()
   {
      if(sameGameBoard == null)
      {
         throw new IllegalArgumentException("SameGameBoard darf bei gameOver nicht null sein");
      }
      
      if(sameGameCanvas == null)
      {
         throw new IllegalArgumentException("SameGameCanvas darf bei gameOver nicht null sein.");
      }
      
      // Zeigen des GameOver screens
      Alert alert = new Alert(AlertType.CONFIRMATION);
      alert.setTitle("Spiel vorbei");
      alert.setHeaderText("Keine Z�ge mehr m�glich!");
      alert.setContentText((this.sameGameBoard.getRemaining() == 0 ? "Keine Bl�cke mehr �brig!" : 
         (this.sameGameBoard.getRemaining() == 1 ? "1 Block" : this.sameGameBoard.getRemaining() + " Bl�cke")
         + " �brig.") + "\n\nM�chtest du ein neues Spiel starten?");
      
      Optional<ButtonType> result = alert.showAndWait();
      
      if(result.get() == ButtonType.OK)
      {
         this.sameGameBoard.resetBoard();
         this.sameGameCanvas.renderBoard();
         updateLabel();
      }
      else
      {
         Log.closePlatform(getClass().getSimpleName());
      }
   }
   
}
