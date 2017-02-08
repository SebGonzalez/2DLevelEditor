package IHM;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Projet.Niveau;

public class PanelInfoCrea extends JPanel implements ActionListener{
	
	private JLabel taillex;
	private JTextField tftaillex;
	private JLabel tailley;
	private JTextField tftailley;
	private JButton validation;
	
public PanelInfoCrea() {
	
	this.setLayout(null);
	
	taillex = new JLabel("Nombre de cases en largeur :");
	taillex.setBounds(50, 10, 200, 25);
	tailley = new JLabel("Nombre de cases en hauteur :");
	tailley.setBounds(50, 40, 200, 25);
	
	tftaillex = new JTextField();
	tftaillex.setBounds(250, 10, 100, 25);
	tftailley = new JTextField();
	tftailley.setBounds(250, 40, 100, 25);
	
	this.add(taillex);
	this.add(tailley);
	this.add(tftaillex);
	this.add(tftailley);
		
	validation = new JButton("Valider");
	validation.setBounds(150, 90, 200, 50);
	validation.addActionListener(this);
	this.add(validation);
	
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
		
     if (estUnEntier(tftaillex.getText())&& estUnEntier(tftailley.getText())){
    	 
        int x= Integer.parseInt(tftaillex.getText());
           int y= Integer.parseInt(tftailley.getText());
           Niveau n = new Niveau(x,y);
           Frame.p.ajouterNiveau(n);
           Frame.p.getListeNiveau().get(0).getGestionTexture().decouperImage("src/tileset1.bmp", 8);
           
           	//frame.setExtendedState(frame.MAXIMIZED_BOTH);
           Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
           	frame.setSize((int)dimension.getWidth(), (int)dimension.getHeight());
   			frame.setLocationRelativeTo(null);
   			frame.getContentPane().removeAll();
   			frame.setContentPane(new PanelCreation());
   			frame.repaint();
   			frame.validate();
        }
     else{
    	 JOptionPane.showMessageDialog(this,"Veuillez entrer des chiffres");
     }
      
       
}
}
}
