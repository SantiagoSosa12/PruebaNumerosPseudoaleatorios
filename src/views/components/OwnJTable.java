package views.components;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import constants.MyConstants;

public class OwnJTable extends JTable {

	private DefaultTableModel dtmElements;

	public OwnJTable(String[] columnIdentifiers) {
		dtmElements = new DefaultTableModel();
		dtmElements.setColumnIdentifiers(columnIdentifiers);
		setModel(dtmElements);

		getTableHeader().setReorderingAllowed(false);
		getTableHeader().setResizingAllowed(false);
		getTableHeader().setBackground(Color.decode(MyConstants.CLR_BLUE_PANEL));
		getTableHeader().setForeground(Color.white);
		getTableHeader().setFont(new Font(MyConstants.FONT_ARIAL, 1, 20));
		setFont(new Font(MyConstants.FONT_ARIAL, 0, 16));
		setBackground(Color.WHITE);
		setForeground(Color.BLACK);
	}

	public void manageTableData(ArrayList<Double> dataForTable) {
		dtmElements.setRowCount(0);

		for (Double number : dataForTable) {
			Object[] a = {number};
			dtmElements.addRow(a);
		}
	}

	private static final long serialVersionUID = 1L;
}