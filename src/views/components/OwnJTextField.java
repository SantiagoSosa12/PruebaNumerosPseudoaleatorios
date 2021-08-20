package views.components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

import constants.MyConstants;

public class OwnJTextField extends JTextField {

	private static final long serialVersionUID = 1L;

	public OwnJTextField(String borderT) {
		setBorder(BorderFactory.createTitledBorder(borderT));
		setBackground(Color.WHITE);
		setForeground(Color.BLACK);
		setText("0.95");
		setFont(new Font(MyConstants.FONT_ROBOTO, 0, 12));
	}
}