package IHM;

import gestionTexture.IconCustom;
import gestionTexture.LabelCustomTexture;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class PanelSelectionTexture extends JPanel implements KeyListener{
	
	public JButton valider;
	ArrayList<Integer> choixTexture = new ArrayList<Integer>();
	
	String typeBlock;
	int compteurTexture = 1;
	JPanel texture;
	ArrayList<LabelCustomTexture> listeJLabel = new ArrayList<LabelCustomTexture>();
	boolean click = false;
	boolean shift = false;
	
	public PanelSelectionTexture() {
		
		this.setLayout(null);
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
       	this.setSize((int)dimension.getWidth(), (int)dimension.getHeight());;
       	
       	typeBlock = Frame.p.getListeNiveau().get(0).getGestionTexture().listeTypeBlock.get(0);
       	JLabel labelSelection = new JLabel("Sélectionner toute les texture de type : " + typeBlock + " (cliquer sur valider ensuite)");
       	labelSelection.setBounds(10,10,900,25);
       	labelSelection.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 25));
       	this.add(labelSelection);
       	
       	JButton valider = new JButton("Valider");
       	valider.setBounds(this.getWidth()-360, 5, 300, 40);
       	valider.addActionListener(new ActionListener() {	
       		
			@Override
			public void actionPerformed(ActionEvent e) {
				if(compteurTexture < Frame.p.getListeNiveau().get(0).getGestionTexture().listeTypeBlock.size()) {
					typeBlock = Frame.p.getListeNiveau().get(0).getGestionTexture().listeTypeBlock.get(compteurTexture);
					labelSelection.setText("Sélectionner toute les texture de type : " + typeBlock + " (cliquer sur valider ensuite)");
					compteurTexture++;
					resetBorder();
					repaint();
					revalidate();
				}
				else {
					JFrame frame =  (JFrame)PanelSelectionTexture.this.getTopLevelAncestor();
					Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
			        frame.setSize((int)dimension.getWidth(), (int)dimension.getHeight()-40);
			   		frame.setLocationRelativeTo(null);
			   		frame.getContentPane().removeAll();
			   		frame.setContentPane(new PanelCreation());
			   		frame.repaint();
			   		frame.validate();
				}
			}
		});
       	this.add(valider);
       	
       	initPanelTexture();
       	addKeyListener(this);
       	this.setFocusable(true);
       	this.requestFocusInWindow();
       	this.setVisible(true);
       	//this.setFocusable(true);
       	
       	KeyboardFocusManager focusManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
       	focusManager.addPropertyChangeListener(
       	    new PropertyChangeListener() {
       	        public void propertyChange(PropertyChangeEvent e) {
       	            String properties = e.getPropertyName();
       	            if (("focusOwner".equals(properties)) && (e.getNewValue() != null)) {
       	                Component component = (Component)e.getNewValue();
       	                String name = component.getName();
       	 
       	               	System.out.println(name + " a pris le focus");
       	            }
       	        }
       	    }
       	);
		
	}
	
	public void initPanelTexture() {
		texture = new JPanel();
       	texture.setLayout(new GridLayout(Frame.p.getListeNiveau().get(0).getGestionTexture().getNbLigne(), Frame.p.getListeNiveau().get(0).getGestionTexture().getNbColonne()));
		texture.setBounds(50,50,this.getWidth()-100, this.getHeight()-100);
       	
       	MouseListener ml = new MouseListener() {

            @Override
            public void mousePressed(MouseEvent e) {
            	click = true;
            	LabelCustomTexture j = (LabelCustomTexture) e.getSource();
            	if(j.isEnabled()) {
	            	if(!j.isEstCoche()) {
	            		j.setBorder(BorderFactory.createMatteBorder( 5, 5, 5, 5, Color.red));
	            	}
	            	else {
	            		j.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
	            	}
            	}
            	else {
            		JOptionPane.showMessageDialog(PanelSelectionTexture.this.getTopLevelAncestor(), "La texture a déjà été associé à un type");
            	}
            	j.modifSelection();
            }
            @Override
            public void mouseClicked(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            	click = false;
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            	if(click == true) {
            		LabelCustomTexture j = (LabelCustomTexture) e.getSource();
                	if(j.isEnabled()) {
    	            	if(!j.isEstCoche()) {
    	            		j.setBorder(BorderFactory.createMatteBorder( 5, 5, 5, 5, Color.red));
    	            	}
    	            	else {
    	            		j.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
    	            	}
                	}
                	else {
                		JOptionPane.showMessageDialog(PanelSelectionTexture.this.getTopLevelAncestor(), "La texture a déjà été associé à un type");
                	}
                	j.modifSelection();
            	}
            }
            @Override
            public void mouseExited(MouseEvent e) {}
        };
              
        int hauteur = (this.getWidth()-100)/Frame.p.getListeNiveau().get(0).getGestionTexture().getNbLigne();
        int hauteurH = (this.getHeight()-100)/Frame.p.getListeNiveau().get(0).getGestionTexture().getNbColonne();
        System.out.println(hauteur + " "+hauteurH);
		
		for(int i=0; i<Frame.p.getListeNiveau().get(0).getGestionTexture().listeTileTexture.size(); i++) {
			LabelCustomTexture p = new LabelCustomTexture(i);
			p.addMouseListener(ml);
			p.addKeyListener(this);
			//p.addMouseMotionListener(mm);
			p.setMinimumSize(new Dimension(hauteur, hauteurH));
			p.setPreferredSize(new Dimension(hauteur, hauteurH));
			p.setIcon(new IconCustom(Frame.p.getListeNiveau().get(0).getGestionTexture().listeTileTexture.get(i).getScaledInstance(hauteur, hauteurH, Image.SCALE_DEFAULT),i));
			p.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
			p.setOpaque(true);
			p.setVisible(true);
			listeJLabel.add(p);
			texture.add(p);
		}
		
		this.add(texture);
	}
	
	public void resetBorder() {
		for(int i=0; i<listeJLabel.size(); i++) {
			if(listeJLabel.get(i).isEstCoche()) {
				listeJLabel.get(i).setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
				listeJLabel.get(i).setEnabled(false);
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent paramKeyEvent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//System.out.println(e);
		//System.out.println(e.getKeyCode());
		if(e.getKeyCode() == 16) {
			System.out.println("oui");
		}
		
	}

	@Override
	public void keyReleased(KeyEvent paramKeyEvent) {
		// TODO Auto-generated method stub
		
	}

}
