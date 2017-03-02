package IHM;
import gestionTexture.IconCustom;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

import Projet.Memoire;

public class PanelCreation extends JPanel implements ActionListener, MouseListener, MouseMotionListener {
	
	private JButton sauvegarder;
	int hauteur;
	boolean oui = false;
	
	int xCaseNiveau;
	int yCaseNiveau;
	int widthCaseNiveau;
	int heightCaseNiveau;
	int widthTexture;
	int hauteurFrame;
	String cheminSauvegarde;
	int xBarre;
	JScrollPane scrollPane;
	JScrollPane scrollPaneTexture;
	boolean cliqueBarre = false;
	
	JLabel test;
	
	private Point lastPosition;
	
	public PanelCreation() {
		
		this.setLayout(null);
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
       	this.setSize((int)dimension.getWidth(), (int)dimension.getHeight());
       	hauteurFrame = this.getHeight();
       	
       	ComponentListener componentListener = new ComponentListener() {
			@Override
			public void componentHidden(ComponentEvent arg0) {}
			@Override
			public void componentMoved(ComponentEvent arg0) {}
			@Override
			public void componentResized(ComponentEvent arg0) {
				hauteurFrame = getHeight();
			}
			@Override
			public void componentShown(ComponentEvent arg0) {}
       	};
       	this.addComponentListener(componentListener);
       	
       	sauvegarder = new JButton("Enregistrer");
       	sauvegarder.setBounds(50,50,150,50);
       	sauvegarder.addActionListener(this);
		this.add(sauvegarder);
		
		 hauteur = (this.getHeight() - (2*(this.getHeight()/Frame.p.getListeNiveau().get(0).getGestionTexture().listeTileTexture.size()) ))/Frame.p.getListeNiveau().get(0).getGestionTexture().listeTileTexture.size();
		 hauteur = this.getHeight()/8 - 34;
		
		scrollPane=new JScrollPane(Frame.p.getListeNiveau().get(0).dessinerPlateauCreation(this.getWidth(), this.getHeight(), hauteur)); 
		
		//On vérifié si le nombre de case est inférieur à la taille max
		
		//si oui on définit la taille exact (+18 pour la taille de la scrollbar)
		if( (this.getWidth()-200-2*hauteur)/hauteur > Frame.p.getListeNiveau().get(0).getNbcasex()) {
			widthCaseNiveau = Frame.p.getListeNiveau().get(0).getNbcasex() * hauteur + 18;
			xCaseNiveau = (((this.getWidth()-200-2*hauteur) - widthCaseNiveau) / 2) + 10;
		}
		else { //sinon on définit la taille maximal possible
			widthCaseNiveau = this.getWidth()-200-2*hauteur;
			xCaseNiveau = 10;
		}
		if( (this.getHeight()-200)/hauteur > Frame.p.getListeNiveau().get(0).getNbcasey()) {
			heightCaseNiveau = Frame.p.getListeNiveau().get(0).getNbcasey() * hauteur + 18;
			yCaseNiveau = (((this.getHeight()-200) - heightCaseNiveau) / 2) + 100;
		}
		else {
			heightCaseNiveau = this.getHeight()-200;
			yCaseNiveau = 100;
		}
		scrollPane.setBounds(xCaseNiveau, yCaseNiveau, widthCaseNiveau, heightCaseNiveau);
		scrollPane.getViewport ().setScrollMode ( JViewport.SIMPLE_SCROLL_MODE );
		scrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
			 
			@Override
			public void adjustmentValueChanged(AdjustmentEvent arg0) { 
				revalidate();
				repaint(); 
			}
		});
		scrollPane.getHorizontalScrollBar().addAdjustmentListener(new AdjustmentListener() {
 
			@Override
			public void adjustmentValueChanged(AdjustmentEvent arg0) { 
				revalidate();
				repaint(); 
			}
		});
		scrollPane.setBounds(xCaseNiveau, yCaseNiveau, widthCaseNiveau, heightCaseNiveau);
		this.add(scrollPane);
		
		scrollPaneTexture=new JScrollPane(Frame.p.getListeNiveau().get(0).getGestionTexture().dessinerTile(this.getWidth(), this.getHeight())); 
		scrollPaneTexture.setBounds(xBarre+30, 20, widthTexture, hauteurFrame-100);
		scrollPaneTexture.setBounds(widthCaseNiveau+xCaseNiveau+60, 20, this.getWidth() - (widthCaseNiveau+xCaseNiveau+60)-30, this.getHeight()-100);
		scrollPaneTexture.getViewport ().setScrollMode ( JViewport.SIMPLE_SCROLL_MODE );
		this.add(scrollPaneTexture);
		
		xBarre = widthCaseNiveau+xCaseNiveau+30;
		widthTexture = this.getWidth()-(widthCaseNiveau+xCaseNiveau+60)-30;
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	public void paintComponent(Graphics g) { 
		super.paintComponent(g);
		g.drawLine(xBarre , 0, xBarre, this.getHeight());
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//KEVIN
		/*if(e.getSource() == sauvegarder) {
			JFileChooser fc = new JFileChooser();
			fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			//fc.addChoosableFileFilter(new FileNameExtensionFilter("Images", "bmp"));
			//fc.setFileFilter(new FileNameExtensionFilter("Images (bmp, jpg, png)", "bmp", "jpg", "png"));
			//int returnVal = fc.showOpenDialog(this);
			if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
	    	{	
				cheminSauvegarde=fc.getSelectedFile().getAbsolutePath();		
				Memoire.save(Frame.p, cheminSauvegarde);
	    	}*/
		
		if(e.getSource() == sauvegarder) {
			JFileChooser fc = new JFileChooser();
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
	    	{	
				cheminSauvegarde=fc.getSelectedFile().getAbsolutePath();		
				Frame.p.export(cheminSauvegarde);
	    	}
		}	
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}
	@Override
	public void mouseEntered(MouseEvent e) {	
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getX() < xBarre+20 && e.getX()> xBarre-20) {
			cliqueBarre = true;
			lastPosition = e.getPoint();
		}
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		cliqueBarre = false;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		Point p = e.getPoint();
		if(cliqueBarre) {
			if(p.getX() < lastPosition.getX()) {
				xBarre = e.getX();
				widthCaseNiveau -= lastPosition.getX()-p.getX();
				widthTexture += lastPosition.getX()-p.getX();
				lastPosition = p;
				scrollPane.setBounds(xCaseNiveau, yCaseNiveau, widthCaseNiveau, heightCaseNiveau);
				scrollPaneTexture.setBounds(xBarre+30, 20, widthTexture, hauteurFrame-100);
			}
			else {
				xBarre = e.getX();
				widthCaseNiveau += Math.abs(lastPosition.getX()-p.getX());
				widthTexture -= Math.abs(lastPosition.getX()-p.getX());
				lastPosition = p;
				scrollPane.setBounds(xCaseNiveau, yCaseNiveau, widthCaseNiveau, heightCaseNiveau);
				scrollPaneTexture.setBounds(xBarre+30, 20, widthTexture, hauteurFrame-100);
			}
			repaint();
			scrollPane.revalidate();
			scrollPaneTexture.revalidate();
		}
		
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if(e.getX() < xBarre+20 && e.getX()> xBarre-20) {
			setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
		}
		else {
			setCursor(Cursor.getDefaultCursor());
		}
	}
}
