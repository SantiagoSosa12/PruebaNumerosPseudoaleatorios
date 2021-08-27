package test;

import java.util.ArrayList;

import org.apache.commons.math3.distribution.ChiSquaredDistribution;

import models.Chi2;

public class TestChi2 {

	public static void main(String[] args) {
		Double[] n = {0.70779,0.78584,0.93603,0.00048,0.46475,0.15037,0.33706,0.25999,0.115897};
		ArrayList<Double> lista = new ArrayList<Double>();
		for (int i = 0; i < n.length; i++) {
			lista.add(n[i]);
		}
		if(9 > n.length -1 ) {
			System.out.println("No hay posicion 9!!");
		}
		Chi2 chi = new Chi2();
		chi.segmentarDatos(lista);
		System.out.println(chi.toString());
//		int nivelConfianza = 95;
		double alfa = 0.05;
		double apache = 1 - (alfa / 2);
		ChiSquaredDistribution chi2 = new ChiSquaredDistribution(39);//49 grados de libertad 
		System.out.println(chi2.inverseCumulativeProbability(apache));
	}

}
