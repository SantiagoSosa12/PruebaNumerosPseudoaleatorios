package views;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PnlTablePoker extends JPanel{

	private static final long serialVersionUID = 1L;
	private JTable tableBill;
	private DefaultTableModel model;

	public PnlTablePoker() {
		this.setLayout(new BorderLayout());
		model = new DefaultTableModel();
		String[] aux = {"No","Cat","Oi","Prob","Ei","(Ei-Oi)^2 / Ei"};
		this.setBackground(Color.decode("#2baae2"));
		model.setColumnIdentifiers(aux);
		tableBill = new JTable(model);
		tableBill.setRowHeight(25);
		tableBill.getTableHeader().setForeground(Color.BLACK);
		add(new JScrollPane(tableBill), BorderLayout.CENTER);
	}

	public void chargueProducts(String[] cat, double[] oi, double[] prob,double[] ei, double[] eie){
		model.setRowCount(0);
		for (int i = 0; i < cat.length; i++) {
			Object[] aux1 = {""+(i+1),cat[i],oi[i],prob[i],ei[i],eie[i]};
			model.addRow(aux1);
		}
		model.fireTableStructureChanged();
		revalidate();
	}

}
