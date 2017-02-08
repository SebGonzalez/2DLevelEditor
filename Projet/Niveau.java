package Projet;

import gestionTexture.GestionTexture;

public class Niveau {
	
	private int nbcasex;
	private int nbcasey;

	int width = 1000;
	int height = 500;
	
	private GestionTexture gestionTexture;
	
	public Niveau() {
		gestionTexture = new GestionTexture();
	}
	
	public Niveau(int nbx, int nby) {
		gestionTexture = new GestionTexture();
		nbcasex=nbx;
		nbcasey=nby;
	}
	
	public GestionTexture getGestionTexture() {
		return gestionTexture;
	}

	public int getNbcasex() {
		return nbcasex;
	}

	public void setNbcasex(int nbcasex) {
		this.nbcasex = nbcasex;
	}

	public int getNbcasey() {
		return nbcasey;
	}

	public void setNbcasey(int nbcasey) {
		this.nbcasey = nbcasey;
	}
	
	
	
	
}
