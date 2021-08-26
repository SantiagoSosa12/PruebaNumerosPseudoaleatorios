package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import views.chi2.InfoChi2;
import views.chi2.TablasChi2;

public class PanelChi2 extends JPanel {

	private static final long serialVersionUID = 1L;
	private TablasChi2 tablasChi2;
	private InfoChi2 infoChi2;
	
	public PanelChi2(ActionListener action) {
		setBackground(Color.WHITE);
		this.setLayout(new BorderLayout());
		this.initComponents(action);
		this.setVisible(true);
	}
	
	private void initComponents(ActionListener action) {
		infoChi2 = new InfoChi2(action);
		this.add(infoChi2 , BorderLayout.NORTH);
		
		tablasChi2 = new TablasChi2();
		this.add(tablasChi2 , BorderLayout.CENTER);
	}
	
	public void fillTable(double[] Ri, String[] intervalo, double[] cantPorIntervalo, double[] frecuencias) {
		this.tablasChi2.chargueProducts(Ri, intervalo, cantPorIntervalo, frecuencias);
	}
	
	public void setChi2(double chi) {
		this.infoChi2.setChi(""+chi);
	}
	
	public void setM(double M) {
		this.infoChi2.setM(""+M);
	}
	
	public void aproboPrueba(boolean aprobo) {
		this.infoChi2.aprobo(aprobo);
	}
	
}