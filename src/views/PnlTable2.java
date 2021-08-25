package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PnlTable2 extends JPanel{

	private static final long serialVersionUID = 1L;
	private JTable tableBill;
	private DefaultTableModel model;

	public PnlTable2() {
		this.setLayout(new BorderLayout());
		model = new DefaultTableModel();
		String[] aux = {"i","Ri"};
		this.setBackground(Color.decode("#2baae2"));
		model.setColumnIdentifiers(aux);
		tableBill = new JTable(model);
		tableBill.setRowHeight(15);
		tableBill.getTableHeader().setForeground(Color.BLACK);
		this.add(new JScrollPane(tableBill), BorderLayout.CENTER);
	}
	
	public PnlTable2(String poker) {
		this.setLayout(new BorderLayout());
		model = new DefaultTableModel();
		String[] aux = {"i","Ri", poker};
		this.setBackground(Color.decode("#2baae2"));
		model.setColumnIdentifiers(aux);
		tableBill = new JTable(model);
		tableBill.setRowHeight(15);
		tableBill.getTableHeader().setForeground(Color.BLACK);
		this.add(new JScrollPane(tableBill), BorderLayout.CENTER);
	}

	public void chargueProducts(ArrayList<Double> listaDatos){
		model.setRowCount(0);
		Object[] aux = new Object[listaDatos.size()];
		for (int i = 0; i < listaDatos.size(); i++) {
			aux[i] = listaDatos.get(i)+"";
		}
		for (int i = 0; i < listaDatos.size(); i++) {
			Object[] aux1 = {""+(i+1),aux[i]};
			model.addRow(aux1);
		}
		model.fireTableStructureChanged();
		revalidate();
	}
	
	public void chargueProductsPoker(ArrayList<Double> listaDatos, ArrayList<String> hands){
		model.setRowCount(0);
		Object[] aux = new Object[listaDatos.size()];
		for (int i = 0; i < listaDatos.size(); i++) {
			aux[i] = listaDatos.get(i)+"";
		}
		for (int i = 0; i < listaDatos.size(); i++) {
			Object[] aux1 = {""+(i+1),aux[i], hands.get(i)};
			model.addRow(aux1);
		}
		model.fireTableStructureChanged();
		revalidate();
	}

}
