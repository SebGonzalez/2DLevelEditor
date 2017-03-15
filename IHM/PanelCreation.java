package IHM;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JViewport;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.xml.ws.handler.MessageContext.Scope;

import Projet.Memoire;

public class PanelCreation extends JPanel implements MouseListener, MouseMotionListener {
	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menuSauvegarder = new JMenu("Sauvegarder");
	private JMenu menuAjoutern = new JMenu("Ajouter niveau");
	private JMenu menuSupprimern = new JMenu("Supprimer niveau");
	private JMenu menuExporter = new JMenu("Exporter");

	/*private JMenuItem item1 = new JMenuItem("Ouvrir");
	private JMenuItem item2 = new JMenuItem("Fermer");
	private JMenuItem item3 = new JMenuItem("Lancer");
	private JMenuItem item4 = new JMenuItem("Arrêter");*/
	
	int hauteur;
	boolean oui = false;
	
	int xCaseNiveau;
	int yCaseNiveau;
	int widthCaseNiveau;
	int heightCaseNiveau;
	int widthTexture;
	int hauteurFrame;
	String cheminSauvegarde;
	int xBarre;
	
	private JTabbedPane pane;
	private JTabbedPane paneTexture;
	JScrollPane scrollPane;
	JScrollPane scrollPaneTexture;
	boolean cliqueBarre = false;
	
	JLabel test;
	
	private Point lastPosition;
	int nomniveaux = 0;
	
