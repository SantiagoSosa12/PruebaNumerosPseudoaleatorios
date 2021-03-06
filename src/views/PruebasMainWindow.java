package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import constants.MyConstants;
import views.components.OwnJFileChooser;

public class PruebasMainWindow extends JFrame {

	private JTabbedPane panelesPruebas;
	private OwnJFileChooser fileChooser;
	private PanelMedias panelMedias;
	private PanelVarianza panelVarianza;
	private PanelKS panelKS;
	private PanelPoker panelPoker;
	private PanelChi2 panelChi2;

	public PruebasMainWindow(ActionListener actionListener) {
		getContentPane().setBackground(Color.WHITE);
		setTitle(MyConstants.APP_TITLE);
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.WHITE);
		setMinimumSize(new Dimension(950, 550));

		panelesPruebas = new JTabbedPane();
		panelesPruebas.setBackground(Color.WHITE);
		panelesPruebas.setForeground(Color.BLACK);

		panelMedias = new PanelMedias(actionListener);
		panelesPruebas.add("Medias", panelMedias);

		panelVarianza = new PanelVarianza(actionListener);
		panelesPruebas.add("Varianza", panelVarianza);

		panelKS = new PanelKS(actionListener);
		panelesPruebas.add("Prueba KS", panelKS);

		panelPoker = new PanelPoker(actionListener);
		panelesPruebas.add("Prueba Poker", panelPoker);
		
		this.panelChi2 = new PanelChi2(actionListener);
		panelesPruebas.add("Prueba Chi2" , panelChi2);
		
		add(panelesPruebas);

		fileChooser = new OwnJFileChooser();

		setVisible(true);
	}
	
	public void fillTable(ArrayList<Double> datos) {
		
	}
	
	public void fillTableKS(double[] Ri, String[] intervalo,  double[] frecuencias,
			double[] frecuenciasAcumuladas, double[] ProbAcumulada, double[] esperadaMinObtenida  ) {
		panelKS.chargueProducts(Ri, intervalo, frecuencias, frecuenciasAcumuladas, ProbAcumulada, esperadaMinObtenida);
	}
	
	public void fillTable2(ArrayList<Double>list) {
		panelVarianza.fillTable(list);
	}
	
	public File getFileFromFileChooser() {
		int option = fileChooser.showOpenDialog(this);
		return option != 1 ? fileChooser.getSelectedFile() : null;
	}
	
	public double getMediasAcceptanceMargin(ArrayList<Double> dataForTable) {
		return panelMedias.getAcceptanceMargin(dataForTable);
	}
	
	public void mediasApprovedProve(boolean isApproved, Object[] results) {
		panelMedias.approvedProve(isApproved, results);
	}
 
	/**
	 * Muestra el mensaje por parametro en un JOptionPane con icono de error
	 * 
	 * @param errorMessage to show to the user
	 */
	public void showErrorMessage(String errorMessage) {
		JOptionPane.showMessageDialog(this, errorMessage, "ERROR !", JOptionPane.ERROR_MESSAGE);
	}

	private static final long serialVersionUID = 1L;

	public void varianzaApprovedProve(boolean aprobo, Object[] results) {
		panelVarianza.setData(aprobo, results);
	}

	public void varianza(boolean aprobo, Object[] results) {
		panelVarianza.setData(aprobo, results);
	}

	public void fillTablePoker(ArrayList<Double> pseudoRandomNumbers, ArrayList<String> hands) {
		panelPoker.fillTablePoker(pseudoRandomNumbers, hands);
	}
	
	public void fillTablePoker(String[] cat, double[] oi, double[] prob,double[] ei, double[] eie) {
		panelPoker.fillTablePoker(cat, oi, prob, ei, eie);
	}

	public void setN(String string) {
		panelPoker.setN(string);
		
	}
	
	public void setKsAndAprobed(double ks , boolean aproved) {
		this.panelKS.setKS(ks);
		this.panelKS.aprobo(aproved);
	}
	
	public void pasoPrueba(String paso, String sum) {
		panelPoker.pasoPrueba(paso, sum);
	}
	
	public void fillTableChi2(double[] Ri, String[] intervalo, double[] cantPorIntervalo, double[] frecuencias, double chi2, double M, boolean aprobo) {
		this.panelChi2.fillTable(Ri, intervalo, cantPorIntervalo, frecuencias);
		this.panelChi2.setChi2(chi2);
		this.panelChi2.setM(M);
		this.panelChi2.aproboPrueba(aprobo);
	}
	
}