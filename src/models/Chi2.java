package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Chi2 {
	
	private ArrayList<Intervalo> listaIntervalos;
	
	public Chi2() {
		this.listaIntervalos = new ArrayList<Intervalo>();
	}
	
	
	public void segmentarDatos(ArrayList<Double> data) {
		Collections.sort(data); 
		System.out.println(data.toString());
		int m = (int) Math.sqrt(data.size());
		Intervalo actual = new Intervalo(0.0, 0.0, 0);
		int contador = 0; 
		for (int i = 0; i < data.size(); i++) {
			double currentValue = data.get(i);
			if((i + 1) % m == 0) {
				contador++;
				actual.setFin(currentValue);
				actual.setCantidad(contador);
				this.listaIntervalos.add(actual);
				actual = new Intervalo(currentValue, 0, 0);
				contador = 0;
			}else {
				contador++;
			}
		}
	}
	
	
	public ArrayList<Intervalo> getListaIntervalos() {
		return listaIntervalos;
	}


	public void setListaIntervalos(ArrayList<Intervalo> listaIntervalos) {
		this.listaIntervalos = listaIntervalos;
	}
	
	public String toString() {
		return this.listaIntervalos.toString();
	}
	
}