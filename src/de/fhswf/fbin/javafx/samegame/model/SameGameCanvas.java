package de.fhswf.fbin.javafx.samegame.model;

import static de.fhswf.fbin.javafx.samegame.common.Constants.SCREEN_DIMENSION;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

/**
 *
 * Canvas worauf die Blöcke des Games gezeichnet werden.
 *
 * @author Zoe Günther
 * @version 1.0
 *
 */
public class SameGameCanvas extends Canvas
{

   private SameGameBoard sameGameBoard;
   
   private final Color[] blockColors = new Color[] {
         Color.BLACK,
         Color.RED,
         Color.BLUE,
         Color.YELLOW
   };
   
   /**
    * Konstruktor zum initialisieren der Parameter
    * 
    * @param sameGameBoard Board worauf der Canvas gerendert wird
    * @throws IllegalArgumentException sameGameBoard ist null
    */
   public SameGameCanvas(SameGameBoard sameGameBoard)
   {  
      this.sameGameBoard = sameGameBoard;
      
      if(sameGameBoard == null)
      {
         throw new IllegalArgumentException("SameGameBoard darf in Canvas nicht null sein");
      }
      
      this.setWidth(SCREEN_DIMENSION[0]);
      this.setHeight(SCREEN_DIMENSION[1] - 15);
   }
   
   /**
    * Rendert die Blöcke auf dem Board.
    */
   public void renderBoard()
   {
      this.getGraphicsContext2D().setFill(Color.BLACK);
      this.getGraphicsContext2D().fillRect(0, 0, this.getWidth(), this.getHeight());
      
      double x = this.getWidth() / sameGameBoard.getColumns();
      double y = this.getHeight() / sameGameBoard.getRows();
      
      for(int cols = 0; cols < sameGameBoard.getColumns(); cols++)
      {
         for(int rows = 0; rows < sameGameBoard.getRows(); rows++)
         {
            int colorBlock = sameGameBoard.getBoardSpace(rows, cols);
            
            // if not black, render block
            if(colorBlock > 0)
            {
               this.getGraphicsContext2D().setFill(this.blockColors[colorBlock]);
               this.getGraphicsContext2D().fillRect((cols * x) + 2, (rows * y) + 2, x - 2, y - 2);
            }
         }
      }
   }
   
}
