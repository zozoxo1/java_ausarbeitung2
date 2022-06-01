package de.fhswf.fbin.javafx.samegame.listener;

import de.fhswf.fbin.javafx.samegame.pane.SameGamePane;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class SameGamePaneHeightResizeListener implements ChangeListener<Number>
{
   
   private SameGamePane sameGamePane;
   
   public SameGamePaneHeightResizeListener(SameGamePane sameGamePane)
   {
      // TODO PARAM PRÜFEN
      this.sameGamePane = sameGamePane;
   }

   @Override
   public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1)
   {
      sameGamePane.getSameGameCanvas().setHeight(t1.intValue() - 15);
      sameGamePane.getSameGameCanvas().renderBoard();
   }

}
