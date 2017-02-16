package IHM;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
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
	String cheminSauvegarde;
	int xBarre;
	JScrollPane scrollPane;
	JScrollPane scrollPaneTexture;
	boolean cliqueBarre = false;
	
	private Point lastPosition;
	
	public PanelCreation() {
		
		this.setLayout(null);
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
       	this.setSize((int)dimension.getWidth(), (int)dimension.getHeight());
       	
       	sauvegarder = new JButton("Sauvegarder");
       	sauvegarder.setBounds(50,50,150,50);
       	sauvegarder.addActionListener(this);
		this.add(sauvegarder);
		
		 hauteur = (this.getHeight() - (2*(this.getHeight()/Frame.p.getListeNiveau().get(0).getGestionTexture().listeTileTexture.size()) ))/Frame.p.getListeNiveau().get(0).getGestionTexture().listeTileTexture.size();
		 hauteur = this.getHeight()/8 - 34;
		
		scrollPane=new JScrollPane(Frame.p.getListeNiveau().get(0).dessinerPlateauCreation(this.getWidth(), this.getHeight(), hauteur)); 
		
		//On v�rifi� si le nombre de case est inf�rieur � la taille max
		
		//si oui on d�finit la taille exact (+18 pour la taille de la scrollbar)
		if( (this.getWidth()-200-2*hauteur)/hauteur > Frame.p.getListeNiveau().get(0).getNbcasex()) {
			widthCaseNiveau = Frame.p.getListeNiveau().get(0).getNbcasex() * hauteur + 18;
			xCaseNiveau = (((this.getWidth()-200-2*hauteur) - widthCaseNiveau) / 2) + 10;
		}
		else { //sinon on d�finit la taille maximal possible
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
		this.add(scrollPane);
		
		scrollPaneTexture=new JScrollPane(Frame.p.getListeNiveau().get(0).getGestionTexture().dessinerTile(this.getWidth(), this.getHeight())); 
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
		scrollPane.setBounds(xCaseNiveau, yCaseNiveau, widthCaseNiveau, heightCaseNiveau);
		scrollPaneTexture.setBounds(xBarre+30, 20, widthTexture, this.getHeight()-100);

		g.drawLine(xBarre , 0, xBarre, this.getHeight());
		System.out.println(" boolean : " + cliqueBarre + " x : " + xBarre);
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == sauvegarder) {
			JFileChooser fc = new JFileChooser();
			fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			//fc.addChoosableFileFilter(new FileNameExtensionFilter("Images", "bmp"));
			//fc.setFileFilter(new FileNameExtensionFilter("Images (bmp, jpg, png)", "bmp", "jpg", "png"));
			//int returnVal = fc.showOpenDialog(this);
			if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
	    	{	
				cheminSauvegarde=fc.getSelectedFile().getAbsolutePath();		
				Memoire.save(Frame.p, cheminSauvegarde);
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
			}
			else {
				xBarre = e.getX();
				widthCaseNiveau += Math.abs(lastPosition.getX()-p.getX());
				widthTexture -= Math.abs(lastPosition.getX()-p.getX());
				lastPosition = p;
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
