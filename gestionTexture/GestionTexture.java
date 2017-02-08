package gestionTexture;

import java.awt.Frame;
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
			System.out.println(" x : " + i*(img.getWidth()/nb) + " ,  0 " +  " y : " + (img.getWidth()/nb) + " , " + img.getHeight());
			Image newImg = img.getSubimage(i*(img.getWidth()/nb), 0, (img.getWidth()/nb), img.getHeight());
			System.out.println(" oui : " + img.getWidth() + " non : " + img.getHeight());
			listeTileTexture.add(newImg);
		}
	}
	
	public void dessinerTile(Graphics g, int width, int height) {
		
		int hauteur = (height - (2*(height/listeTileTexture.size()) ))/listeTileTexture.size();
		for(int i=0; i<listeTileTexture.size(); i++) {
			g.drawImage(listeTileTexture.get(i), width-(hauteur+30), ((hauteur+20)*i) + 10, hauteur, hauteur,  null);
		}
	}
}
