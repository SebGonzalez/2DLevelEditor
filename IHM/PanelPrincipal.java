package IHM;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
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
		
		creer = new JButton("Créer un projet");
		creer.setBounds(50, 80, 190, 50);
		creer.addActionListener(this);
		this.add(creer);
		
		modifier = new JButton("Modifier un projet");
		modifier.setBounds(250, 80, 190, 50);
		this.add(modifier);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFrame frame =  (JFrame) this.getTopLevelAncestor();
    
		if(e.getSource() == creer) {
			frame.setSize(1000,900);
			frame.setLocationRelativeTo(null);
			frame.getContentPane().removeAll();
	        frame.setContentPane(new PanelCreation());
	        frame.repaint();
	        frame.validate();
		}
		
	}
}
