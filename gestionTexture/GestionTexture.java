package gestionTexture;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class GestionTexture {
	
	public ArrayList<Image> listeTileTexture;
	
	public GestionTexture() {
		listeTileTexture = new ArrayList<Image>();
	}
	
	public void decouperImage(String fichierBmp, int nb) {
		File f = new File(fichierBmp);
		BufferedImage img = (BufferedImage) BMPImage.readBMP(f);
		
		for(int i=0; i<nb; i++) {
			Image newImg = img.getSubimage(i*(img.getWidth(null)/nb), 0, (i+1)*(img.getWidth(null)/nb), img.getHeight(null));
			listeTileTexture.add(newImg);
		}
	}
	
	public void dessinerTile(Graphics g) {
		for(int i=0; i<listeTileTexture.size(); i++) {
			g.drawImage(listeTileTexture.get(i), 950, (listeTileTexture.get(i).getHeight(null)+10)*i, null);
		}
	}
}
