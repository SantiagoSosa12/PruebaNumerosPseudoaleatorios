package views.chi2;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.ActionsE;
import views.components.OwnJButton;

public class InfoChi2 extends JPanel{

	
	private static final long serialVersionUID = 1L;
	private JLabel m;
	private JLabel chi2;
	private OwnJButton pruebaChi2;
	public InfoChi2(ActionListener action) {
		this.setLayout(new FlowLayout());
		this.initComponents(action);
		this.setVisible(true);
	}
	
	private void initComponents(ActionListener action) {
		this.m = new JLabel(" M = ");
		this.add(m);
		
		this.chi2 = new JLabel(" Chi2 95 % Aprobacion = ");
		this.add(chi2);
		
		pruebaChi2 = new OwnJButton("Prueba Chi 2", ActionsE.CHI2, action);
		this.add(pruebaChi2);
		
	}

	public void setM(String text) {
		this.m.setText(" M = " + text);
	}
	
	public void setChi(String text) {
		this.chi2.setText(" Chi2 95 % Aprobacion = " + text);
	}
	
	public void aprobo(boolean aprobo) {
		if(aprobo) {
			this.pruebaChi2.setColor(Color.GREEN);
		}else {
			this.pruebaChi2.setColor(Color.RED);
		}
	}
	
}
