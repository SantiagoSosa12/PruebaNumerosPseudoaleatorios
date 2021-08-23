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

public class Controller implements ActionListener {

	private Medias medias;

	private FileManager fileManager;
	private PruebasMainWindow mainW;
	private Varianza varianza;
	private KS ks;

	private static File FILETOREAD;

	public Controller() {
		medias = new Medias();
		fileManager = new FileManager();
		ArrayList<Double> valores = new ArrayList<>();
		varianza = new Varianza();
		ks = new KS(valores,0d, 0d);
		mainW = new PruebasMainWindow(this);
		mainW.fillTable(valores);
		mainW.crearTabla2();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(ActionsE.valueOf(e.getActionCommand()));
		switch (ActionsE.valueOf(e.getActionCommand())) {
		case MEDIAS:
			if (FILETOREAD != null) {
				medias();
			} else {
				mainW.showErrorMessage(MyConstants.ERR_NO_FILE_SELECTED);
				break;
			}
			break;
		case KS:
			break;
		case CHI2:
			break;
		case VARIANZA:
			if (FILETOREAD != null) {
				varianza();
			} else {
				mainW.showErrorMessage(MyConstants.ERR_NO_FILE_SELECTED);
				break;
			}
			break;
		case POKER:
			break;
		case SELECT_FILE:
			FILETOREAD = mainW.getFileFromFileChooser();
			break;
		}
	}
	
	private void varianza() {
		ArrayList<Double> pseudoRandomNumbers = new ArrayList<Double>();
		try {
			pseudoRandomNumbers = fileManager.readFile(FILETOREAD);
		} catch (IOException e) {
			mainW.showErrorMessage(MyConstants.ERR_READ_FILE);
		}
		mainW.fillTable2(pseudoRandomNumbers);
		double acceptanceMargin = mainW.getMediasAcceptanceMargin(pseudoRandomNumbers);
		varianza.getVarianza(acceptanceMargin, pseudoRandomNumbers);
		Object[]  results = varianza.getResults();
		boolean aprobo = varianza.cumple();
		mainW.varianza(aprobo, results);
	}
	
	private void medias() {
		ArrayList<Double> pseudoRandomNumbers = new ArrayList<Double>();
		
		try {
			pseudoRandomNumbers = fileManager.readFile(FILETOREAD);
		} catch (IOException e) {
			mainW.showErrorMessage(MyConstants.ERR_READ_FILE);
		}
		mainW.fillTable2(pseudoRandomNumbers);
		double acceptanceMargin = mainW.getMediasAcceptanceMargin(pseudoRandomNumbers);
		boolean paso = medias.mediasTesting(acceptanceMargin, pseudoRandomNumbers);
		mainW.mediasApprovedProve(paso, medias.getResults());
	}
}