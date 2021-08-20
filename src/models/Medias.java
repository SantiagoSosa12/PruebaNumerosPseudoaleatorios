package models;

import java.util.ArrayList;

public class Medias {

	/** Cantidad de numeros pseudoaleatorios que llegan */
	private int n;

	/**
	 * 1 - el margen de aceptacion, los limites de aceptacion a partir de miu 0,5
	 */
	private double alpha;

	/**
	 * El valor x generado a buscar en la tabla Z de distribucion normal, esta dado
	 * por la formula x = 1 - (alpha / 2)
	 */
	private double x;

	/** Valor Z encontrado en la tabla de distro normal */
	private double z;

	/** Limite inferior de aceptación */
	private double lowerLimit;

	/** Limite superior de aceptación */
	private double upperLimit;

	private double miu;

	/**
	 * Ejecuta la prueba de medias
	 * 
	 * @param acceptanceMargin margen de aceptacion para la prueba de medias,
	 *                         generalmente 95%
	 * @param pseudoNumbers    los numeros a testear para la prueba
	 * @return si el conjunto de números pseudoaleatorios pasa la prueba o no
	 */
	public boolean mediasTesting(double acceptanceMargin, ArrayList<Double> pseudoNumbers) {
		n = pseudoNumbers.size();

		alpha = 1 - acceptanceMargin;

		x = 1 - (alpha / 2);

		miu = this.findAverageOfPseudoNumbers(pseudoNumbers);

		double sumatoriaVarianza = 0;
		for (Double pseudoNumber : pseudoNumbers) {
			sumatoriaVarianza += Math.pow((pseudoNumber - miu), 2);
		}
		double varianza = sumatoriaVarianza / n;

		double desvEstandar = Math.sqrt(varianza);

		z = (x - miu) / desvEstandar;

		lowerLimit = (0.5) - (z * (1 / Math.sqrt(12 * n)));

		upperLimit = (0.5) + (z * (1 / Math.sqrt(12 * n)));

		if (miu >= lowerLimit && miu <= upperLimit) {
			return true;
		} else
			return false;
	}

	/**
	 * Calcula el promedio de los números pseudoaleatorios
	 * 
	 * @param pseudoNumbers los numeros pseudoaleatorios a testear, vienen del
	 *                      archivo txt
	 * @return el promedio calculado
	 */
	private double findAverageOfPseudoNumbers(ArrayList<Double> pseudoNumbers) {
		double accumulatedSum = 0;
		for (Double pseudoNum : pseudoNumbers) {
			accumulatedSum += pseudoNum;
		}
		return accumulatedSum / this.n;
	}

	public Object[] getResults() {
		return new Object[] { alpha, n, miu, x, z, lowerLimit, upperLimit };
	}
}