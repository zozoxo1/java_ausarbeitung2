package de.fhswf.fbin.javafx.samegame.listener;

import de.fhswf.fbin.javafx.samegame.pane.SameGamePane;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class SameGamePaneWidthResizeListener implements ChangeListener<Number>
{
   
   private SameGamePane sameGamePane;
   
   public SameGamePaneWidthResizeListener(SameGamePane sameGamePane)
   {
      // TODO PARAM PRÜFEN
      this.sameGamePane = sameGamePane;
   }

   @Override
   public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1)
   {
      sameGamePane.getSameGameCanvas().setWidth(t1.intValue());
      sameGamePane.getSameGameCanvas().renderBoard();
   }

}
