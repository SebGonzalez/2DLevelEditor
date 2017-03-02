package IHM;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import Projet.Projet;

public class Frame extends JFrame {

	public static Projet p;
	
	/*private JMenuBar menuBar = new JMenuBar();
	private JMenu menu1 = new JMenu("Fichier");
	private JMenu menu2 = new JMenu("Sous ficher");
	private JMenu menu3 = new JMenu("Edition");*/

	
	public Frame() {
		this.setSize(500, 200);
		this.setContentPane(new PanelPrincipal());
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//this.setUndecorated(true);
		
/*---------------------------------------------------Menu-------------------------------------------------------- */ 	
     
      	
       	//this.menuBar.add(menu1);
       // this.menuBar.add(menu2);
        //this.setJMenuBar(menuBar);
        
        /*JFrame frame = (JFrame)this.getTopLevelAncestor();
        frame.setJMenuBar(menuBar);	*/
       	
       	
  /*---------------------------------------------------Menu---------------------------------------------------------*/ 
		
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		Frame f = new Frame();
		 p = new Projet();
	}

}
