package de.fhswf.fbin.javafx.samegame.listener;

import de.fhswf.fbin.javafx.samegame.logger.Log;
import de.fhswf.fbin.javafx.samegame.model.SameGameBoard;
import de.fhswf.fbin.javafx.samegame.model.SameGameCanvas;
import de.fhswf.fbin.javafx.samegame.pane.SameGamePane;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 *
 * Klasse zur Implementierung des Click Listeners.
 *
 * @author Zoe Günther
 * @version 1.0
 *
 */
public class SameGameMouseListener implements EventHandler<MouseEvent>
{

   private SameGameCanvas sameGameCanvas;
   private SameGamePane sameGamePane;
   private SameGameBoard sameGameBoard;
   
   /**
    * Konstruktor zur initialisierung der Parameter
    * 
    * @param sameGamePane GamePane
    * @throws IllegalArgumentException sameGamePane ist null
    * @throws IllegalArgumentException sameGameCanvas in sameGamePane ist null
    * @throws IllegalArgumentException sameGameBoard in sameGamePane ist null
    */
   public SameGameMouseListener(SameGamePane sameGamePane)
   {
      this.sameGamePane = sameGamePane;
      this.sameGameCanvas = sameGamePane.getSameGameCanvas();
      this.sameGameBoard = sameGamePane.getSameGameBoard();
      
      if(this.sameGamePane == null) {
         throw new IllegalArgumentException("SameGamePane darf nicht null sein");
      }
      
      if(sameGameCanvas == null) {
         throw new IllegalArgumentException("SameGameCanvas darf nicht null sein");
      }
      
      if(sameGameBoard == null) {
         throw new IllegalArgumentException("SameGameBoard darf nicht null sein");
      }
   }
   
   /**
    * Löscht den geclickten block und rendert das Board neu.
    * Updated das Blocklabel und prüft ob das Spiel vorbei ist nach dem click
    */
   @Override
   public void handle(MouseEvent event)
   {
      try {
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
      } catch(Exception ex) {
         Log.printToLogFile(ex, getClass().getSimpleName());
         Log.showErrorAlert("Es ist ein Fehler beim clicken des Boards aufgetreten");
      }
   }

}
