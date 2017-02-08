package IHM;

import javax.swing.JFrame;

import Projet.Projet;

public class Frame extends JFrame {

	public static Projet p;
	
	public Frame() {
		this.setSize(500, 200);
		this.setContentPane(new PanelPrincipal());
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		Frame f = new Frame();
		 p = new Projet();
		p.creerNiveau();
		p.getListeNiveau().get(0).getGestionTexture().decouperImage("src/tileset1.bmp", 8);

	}

}
