package de.fhswf.fbin.javafx.samegame.listener;

import de.fhswf.fbin.javafx.samegame.model.SameGameCanvas;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class SameGameWidthResizeListener implements ChangeListener<Number>
{
   
   private SameGameCanvas sameGameCanvas;
   
   public SameGameWidthResizeListener(SameGameCanvas sameGameCanvas)
   {
      // TODO PARAM PRÜFEN
      this.sameGameCanvas = sameGameCanvas;
   }

   @Override
   public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1)
   {
      sameGameCanvas.setWidth(t1.intValue());
      sameGameCanvas.renderBoard();
   }

}
