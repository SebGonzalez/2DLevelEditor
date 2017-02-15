package Projet;

import java.io.Serializable;
import java.util.ArrayList;

public class Projet implements Serializable{
	
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
	
	public void ajouterNiveau(Niveau n){
		listeNiveau.add(n);
	}
	
}
