package views;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import controller.ActionsE;
import views.components.OwnJButton;

public class InfoKS extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private JLabel ks;
	private OwnJButton pruebaKS;
	
	public InfoKS(ActionListener action) {
		this.setLayout(new FlowLayout());
		this.initComponents(action);
		this.setVisible(true);
	}
	
	private void initComponents(ActionListener action) {
		this.ks = new JLabel(" KS 95 % Aprobacion = ");
		this.add(ks);
		
		pruebaKS = new OwnJButton("Prueba KS", ActionsE.KS, action);
		this.add(pruebaKS);
		
	}
	
	public void setKS(String text) {
		this.ks.setText(" KS 95 % Aprobacion = " + text);
	}
	
	public void aprobo(boolean aprobo) {
		if(aprobo) {
			this.pruebaKS.setColor(Color.GREEN);
		}else {
			this.pruebaKS.setColor(Color.RED);
		}
	}
	
}
