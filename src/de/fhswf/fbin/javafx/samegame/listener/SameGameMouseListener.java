package de.fhswf.fbin.javafx.samegame.listener;

import de.fhswf.fbin.javafx.samegame.model.SameGameBoard;
import de.fhswf.fbin.javafx.samegame.model.SameGameCanvas;
import de.fhswf.fbin.javafx.samegame.pane.SameGamePane;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class SameGameMouseListener implements EventHandler<MouseEvent>
{

   private SameGameCanvas sameGameCanvas;
   private SameGamePane sameGamePane;
   private SameGameBoard sameGameBoard;
   
   public SameGameMouseListener(SameGamePane sameGamePane)
   {
      // TODO PARAMS PRÜFEN
      this.sameGamePane = sameGamePane;
      this.sameGameCanvas = sameGamePane.getSameGameCanvas();
      this.sameGameBoard = sameGamePane.getSameGameBoard();
   }
   
   @Override
   public void handle(MouseEvent event)
   {
      double x = sameGameCanvas.getWidth() / sameGameBoard.getColumns();
      double y = sameGameCanvas.getHeight() / sameGameBoard.getRows();
      
      int col = (int) (event.getX() / x);
      int row = (int) (event.getY() / y);
      
      sameGameBoard.deleteBlocks(row, col);
      sameGameCanvas.renderBoard();
      sameGamePane.updateLabel();
      
      if(sameGameBoard.isGameOver())
      {
         sameGamePane.gameOver();
      }
   }

}
