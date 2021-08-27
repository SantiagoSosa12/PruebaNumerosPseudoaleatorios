package models;

import java.util.ArrayList;

import org.apache.commons.math3.distribution.ChiSquaredDistribution;

public class Chi2 {
	
	private ArrayList<Intervalo> listaIntervalos;
	private double[] Ri;
	private String[] intervalos;
	private double[] Oi;
	private double[] frecuencias;
	private double m;
	private double sumaFrecuencia;
	private double chiCuadrado;
	
	public Chi2() {
		this.listaIntervalos = new ArrayList<Intervalo>();
		this.m = 0;
		this.sumaFrecuencia = 0;
		this.chiCuadrado = 0;
		
	}
	
	/**
	 * Segmenta los datos segun la raiz del valor total de los mismos
	 * Es decir que para 100, m = 5
	 * @param data
	 */
	public void segmentarDatos(ArrayList<Double> data) {
		this.Ri = new double[data.size()];
		this.m = Math.sqrt(data.size());
		for (int i = 0; i < data.size(); i++) {
			this.Ri[i] = data.get(i);
		}
		this.crearIntervalos();
		this.contarSiEstaDentroDeLosLimites(data);
	}
	/**
	 * Se crean intervalos segun los datos, pero si tenemos 100 datos los intervalos
	 * serian: 0.0 - 0.1, 0.1 - 0.2 ..... 
	 */
	private void crearIntervalos() {
		for (int i = 0; i < (int) m; i++) {
			Intervalo intervalo = new Intervalo(i * (1 / this.m) , (i+1) * (1 / this.m), 0);
			this.listaIntervalos.add(intervalo);
		}
		this.listaIntervalos.get(this.listaIntervalos.size() - 1).setFin(1.0);;
	}
	
	/**
	 * Una vez creados los inetvalos contamos cuandos datos hay dentro del mismo,
	 * por ejemplo 0.0 - 0.1 pueden haber 5 datos independiente de si es 0.1 o 0.1
	 * @param data
	 */
	private void contarSiEstaDentroDeLosLimites(ArrayList<Double> data) {
		for (int i = 0; i < this.listaIntervalos.size(); i++) {
			int cont = 0;
			Intervalo current = this.listaIntervalos.get(i);
			double inferior = current.getInicial() ;
			double superior = current.getFin();
			for (int j = 0; j < data.size(); j++) {
				double currentData = data.get(j);
				if(currentData >= inferior && currentData < superior) {
					cont++;
				}
				if(i+1 == listaIntervalos.size() && currentData == superior) {
					cont++;
				}
			}
			current.setCantidad(cont);
		}
	}
	
	/**
	 * Se ejecuta una vez hecha la segmentación 
	 * Los datos son necesarios para registralos en la tabla
	 * aqui mismo desgregamos los datos de la lista de intervalos
	 * los mismos son separados por vectores para mostralos en la tabla
	 * Por ultimo calculamos el valor de CHI2 esperado usando APACHE COMMONS
	 */
	public boolean asignarDatosAdicionales() {
		this.intervalos = new String[this.listaIntervalos.size()];
		this.Oi = new double[this.listaIntervalos.size()];
		this.frecuencias = new double[this.listaIntervalos.size()];
		for (int i = 0; i < this.listaIntervalos.size(); i++) {
			Intervalo intervalo = this.listaIntervalos.get(i);
			this.intervalos[i] = "" + intervalo.getInicial() + " - " + intervalo.getFin();
			this.Oi[i] = intervalo.getCantidad();
			double frecuencia = (Math.pow((this.m - intervalo.getCantidad()), 2)) / this.m;
			this.frecuencias[i] = frecuencia;
			this.sumaFrecuencia += frecuencia;
		}
		double alfa = 0.05;
		double apache = 1 - (alfa / 2);
		ChiSquaredDistribution chi2 = new ChiSquaredDistribution(this.m - 1);//49 grados de libertad 
		this.chiCuadrado = chi2.inverseCumulativeProbability(apache);
		if(this.sumaFrecuencia < this.chiCuadrado) {
			return true;
		}
		return false;
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

	public double[] getRi() {
		return Ri;
	}

	public void setRi(double[] ri) {
		Ri = ri;
	}

	public String[] getIntervalos() {
		return intervalos;
	}

	public void setIntervalos(String[] intervalos) {
		this.intervalos = intervalos;
	}

	public double[] getOi() {
		return Oi;
	}

	public void setOi(double[] oi) {
		Oi = oi;
	}

	public double[] getFrecuencias() {
		return frecuencias;
	}

	public void setFrecuencias(double[] frecuencias) {
		this.frecuencias = frecuencias;
	}

	public double getM() {
		return m;
	}

	public void setM(double m) {
		this.m = m;
	}

	public double getSumaFrecuencia() {
		return sumaFrecuencia;
	}

	public void setSumaFrecuencia(double sumaFrecuencia) {
		this.sumaFrecuencia = sumaFrecuencia;
	}

	public double getChiCuadrado() {
		return chiCuadrado;
	}

	public void setChiCuadrado(double chiCuadrado) {
		this.chiCuadrado = chiCuadrado;
	}
	
}