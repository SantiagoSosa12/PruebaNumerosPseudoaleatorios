package models;

import java.util.ArrayList;

public class Poker {

	/**
	 * listas para la aplicacion del algoritmo
	 */
	
	private ArrayList<Double> pseudoRandomNumbers;
	private ArrayList<String> hands;
	private int cant_datos;
	//Estas variables estaticas son las probabilidades predeterminadas de que salga cada mano en poker 5
	private static double D = 0.3024d;
	private static double O = 0.504d;
	private static double T = 0.108d;
	private static double K = 0.072d;
	private static double F = 0.009d;
	private static double P = 0.0045;
	private static double Q = 0.0001d;
	private static double X2alfa = 12.59158d;

	private double[] ei;
	
	/**
	 * este metodo obtiene las probabilidades de cada mano
	 * @return double[] con las probabilidades
	 */
	public double[] getProbs() {
		double[] probs = new double[7];
		probs[0] = D;
		probs[1] = O;
		probs[2] = T;
		probs[3] = K;
		probs[4] = F;
		probs[5] = P;
		probs[6] = Q;	
		return probs;
	}
	
	/**
	 * Este metodo instancia las lista
	 * @param pseudoRandomNumbers
	 */
	public void setDataPoker(ArrayList<Double> pseudoRandomNumbers) {
		this.pseudoRandomNumbers = pseudoRandomNumbers;
		this.hands= new ArrayList<>();
		this.cant_datos = this.pseudoRandomNumbers.size();
		ei = new double[7];
	}
	/**
	 * Este es el algoritmo principal aqui se analiza cada numero y se determina a que mano corresponde
	 */
	public void pokerAlgorithm() {
		ArrayList<String> numbers;
		ArrayList<String> numbersAdded;
		//Con este for each se recorre la lista de numeros pseudoaleatorios
		for (Double double1 : pseudoRandomNumbers) {
			//INstanciamos dos listas auxiliares
			numbers = new ArrayList<String>();
			numbersAdded = new ArrayList<>();
			//Hacemos un split para quitar el punto y solo quedarnos con los decimales
			String numb = double1.toString().split("\\.")[1];
			char charr = 'a';
			//Recorremos los decimales que estan guardados como un String
			for (int i = 0; i < numb.length(); i++) {
				//Analizamos cada posicion del string 
				charr = numb.charAt(i);
				//Si el numero ya fue agregado ala lista de numeros agregados no se hace el siguiente procedimiento
				if(!numbersAdded.contains(String.valueOf(charr))){
					//Agregamos como un string el numero y la cantidad de veces repetido en la cadena
					numbers.add(charr + "-"+ countChar(numb, charr));
					//lo agregamos a la lista de numeros ya revisados para evitar repetir
					numbersAdded.add(String.valueOf(charr));
				}
			}
			//Con las siguientes lineas se asigna a un array cada uno de la categoria obtenida para su posterior visualiacion en la interfaz
			ArrayList<String> encuentro = new ArrayList<String>();
			for (String string : numbers) {
				encuentro.add(string.split("-")[1]);
			}
			this.hands.add(getHand(encuentro));
		}
	}
	/**
	 * Este metodo recibe una lista con las manos encontradas en el numero y retorna D, O, T, etc, segun si hay un par, dos pares,etc.
	 * @param encontrados
	 * @return
	 */
	public String getHand(ArrayList<String> encontrados){
		//primero se calcula la cantidad total de cada uno que hay en la lista
		int todosDiferentes = 0;
		int unPar = 0;
		int tercia = 0;
		int cuatroDelMismo = 0;
		int cincoDelMismo = 0;
		for (String string : encontrados) {
			if (string.equals("1")) {
				todosDiferentes++;
			}else if (string.equals("2")) {
				unPar++;
			}else if (string.equals("3")) {
				tercia++;
			}else if (string.equals("4")) {
				cuatroDelMismo++;
			}else {
				cincoDelMismo++;
			}
		}
		//Finalmente segun la cantidad este if encadenado retornara el tipo de mano respectiva
		if (cincoDelMismo == 1) {
			return "Q";
		}else {
			if (cuatroDelMismo == 1) {
				return "P";
			}else {
				if (tercia == 1) {
					if (unPar == 1) {
						return "F";
					}else {
						return "K";
					}
				} else {
					if (unPar == 2) {
						return "T";
					}else {
						if(unPar == 1) {
							return "O";
						}else {
							if (todosDiferentes> 0) {
								return "D";
							}else {
								return "D";
							}
						}
					}
				}
			}
		}
		
	}
	/**
	 * Este metodo determina cuanta veces aparece un caracter en una cadena
	 * @param str
	 * @param c
	 * @return
	 */
	public int countChar(String str, char c){
	    int count = 0;
	    for(int i=0; i < str.length(); i++){    
	    	if(str.charAt(i) == c) {
	            count++;
	    	}
	    }
	    return count;
	}
	/**
	 * Con este metodo se obtiene Ei para cada mano y se concatena a 5 decimales
	 */
	public void createEi() {
		ei[0] = Math.round(cant_datos * D * 100000d) / 100000d;
		ei[1] = Math.round(cant_datos * O * 100000d) / 100000d;
		ei[2] = Math.round(cant_datos * T * 100000d) / 100000d;
		ei[3] = Math.round(cant_datos * K * 100000d) / 100000d;
		ei[4] = Math.round(cant_datos * F * 100000d) / 100000d;
		ei[5] = Math.round(cant_datos * P * 100000d) / 100000d;
		ei[6] = Math.round(cant_datos * Q * 100000d) / 100000d;			
	}
	
	public ArrayList<Double> getPseudoRandomNumbers() {
		return pseudoRandomNumbers;
	}

	public ArrayList<String> getHands() {
		return hands;
	}

	public double[] getEi() {
		return ei;
	}

	/**
	 * este metodo retorna la cantida de D, O, T, etc. que hay en el maso
	 * recibe como parametro la lista y el caracter o mano que se quiere contar
	 * @param chars
	 * @param s
	 * @return
	 */
	public double getOi(ArrayList<String> chars, String s) {
		double count = 0;
		for (String string : chars) {
			if (string.equals(s)) {
				count++;
			}
		}
		return count;
	}
	/**
	 * Con este metodo calculamos (Ei-Oi)^2/Ei y retornamos la lista de todos los calculos
	 * @param oi
	 * @return
	 */
	public double[] getEie(double[] oi) {
		double [] eie = new double[7];
		for (int i = 0; i < ei.length; i++) {
			eie[i] = Math.round(Math.pow((ei[i] - oi[i]),2 )/ ei[i]* 100000d) / 100000d;;
		}
		return eie;
	}
	
	public double getX2alfa() {
		return X2alfa;
	}
	
}