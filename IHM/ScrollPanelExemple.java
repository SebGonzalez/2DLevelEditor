package IHM;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

public class ScrollPanelExemple {
	 
	public static void main(String[] args) {
 
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(null);
		JScrollPane scrollPane=new JScrollPane(createPanel()); 
		scrollPane.setSize(100,100);
		scrollPane.getViewport ().setScrollMode ( JViewport.SIMPLE_SCROLL_MODE );
		scrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
 
			@Override
			public void adjustmentValueChanged(AdjustmentEvent arg0) { 
				mainPanel.revalidate();
				mainPanel.repaint(); 
			}
		});
		scrollPane.getHorizontalScrollBar().addAdjustmentListener(new AdjustmentListener() {
 
			@Override
			public void adjustmentValueChanged(AdjustmentEvent arg0) { 
				mainPanel.revalidate();
				mainPanel.repaint(); 
			}
		});
 
		JPanel frozenPanel = new JPanel(new GridBagLayout());
		frozenPanel.setOpaque(false);
		frozenPanel.add(new JButton("Clique-moi !"));
 
		mainPanel.add(frozenPanel);
		mainPanel.add(scrollPane);
 
		frame.add(mainPanel);
 
		frame.setSize(354,377);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
 
	}
 
	private static Component createPanel() {
		JPanel panel = new JPanel(new GridLayout(0, 10));
		panel.setSize(100, 100);
 
		for(int i=0; i<100; i++) {
			JLabel c = new JLabel("test");
			c.setPreferredSize(new Dimension(64, 64));
			c.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			c.setBackground(Color.WHITE);
			panel.add(c);
		}
 
		return panel;
	}
 
}