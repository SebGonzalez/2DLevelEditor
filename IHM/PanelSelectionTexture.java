package IHM;

import gestionTexture.IconCustom;
import gestionTexture.LabelCustomTexture;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PanelSelectionTexture extends JPanel implements ActionListener{
	
	public JButton valider;
	
	public PanelSelectionTexture() {
		
		this.setLayout(null);
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
       	this.setSize((int)dimension.getWidth(), (int)dimension.getHeight());;
       	
       	JPanel texture = new JPanel();
       	texture.setLayout(new GridLayout(Frame.p.getListeNiveau().get(0).getGestionTexture().getNbLigne(), Frame.p.getListeNiveau().get(0).getGestionTexture().getNbColonne()));
		texture.setBounds(50,50,this.getWidth()-100, this.getHeight()-100);
       	
       	MouseListener ml = new MouseListener() {

            @Override
            public void mousePressed(MouseEvent e) {
            	LabelCustomTexture j = (LabelCustomTexture) e.getSource();
            	j.setBorder(BorderFactory.createMatteBorder( 5, 5, 5, 5, Color.red));
            }
            @Override
            public void mouseClicked(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            	
            }
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        };
        
        
        int hauteur = (this.getWidth()-100)/Frame.p.getListeNiveau().get(0).getGestionTexture().getNbLigne();
        int hauteurH = (this.getHeight()-100)/Frame.p.getListeNiveau().get(0).getGestionTexture().getNbColonne();
        System.out.println(hauteur + " "+hauteurH);
		
		for(int i=0; i<Frame.p.getListeNiveau().get(0).getGestionTexture().listeTileTexture.size(); i++) {
			LabelCustomTexture p = new LabelCustomTexture(i);
			p.addMouseListener(ml);
			p.setMinimumSize(new Dimension(hauteur, hauteurH));
			p.setPreferredSize(new Dimension(hauteur, hauteurH));
			p.setIcon(new IconCustom(Frame.p.getListeNiveau().get(0).getGestionTexture().listeTileTexture.get(i).getScaledInstance(hauteur, hauteurH, Image.SCALE_DEFAULT),i));
			p.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
			p.setOpaque(true);
			p.setVisible(true);
			texture.add(p);
		}
		
		this.add(texture);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFrame frame =  (JFrame) this.getTopLevelAncestor();
		if(e.getSource() == valider) {
			 Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	           	frame.setSize((int)dimension.getWidth(), (int)dimension.getHeight()-30);
	   			frame.setLocationRelativeTo(null);
	   			frame.getContentPane().removeAll();
	   			frame.setContentPane(new PanelCreation());
	   			frame.repaint();
	   			frame.validate();
		}
		
	}
}
