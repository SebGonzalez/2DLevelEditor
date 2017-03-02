package IHM;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import Projet.Niveau;

public class PanelInfoCrea extends JPanel implements ActionListener{
	
	private JLabel taillex;
	private JTextField tftaillex;
	private JLabel tailley;
	private JTextField tftailley;
	private JLabel nombredec;
	private JTextField tfnombredec;
	private JLabel nombreLigne;
	private JTextField tfnombreLigne;
	private JLabel src;
	private JTextField tfsrc;
	private JButton validation;
	private JButton chemin;
	
	public File imagesrc;
	public File imagedest;
	private String fdest;
	private String pathsource;
	
public PanelInfoCrea() {
	
	this.setLayout(null);
	
	taillex = new JLabel("Nombre de cases en largeur :");
	taillex.setBounds(50, 10, 200, 25);
	tailley = new JLabel("Nombre de cases en hauteur :");
	tailley.setBounds(50, 40, 200, 25);
	src = new JLabel("src :");
	src.setBounds(50, 70, 50, 25);
	nombredec = new JLabel("Nombre de colonne de l'image:");
	nombredec.setBounds(50, 100, 200, 25);
	nombreLigne = new JLabel("Nombre de ligne de l'image:");
	nombreLigne.setBounds(50, 130, 200, 25);
	
	
	tftaillex = new JTextField("100");
	tftaillex.setBounds(250, 10, 100, 25);
	tftailley = new JTextField("100");
	tftailley.setBounds(250, 40, 100, 25);
	tfsrc = new JTextField("");
	tfsrc.setBounds(100, 70, 250, 25);
	tfnombredec = new JTextField("16");
	tfnombredec.setBounds(250, 100, 100, 25);
	tfnombreLigne = new JTextField("12");
	tfnombreLigne.setBounds(250, 130, 100, 25);
	
	
	
	this.add(taillex);
	this.add(tailley);
	this.add(nombredec);
	this.add(tftaillex);
	this.add(tftailley);
	this.add(src);
	this.add(tfsrc);
	this.add(tfnombredec);
	this.add(nombreLigne);
	this.add(tfnombreLigne);
		
	validation = new JButton("Valider");
	validation.setBounds(150, 190, 200, 50);
	validation.addActionListener(this);
	this.add(validation);
	
	chemin = new JButton("SRC");
	chemin.setBounds(350,70,25,25);
	chemin.addActionListener(this);
	this.add(chemin);
	
	}

public boolean estUnEntier(String chaine) {
	try {
		Integer.parseInt(chaine);
	} catch (NumberFormatException e){
		return false;
	}

	return true;
}

@Override
public void actionPerformed(ActionEvent e) {
	JFrame frame =  (JFrame) this.getTopLevelAncestor();
    
	if(e.getSource() == validation) {
		
     if (estUnEntier(tftaillex.getText())&& estUnEntier(tftailley.getText())&& estUnEntier(tfnombredec.getText())){
    	 
    	   int x= Integer.parseInt(tftaillex.getText());
           int y= Integer.parseInt(tftailley.getText());
           int nbdc=Integer.parseInt(tfnombredec.getText());
           int nbdl=Integer.parseInt(tfnombreLigne.getText());
           Niveau n = new Niveau(x,y);
           Frame.p.ajouterNiveau(n);
           Frame.p.getListeNiveau().get(0).getGestionTexture().setNbColonne(nbdc);
           Frame.p.getListeNiveau().get(0).getGestionTexture().setNbLigne(nbdl);
           Frame.p.getListeNiveau().get(0).getGestionTexture().setImage("C:/Users/gonzo/Desktop/tilea2.bmp");
           Frame.p.getListeNiveau().get(0).getGestionTexture().decouperImage();
          
           frame.setSize(400,500);
			frame.setLocationRelativeTo(null);
			frame.getContentPane().removeAll();
	        frame.setContentPane(new PanelInfoTypeBlock());
	        frame.repaint();
	        frame.validate();
        }
     else{
    	 JOptionPane.showMessageDialog(this,"Veuillez entrer des chiffres");
     }
      
       
}
	else if(e.getSource() == chemin){
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		//fc.addChoosableFileFilter(new FileNameExtensionFilter("Images", "bmp"));
		fc.setFileFilter(new FileNameExtensionFilter("Images (bmp, jpg, png)", "bmp", "jpg", "png"));
		
		//int returnVal = fc.showOpenDialog(this);
		if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
    	{	
			imagesrc = fc.getSelectedFile();
			pathsource = imagesrc.getPath();
			tfsrc.setText(imagesrc.getPath());
			/*
			fdest = imagesrc.getName();
			imagedest = new File("D:/TESTIMAGE/bo/"+fdest);
			String path = System.getProperty("user.dir");//chemin du projet
			imagedest = new File(path+"/src/image/"+fdest);//chemin ou le fichier devra etre copi�
			
			System.out.println("image source : " + imagesrc);
			System.out.println("chemin destination : " + imagedest);
			System.out.println(fc.getSelectedFile().getAbsolutePath()); //si un fichier est selectionn�, r�cup�rer le fichier puis sont path et l'afficher dans le champs de texte
			System.out.println("source projet : " + path);*/
    	}
	}
}

}
