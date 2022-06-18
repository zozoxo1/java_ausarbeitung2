package de.fhswf.fbin.javafx.samegame.pane;

import de.fhswf.fbin.javafx.samegame.listener.SameGameMouseListener;

import java.util.Optional;

import de.fhswf.fbin.javafx.samegame.listener.SameGameHeightResizeListener;
import de.fhswf.fbin.javafx.samegame.listener.SameGameWidthResizeListener;
import de.fhswf.fbin.javafx.samegame.model.SameGameBoard;
import de.fhswf.fbin.javafx.samegame.model.SameGameCanvas;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class SameGamePane extends BorderPane
{

   private SameGameCanvas sameGameCanvas;
   private SameGameBoard sameGameBoard;
   private Label blockCountLabel = new Label();
   
   public SameGamePane()
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
      blockCountLabel.setText("Bl�cke vorhanden: " + sameGameBoard.getRemaining());
   }
   
   public void gameOver()
   {
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
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
         Platform.exit();
      }
   }
   
}
