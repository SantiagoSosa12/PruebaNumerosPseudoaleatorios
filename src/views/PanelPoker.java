package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import constants.MyConstants;
import controller.ActionsE;
import views.components.OwnJButton;

public class PanelPoker extends JPanel {

	private static final long serialVersionUID = 1L;
	public PnlTable2 pnlTable;
	public PnlTablePoker pnlTablePoker;
	
	public JLabel jLPaso, menor,rPromedio,x2alpha,dMaxp, sum;
	private JPanel centro = new JPanel();
	
	
	public PanelPoker(ActionListener actionListener) {
		centro.setLayout(new BoxLayout(this.centro , BoxLayout.X_AXIS));
		centro.setBackground(Color.decode("#2baae2"));
		this.setLayout(new BorderLayout());
		this.setBackground(Color.decode("#2baae2"));
		initComponent(actionListener);
		crearTabla();
		crearTablaPoker();
		this.add(this.centro , BorderLayout.CENTER);
	}
	
	public void initComponent(ActionListener actionListener) {
		JPanel norte = new JPanel();
		norte.setLayout(new FlowLayout());
		norte.setBackground(Color.decode("#2baae2"));
		
		dMaxp= new JLabel();
		dMaxp.setBorder(new TitledBorder("N"));
		dMaxp.setText("00");
		dMaxp.setForeground(Color.decode(MyConstants.CLR_WHITE));
		dMaxp.setBackground(Color.decode(MyConstants.CLR_BLUE_BTNS));
		dMaxp.setFont(new Font(MyConstants.FONT_ROBOTO, 1, 25));
		dMaxp.setHorizontalAlignment(JLabel.CENTER);
		dMaxp.setBounds(670, 550, 150, 80);
		
		x2alpha= new JLabel();
		x2alpha.setBorder(new TitledBorder("X^2*alpha"));
		x2alpha.setText("12.59158");
		x2alpha.setForeground(Color.decode(MyConstants.CLR_WHITE));
		x2alpha.setBackground(Color.decode(MyConstants.CLR_BLUE_BTNS));
		x2alpha.setFont(new Font(MyConstants.FONT_ROBOTO, 1, 25));
		x2alpha.setHorizontalAlignment(JLabel.CENTER);
		x2alpha.setBounds(670, 550, 150, 80);
		
		sum= new JLabel();
		sum.setBorder(new TitledBorder("Sumatoria (Ei-Oi)^2/Ei"));
		sum.setText("0.000000");
		sum.setForeground(Color.decode(MyConstants.CLR_WHITE));
		sum.setBackground(Color.decode(MyConstants.CLR_BLUE_BTNS));
		sum.setFont(new Font(MyConstants.FONT_ROBOTO, 1, 25));
		sum.setHorizontalAlignment(JLabel.CENTER);
		sum.setBounds(670, 550, 150, 80);
		
		jLPaso = new JLabel();
		jLPaso.setBorder(new TitledBorder("Cumple?"));
			jLPaso.setText("Sin determinar");
		jLPaso.setForeground(Color.decode(MyConstants.CLR_WHITE));
		jLPaso.setBackground(Color.decode(MyConstants.CLR_BLUE_BTNS));
		jLPaso.setFont(new Font(MyConstants.FONT_ROBOTO, 1, 25));
		jLPaso.setHorizontalAlignment(JLabel.CENTER);
		jLPaso.setBounds(840, 550, 250, 80);
		
		norte.add(dMaxp);
		norte.add(x2alpha);
		norte.add(sum);
		norte.add(jLPaso);
		norte.add(new OwnJButton("Prueba de Poker", ActionsE.POKER, actionListener));
		this.add(norte , BorderLayout.NORTH);
	}
	
	public void setN(String cant) {
		dMaxp.setText(cant);
	}
	
	public void crearTablaPoker() {
		pnlTablePoker = new PnlTablePoker();
		pnlTablePoker.setBounds(500, 30, 950, 280);
		centro.add(pnlTablePoker);
	}
	
	public void crearTabla() {
		pnlTable = new PnlTable2("hand");
		pnlTable.setBounds(0, 30, 700, 500);
		centro.add(pnlTable);
	}

	public void fillTable(ArrayList<Double>datos) {
		pnlTable.chargueProducts(datos);
	}
	
	public void fillTablePoker(ArrayList<Double>datos, ArrayList<String> hands) {
		pnlTable.chargueProductsPoker(datos, hands);
	}
	
	public void fillTablePoker(String[] cat, double[] oi, double[] prob,double[] ei, double[] eie) {
		pnlTablePoker.chargueProducts(cat, oi, prob, ei, eie);
	}

	public void pasoPrueba(String paso, String sum) {
		jLPaso.setText(paso);
		this.sum.setText(sum);
	}
}