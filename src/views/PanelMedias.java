package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import constants.MyConstants;
import controller.ActionsE;
import views.components.OwnJButton;
import views.components.OwnJLabel;
import views.components.OwnJSpinner;
import views.components.OwnJTable;

public class PanelMedias extends JPanel {

	private OwnJSpinner jspAcceptanceMargin;
	private OwnJLabel lblAlpha, lbln, lblR, lblAz, lblZ, lblLI, lblLS;
	private OwnJTable tblMedias;
	private OwnJLabel jLPaso;
	private OwnJButton botonAproboPrueba;
	
	
	public static DecimalFormat FORMAT = new DecimalFormat("#.#####");

	public PanelMedias(ActionListener actionListener) {
		setBackground(Color.WHITE);

		JPanel pnlElements = new JPanel(new GridLayout(2, 6, 6, 2));
		pnlElements.setBackground(Color.WHITE);

		jspAcceptanceMargin = new OwnJSpinner("Margen de aceptacion", true);
		pnlElements.add(jspAcceptanceMargin);

		lblAlpha = new OwnJLabel("Alpha = ");
		pnlElements.add(lblAlpha);

		lbln = new OwnJLabel("n = ");
		pnlElements.add(lbln);

		lblR = new OwnJLabel("R = ");
		pnlElements.add(lblR);

		lblAz = new OwnJLabel("1-(alpha/2) = ");
		pnlElements.add(lblAz);

		lblZ = new OwnJLabel("Z = ");
		pnlElements.add(lblZ);

		lblLI = new OwnJLabel("LI = ");
		pnlElements.add(lblLI);

		lblLS = new OwnJLabel("LS = ");
		pnlElements.add(lblLS);
		
		jLPaso = new OwnJLabel("Aprobo?");
		pnlElements.add(jLPaso);
		
		this.botonAproboPrueba = new OwnJButton("Prueba de medias", ActionsE.MEDIAS, actionListener); 
		
		pnlElements.add(new OwnJButton("Abrir archivo", ActionsE.SELECT_FILE, actionListener));
		pnlElements.add(this.botonAproboPrueba);

		this.setLayout(new BorderLayout());
		tblMedias = new OwnJTable(MyConstants.RI_NUMBERS);
		this.add(pnlElements, BorderLayout.NORTH);
		this.add(new JScrollPane(tblMedias), BorderLayout.CENTER);
		

	}

	public double getAcceptanceMargin(ArrayList<Double> dataForTable) {
		tblMedias.manageTableData(dataForTable);
		return (double) jspAcceptanceMargin.getValue();
	}

	public void approvedProve(boolean isApproved, Object[] results) {
		lblAlpha.setText("Alpha = " + FORMAT.format(results[0]));
		lbln.setText("n = " + results[1]);
		lblR.setText("R = " + FORMAT.format(results[2]));
		lblAz.setText("1-(alpha/2) = " + FORMAT.format(results[3]));
		lblZ.setText("Z = " + FORMAT.format(results[4]));
		lblLI.setText("LI = " + FORMAT.format(results[5]));
		lblLS.setText("LS = " + FORMAT.format(results[6]));

		if (isApproved) {
			jLPaso.setText("Si aprobo la prueba");
			this.botonAproboPrueba.changeColor(Color.green);
		} else {
			jLPaso.setText("No aprobo la prueba");
			this.botonAproboPrueba.changeColor(Color.red);
		}
	}

	private static final long serialVersionUID = 1L;
}