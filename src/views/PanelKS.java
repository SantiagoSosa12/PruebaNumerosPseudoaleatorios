package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class PanelKS extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private InfoKS infoKS;
	private DefaultTableModel model;
	private JTable tableBill;
	
	public PanelKS(ActionListener action) {
		setBackground(Color.WHITE);
		this.setLayout(new BorderLayout());
		this.initCom(action);
		this.setVisible(true);
	}
	
	private void initCom(ActionListener action) {
		this.infoKS = new InfoKS(action);
		this.add(this.infoKS , BorderLayout.NORTH);
		
		model = new DefaultTableModel();
		String[] aux = {"i","Ri","Intervalo","Oi","Frecuencias acumuladas" , "Probabilidad Obtenida", "Prob. esperada - obtenida" };
		this.setBackground(Color.decode("#2baae2"));
		model.setColumnIdentifiers(aux);
		tableBill = new JTable(model);
		tableBill.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tableBill.getTableHeader().setForeground(Color.BLACK);
		add(new JScrollPane(tableBill) , BorderLayout.CENTER);
		
	}
	
	public void chargueProducts(double[] Ri, String[] intervalo,  double[] frecuencias,
			double[] frecuenciasAcumuladas, double[] ProbAcumulada, double[] esperadaMinObtenida 
			){
		model.setRowCount(0);
		for (int i = 0; i < Ri.length; i++) {
			if(i > (intervalo.length - 1)) {
				Object[] aux1 = {""+(i+1),Ri[i]," - "," - "," - ", " - "};
				model.addRow(aux1);
			}else {
				Object[] aux1 = {""+(i+1), Ri[i] , intervalo[i] ," " + frecuencias[i],
						frecuenciasAcumuladas[i], ProbAcumulada[i] , esperadaMinObtenida[i]	
				};
				model.addRow(aux1);
			}
		}
		model.fireTableStructureChanged();
		revalidate();
	}
	
	public void setKS(double ks) {
		this.infoKS.setKS(" "+ ks + "  ");
	}
	
	public void aprobo(boolean aprobo) {
		this.infoKS.aprobo(aprobo);
	}
	
}