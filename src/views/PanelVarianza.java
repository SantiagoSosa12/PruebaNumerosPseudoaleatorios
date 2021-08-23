package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import constants.MyConstants;
import controller.ActionsE;
import views.components.OwnJButton;


public class PanelVarianza extends JPanel {
	public PnlTable pnlTable;
	private static final long serialVersionUID = 1L;
	public JLabel jLmedia;
	public JLabel jLVarianza;
	public JLabel jLS;
	public JLabel jLI;
	public JLabel jLPaso, jLAceptacion;
	
	public static DecimalFormat FORMAT = new DecimalFormat("#.#####");

	public PanelVarianza(ActionListener actionListener) {
		this.setLayout(new BorderLayout());
		this.setBackground(Color.decode("#2baae2"));
		
		crearTabla();
		initComponent(actionListener);
	}
	
	public void initComponent(ActionListener actionListener) {
		
		JPanel panelVarianza = new JPanel();
		panelVarianza.setLayout(new FlowLayout());
		panelVarianza.setBackground(Color.decode("#2baae2"));
		
		jLAceptacion = new JLabel();
		jLAceptacion.setBorder(new TitledBorder("Aceptacion"));
		jLAceptacion.setText("0.00000");
		jLAceptacion.setForeground(Color.decode(MyConstants.CLR_WHITE));
		jLAceptacion.setBackground(Color.decode(MyConstants.CLR_BLUE_BTNS));
		jLAceptacion.setFont(new Font(MyConstants.FONT_ROBOTO, 1, 25));
		jLAceptacion.setHorizontalAlignment(JLabel.CENTER);
		
		jLmedia = new JLabel();
		jLmedia.setBorder(new TitledBorder("Media"));
		jLmedia.setText("0.00000");
		jLmedia.setMinimumSize(new Dimension(50, 30));
		
		jLmedia.setSize(100, 50);
		jLmedia.setForeground(Color.decode(MyConstants.CLR_WHITE));
		jLmedia.setBackground(Color.decode(MyConstants.CLR_BLUE_BTNS));
		jLmedia.setFont(new Font(MyConstants.FONT_ROBOTO, 1, 25));
		jLmedia.setHorizontalAlignment(JLabel.CENTER);
		
		jLVarianza = new JLabel();
		jLVarianza.setBorder(new TitledBorder("Varianza"));
		jLVarianza.setText("0.00000");
		jLVarianza.setForeground(Color.decode(MyConstants.CLR_WHITE));
		jLVarianza.setBackground(Color.decode(MyConstants.CLR_BLUE_BTNS));
		jLVarianza.setFont(new Font(MyConstants.FONT_ROBOTO, 1, 25));
		jLVarianza.setHorizontalAlignment(JLabel.CENTER);
		
		jLS = new JLabel();
		jLS.setBorder(new TitledBorder("Limite Superior"));
		jLS.setText("0.00000");
		jLS.setForeground(Color.decode(MyConstants.CLR_WHITE));
		jLS.setBackground(Color.decode(MyConstants.CLR_BLUE_BTNS));
		jLS.setFont(new Font(MyConstants.FONT_ROBOTO, 1, 25));
		jLS.setHorizontalAlignment(JLabel.CENTER);
		
		jLI = new JLabel();
		jLI.setBorder(new TitledBorder("Limite Inferior"));
		jLI.setText("0.00000");
		jLI.setForeground(Color.decode(MyConstants.CLR_WHITE));
		jLI.setBackground(Color.decode(MyConstants.CLR_BLUE_BTNS));
		jLI.setFont(new Font(MyConstants.FONT_ROBOTO, 1, 25));
		jLI.setHorizontalAlignment(JLabel.CENTER);
		
		jLPaso = new JLabel();
		jLPaso.setBorder(new TitledBorder("Cumple?"));
		jLPaso.setText("No pasa la prueba");
		jLPaso.setForeground(Color.decode(MyConstants.CLR_WHITE));
		jLPaso.setBackground(Color.decode(MyConstants.CLR_BLUE_BTNS));
		jLPaso.setFont(new Font(MyConstants.FONT_ROBOTO, 1, 25));
		jLPaso.setHorizontalAlignment(JLabel.CENTER);
		
		
		panelVarianza.add(jLAceptacion);
		panelVarianza.add(jLmedia);
		panelVarianza.add(jLVarianza);
		panelVarianza.add(jLI);
		panelVarianza.add(jLS);
		panelVarianza.add(jLPaso);
		panelVarianza.add(new OwnJButton("Prueba de Varianza", ActionsE.VARIANZA, actionListener));
		
		this.add(panelVarianza , BorderLayout.NORTH);
		
	}
	
	public void setData(boolean isApproved, Object[] results) {
		jLAceptacion.setText(FORMAT.format(results[0]));
		jLmedia.setText(FORMAT.format(results[1]));
		jLVarianza.setText(FORMAT.format(results[2]));
		jLS.setText(FORMAT.format(results[3]));
		jLI.setText(FORMAT.format(results[4]));
		if (isApproved) {
			jLPaso.setText("Si aprobo la prueba");
		} else {
			jLPaso.setText("No aprobo la prueba");
		}
	}
	
	public void crearTabla() {
		pnlTable = new PnlTable();
		this.add(pnlTable, BorderLayout.CENTER);
	}

	public void fillTable(ArrayList<Double> list) {
		pnlTable.chargueProducts(list);
	}
}