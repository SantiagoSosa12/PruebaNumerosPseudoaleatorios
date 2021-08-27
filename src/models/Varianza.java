package models;

import java.util.ArrayList;

import org.apache.commons.math3.distribution.ChiSquaredDistribution;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class Varianza {
	//Cantidad de datos
	public int cant_datos;
	public int grados_libertad;
	public double aceptacion;
	public double alfa;
	//Arraylist con los datos ingresados
	public ArrayList<Double> datos;
	// media, varianza, limite inferior, limite superior;
	public double media, varianza, li, ls;

	public Varianza() {
	}

	/**
	 * Algoritmo para determinar la variana
	 * @param acceptanceMargin
	 * @param pseudoRandomNumbers
	 */
	public void getVarianza(double acceptanceMargin, ArrayList<Double> pseudoRandomNumbers) {
		//Se instancian las variables que se van a usar 
		this.datos = pseudoRandomNumbers;
		cant_datos = datos.size();
		grados_libertad = cant_datos - 1;
		this.aceptacion = acceptanceMargin;
		
		alfa = 1 - aceptacion;
		//Instanciamos esta clase de la libreria de apache math3 para crear una distribucion chi cuadrao
		ChiSquaredDistribution chi = new ChiSquaredDistribution(grados_libertad);
		// Metemos los datos del arreglo en el objeto estadistica.
		DescriptiveStatistics estadistica = new DescriptiveStatistics();
		for (Double double1 : pseudoRandomNumbers) {
			estadistica.addValue(double1);
		}
		//determinamos la media y la varianza con esta libreria
		media = estadistica.getMean();
		varianza = estadistica.getVariance();
		//Finalmente con el uso de la clase chi calculamos el limite inferior y superior
		li = chi.inverseCumulativeProbability(alfa / 2) / (12 * grados_libertad);
		ls = chi.inverseCumulativeProbability(1 - (alfa / 2)) / (12 * grados_libertad);
	}
	/**
	 * Este metodo determina si se cumple la prueba
	 * @return
	 */
	public boolean cumple() {
		if (varianza<= ls && varianza >= li) {
			return true;
		}else {
			return false;
		}
			
	}

	/**
	 * Obtiene los resultados de la prueba
	 * @return
	 */
	public Object[] getResults() {
		return new Object[] {aceptacion, media, varianza, li, ls };
	}
}