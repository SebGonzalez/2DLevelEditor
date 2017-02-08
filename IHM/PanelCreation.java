package IHM;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.TransferHandler;
import javax.swing.filechooser.FileNameExtensionFilter;

public class PanelCreation extends JPanel implements ActionListener {
	
	private JButton test;
	int hauteur;
	boolean oui = false;
	
	public PanelCreation() {
		
		this.setLayout(null);
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
       	this.setSize((int)dimension.getWidth(), (int)dimension.getHeight());
       	
		test = new JButton("test");
		test.setBounds(500,500,100,50);
		test.addActionListener(this);
		this.add(test);
		
		 hauteur = (this.getHeight() - (2*(this.getHeight()/Frame.p.getListeNiveau().get(0).getGestionTexture().listeTileTexture.size()) ))/Frame.p.getListeNiveau().get(0).getGestionTexture().listeTileTexture.size();	
		Frame.p.getListeNiveau().get(0).dessinerPlateauCreation(this, this.getWidth(), this.getHeight(), hauteur);
		Frame.p.getListeNiveau().get(0).getGestionTexture().dessinerTile(this.getWidth(), this.getHeight(), this); 
	}

	public void paintComponent(Graphics g) {
		//Frame.p.getListeNiveau().get(0).getGestionTexture().dessinerTile(g, this.getWidth(), this.getHeight(), this); 
		g.drawLine(this.getWidth()-hauteur*2, 0, this.getWidth()-hauteur*2, this.getHeight());
		g.drawRect(100, 100, this.getWidth()-200-2*hauteur, this.getHeight()-200);
		
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == test) {
			JFileChooser fc = new JFileChooser();
			fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			//fc.addChoosableFileFilter(new FileNameExtensionFilter("Images", "bmp"));
			fc.setFileFilter(new FileNameExtensionFilter("Images (bmp, jpg, png)", "bmp", "jpg", "png"));
			//int returnVal = fc.showOpenDialog(this);
			if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
	    	{	
				System.out.println(fc.getSelectedFile().getAbsolutePath()); //si un fichier est selectionné, récupérer le fichier puis sont path et l'afficher dans le champs de texte
	    	}
		}
		
	}
}
