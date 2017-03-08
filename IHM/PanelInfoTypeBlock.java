package IHM;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelInfoTypeBlock extends JPanel implements ActionListener {
	
	JButton plus;
	JButton valider;
	int row = 1;
	
	ArrayList<JTextField> listeTextField;
	GridBagConstraints gbcPlus;
	GridBagConstraints gbcValider;
	
	public PanelInfoTypeBlock() {
		this.setLayout(new GridBagLayout());
		
		listeTextField = new ArrayList<>();
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.insets = new Insets(10, 10, 10, 10);
		this.add(new JLabel("Entrez les types de blocks : "), gbc);
		
		GridBagConstraints gbc2 = new GridBagConstraints();
		gbc2.gridx=0;
		gbc2.gridy=1;
		gbc2.weightx = 1;
		gbc2.weighty = 1;
		
		JTextField champ1 = new JTextField("");
		champ1.setPreferredSize(new Dimension(200, 30));
		champ1.setMinimumSize(new Dimension(200, 30));
		this.add(champ1, gbc2);
		listeTextField.add(champ1);
		
		gbcPlus = new GridBagConstraints();
		gbcPlus.gridx=1;
		gbcPlus.weightx = 1;
		gbcPlus.weighty = 1;
		gbcPlus.gridy=row;
		
		plus = new JButton("+");
		this.add(plus, gbcPlus);
		plus.addActionListener(this);
		
		gbcValider = new GridBagConstraints();
		gbcValider.gridx=0;
		gbcValider.weightx = 1;
		gbcValider.weighty = 1;
		gbcValider.gridy=row+1;
		
		valider = new JButton("Valider");
		valider.addActionListener(this);
		this.add(valider, gbcValider);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == plus) {
			ajoutTextField();
			gbcPlus.gridy=row;
			gbcValider.gridy=row+1;
			((GridBagLayout) this.getLayout()).setConstraints(plus, gbcPlus);
			((GridBagLayout) this.getLayout()).setConstraints(valider, gbcValider);
			this.revalidate();
		}
		else if(e.getSource() == valider) {
			JFrame frame =  (JFrame) this.getTopLevelAncestor();
			if(Frame.p.getListeNiveau().get(0).getGestionTexture().setListeTypeBlock(listeTextField)) {
				Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		        frame.setSize((int)dimension.getWidth(), (int)dimension.getHeight()-40);
		   		frame.setLocationRelativeTo(null);
		   		frame.getContentPane().removeAll();
		   		frame.setContentPane(new PanelSelectionTexture());
		   		frame.repaint();
		   		frame.validate();
			}
			else {
				JOptionPane.showMessageDialog(frame, "Veuillez remplir au moins un champ !");
			}
		}
		
	}
	
	public void ajoutTextField() {
		GridBagConstraints gbc3 = new GridBagConstraints();
		this.row++;
		gbc3.gridx=0;
		gbc3.gridy=row;
		gbc3.weightx = 1;
		gbc3.weighty = 1;
		JTextField champ1 = new JTextField("");
		champ1.setPreferredSize(new Dimension(200, 30));
		champ1.setMinimumSize(new Dimension(200, 30));
		this.add(champ1, gbc3);
		listeTextField.add(champ1);
	}
}
