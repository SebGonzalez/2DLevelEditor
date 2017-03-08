package dragAndDrop;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.TransferHandler;

//CTRL + SHIFT + O pour générer les imports
public class MouseGlassListener extends MouseAdapter{

private MyGlassPane myGlass;
private BufferedImage image;

public MouseGlassListener(MyGlassPane glass){
  myGlass = glass;
}
 
public void mousePressed(MouseEvent event) {
  //On récupère le composant pour en déduire sa position
  Component composant = event.getComponent();
  Point location = (Point)event.getPoint().clone();
    
  //Les méthodes ci-dessous permettent, dans l'ordre, 
  //de convertir un point en coordonnées d'écran
  //et de reconvertir ce point en coordonnées fenêtres
  SwingUtilities.convertPointToScreen(location, composant);
  SwingUtilities.convertPointFromScreen(location, myGlass);
      
  //Les instructions ci-dessous permettent de redessiner le composant
  image = new BufferedImage(composant.getWidth(), composant.getHeight(), BufferedImage.TYPE_INT_ARGB);
  Graphics g = image.getGraphics();
  composant.paint(g);
      
  //On passe les données qui vont bien à notre GlassPane
  myGlass.setLocation(location);
  myGlass.setImage(image);
    
  //On n'oublie pas de dire à notre GlassPane de s'afficher
  myGlass.setVisible(true);
}

public void mouseReleased(MouseEvent event) {
  //---------------------------------------------------------------------
  //On implémente le transfert lorsqu'on relâche le bouton de souris
  //Ceci afin de ne pas supplanter le fonctionnement du déplacement
  JComponent lab = (JComponent)event.getSource();
  TransferHandler handle = lab.getTransferHandler();
  handle.exportAsDrag(lab, event, TransferHandler.COPY);
  //---------------------------------------------------------------------
    
  //On récupère le composant pour en déduire sa position
  Component composant = event.getComponent();
  Point location = (Point)event.getPoint().clone();
  //Les méthodes ci-dessous permettent, dans l'ordre, 
  //de convertir un point en coordonnées d'écran
  //et de reconvertir ce point en coordonnées fenêtre
  SwingUtilities.convertPointToScreen(location, composant);
  SwingUtilities.convertPointFromScreen(location, myGlass);
    
  //On passe les données qui vont bien à notre GlassPane
  myGlass.setLocation(location);
  myGlass.setImage(null);
  //On n'oublie pas de ne plus l'afficher
  myGlass.setVisible(false);
    
}
}
