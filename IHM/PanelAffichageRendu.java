package IHM;

import java.awt.GridLayout;

import javax.swing.JPanel;

public class PanelAffichageRendu extends JPanel{
	
	private int id;
	
	public PanelAffichageRendu(int id){
		
		GridLayout gl = new GridLayout(Frame.p.listeNiveau.get(id).getNbcasex(), Frame.p.listeNiveau.get(id).getNbcasey());
		gl.setHgap(0);
		gl.setVgap(0);
		this.setLayout(gl);
		
		for(int i=0; i<Frame.p.listeNiveau.get(id).getLignePlateau().size(); i++){
			for(int y=0; y<Frame.p.listeNiveau.get(id).getNbcasex(); y++) {
				this.add(Frame.p.listeNiveau.get(id).getLignePlateau().get(i).get(y));
			}
		}
		
		this.setVisible(true);
	}
	
	

}
