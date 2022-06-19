package de.fhswf.fbin.javafx.samegame.listener;

import de.fhswf.fbin.javafx.samegame.logger.Log;
import de.fhswf.fbin.javafx.samegame.model.SameGameCanvas;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 * 
 * Klasse zur Implementierung des Height resize Listeners.
 *
 * @author Zoe Günther
 * @version 1.0
 *
 */
public class SameGameHeightResizeListener implements ChangeListener<Number>
{
   
   private SameGameCanvas sameGameCanvas;
   
   /**
    * Konstruktor zur initialisierung der Parameter
    * 
    * @param sameGameCanvas Canvas welcher geresized wird
    * @throws IllegalArgumentException Parameter sameGameCanvas ist null
    */
   public SameGameHeightResizeListener(SameGameCanvas sameGameCanvas)
   {
      this.sameGameCanvas = sameGameCanvas;
      
      if(sameGameCanvas == null)
      {
         throw new IllegalArgumentException("SameGameCanvas darf in resize Listener nicht null sein");
      }
   }

   /**
    * Setzt die neue Höhe des Canvas und rendert es neu
    */
   @Override
   public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1)
   {
      try {
         sameGameCanvas.setHeight(t1.intValue() - 15);
         sameGameCanvas.renderBoard();
      } catch(Exception ex) {
         Log.printToLogFile(ex, getClass().getSimpleName());
         Log.showErrorAlert("Es ist ein Fehler beim resizen des Boards aufgetreten");
      }
   }

}
