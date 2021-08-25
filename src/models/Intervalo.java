package models;

public class Intervalo {
	
	private double inicial;
	private double fin;
	private int cantidad;
	
	public Intervalo(double inicial, double fin, int cantidad) {
		this.inicial = inicial;
		this.fin = fin;
		this.cantidad = cantidad;
	}

	public double getInicial() {
		return inicial;
	}

	public void setInicial(double inicial) {
		this.inicial = inicial;
	}

	public double getFin() {
		return fin;
	}

	public void setFin(double fin) {
		this.fin = fin;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "Intervalo [inicial=" + inicial + ", fin=" + fin + ", cantidad=" + cantidad + "]";
	} 
	
}
