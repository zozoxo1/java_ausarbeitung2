package de.fhswf.fbin.javafx.samegame.model;

import static de.fhswf.fbin.javafx.samegame.common.Constants.SCREEN_DIMENSION;

import de.fhswf.fbin.javafx.samegame.pane.SameGamePane;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class SameGameCanvas extends Canvas
{

   private SameGamePane sameGamePane;
   private final Color[] blockColors;
   
   public SameGameCanvas(SameGamePane sameGamePane, final Color[] blockColors)
   {
      // TODO PARAMS PRÜFEN
      
      this.sameGamePane = sameGamePane;
      this.blockColors = blockColors;
      
      this.setWidth(SCREEN_DIMENSION[0]);
      this.setHeight(SCREEN_DIMENSION[1] - 15);
   }
   
   public void renderBoard()
   {
      this.getGraphicsContext2D().setFill(Color.BLACK);
      this.getGraphicsContext2D().fillRect(0, 0, this.getWidth(), this.getHeight());
      
      double x = this.getWidth() / sameGamePane.getSameGameBoard().getColumns();
      double y = this.getHeight() / sameGamePane.getSameGameBoard().getRows();
      
      for(int cols = 0; cols < sameGamePane.getSameGameBoard().getColumns(); cols++)
      {
         for(int rows = 0; rows < sameGamePane.getSameGameBoard().getRows(); rows++)
         {
            int colorBlock = sameGamePane.getSameGameBoard().getBoardSpace(rows, cols);
            
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
