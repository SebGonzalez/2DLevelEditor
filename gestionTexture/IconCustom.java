package gestionTexture;

import java.awt.Image;

import javax.swing.ImageIcon;

public class IconCustom extends ImageIcon{
	
	private int num = -1;
	
	public IconCustom(Image i, int id) {
		super(i);
		this.num = id;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int id) {
		this.num = id;
	}

}
