package Projet;

import gestionTexture.GestionTexture;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.TransferHandler;

import IHM.Frame;

public class Niveau {
	
	private int nbCasex;
	private int nbCasey;

	int width = 800;
	int height = 450;
	
	ArrayList<HashMap<Integer, JLabel>> lignePlateau;
	
	private GestionTexture gestionTexture;
	
	public Niveau() {
		gestionTexture = new GestionTexture();
	}
	
	public Niveau(int nbx, int nby) {
		gestionTexture = new GestionTexture();
		lignePlateau = new ArrayList<>();
		nbCasex=nbx;
		nbCasey=nby;
	}
	
	public GestionTexture getGestionTexture() {
		return gestionTexture;
	}

	public int getNbcasex() {
		return nbCasex;
	}

	public void setNbcasex(int nbcasex) {
		this.nbCasex = nbcasex;
	}

	public int getNbcasey() {
		return nbCasey;
	}

	public void setNbcasey(int nbcasey) {
		this.nbCasey = nbcasey;
	}
	
	public void ajouterLigne(HashMap ligne) {
		lignePlateau.add(ligne);
	}
	
	private static Component createPanel() {
		JPanel panel = new JPanel(new GridLayout(0, 10));
		panel.setSize(100, 100);
 
		for(int i=0; i<100; i++) {
			JPanel c = new JPanel();
			c.setPreferredSize(new Dimension(64, 64));
			c.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			c.setBackground(Color.WHITE);
			panel.add(c);
		}
 
		return panel;
	}
	public Component dessinerPlateauCreation(int width, int height, int hauteur) {
		
        int nbCaseX = Frame.p.getListeNiveau().get(0).getNbcasex();
        int nbCaseY = Frame.p.getListeNiveau().get(0).getNbcasey();
        
		JPanel j = new JPanel();
		j.setLayout(new GridLayout(nbCaseY, nbCaseX));
		
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
        
        
		for(int i=0; i<nbCaseY; i++) {
			HashMap<Integer, JLabel> ligne = new HashMap();
			for(int y=0; y<nbCaseX; y++) {
				JLabel p = new JLabel();
				p.addMouseListener(ml);
				//p.setBounds(100+ y*(hauteur), 100 + i*( hauteur), hauteur, hauteur);
				p.setPreferredSize(new Dimension(hauteur, hauteur));

				//p.setBounds(100+ y*( (width-200-2*hauteur)/nbCaseX), 100 + i*( (height-200)/nbCaseY), ((width-200-2*hauteur)/nbCaseX), ((height-200)/nbCaseY));
				p.setTransferHandler(new TransferHandler("icon"));
				p.setBackground(Color.white);
				p.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
				
				p.setOpaque(true);
				p.setVisible(true);
				j.add(p);
				ligne.put(y, p);
			}
			Frame.p.getListeNiveau().get(0).ajouterLigne(ligne);
		}
		return j;
	}
	
	
	
	
}
