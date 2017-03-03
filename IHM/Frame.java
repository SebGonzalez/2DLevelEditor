package IHM;

import javax.swing.JFrame;
import javax.swing.UIManager;

import Projet.Projet;

public class Frame extends JFrame {

	public static Projet p;
	
	/*private JMenuBar menuBar = new JMenuBar();
	private JMenu menu1 = new JMenu("Fichier");
	private JMenu menu2 = new JMenu("Sous ficher");
	private JMenu menu3 = new JMenu("Edition");*/

	
	public Frame() {
		this.setSize(500, 200);
		setBestLookAndFeelAvailable();
		this.setContentPane(new PanelPrincipal());
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	 public static void setBestLookAndFeelAvailable(){
		 
		       try {
		           UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		       }catch (Exception e) {}
		 
		   //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		 }
	 
	
	public static void main(String[] args) {
		Frame f = new Frame();
		 p = new Projet();
	}

}
