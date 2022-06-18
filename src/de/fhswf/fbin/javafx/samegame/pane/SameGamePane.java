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

public class SameGamePane extends BorderPane
{

   private final SameGameCanvas sameGameCanvas;
   private final SameGameBoard sameGameBoard;
   private final Label blockCountLabel = new Label();
   
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
   
   public SameGameCanvas getSameGameCanvas()
   {
      return sameGameCanvas;
   }


   public SameGameBoard getSameGameBoard()
   {
      return sameGameBoard;
   }


   public void updateLabel()
   {
      blockCountLabel.setText("Blöcke vorhanden: " + sameGameBoard.getRemaining());
   }
   
   public void gameOver()
   {
      if(sameGameBoard == null)
      {
         throw new IllegalArgumentException("SameGameBoard darf bei gameOver nicht null sein");
      }
      
      // Parameterprüfung
      if(sameGameCanvas == null)
      {
         throw new IllegalArgumentException("SameGameCanvas darf bei gameOver nicht null sein.");
      }
      
      // Zeigen des GameOver screens
      Alert alert = new Alert(AlertType.CONFIRMATION);
      alert.setTitle("Spiel vorbei");
      alert.setHeaderText("Keine Züge mehr möglich!");
      alert.setContentText((this.sameGameBoard.getRemaining() == 0 ? "Keine Blöcke mehr übrig!" : 
         (this.sameGameBoard.getRemaining() == 1 ? "1 Block" : this.sameGameBoard.getRemaining() + " Blöcke")
         + " übrig.") + "\n\nMöchtest du ein neues Spiel starten?");
      
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
