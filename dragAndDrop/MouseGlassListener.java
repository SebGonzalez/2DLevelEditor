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

//CTRL + SHIFT + O pour g�n�rer les imports
public class MouseGlassListener extends MouseAdapter{

private MyGlassPane myGlass;
private BufferedImage image;

public MouseGlassListener(MyGlassPane glass){
  myGlass = glass;
}
 
public void mousePressed(MouseEvent event) {
  //On r�cup�re le composant pour en d�duire sa position
  Component composant = event.getComponent();
  Point location = (Point)event.getPoint().clone();
    
  //Les m�thodes ci-dessous permettent, dans l'ordre, 
  //de convertir un point en coordonn�es d'�cran
  //et de reconvertir ce point en coordonn�es fen�tres
  SwingUtilities.convertPointToScreen(location, composant);
  SwingUtilities.convertPointFromScreen(location, myGlass);
      
  //Les instructions ci-dessous permettent de redessiner le composant
  image = new BufferedImage(composant.getWidth(), composant.getHeight(), BufferedImage.TYPE_INT_ARGB);
  Graphics g = image.getGraphics();
  composant.paint(g);
      
  //On passe les donn�es qui vont bien � notre GlassPane
  myGlass.setLocation(location);
  myGlass.setImage(image);
    
  //On n'oublie pas de dire � notre GlassPane de s'afficher
  myGlass.setVisible(true);
}

public void mouseReleased(MouseEvent event) {
  //---------------------------------------------------------------------
  //On impl�mente le transfert lorsqu'on rel�che le bouton de souris
  //Ceci afin de ne pas supplanter le fonctionnement du d�placement
  JComponent lab = (JComponent)event.getSource();
  TransferHandler handle = lab.getTransferHandler();
  handle.exportAsDrag(lab, event, TransferHandler.COPY);
  //---------------------------------------------------------------------
    
  //On r�cup�re le composant pour en d�duire sa position
  Component composant = event.getComponent();
  Point location = (Point)event.getPoint().clone();
  //Les m�thodes ci-dessous permettent, dans l'ordre, 
  //de convertir un point en coordonn�es d'�cran
  //et de reconvertir ce point en coordonn�es fen�tre
  SwingUtilities.convertPointToScreen(location, composant);
  SwingUtilities.convertPointFromScreen(location, myGlass);
    
  //On passe les donn�es qui vont bien � notre GlassPane
  myGlass.setLocation(location);
  myGlass.setImage(null);
  //On n'oublie pas de ne plus l'afficher
  myGlass.setVisible(false);
    
}
}
