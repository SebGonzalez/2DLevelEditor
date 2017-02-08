package IHM;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class PanelCreation extends JPanel implements ActionListener {
	
	private JButton test;
	
	public PanelCreation() {
		this.setSize(1000,900);
		
		
		test = new JButton("test");
		test.setSize(100,50);
		test.addActionListener(this);
		this.add(test);
	}

	public void paintComponent(Graphics g) {
		Frame.p.getListeNiveau().get(0).getGestionTexture().dessinerTile(g); 
		
        //repaint();
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
