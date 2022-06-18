package de.fhswf.fbin.javafx.samegame.listener;

import de.fhswf.fbin.javafx.samegame.model.SameGameCanvas;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class SameGameHeightResizeListener implements ChangeListener<Number>
{
   
   private SameGameCanvas sameGameCanvas;
   
   public SameGameHeightResizeListener(SameGameCanvas sameGameCanvas)
   {
      // TODO PARAM PRÜFEN
      this.sameGameCanvas = sameGameCanvas;
   }

   @Override
   public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1)
   {
      sameGameCanvas.setHeight(t1.intValue() - 15);
      sameGameCanvas.renderBoard();
   }

}
