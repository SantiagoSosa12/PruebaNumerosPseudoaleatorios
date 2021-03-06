package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;

import constants.MyConstants;
import models.Chi2;
import models.KS;
import models.Medias;
import models.Poker;
import models.Varianza;
import persistence.FileManager;
import views.PruebasMainWindow;;

public class Controller implements ActionListener {

	private Medias medias;

	private FileManager fileManager;
	private PruebasMainWindow mainW;
	private Varianza varianza;
	private Poker poker;

	private static File FILETOREAD;

	public Controller() {
		medias = new Medias();
		fileManager = new FileManager();
		ArrayList<Double> valores = new ArrayList<>();
		varianza = new Varianza();
		poker = new Poker();
		mainW = new PruebasMainWindow(this);
		mainW.fillTable(valores);
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
			break;
		case KS:
			if (FILETOREAD != null) {
				ks();
			} else {
				mainW.showErrorMessage(MyConstants.ERR_NO_FILE_SELECTED);
				break;
			}
			break;
		case CHI2:
			if (FILETOREAD != null) {
				chiCuadrado();
			} else {
				mainW.showErrorMessage(MyConstants.ERR_NO_FILE_SELECTED);
				break;
			}
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
			if (FILETOREAD != null) {
				poker();
			} else {
				mainW.showErrorMessage(MyConstants.ERR_NO_FILE_SELECTED);
				break;
			}
			break;
		case SELECT_FILE:
			FILETOREAD = mainW.getFileFromFileChooser();
			break;
		}
	}
	
	private void poker() {
		ArrayList<Double> pseudoRandomNumbers = new ArrayList<Double>();
		try {
			pseudoRandomNumbers = fileManager.readFile(FILETOREAD);
		} catch (IOException e) {
			mainW.showErrorMessage(MyConstants.ERR_READ_FILE);
		}
		poker.setDataPoker(pseudoRandomNumbers);
		mainW.setN(pseudoRandomNumbers.size()+ "");
		poker.pokerAlgorithm();
		ArrayList<String> hands = poker.getHands();
		mainW.fillTablePoker(pseudoRandomNumbers, hands);
		
		String[] cat = {"D", "O", "T", "K", "F", "P", "Q"};
		double[] oi = new double[7];
		for (int i = 0; i < oi.length; i++) {
			oi[i] = poker.getOi(hands, cat[i]);
		}
		poker.createEi();
		double[] eie = poker.getEie(oi);
		mainW.fillTablePoker(cat, oi, poker.getProbs(), poker.getEi(), eie);
		double sum = 0;
		for (int i = 0; i < eie.length; i++) {
			sum+= eie[i];
		}
		if (sum <= poker.getX2alfa()) {
			mainW.pasoPrueba("Paso la prueba", sum+ "");
		}else {
			mainW.pasoPrueba("No paso la prueba", sum+ "");
		}
		
		
//		Object[]  results = varianza.getResults();
//		boolean aprobo = varianza.cumple();
//		mainW.varianza(aprobo, results);
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
	
	private void chiCuadrado() {
		ArrayList<Double> pseudoRandomNumbers = new ArrayList<Double>();
		try {
			pseudoRandomNumbers = fileManager.readFile(FILETOREAD);
		} catch (IOException e) {
			mainW.showErrorMessage(MyConstants.ERR_READ_FILE);
		}
		Chi2 chi = new Chi2();
		chi.segmentarDatos(pseudoRandomNumbers);
		boolean aprobo = chi.asignarDatosAdicionales();
		mainW.fillTableChi2(chi.getRi(), chi.getIntervalos(), chi.getOi(), chi.getFrecuencias() , chi.getChiCuadrado() , chi.getM() , aprobo);
	}
	
	private void ks() {
		ArrayList<Double> pseudoRandomNumbers = new ArrayList<Double>();
		try {
			pseudoRandomNumbers = fileManager.readFile(FILETOREAD);
		} catch (IOException e) {
			mainW.showErrorMessage(MyConstants.ERR_READ_FILE);
		}
		KS ks = new KS(pseudoRandomNumbers);
		ks.segmentarDatos();
		ks.asignarFrecuencias();
		boolean aprobo = ks.pasoPrueba();
		mainW.fillTableKS(ks.getRi(), ks.getIntervalos(), ks.getFrecuencias(), ks.getFrecuenciasAcumuladas(), ks.getProbabilidadObtenida(), ks.getEsperadaMenosObtenida());
		mainW.setKsAndAprobed(ks.getKs(), aprobo);
	}
	
}