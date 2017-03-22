package Projet;

import gestionTexture.GestionTexture;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.TransferHandler;

import dragAndDrop.MouseGlassListener;
import dragAndDrop.MouseGlassMotionListener;
import IHM.Frame;

public class Niveau implements Serializable{
	
	private int nbCasex;
	private int nbCasey;
	private String fichierImage;
	
	int width = 800;
	int height = 450;
	
	int id;
	
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
	
	public ArrayList<HashMap<Integer, JLabel>> getLignePlateau() {
		return lignePlateau;
	}

	public String getFichierImage() {
		return fichierImage;
	}
	
	public void setFichierImage(String fichierImage) {
		this.fichierImage = fichierImage;
	}
	
	public void ajouterLigne(HashMap ligne) {
		lignePlateau.add(ligne);
	}
	
	public Component dessinerPlateauCreation(int width, int height, int hauteur) {
		
        int nbCaseX = Frame.p.getListeNiveau().get(Frame.p.getListeNiveau().size()-1).getNbcasex();
        int nbCaseY = Frame.p.getListeNiveau().get(Frame.p.getListeNiveau().size()-1).getNbcasey();
        
		JPanel j = new JPanel();
		j.setLayout(new GridLayout(nbCaseY, nbCaseX));
		
		MouseListener ml = new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
            
            	LabelCustom jc = (LabelCustom)e.getSource();
                TransferHandler th = jc.getTransferHandler();
                th.exportAsDrag(jc, e, TransferHandler.COPY);
               // System.out.println(" x : " + jc.getLigne());
               // System.out.println(" y : " + jc.getColonne());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            	System.out.println("Kevin");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            	/*LabelCustom jc = (LabelCustom)e.getSource();
            	System.out.println(" x : " + jc.getLigne());
                System.out.println(" y : " + jc.getColonne());*/
            }

            @Override
            public void mouseExited(MouseEvent e) {}
        };
        
        if(lignePlateau.isEmpty()) {
			for(int i=0; i<nbCaseY; i++) {
				HashMap<Integer, JLabel> ligne = new HashMap();
				for(int y=0; y<nbCaseX; y++) {
					LabelCustom p = new LabelCustom(i, y);
					p.addMouseListener(new MouseGlassListener(Frame.glass));
				    p.addMouseMotionListener(new MouseGlassMotionListener(Frame.glass));
					//p.addMouseListener(ml);
					//p.setBounds(100+ y*(hauteur), 100 + i*( hauteur), hauteur, hauteur);
					p.setPreferredSize(new Dimension(hauteur, hauteur));
					p.setBackground(Color.white);
					//p.setBounds(100+ y*( (width-200-2*hauteur)/nbCaseX), 100 + i*( (height-200)/nbCaseY), ((width-200-2*hauteur)/nbCaseX), ((height-200)/nbCaseY));
					p.setTransferHandler(new TransferHandler("icon"));
					p.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
					p.setOpaque(true);
					p.setVisible(true);
					j.add(p);
					ligne.put(y, p);
				}
				Frame.p.getListeNiveau().get(Frame.p.getListeNiveau().size()-1).ajouterLigne(ligne);
			}
        }
        else {
        	for(int i=0; i<nbCaseY; i++) {
				for(int y=0; y<nbCaseX; y++) {
					System.out.println(""+i + " " + y);
					lignePlateau.get(i).get(y).setTransferHandler(new TransferHandler("icon"));
					lignePlateau.get(i).get(y).addMouseListener(ml);
					j.add(lignePlateau.get(i).get(y));
				}
			}
        }
		return j;
	}
	
	
	
	
}
