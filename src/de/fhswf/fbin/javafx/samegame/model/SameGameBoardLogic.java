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
      // TODO PARAMS PR�FEN
      
      this.gamePane = sameGamePane;
      this.sameGameBoard = sameGamePane.getSameGameBoard();
      this.sameGameCanvas = sameGamePane.getSameGameCanvas();
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
         this.gamePane.updateLabel();
      }
      else
      {
         Platform.exit();
      }
   }
   
}
