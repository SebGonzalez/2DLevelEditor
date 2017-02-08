package Projet;

import java.util.ArrayList;

public class Projet {
	
	public ArrayList<Niveau> listeNiveau;
	
	public Projet() {
		listeNiveau = new ArrayList<Niveau>();
	}
	
	public void creerNiveau() {
		Niveau n = new Niveau();
		listeNiveau.add(n);
	}

	public ArrayList<Niveau> getListeNiveau() {
		return listeNiveau;
	}
	
}
