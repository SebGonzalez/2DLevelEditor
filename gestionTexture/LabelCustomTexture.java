package gestionTexture;

import javax.swing.JLabel;

public class LabelCustomTexture extends JLabel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;

	public LabelCustomTexture(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	

}
