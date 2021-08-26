package views.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import constants.MyConstants;
import controller.ActionsE;

/**
 * A class for create multiple buttons objects with similar characteristics
 */
public class OwnJButton extends JButton {

	private static final long serialVersionUID = 1L;

	public OwnJButton(String text, ActionsE actionCommand, ActionListener actionListener) {
		setText(text);
		setForeground(Color.decode(MyConstants.CLR_WHITE));
		setBackground(Color.decode(MyConstants.CLR_BLUE_BTNS));
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
		setFont(new Font(MyConstants.FONT_ROBOTO, 1, 16));
		setActionCommand(actionCommand.toString());
		addActionListener(actionListener);
	}
	
	public void setColor(Color c) {
		this.setBackground(c);
	}
	
}