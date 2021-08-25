package test;

import java.util.ArrayList;
import models.Chi2;

public class TestChi2 {

	public static void main(String[] args) {
		Double[] n = {0.70779,0.78584,0.93603,0.00048,0.46475,0.15037,0.33706,0.25999};
		ArrayList<Double> lista = new ArrayList<Double>();
		for (int i = 0; i < n.length; i++) {
			lista.add(n[i]);
		}
		System.out.println(lista.toString());
		Chi2 chi = new Chi2();
		chi.segmentarDatos(lista);
		System.out.println(chi.toString());
	}

}
