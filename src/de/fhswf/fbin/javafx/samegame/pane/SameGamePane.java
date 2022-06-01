package de.fhswf.fbin.javafx.samegame.pane;

import de.fhswf.fbin.javafx.samegame.listener.SameGameCanvasMouseListener;
import de.fhswf.fbin.javafx.samegame.listener.SameGamePaneHeightResizeListener;
import de.fhswf.fbin.javafx.samegame.listener.SameGamePaneWidthResizeListener;
import de.fhswf.fbin.javafx.samegame.model.SameGameBoard;
import de.fhswf.fbin.javafx.samegame.model.SameGameBoardLogic;
import de.fhswf.fbin.javafx.samegame.model.SameGameCanvas;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class SameGamePane extends BorderPane
{

   private SameGameCanvas sameGameCanvas;
   private SameGameBoard sameGameBoard;
   private SameGameBoardLogic sameGameBoardLogic;
   private Label blockCountLabel = new Label();
   
   private final Color[] blockColors = new Color[] {
         Color.BLACK,
         Color.RED,
         Color.BLUE,
         Color.YELLOW
   };
   
   public SameGamePane()
   {
      sameGameBoard = new SameGameBoard();
      sameGameCanvas = new SameGameCanvas(this, blockColors);
      sameGameBoardLogic = new SameGameBoardLogic(this);
      
      updateLabel();
      
      setOnMouseClicked(new SameGameCanvasMouseListener(this));
      
      heightProperty().addListener(new SameGamePaneHeightResizeListener(this));
      widthProperty().addListener(new SameGamePaneWidthResizeListener(this));
      
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


   public SameGameBoardLogic getSameGameBoardLogic()
   {
      return sameGameBoardLogic;
   }


   public void updateLabel()
   {
      blockCountLabel.setText("Blöcke vorhanden: " + sameGameBoard.getRemaining());
   }
   
}
