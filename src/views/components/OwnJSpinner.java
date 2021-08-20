package views.components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import constants.MyConstants;

public class OwnJSpinner extends JSpinner {

	public OwnJSpinner(String text) {
		setBorder(BorderFactory.createTitledBorder(text));
		setBackground(Color.WHITE);
		setFont(new Font(MyConstants.FONT_ARIAL, 0, 14));
	}

	public OwnJSpinner(String text, boolean doubleLimits) {
		setBorder(BorderFactory.createTitledBorder(text));
		setBackground(Color.WHITE);
		setFont(new Font(MyConstants.FONT_ARIAL, 0, 14));
		setModel(new SpinnerNumberModel(0.95, 0.00, 1.00, 0.01));
	}

	private static final long serialVersionUID = 1L;
}