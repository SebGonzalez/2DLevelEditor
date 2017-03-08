package dragAndDrop;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

//CTRL + SHIFT + O pour générer les imports
public class MyGlassPane extends JPanel{

//L'image qui sera dessinée
private BufferedImage img;
//Les coordonnées de l'image
private Point location;
//La transparence de notre glace
private Composite transparence;
 
public MyGlassPane(){
  //Afin de ne peindre que ce qui nous intéresse
  setOpaque(false);
  //On définit la transparence
  transparence = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.55f);
}   
 
public void setLocation(Point location){
  this.location = location;        
}
 
public void setImage(BufferedImage image){
  img = image;
}
 
public void paintComponent(Graphics g){
  //Si on n'a pas d'image à dessiner, on ne fait rien…
  if(img == null)
    return;
    
  //Dans le cas contraire, on dessine l'image souhaitée
  Graphics2D g2d = (Graphics2D)g;
  g2d.setComposite(transparence);
  g2d.drawImage(img, (int) (location.getX() - (img.getWidth(this)  / 2)), (int) (location.getY() - (img.getHeight(this) / 2)), null);
}   
}
