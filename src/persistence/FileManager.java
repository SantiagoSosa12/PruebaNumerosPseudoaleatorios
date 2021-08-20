package persistence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileManager {

	/**
	 * Lee un archivo tipo .txt especificado
	 * @param fileToRead el archivo a leer viene del controlador
	 * @return una lista de numeros pseudoaleatorios leidos, decimales
	 * @throws IOException excepcion de lectura de archivos
	 */
	public ArrayList<Double> readFile(File fileToRead) throws IOException {
		FileReader fileReader = new FileReader(fileToRead);
		BufferedReader bufferedReader = new BufferedReader(fileReader);

		ArrayList<Double> pseudoNumbers = new ArrayList<Double>();

		String data = "";
		while ((data = bufferedReader.readLine()) != null) {
			String[] lineData = data.split("#");
			for (String number : lineData) {
				pseudoNumbers.add(Double.parseDouble(number.replace(',', '.')));
			}
		}

		bufferedReader.close();
		fileReader.close();

		return pseudoNumbers;
	}
}