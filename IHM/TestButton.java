package IHM;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class TestButton {
	 
	private static final int NB_FIELDS = 5;
 
	public static void main(String[] args) {
 
		JFrame frame=new JFrame();
 
		final JPanel panel=new JPanel();
		frame.getContentPane().add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		JButton button = new JButton("ADD");
		panel.add(button);
 
 
 
		final JPanel fieldpanel=new JPanel();
		panel.add(new JScrollPane(fieldpanel));
 
		final GridBagLayout layout = new GridBagLayout();
 
		fieldpanel.setLayout(layout);
 
 
		button.addActionListener(new ActionListener() {
 
			int row=0;
 
			public void actionPerformed(ActionEvent e) {
				createFields(fieldpanel, row++);
			}
		});
 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setVisible(true);
 
	}
 
	private static void createFields(JPanel fieldpanel, int row) {
		for(int i=0; i<NB_FIELDS; i++) {
			System.out.println(" i : " + i + "row : " + row);
			GridBagConstraints constraint=new GridBagConstraints();
			constraint.gridx=i;
			constraint.gridy=row;
			fieldpanel.add(new JTextField("",10),constraint);
		}
		fieldpanel.revalidate();
		/*//alternative exemple avec doLayout()
		fieldpanel.getParent().doLayout();
		fieldpanel.doLayout();*/
	}
 
}
