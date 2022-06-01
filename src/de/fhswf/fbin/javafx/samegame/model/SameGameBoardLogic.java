package de.fhswf.fbin.javafx.samegame.model;

import java.util.Optional;

import de.fhswf.fbin.javafx.samegame.pane.SameGamePane;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class SameGameBoardLogic
{

   private SameGamePane gamePane;
   private SameGameBoard sameGameBoard;
   private SameGameCanvas sameGameCanvas;
   
   public SameGameBoardLogic(SameGamePane sameGamePane)
   {
      // TODO PARAMS PRÜFEN
      
      this.gamePane = sameGamePane;
      this.sameGameBoard = sameGamePane.getSameGameBoard();
      this.sameGameCanvas = sameGamePane.getSameGameCanvas();
   }
   
   public void gameOver()
   {
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
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
         this.gamePane.updateLabel();
      }
      else
      {
         Platform.exit();
      }
   }
   
}
