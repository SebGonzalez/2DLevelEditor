package IHM;

import java.awt.Dimension;
import java.awt.Font;
import Projet.*;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class PanelPrincipal extends JPanel implements ActionListener {
	
	private JLabel titre;
	private JButton creer;
	private JButton modifier;
	
	
	public PanelPrincipal() {
		this.setLayout(null);
		
		Font f = new Font("Arial",Font.BOLD, 25);
		
		titre = new JLabel("Level Editor");
		titre.setFont(f);
		titre.setHorizontalAlignment(JLabel.CENTER);
		titre.setBounds(0, 10, 500, 50);
		this.add(titre);
		
		creer = new JButton("Cr�er un projet");
		creer.setBounds(50, 80, 190, 50);
		creer.addActionListener(this);
		this.add(creer);
		
		modifier = new JButton("Modifier un projet");
		modifier.setBounds(250, 80, 190, 50);
		modifier.addActionListener(this);
		this.add(modifier);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFrame frame =  (JFrame) this.getTopLevelAncestor();
    
		if(e.getSource() == creer) {
			//frame.setExtendedState(frame.MAXIMIZED_BOTH);
			frame.setSize(500,300);
			frame.setLocationRelativeTo(null);
			frame.getContentPane().removeAll();
	        frame.setContentPane(new PanelInfoCrea());
	        frame.repaint();
	        frame.validate();
		}
		
		if(e.getSource() == modifier) {
			JFileChooser fc = new JFileChooser();
			fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			//fc.addChoosableFileFilter(new FileNameExtensionFilter("Images", "bmp"));
			//fc.setFileFilter(new FileNameExtensionFilter("Images (bmp, jpg, png)", "bmp", "jpg", "png"));
			//int returnVal = fc.showOpenDialog(this);
			if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
	    	{	
				System.out.println(fc.getSelectedFile().getAbsolutePath()); //si un fichier est selectionn�, r�cup�rer le fichier puis sont path et l'afficher dans le champs de texte
				String cheminSauvegarde=fc.getSelectedFile().getAbsolutePath();		
				
				Object o = Memoire.read(cheminSauvegarde);
				Frame.p=(Projet)o;
				
				//System.out.println(Frame.p.getListeNiveau().get(0).getGestionTexture().listeTileTexture.size());
				Frame.p.getListeNiveau().get(0).getGestionTexture().decouperImage(Frame.p.getListeNiveau().get(0).getFichierImage(),8 );
				//System.out.println(Frame.p.getListeNiveau().get(0).getFichierImage());
				//System.out.println(Frame.p.getListeNiveau().get(0));//Projet.Projet@4d0eb98c
				System.out.println(Frame.p.getListeNiveau().get(0).getFichierImage().toString());
				
				Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	           	frame.setSize((int)dimension.getWidth(), (int)dimension.getHeight());
	   			frame.setLocationRelativeTo(null);
	   			frame.getContentPane().removeAll();
	   			frame.setContentPane(new PanelCreation());
	   			frame.repaint();
	   			frame.validate();
				
	    	}
			
		}
	}
}
