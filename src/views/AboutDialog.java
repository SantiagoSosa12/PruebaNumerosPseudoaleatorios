package views;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import constants.MyConstants;

public class AboutDialog extends JDialog {

	public AboutDialog(JFrame mainWindow) {
		super(mainWindow);
		setModal(true);
		setSize(800, 350);
		setTitle("Acerca de ...");
		setLocationRelativeTo(mainWindow);
		getContentPane().setBackground(Color.WHITE);
		setLayout(new GridBagLayout());
		setResizable(false);

		GridBagConstraints c = new GridBagConstraints();
		createGrid(c);

		JLabel lblAboutImg = new JLabel(createImageIcon(MyConstants.SISTEMAS_ICON));
		c.gridy = 0;
		c.weighty = 1;
		c.gridx = 0;
		c.gridwidth = 5;
		add(lblAboutImg, c);

		JLabel names = new JLabel();
		names.setText("<html>" + "<h1>Esteban Gutierrez</h2>" + "<br>" + "<br>"
				+ "<h2>Santiago Sosa</h2>" 
				+ "</html>");

		c.gridy = 0;
		c.weighty = 1;
		c.gridx = 7;
		c.gridwidth = 3;
		add(names, c);
	}

	/**
	 * Creates the grid for the GridBagLayout
	 * 
	 * @param c the layout columns
	 */
	private void createGrid(GridBagConstraints c) {
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridheight = 1;
		c.weighty = 0.01;

		for (int i = 0; i < 12; i++) {
			c.gridx = i;
			add(new JLabel(""), c);
		}
	}

	private ImageIcon createImageIcon(String path) {
		return new ImageIcon(getClass().getResource(path));
	}

	private static final long serialVersionUID = 1L;
}