package IHM;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Projet.Memoire;
import Projet.Projet;



public class PanelPrincipal extends JPanel implements ActionListener {
	
	private JLabel titre;
	private JButton creer;
	private JButton modifier;
	private Image img ;
	
	
	public PanelPrincipal() {
		this.setLayout(null);
		
		try {
			img = ImageIO.read(new FileInputStream("/image/site.jpg"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Font f = new Font("Arial",Font.BOLD, 25);
		
		titre = new JLabel("Level Editor");
		titre.setFont(f);
		titre.setForeground( Color.white);
		titre.setHorizontalAlignment(JLabel.CENTER);
		titre.setBounds(0, 10, 500, 50);
		this.add(titre);
		
		creer = new JButton("Créer un projet");
		creer.setBounds(50, 80, 190, 50);
		creer.addActionListener(this);
		this.add(creer);
		
		modifier = new JButton("Modifier un projet");
		modifier.setBounds(250, 80, 190, 50);
		modifier.addActionListener(this);
		this.add(modifier);
		
	}

	
public void paintComponent(Graphics g){
	super.paintComponent(g);
	g.drawImage(img,0, 0, this.getWidth(),this.getHeight(),null);
	System.out.println(this.getHeight());
	System.out.println(this.getWidth());

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

			if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
	    	{	
				System.out.println(fc.getSelectedFile().getAbsolutePath());
				String cheminSauvegarde=fc.getSelectedFile().getAbsolutePath();		
				
				Object o = Memoire.read(cheminSauvegarde);
				Frame.p=(Projet)o;
				Frame.p.listeNiveau.get(0).getGestionTexture().decouperImage();
				
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
