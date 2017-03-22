package IHM;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelAffichageRendu extends JPanel{
	
	private int id;
	
	public PanelAffichageRendu(int id){
		
		GridLayout gl = new GridLayout(Frame.p.listeNiveau.get(id).getNbcasey(), Frame.p.listeNiveau.get(id).getNbcasex());
		gl.setHgap(0);
		gl.setVgap(0);
		this.setLayout(gl);
		
		for(int i=0; i<Frame.p.listeNiveau.get(id).getLignePlateau().size(); i++){
			for(int y=0; y<Frame.p.listeNiveau.get(id).getNbcasex(); y++) {
				JLabel j = new JLabel();
				j.setBackground(Color.white);
				j.setOpaque(true);
				j.setVisible(true);
				j.setIcon(Frame.p.listeNiveau.get(id).getLignePlateau().get(i).get(y).getIcon());
				this.add(j);
			}
		}
		
		this.setVisible(true);
	}
	
	

}
