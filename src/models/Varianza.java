package models;

import java.util.ArrayList;

import org.apache.commons.math3.distribution.ChiSquaredDistribution;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class Varianza {

	public int cant_datos;
	public int grados_libertad;
	public double confianza;
	public double alfa;
	public ArrayList<Double> datos;
	public double media, varianza, li2, ls2;

	public Varianza(ArrayList<Double> datos, double confianza) {
		// this.datos = datos;
		cant_datos = datos.size();
		grados_libertad = cant_datos - 1;
		this.confianza = confianza;
		alfa = 1 - confianza;
		// Creamos objetos con las tablas de distribución normal y chi cuadrada, y un
		// objeto que nos ayudará a obtener la media y la varianza.
		ChiSquaredDistribution chi = new ChiSquaredDistribution(grados_libertad);
		DescriptiveStatistics estadistica = new DescriptiveStatistics();

		// Metemos los datos del arreglo en el objeto estadistica.

		for (int i = 0; i < cant_datos; i++) {
			estadistica.addValue(datos.get(i));
		}

		media = estadistica.getMean();
		varianza = estadistica.getVariance();

		li2 = chi.inverseCumulativeProbability(alfa / 2) / (12 * grados_libertad);
		ls2 = chi.inverseCumulativeProbability(1 - alfa / 2) / (12 * grados_libertad);
	}

	public double[] getDatos() {
		double[] datos = new double[5];
		datos[0] = media;
		datos[1] = varianza;
		datos[2] = li2;
		datos[3] = ls2;
		if (varianza >= li2 && varianza <= ls2) {
			datos[4] = 1;
		} else {
			datos[4] = 0;
		}
		return datos;
	}
}