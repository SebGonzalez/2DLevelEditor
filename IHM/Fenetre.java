package IHM;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.TransferHandler;

import dragAndDrop.MouseGlassListener;
import dragAndDrop.MouseGlassMotionListener;
import dragAndDrop.MyGlassPane;

//CTRL + SHIFT + O pour générer les imports
public class Fenetre extends JFrame{

private MyGlassPane glass = new MyGlassPane();

public Fenetre(){
  super("Test de GlassPane");
  setSize(400, 200);
  setLocationRelativeTo(null);
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  JPanel pan = new JPanel();
  JPanel pan2 = new JPanel();
    
  //On crée un composant
  JButton bouton1 = new JButton("Bouton N°1");
  //On y attache les écouteurs qui auront pour rôle
  //d'initialiser notre glace et d'y affecter les données
  //qui permettront de simuler le déplacement
  bouton1.addMouseListener(new MouseGlassListener(glass));
  bouton1.addMouseMotionListener(new MouseGlassMotionListener(glass));
  //On affecte maintenant un TranferHandler spécifique
  //initialisé avec la propriété JavaBean "text" 
  bouton1.setTransferHandler(new TransferHandler("text"));
    
  JButton bouton2 = new JButton("Bouton N°2");
  bouton2.addMouseListener(new MouseGlassListener(glass));
  bouton2.addMouseMotionListener(new MouseGlassMotionListener(glass));
  bouton2.setTransferHandler(new TransferHandler("text"));

  JLabel text = new JLabel("Deuxième texte statique");
  text.addMouseListener(new MouseGlassListener(glass));
  text.addMouseMotionListener(new MouseGlassMotionListener(glass));
  text.setTransferHandler(new TransferHandler("text"));

  JLabel label = new JLabel("Texte statique !");
  label.addMouseListener(new MouseGlassListener(glass));
  label.addMouseMotionListener(new MouseGlassMotionListener(glass));
  label.setTransferHandler(new TransferHandler("text"));

  pan.add(bouton1);
  pan.add(label);
  add(pan, BorderLayout.NORTH);

  pan2.add(text);
  pan2.add(bouton2);
  add(pan2, BorderLayout.SOUTH);

  setGlassPane(glass);
  setLocationRelativeTo(null);
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  setVisible(true);
}

public static void main(String[] args){
  new Fenetre();
}   
}
