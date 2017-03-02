package gestionTexture;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import javax.swing.TransferHandler;

public class GestionTexture implements Serializable{
	
	private int nbLigne;
	private int nbColonne;
	private String image;
	public transient ArrayList<Image> listeTileTexture;
	
	public GestionTexture() {
		listeTileTexture = new ArrayList<Image>();
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
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
            
                JComponent jc = (JComponent)e.getSource();
                TransferHandler th = jc.getTransferHandler();
                th.exportAsDrag(jc, e, TransferHandler.COPY);
            }

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        };
        
        int hauteur = height/8 - 34;
		
		for(int i=0; i<listeTileTexture.size(); i++) {
			JLabel p = new JLabel();
			p.addMouseListener(ml);
			//p.setBounds(300,300,100,100);
			//p.setBounds(width-(hauteur+30), ((hauteur+20)*i) + 50, hauteur, hauteur);
			p.setSize(hauteur, hauteur);
			//p.setPreferredSize(new Dimension(hauteur, hauteur));
			p.setTransferHandler(new TransferHandler("icon"));
			p.setBackground(Color.white);
			p.setIcon(new ImageIcon(listeTileTexture.get(i).getScaledInstance(hauteur, hauteur, Image.SCALE_DEFAULT)));
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
