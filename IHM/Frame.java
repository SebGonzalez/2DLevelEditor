package IHM;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.UIManager;

import Projet.Projet;
import dragAndDrop.MyGlassPane;

public class Frame extends JFrame {

	public static Projet p;
	public static boolean creation;
	
	/*private JMenuBar menuBar = new JMenuBar();
	private JMenu menu1 = new JMenu("Fichier");
	private JMenu menu2 = new JMenu("Sous ficher");
	private JMenu menu3 = new JMenu("Edition");*/
	public static MyGlassPane glass = new MyGlassPane();
	
	public Frame() {
		
		setGlassPane(glass);
		
		this.setSize(500, 200);
		setBestLookAndFeelAvailable();
		this.setContentPane(new PanelPrincipal());
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Level Editor");

        try {
            this.setIconImage(ImageIO.read(getClass().getResourceAsStream("../logo.png")));
        } catch (IOException e) {
        }
        
		this.setVisible(true);
	}
	
	 public static void setBestLookAndFeelAvailable(){
		 
		       try {
		           UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		           //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

		       }catch (Exception e) {}
		 
		   //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		 }
	 
	
	public static void main(String[] args) {
		Frame f = new Frame();
		 p = new Projet();
	}

}
