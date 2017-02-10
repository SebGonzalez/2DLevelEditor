package gestionTexture;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.TransferHandler;

public class GestionTexture {
	
	public ArrayList<Image> listeTileTexture;
	
	public GestionTexture() {
		listeTileTexture = new ArrayList<Image>();
	}
	
	public void decouperImage(String fichierBmp, int nb) {
		File f = new File(fichierBmp);
		BufferedImage img = (BufferedImage) BMPImage.readBMP(f);
		
		for(int i=0; i<nb; i++) {
			Image newImg = img.getSubimage(i*(img.getWidth()/nb), 0, (img.getWidth()/nb), img.getHeight());
			listeTileTexture.add(newImg);
		}
	}
	
	public void dessinerTile( int width, int height, JPanel j) {
		MouseListener ml = new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
            
                JComponent jc = (JComponent)e.getSource();
                TransferHandler th = jc.getTransferHandler();
                th.exportAsDrag(jc, e, TransferHandler.COPY);
            }

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        };
        
		int hauteur = (height - (2*(height/listeTileTexture.size()) ))/listeTileTexture.size();
		
		for(int i=0; i<listeTileTexture.size(); i++) {
			JLabel p = new JLabel();
			p.addMouseListener(ml);
			//p.setBounds(300,300,100,100);
			p.setBounds(width-(hauteur+30), ((hauteur+20)*i) + 50, hauteur, hauteur);
			p.setTransferHandler(new TransferHandler("icon"));
			p.setBackground(Color.white);
			p.setIcon(new ImageIcon(listeTileTexture.get(i).getScaledInstance(hauteur, hauteur, Image.SCALE_DEFAULT)));
			p.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
			p.setOpaque(true);
			p.setVisible(true);
			j.add(p);
		}
		
		/*int hauteur = (height - (2*(height/listeTileTexture.size()) ))/listeTileTexture.size();
		g.drawLine(width-hauteur*2, 0, width-hauteur*2, height);
		for(int i=0; i<listeTileTexture.size(); i++) {
			g.drawImage(listeTileTexture.get(i), width-(hauteur+30), ((hauteur+20)*i) + 50, hauteur, hauteur,  null);
			
		}*/
	}
}
