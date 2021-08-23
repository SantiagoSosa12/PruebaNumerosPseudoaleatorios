package models;

import java.util.ArrayList;

public class KS{
	public int numDatos;
	public ArrayList<Double> listaDatos;
	public double mayor, menor,rPromedio,aceptacion,alfa,dMax,dMaxp;
	public double[] inicial, finalx,frecObt,fAcum,pObt,fEsperA,pEsp,dif;
	public boolean cumple;

	public KS(ArrayList<Double> listaDatos,double aceptacion, double alfa){
		this.listaDatos=listaDatos;
		this.aceptacion=aceptacion;
		this.alfa=alfa;
		numDatos=listaDatos.size();
		obtenerRPromedio();
		asignarMayor();
		asignarMenor();
		llenarDatosIF();
		llenarFrecuencia();
		llenarFrecuenciaAcumulada();
		llenarPObtenida();
		llenarFEsperada();
		llenarPEsperada();
		llenarDiferencia();
		buscarMayorDmax();
		dMaxp=0.1884; //debido a la confiabilidad y erro, esta en la tabla, viene por defecto
		//aqui para utilizar siempre a ese dato.
		validarCumplimiento();
	}
	
	private void validarCumplimiento() {
		if(dMax<dMaxp) {
			cumple= true;
		}else {
			cumple=false;
		}
	}

	private void buscarMayorDmax() {
		double max =0;
		for (int i = 0; i < dif.length; i++) {
			if(max<dif[i]) {
				max=dif[i];
			}
		}
		dMax=max;
	}

	private void llenarDiferencia() {
		dif = new double[10];
		for (int i = 0; i < dif.length; i++) {
			if(pEsp[i]-pObt[i]<0) {
				dif[i]= (pEsp[i]-pObt[i])*-1;
			}else {
				dif[i]= (pEsp[i]-pObt[i]);
			}
		}
	}

	private void llenarPEsperada() {
		pEsp= new double[10];
		for (int i = 0; i < pEsp.length; i++) {
			pEsp[i] = fEsperA[i]/numDatos;
		}
	}

	private void llenarFEsperada() {
		fEsperA=new double[10];
		int valor = numDatos/10;
		fEsperA[0]= valor;
		for (int i = 1; i < fEsperA.length; i++) {
			fEsperA[i] = fEsperA[i-1]+valor;
		}
	}

	private void llenarPObtenida() {
		pObt=new double[10];
		for (int i = 0; i < pObt.length; i++) {
			pObt[i]= fAcum[i]/numDatos;
		}
	}

	private void llenarFrecuenciaAcumulada() {
		fAcum=new double[10];
		fAcum[0] =frecObt[0];
		for (int i = 1; i < fAcum.length; i++) {
			if((i+1)<fAcum.length) {
				fAcum[i]=fAcum[i-1]+frecObt[i];
			}else {
				fAcum[9]=fAcum[i-1]+frecObt[i];
			}
		}
	}

	private void llenarFrecuencia() {
		int valor =0;
		frecObt= new double[10];
		for (int i = 0; i < frecObt.length; i++) {
			for (int j = 0; j < listaDatos.size(); j++) {
				if(listaDatos.get(j)>= inicial[i] &&listaDatos.get(j)<= finalx[i] ) {
					valor++;
				}
			}
			frecObt[i]=valor;
			valor=0;
		}
		int value=0;
		for (int i = 0; i < frecObt.length; i++) {
			value+=frecObt[i];
		}
		if(value!=numDatos) {
			frecObt[9]=frecObt[9]+1;
		}
	}

	private void llenarDatosIF() {
		inicial=new double[10];
		finalx=new double[10];
		inicial[0]=0.00000;
		for (int i = 0; i < inicial.length; i++) {
			if((i+1)<inicial.length) {
				finalx[i] =inicial[i]+(mayor-menor)/10;
				inicial[i+1]=finalx[i];
			}
		}
		finalx[9]=1.0000;
	}

	public void obtenerRPromedio() {
		double valor=0;
		System.out.println(listaDatos.size());
		for (int i = 0; i < listaDatos.size(); i++) {
			valor+= listaDatos.get(i);
		}
		rPromedio = valor/numDatos;
	}
	
	public void asignarMayor() {
		double valor =0;
		for (int i = 0; i < listaDatos.size(); i++) {
			if(valor<listaDatos.get(i)) {
				valor=listaDatos.get(i);
			}
		}
		mayor=valor;
	}
	
	public void asignarMenor() {
		double valor =99.99999;
		for (int i = 0; i < listaDatos.size(); i++) {
			if(listaDatos.get(i)<valor) {
				valor=listaDatos.get(i);
			}
		}
		menor =valor;
	}

	public double[] getDatos() {
		double[] aux = new double[6];
		aux[0]=menor;
		aux[1]=mayor;
		aux[2]=rPromedio;
		aux[3]=dMax;
		aux[4]=dMaxp;
		if(cumple==true) {
			aux[5]= 1;
		}else {
			aux[5]=0;
		}
		return aux;
	}

	public double getrPromedio() {
		return rPromedio;
	}

	public double[] getInicial() {
		return inicial;
	}

	public double[] getFinalx() {
		return finalx;
	}

	public double[] getFrecObt() {
		return frecObt;
	}

	public double[] getfAcum() {
		return fAcum;
	}

	public double[] getpObt() {
		return pObt;
	}

	public double[] getfEsperA() {
		return fEsperA;
	}

	public double[] getpEsp() {
		return pEsp;
	}

	public double[] getDif() {
		return dif;
	}
}