	public PanelCreation() {
		
		this.setLayout(null);
		this.setBackground(new Color(241,241,241));//226,234,247
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
       	this.setSize((int)dimension.getWidth(), (int)dimension.getHeight());
       	hauteurFrame = this.getHeight();
       	
       	ComponentListener componentListener = new ComponentListener() {
			@Override
			public void componentHidden(ComponentEvent arg0) {}
			@Override
			public void componentMoved(ComponentEvent arg0) {}
			@Override
			public void componentResized(ComponentEvent arg0) {
				hauteurFrame = getHeight();
			}
			@Override
			public void componentShown(ComponentEvent arg0) {}
       	};
       	this.addComponentListener(componentListener);
       	
  /*---------------------------------------------------Menu-------------------------------------------------------- */	
       	
       	Font font = new Font("serial", Font.PLAIN, 17);
          	
       	menuAjoutern.setFont(font);
       	menuAjoutern.setForeground(Color.black);
       	menuAjoutern.setBorder(BorderFactory.createEmptyBorder(0, 50, 0,50));
       	menuAjoutern.addMouseListener(this);
       	
       	menuSupprimern.setFont(font);
       	menuSupprimern.setForeground(Color.black);
       	menuSupprimern.setBorder(BorderFactory.createEmptyBorder(0, 50, 0,50));
       	
       	menuSauvegarder.setFont(font);
       	menuSauvegarder.setForeground(Color.black);
       	menuSauvegarder.setBorder(BorderFactory.createEmptyBorder(0, 50, 0,50));
       	menuSauvegarder.addMouseListener(this);
       	
       	menuExporter.setFont(font);
       	menuExporter.setForeground(Color.black);
       	menuExporter.setBorder(BorderFactory.createEmptyBorder(0, 50, 0,50));
       	menuExporter.addMouseListener(this);

       	this.menuBar.add(menuAjoutern);
        this.menuBar.add(menuSupprimern);
        this.menuBar.add(menuSauvegarder);
        this.menuBar.add(menuExporter);
        
        
        menuBar.setBounds(0,0,this.getWidth(),30);
       // menuBar.setBackground(Color.BLUE);
        menuBar.setBackground(new Color(43, 87, 154));
        this.add(menuBar);
        
        /*JFrame frame = (JFrame)this.getTopLevelAncestor();
        frame.setJMenuBar(menuBar);	*/
       	
       	
  /*---------------------------------------------------Menu---------------------------------------------------------*/     	
		
		 hauteur = (this.getHeight() - (2*(this.getHeight()/Frame.p.getListeNiveau().get(0).getGestionTexture().listeTileTexture.size()) ))/Frame.p.getListeNiveau().get(0).getGestionTexture().listeTileTexture.size();
		 hauteur = this.getHeight()/8 - 34;
		
		scrollPane=new JScrollPane(Frame.p.getListeNiveau().get(0).dessinerPlateauCreation(this.getWidth(), this.getHeight(), hauteur)); 
		
		//On vérifié si le nombre de case est inférieur à la taille max
		
		//si oui on définit la taille exact (+18 pour la taille de la scrollbar)
		if( (this.getWidth()-200-2*hauteur)/hauteur > Frame.p.getListeNiveau().get(0).getNbcasex()) {
			widthCaseNiveau = Frame.p.getListeNiveau().get(0).getNbcasex() * hauteur + 18;
			xCaseNiveau = (((this.getWidth()-200-2*hauteur) - widthCaseNiveau) / 2) + 10;
		}
		else { //sinon on définit la taille maximal possible
			widthCaseNiveau = this.getWidth()-200-2*hauteur;
			xCaseNiveau = 10;
		}
		if( (this.getHeight()-200)/hauteur > Frame.p.getListeNiveau().get(0).getNbcasey()) {
			heightCaseNiveau = Frame.p.getListeNiveau().get(0).getNbcasey() * hauteur + 18;
			yCaseNiveau = (((this.getHeight()-200) - heightCaseNiveau) / 2) + 100;
		}
		else {
			heightCaseNiveau = this.getHeight()-150;
			yCaseNiveau = 50;
		}
		scrollPane.setBounds(xCaseNiveau, yCaseNiveau, widthCaseNiveau, heightCaseNiveau);
		scrollPane.getViewport ().setScrollMode ( JViewport.SIMPLE_SCROLL_MODE );
		scrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
			 
			@Override
			public void adjustmentValueChanged(AdjustmentEvent arg0) { 
				revalidate();
				repaint(); 
			}
		});
		scrollPane.getHorizontalScrollBar().addAdjustmentListener(new AdjustmentListener() {
 
			@Override
			public void adjustmentValueChanged(AdjustmentEvent arg0) { 
				revalidate();
				repaint(); 
			}
		});
		this.add(scrollPane);
		
		scrollPaneTexture=new JScrollPane(Frame.p.getListeNiveau().get(0).getGestionTexture().dessinerTile(this.getWidth(), this.getHeight())); 
		scrollPaneTexture.setBounds(widthCaseNiveau+xCaseNiveau+60, yCaseNiveau, this.getWidth() - (widthCaseNiveau+xCaseNiveau+60)-30,heightCaseNiveau);
		scrollPaneTexture.getViewport ().setScrollMode ( JViewport.SIMPLE_SCROLL_MODE );
		this.add(scrollPaneTexture);
		
		xBarre = widthCaseNiveau+xCaseNiveau+30;
		widthTexture = this.getWidth()-(widthCaseNiveau+xCaseNiveau+60)-30;
		addMouseListener(this);
		addMouseMotionListener(this);
		
		pane=new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
		nomniveaux++;
		pane.add("Niveau 1", scrollPane);
		pane.setBounds(xCaseNiveau, yCaseNiveau, widthCaseNiveau, heightCaseNiveau);
		pane.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent paramChangeEvent) {
				paneTexture.setSelectedIndex(pane.getSelectedIndex());
			}
		});
		this.add(pane);
		
		paneTexture=new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
		paneTexture.add("Niveau 1", scrollPaneTexture);
		paneTexture.setBounds(widthCaseNiveau+xCaseNiveau+60, yCaseNiveau, this.getWidth() - (widthCaseNiveau+xCaseNiveau+60)-30,heightCaseNiveau);
		paneTexture.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent paramChangeEvent) {
				pane.setSelectedIndex(paneTexture.getSelectedIndex());
			}
		});
		this.add(paneTexture);
	}

	public void paintComponent(Graphics g) { 
		super.paintComponent(g);
		g.drawLine(xBarre , 0, xBarre, this.getHeight());
    }

	@Override
	public void mouseClicked(MouseEvent e) {
	}
	@Override
	public void mouseEntered(MouseEvent e) {	
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		 if(e.getSource() == menuAjoutern){
			JScrollPane scrollpane;
			JScrollPane scrollPaneTexture;
			
			nomniveaux++;
			
			/*Frame p = new Frame();
			p.setContentPane(new PanelInfoCrea());
			p.setVisible(true);*/
			
			JFrame frame =  (JFrame) this.getTopLevelAncestor();
			JDialog j = new JDialog(frame, true);
			j.add(new PanelInfoCrea());
			j.setSize(500,300);
			j.setVisible(true);
			
			scrollpane = new JScrollPane(Frame.p.getListeNiveau().get(Frame.p.getListeNiveau().size()-1).dessinerPlateauCreation(this.getWidth(), this.getHeight(), hauteur));
			pane.add("Niveau "+nomniveaux ,scrollpane);
			
			scrollPaneTexture=new JScrollPane(Frame.p.getListeNiveau().get(Frame.p.getListeNiveau().size()-1).getGestionTexture().dessinerTile(this.getWidth(), this.getHeight())); 
			paneTexture.add("Niveau " + nomniveaux, scrollPaneTexture);
		}
		 else if(e.getSource() == menuSauvegarder) {
			JFileChooser fc = new JFileChooser();
			fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
	    	{	
				cheminSauvegarde=fc.getSelectedFile().getAbsolutePath();		
				Memoire.save(Frame.p, cheminSauvegarde);
	    	}
		}
		else if(e.getSource() == menuExporter) {
			JFileChooser fc = new JFileChooser();
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
	    	{	
				cheminSauvegarde=fc.getSelectedFile().getAbsolutePath();	
				if(Frame.p.export(cheminSauvegarde)) {
					JOptionPane.showMessageDialog(null, "Export réussi", "Export", JOptionPane.INFORMATION_MESSAGE);
				}
				else{
					JOptionPane.showMessageDialog(null, "Veuillez ne pas laisser de case vide avant d'exporter le projet","Export", JOptionPane.ERROR_MESSAGE);
				}
				
	    	}
		}
		else if(e.getX() < xBarre+20 && e.getX()> xBarre-20) {
			cliqueBarre = true;
			lastPosition = e.getPoint();
		}

	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		cliqueBarre = false;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		Point p = e.getPoint();
		if(cliqueBarre) {
			if(p.getX() < lastPosition.getX()) {
				xBarre = e.getX();
				widthCaseNiveau -= lastPosition.getX()-p.getX();
				widthTexture += lastPosition.getX()-p.getX();
				lastPosition = p;
				pane.setBounds(xCaseNiveau, yCaseNiveau, widthCaseNiveau, heightCaseNiveau);
				paneTexture.setBounds(widthCaseNiveau+xCaseNiveau+60, yCaseNiveau, this.getWidth() - (widthCaseNiveau+xCaseNiveau+60)-30,heightCaseNiveau);
			}
			
			else {
				xBarre = e.getX();
				widthCaseNiveau += Math.abs(lastPosition.getX()-p.getX());
				widthTexture -= Math.abs(lastPosition.getX()-p.getX());
				lastPosition = p;
				pane.setBounds(xCaseNiveau, yCaseNiveau, widthCaseNiveau, heightCaseNiveau);
				paneTexture.setBounds(widthCaseNiveau+xCaseNiveau+60, yCaseNiveau, this.getWidth() - (widthCaseNiveau+xCaseNiveau+60)-30,heightCaseNiveau);
			}
			pane.revalidate();
			scrollPane.revalidate();
			scrollPaneTexture.revalidate();
			repaint();
		}
		
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if(e.getX() < xBarre+20 && e.getX()> xBarre-20) {
			setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
		}
		else {
			setCursor(Cursor.getDefaultCursor());
		}
	}
}
