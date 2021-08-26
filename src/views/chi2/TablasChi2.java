package views.chi2;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TablasChi2 extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private DefaultTableModel model;
	private JTable tableBill;

	public TablasChi2() {
		this.setLayout(new BorderLayout());
		model = new DefaultTableModel();
		String[] aux = {"i","Ri","Intervalo","Oi","(Ei-Oi)^2 / Ei"};
		this.setBackground(Color.decode("#2baae2"));
		model.setColumnIdentifiers(aux);
		tableBill = new JTable(model);
		tableBill.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tableBill.getTableHeader().setForeground(Color.BLACK);
		add(new JScrollPane(tableBill) , BorderLayout.CENTER);
	}

	public void chargueProducts(double[] Ri, String[] intervalo, double[] cantPorIntervalo, double[] frecuencias){
		model.setRowCount(0);
		for (int i = 0; i < Ri.length; i++) {
			if(i > (intervalo.length - 1)) {
				Object[] aux1 = {""+(i+1),Ri[i]," - "," - "," - "};
				model.addRow(aux1);
			}else {
				Object[] aux1 = {""+(i+1), Ri[i] , intervalo[i] ," " + cantPorIntervalo[i] ," " + frecuencias[i]};
				model.addRow(aux1);
			}
		}
		model.fireTableStructureChanged();
		revalidate();
	}
	
	
}
