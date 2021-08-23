package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;

import constants.MyConstants;
import models.KS;
import models.Medias;
import models.Varianza;
import persistence.FileManager;
import views.PruebasMainWindow;;

public class Control implements ActionListener {

	private Medias medias;

	private FileManager fileManager;
	private PruebasMainWindow mainW;
	private Varianza varianza;
	private KS ks;

	private static File FILETOREAD;

	public Control() {
		medias = new Medias();

		fileManager = new FileManager();
		ArrayList<Double> valores;
		valores =infoProfe();
		varianza = new Varianza();
		ks = new KS(infoProfeKS(),95,5);
		mainW = new PruebasMainWindow(this);
		mainW.fillTable(valores);
		mainW.fillTable2(infoProfeKS());
		mainW.crearTabla2();
		mainW.fillTable3(ks.getInicial(), ks.getFinalx(),ks.getFrecObt(),ks.getfAcum(),ks.getpObt(),ks.getfEsperA(),ks.getpEsp(),ks.getDif());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (ActionsE.valueOf(e.getActionCommand())) {
		case MEDIAS:
			if (FILETOREAD != null) {
				medias();
			} else {
				mainW.showErrorMessage(MyConstants.ERR_NO_FILE_SELECTED);
				break;
			}
		case VARIANZA:
			break;
		case KS:
			break;
		case CHI2:
			break;
		case POKER:
			break;
		case SELECT_FILE:
			FILETOREAD = mainW.getFileFromFileChooser();
			break;
		}
	}
	
	public ArrayList<Double> infoProfe() {
		ArrayList<Double>aux = new ArrayList<>();
		aux.add(0.031440);	aux.add(0.311271);	aux.add(0.928426);	aux.add(0.699545);	aux.add(0.070885);	
		aux.add(0.263751);	aux.add(0.896510);	aux.add(0.601238);	aux.add(0.695186);	aux.add(0.257217);	
		aux.add(0.661090);	aux.add(0.911817);	aux.add(0.766321);	aux.add(0.834743);	aux.add(0.463014);	
		aux.add(0.209180);	aux.add(0.723503);	aux.add(0.180494);	aux.add(0.157221);	aux.add(0.414227);	
		aux.add(0.833130);	aux.add(0.617190);	aux.add(0.525914);	aux.add(0.199922);	aux.add(0.485106);	
		aux.add(0.104745);	aux.add(0.021767);	aux.add(0.049527);	aux.add(0.730993);	aux.add(0.265738);
		aux.add(0.444933);	aux.add(0.916792);	aux.add(0.688692);	aux.add(0.783611);	aux.add(0.297193);	
		aux.add(0.702240);	aux.add(0.459911);	aux.add(0.228080);	aux.add(0.191410);	aux.add(0.161257);	
		aux.add(0.712137);	aux.add(0.596760);	aux.add(0.116946);	aux.add(0.833208);	aux.add(0.008572);	
		aux.add(0.057809);	aux.add(0.861116);	aux.add(0.915069);	aux.add(0.078038);	aux.add(0.002133);
		return aux;
	}
	
	public ArrayList<Double> infoProfeKS() {
		ArrayList<Double> aux = new ArrayList<>();
		aux.add(0.578683);	aux.add(0.710617);	aux.add(0.689988);	aux.add(0.538994);	aux.add(0.394848);	
		aux.add(0.296490);	aux.add(0.000000);	aux.add(0.816477);	aux.add(0.646391);	aux.add(0.627781);	
		aux.add(0.578075);	aux.add(0.509941);	aux.add(0.525652);	aux.add(0.670381);	aux.add(0.590813);	
		aux.add(0.479619);	aux.add(0.496725);	aux.add(0.328576);	aux.add(0.909237);	aux.add(0.549172);	
		aux.add(0.450164);	aux.add(0.564734);	aux.add(0.250452);	aux.add(0.137134);	aux.add(0.816437);	
		aux.add(0.641199);	aux.add(0.633333);	aux.add(0.386215);	aux.add(0.220694);	aux.add(0.593446);
		aux.add(0.821086);	aux.add(0.506602);	aux.add(0.290430);	aux.add(0.247806);	aux.add(0.574323);	
		aux.add(0.491296);	aux.add(1.000000);	aux.add(0.437993);	aux.add(0.620486);	aux.add(0.639490);	
		aux.add(0.700753);	aux.add(0.391825);	aux.add(0.346095);	aux.add(0.517212);	aux.add(0.824114);	
		aux.add(0.427438);	aux.add(0.443694);	aux.add(0.161216);	aux.add(0.376285);	aux.add(0.348020);
		return aux;
	}
		
	private void medias() {
		ArrayList<Double> pseudoRandomNumbers = new ArrayList<Double>();
		try {
			pseudoRandomNumbers = fileManager.readFile(FILETOREAD);
		} catch (IOException e) {
			mainW.showErrorMessage(MyConstants.ERR_READ_FILE);
		}

		double acceptanceMargin = mainW.getMediasAcceptanceMargin(pseudoRandomNumbers);
		boolean paso = medias.mediasTesting(acceptanceMargin, pseudoRandomNumbers);
		mainW.mediasApprovedProve(paso, medias.getResults());
	}
}