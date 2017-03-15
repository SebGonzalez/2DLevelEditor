package Projet;

import gestionTexture.IconCustom;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Projet implements Serializable{
	
	public ArrayList<Niveau> listeNiveau;
	
	public static int textureClick;
	
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
	
	public boolean export(String chemin) {
		if(verifNiveauComplet()) {
			for(int i=0; i<listeNiveau.size(); i++) {
	
				try {
					File imagesrc = new File(listeNiveau.get(0).getGestionTexture().getImage());
					File imagedest = new File(chemin + "/" + imagesrc.getName());
					System.out.println(imagesrc.getName());
					copyFile(imagesrc,imagedest);
					System.out.println(chemin + "/niveau" + i + ".txt");
					BufferedWriter writer = new BufferedWriter(new FileWriter(new File(chemin + "/niveau" + i + ".txt")));
					writer.write(imagesrc.getName());
					writer.newLine();
					writer.write(listeNiveau.get(0).getGestionTexture().getNbLigne() +" " + listeNiveau.get(0).getGestionTexture().getNbColonne());
					writer.newLine();
					
					for(int z=0; z<listeNiveau.get(0).getGestionTexture().typeTexture.length; z++) {
						writer.write(listeNiveau.get(0).getGestionTexture().typeTexture[z]);
						writer.newLine();
					}
					
					for(int y=0; y<listeNiveau.get(i).getNbcasey(); y++) {
						for(int w=0; w<listeNiveau.get(i).getNbcasex(); w++) {
							writer.write(((IconCustom) listeNiveau.get(i).getLignePlateau().get(y).get(w).getIcon()).getNum() + " ");
						}
						writer.newLine();
					}
					writer.close();
				}
				catch(IOException e) {
					e.printStackTrace();
				}
			}
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean verifNiveauComplet() {
		for(int i=0; i<listeNiveau.size(); i++) {
			for(int y=0; y<listeNiveau.get(i).getNbcasey(); y++) {
				for(int w=0; w<listeNiveau.get(i).getNbcasex(); w++) {
					if((IconCustom) listeNiveau.get(i).getLignePlateau().get(y).get(w).getIcon() == null) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public void copyFile (File src, File dest) throws IOException {
		InputStream in = new BufferedInputStream(new FileInputStream(src));
		OutputStream out = new BufferedOutputStream(new FileOutputStream(dest));
		byte[] buf = new byte[4096];
		int n;
		while ((n=in.read(buf, 0, buf.length)) > 0)
			out.write(buf, 0, n);
		in.close();
		out.close();
	}
}
