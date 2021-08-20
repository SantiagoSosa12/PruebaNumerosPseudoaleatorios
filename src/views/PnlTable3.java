package views;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PnlTable3 extends JPanel{

	private static final long serialVersionUID = 1L;
	private JTable tableBill;
	private DefaultTableModel model;

	public PnlTable3() {
		this.setLayout(new BorderLayout());
		model = new DefaultTableModel();
		String[] aux = {"No","Inicial","Final","Frecuencia Obtenida","F. Acumulada","P. Obtenida","F. Esp. A","P. esp","Diferencia"};
		this.setBackground(Color.decode("#2baae2"));
		model.setColumnIdentifiers(aux);
		tableBill = new JTable(model);
		tableBill.setRowHeight(25);
		tableBill.getTableHeader().setForeground(Color.BLACK);
		add(new JScrollPane(tableBill), BorderLayout.CENTER);
	}

	public void chargueProducts(double[] inicial, double[] finalx,double[] frecObt,double[] fAcum,double[] pObt,double[] fEsperA,double[] pEsp,double[] dif){
		model.setRowCount(0);
		for (int i = 0; i < inicial.length; i++) {
			Object[] aux1 = {""+(i+1),inicial[i],finalx[i],frecObt[i],fAcum[i],pObt[i],fEsperA[i],pEsp[i],dif[i]};
			model.addRow(aux1);
		}
		model.fireTableStructureChanged();
		revalidate();
	}

}
