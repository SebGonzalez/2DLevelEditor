package gestionTexture;

import javax.swing.JLabel;

public class LabelCustomTexture extends JLabel{

	private int id;
	private boolean estCoche;

	public LabelCustomTexture(int id) {
		super();
		this.id = id;
		estCoche = false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	public boolean isEstCoche() {
		return estCoche;
	}

	public void modifSelection() {
		if(estCoche) estCoche = false;
		else estCoche = true;
	}
	
	

}
