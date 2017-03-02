package gestionTexture;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.TransferHandler;

import Projet.Projet;

public class GestionTexture implements Serializable{
	
	private int nbLigne;
	private int nbColonne;
	private String image;
	public transient ArrayList<Image> listeTileTexture;
	public ArrayList<String> listeTypeBlock;
	public String[] typeTexture;
	
	public GestionTexture() {
		listeTileTexture = new ArrayList<Image>();
		listeTypeBlock = new ArrayList<String>();
		typeTexture = new String[listeTileTexture.size()];
	}
	
	public int getNbLigne() {
		return nbLigne;
	}

	public void setNbLigne(int nbLigne) {
		this.nbLigne = nbLigne;
	}

	public int getNbColonne() {
		return nbColonne;
	}

	public void setNbColonne(int nbColonne) {
		this.nbColonne = nbColonne;
	}
	
	public void setImage(String fichier) {
		this.image = fichier;
	}
	
	public String getImage() {
		return image;
	}
	
	//associe un type à une texture
	public void ajouterTypeTexture(String type, int idTexture) {
		typeTexture[idTexture] = type;
	}
	
	//vérifie si l'utilisateur à associé un type à chaque texture
	public boolean verifTypeTextureComplet() {
		for(int i=0; i<typeTexture.length; i++) {
			if(typeTexture[i].equals(""))
				return false;
		}
		return true;
	}
	
	//ajoute les type de block
	public boolean setListeTypeBlock(ArrayList<JTextField> listeTextField) {
		listeTypeBlock.clear();
		for(int i=0; i<listeTextField.size(); i++) {
			if(!listeTextField.get(i).getText().equals("")) {
				listeTypeBlock.add(listeTextField.get(i).getText());
			}
		}
		
		if(listeTypeBlock.size() == 0) return false;
		return true;
	}

	public BufferedImage decouperImage() {
		listeTileTexture = new ArrayList<Image>();
		File f = new File(image);
		BufferedImage img = null;
		try {
			img = ImageIO.read(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("height : " + img.getHeight());
		System.out.println("width : " + img.getWidth());
		for(int y=0; y<nbLigne; y++) {
			for(int i=0; i<nbColonne; i++) {
				Image newImg = img.getSubimage(i*(img.getWidth()/nbColonne), y*(img.getHeight()/nbLigne), (img.getWidth()/nbColonne), img.getHeight()/nbLigne);
				listeTileTexture.add(newImg);
			}
		}
		return img;
	}
	
	public JPanel dessinerTile( int width, int height) {
		
		JPanel j = new JPanel();
		GridLayout gl = new GridLayout(nbLigne, nbColonne);
		j.setBackground(new Color(241,241,241));

		gl.setHgap(30);
		gl.setVgap(30);
		j.setLayout(gl);
		//j.setSize(200,1000);
		

		MouseListener ml = new MouseListener() {

            @Override
            public void mousePressed(MouseEvent e) {
            	LabelCustomTexture jc = (LabelCustomTexture)e.getSource();
                TransferHandler th = jc.getTransferHandler();
                th.exportAsDrag(jc, e, TransferHandler.COPY);
                Projet.textureClick = jc.getId();
                System.out.println("Id : " + Projet.textureClick);
            }
            @Override
            public void mouseClicked(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            	Projet.textureClick = -1;
            	System.out.println("Id : " + Projet.textureClick);
            }
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        };
        
        MouseMotionListener mm = new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent paramMouseEvent) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				
			}
		};
        
        int hauteur = height/8 - 34;
		
		for(int i=0; i<listeTileTexture.size(); i++) {
			LabelCustomTexture p = new LabelCustomTexture(i);
			p.addMouseListener(ml);
			//p.addMouseMotionListener(mm);
			
			//p.setBounds(300,300,100,100);
			//p.setBounds(width-(hauteur+30), ((hauteur+20)*i) + 50, hauteur, hauteur);
			p.setSize(hauteur, hauteur);
			//p.setPreferredSize(new Dimension(hauteur, hauteur));
			p.setTransferHandler(new TransferHandler("icon"));
			p.setBackground(Color.white);
			p.setIcon(new IconCustom(listeTileTexture.get(i).getScaledInstance(hauteur, hauteur, Image.SCALE_DEFAULT),i));
			p.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
			p.setOpaque(true);
			p.setVisible(true);
			j.add(p);
		}
		
		/*int hauteur = (height - (2*(height/listeTileTexture.size()) ))/listeTileTexture.size();
		g.drawLine(width-hauteur*2, 0, width-hauteur*2, height);
		for(int i=0; i<listeTileTexture.size(); i++) {
			g.drawImage(listeTileTexture.get(i), width-(hauteur+30), ((hauteur+20)*i) + 50, hauteur, hauteur,  null);
			
		}*/
		return j;
	}
}
