package Projet;

import javax.swing.JLabel;

public class LabelCustom extends JLabel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//PanelCreation
	int ligne;
	int colonne;
	
	public LabelCustom(int ligne, int colonne) {
		super();
		this.ligne = ligne;
		this.colonne = colonne;
	}

	public int getLigne() {
		return ligne;
	}

	public void setLigne(int ligne) {
		this.ligne = ligne;
	}

	public int getColonne() {
		return colonne;
	}

	public void setColonne(int colonne) {
		this.colonne = colonne;
	}
	
	
	



		
}
