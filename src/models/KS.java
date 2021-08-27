package models;

import java.util.ArrayList;

public class KS{
	
	private static double[] TABLA = {0.975, 0.84189, 0.7076, 0.62394, 0.56328, 0.51926, 0.48342, 0.45427, 0.43001, 0.40925, 0.39122
			, 0.37543, 0.36143, 0.3489, 0.3375, 0.32733, 0.31796, 0.30936, 0.30143, 0.29408, 0.28724, 0.25283, 0.2749, 0.26931, 0.26404, 0.25908, 0.25438, 0.24993
			, 0.2457, 0.2417, 0.23788, 0.23424, 0.20771, 0.22743, 0.22425, 0.22119, 0.21826, 0.21544, 0.21273, 0.21012, 0.2076, 0.20517, 0.20283, 0.20056, 0.19837
			, 0.19625, 0.1942, 0.19221, 0.19028, 0.18841
	};
	
	private ArrayList<Intervalo> listaIntervalos;
	private double[] Ri;
	private String[] intervalos;
	private double m;//Cantidad intervalos
	private double ks;
	private ArrayList<Double> data;
	private double[] frecuencias;
	private double[] frecuenciasAcumuladas;
	private double[] probabilidadObtenida;
	private double[] esperadaMenosObtenida;
	
	public KS(ArrayList<Double> data) {
		this.data = data;
		this.listaIntervalos = new ArrayList<Intervalo>();
		this.Ri = new double[this.listaIntervalos.size()];
		this.m = 10;
		this.frecuenciasAcumuladas = new double[10];
		this.probabilidadObtenida = new double[10];
		this.esperadaMenosObtenida = new double[10];
		this.frecuencias = new double[10];
		this.ks = 0;
	}
	
	public void segmentarDatos() {
		this.Ri = new double[data.size()];
		for (int i = 0; i < data.size(); i++) {
			this.Ri[i] = data.get(i);
		}
		this.crearIntervalos();
		this.contarSiEstaDentroDeLosLimites();
	}
	
	private void crearIntervalos() {
		for (int i = 0; i < (int) this.m; i++) {
			Intervalo intervalo = new Intervalo(i * (1 / this.m) , (i+1) * (1/this.m), 0);
			this.listaIntervalos.add(intervalo);
		}
	}
	
	private void contarSiEstaDentroDeLosLimites() {
		for (int i = 0; i < this.listaIntervalos.size(); i++) {
			int cont = 0;
			Intervalo current = this.listaIntervalos.get(i);
			double inferior = current.getInicial();
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
	 */
	public void asignarFrecuencias() {
		this.intervalos = new String[this.listaIntervalos.size()];
		this.frecuencias = new double[this.listaIntervalos.size()];
		for (int i = 0; i < this.listaIntervalos.size(); i++) {
			Intervalo intervalo = this.listaIntervalos.get(i);
			this.intervalos[i] = intervalo.getInicial() + " - " + intervalo.getFin();
			this.frecuencias[i] = intervalo.getCantidad();
		}
	}
	
	/**
	 * 3er paso
	 * @return
	 */
	public boolean pasoPrueba() {
		this.obtenerFrecuenciasAcumuladas();
		this.obtenerProbabilidadObtenida();
		double total = this.obtenerTotalEsperadaMenosObtenida();
		int cantidadDatos = this.Ri.length; 
		this.ks = TABLA[cantidadDatos];
		if(cantidadDatos <= 50) {
			if(total < TABLA[cantidadDatos]) {
				return true;
			}
		}else if(total < (1.36 / (Math.sqrt(cantidadDatos)))) {
			return true;
		}
		return false;
	}
	
	private void obtenerFrecuenciasAcumuladas() {
		this.frecuenciasAcumuladas[0] = this.frecuencias[0];
		for (int i = 1; i < this.frecuencias.length; i++) {
			this.frecuenciasAcumuladas[i] = this.frecuenciasAcumuladas[i - 1] + this.frecuencias[i]; 
		}
	}
	
	private void obtenerProbabilidadObtenida() {
		for (int i = 0; i < this.frecuencias.length; i++) {
			this.probabilidadObtenida[i] = this.frecuencias[i] / 50;
		}
	}
	
	private double obtenerTotalEsperadaMenosObtenida() {
		int total = 0;
		for (int i = 0; i < this.probabilidadObtenida.length; i++) {
			this.esperadaMenosObtenida[i] = Math.abs(((i+1) * 0.1) -this.probabilidadObtenida[i]);
			total += this.esperadaMenosObtenida[i];
		}
		return total;
	}

	public double getKs() {
		return ks;
	}

	public void setKs(double ks) {
		this.ks = ks;
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

	public double[] getFrecuencias() {
		return frecuencias;
	}

	public void setFrecuencias(double[] frecuencias) {
		this.frecuencias = frecuencias;
	}

	public double[] getFrecuenciasAcumuladas() {
		return frecuenciasAcumuladas;
	}

	public void setFrecuenciasAcumuladas(double[] frecuenciasAcumuladas) {
		this.frecuenciasAcumuladas = frecuenciasAcumuladas;
	}

	public double[] getProbabilidadObtenida() {
		return probabilidadObtenida;
	}

	public void setProbabilidadObtenida(double[] probabilidadObtenida) {
		this.probabilidadObtenida = probabilidadObtenida;
	}

	public double[] getEsperadaMenosObtenida() {
		return esperadaMenosObtenida;
	}

	public void setEsperadaMenosObtenida(double[] esperadaMenosObtenida) {
		this.esperadaMenosObtenida = esperadaMenosObtenida;
	}
	
}