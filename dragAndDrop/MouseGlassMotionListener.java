package dragAndDrop;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

//CTRL + SHIFT + O pour générer les imports
public class MouseGlassMotionListener extends MouseAdapter{

private MyGlassPane myGlass;
 
public MouseGlassMotionListener(MyGlassPane glass){
  myGlass = glass;
}
 
/**
* Méthode fonctionnant sur le même principe que la classe précédente
* mais cette fois sur l'action de déplacement
*/
public void mouseDragged(MouseEvent event) {
  //Vous connaissez maintenant…
  Component c = event.getComponent();

  Point p = (Point) event.getPoint().clone();
  SwingUtilities.convertPointToScreen(p, c);
  SwingUtilities.convertPointFromScreen(p, myGlass);
  myGlass.setLocation(p);
  myGlass.repaint();
}
}
