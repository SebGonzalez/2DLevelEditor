package IHM;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.TransferHandler;
import javax.swing.filechooser.FileNameExtensionFilter;

public class PanelCreation extends JPanel implements ActionListener {
	
	int hauteur;
	boolean oui = false;
	
	int xCaseNiveau;
	int yCaseNiveau;
	int widthCaseNiveau;
	int heightCaseNiveau;
	
	public PanelCreation() {
		
		this.setLayout(null);
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
       	this.setSize((int)dimension.getWidth(), (int)dimension.getHeight());
		
		 hauteur = (this.getHeight() - (2*(this.getHeight()/Frame.p.getListeNiveau().get(0).getGestionTexture().listeTileTexture.size()) ))/Frame.p.getListeNiveau().get(0).getGestionTexture().listeTileTexture.size();
		 hauteur = this.getHeight()/8 - 34;
		
		JScrollPane scrollPane=new JScrollPane(Frame.p.getListeNiveau().get(0).dessinerPlateauCreation(this.getWidth(), this.getHeight(), hauteur)); 
		
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
		this.add(scrollPane);
		
		JScrollPane scrollPaneTexture=new JScrollPane(Frame.p.getListeNiveau().get(0).getGestionTexture().dessinerTile(this.getWidth(), this.getHeight())); 
		scrollPaneTexture.setBounds(widthCaseNiveau+xCaseNiveau+60, 20, this.getWidth() - (widthCaseNiveau+xCaseNiveau+60)-30, this.getHeight()-100);
		scrollPaneTexture.getViewport ().setScrollMode ( JViewport.SIMPLE_SCROLL_MODE );
		this.add(scrollPaneTexture);
	}

	public void paintComponent(Graphics g) {
		//Frame.p.getListeNiveau().get(0).getGestionTexture().dessinerTile(g, this.getWidth(), this.getHeight(), this); 
		g.drawLine(widthCaseNiveau+xCaseNiveau+30 , 0, widthCaseNiveau+xCaseNiveau+30, this.getHeight());
		//g.drawRect(100, 100, this.getWidth()-200-2*hauteur, this.getHeight()-200);
		//g.drawImage(Frame.p.listeNiveau.get(0).getGestionTexture().decouperImage("C:/Users/gonzo/Desktop/tiles.bmp"), 0,0,257,257, null);
		
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
}
