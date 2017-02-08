package Projet;

import gestionTexture.GestionTexture;

public class Niveau {
	
	int width = 1000;
	int height = 500;
	
	private GestionTexture gestionTexture;
	
	public Niveau() {
		gestionTexture = new GestionTexture();
	}
	
	public GestionTexture getGestionTexture() {
		return gestionTexture;
	}
}
