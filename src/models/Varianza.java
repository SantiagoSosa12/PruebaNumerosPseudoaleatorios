package models;

import java.awt.GraphicsConfiguration;
import java.util.ArrayList;

import org.apache.commons.math3.distribution.ChiSquaredDistribution;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class Varianza {

	public int cant_datos;
	public int grados_libertad;
	public double aceptacion;
	public double alfa;
	public ArrayList<Double> datos;
	public double media, varianza, li, ls;

	public Varianza() {
	}

	public void getVarianza(double acceptanceMargin, ArrayList<Double> pseudoRandomNumbers) {
		this.datos = pseudoRandomNumbers;
		cant_datos = datos.size();
		grados_libertad = cant_datos - 1;
		this.aceptacion = acceptanceMargin;
		
		alfa = 1 - aceptacion;
		// Creamos objetos con las tablas de distribuci�n normal y chi cuadrada, y un
		// objeto que nos ayudar� a obtener la media y la varianza.
		ChiSquaredDistribution chi = new ChiSquaredDistribution(grados_libertad);
		// Metemos los datos del arreglo en el objeto estadistica.
		DescriptiveStatistics estadistica = new DescriptiveStatistics();
		for (Double double1 : pseudoRandomNumbers) {
			estadistica.addValue(double1);
		}
		media = estadistica.getMean();
		varianza = estadistica.getVariance();

		li = chi.inverseCumulativeProbability(alfa / 2) / (12 * grados_libertad);
		ls = chi.inverseCumulativeProbability(1 - (alfa / 2)) / (12 * grados_libertad);
	}
	
	public boolean cumple() {
		if (varianza<= ls && varianza >= li) {
			return true;
		}else {
			return false;
		}
			
	}

	public Object[] getResults() {
		return new Object[] {aceptacion, media, varianza, li, ls };
	}

	public GraphicsConfiguration getDatos() {
		// TODO Auto-generated method stub
		return null;
	}
}