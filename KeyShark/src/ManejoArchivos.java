/*
* @(#)ManejoArchivos.java	0.1		24/08/2016

*
* Copyright (c) 2016.
* Paul Estrada León, Stefany Lindao Rodríguez, Elizabeth Sánchez Villamar.
* ESPOL. Guayaquil, Ecuador.
* Todos los derechos reservados.
*
*/

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * La clase ManejoArchivos permite leer y manipular archivos de texto que son
 * utilizados en la aplicación.
 * 
 * @version: 	0.1		24/08/2016
 * @author: 	Paul Estrada León, Stefany Lindao Rodríguez, Elizabeth Sánchez Villamar.
 */
public class ManejoArchivos {
	private String nombreArchivo;
	
	private Scanner scanner;
	private FileReader lectorDeArchivo;
	private PrintWriter writer;
	private Random random = new Random();
	
	/**
	 * Método que inicializa el atributo nombreArchivo 
	 * con un nombre ingresado como parámetro del constructor.
	 * @param: nombreArchivo
	 */
	public ManejoArchivos (String nombreArchivo) throws FileNotFoundException {
		this.nombreArchivo = nombreArchivo;
	}
	
	/**
	 * Método que retorna un List<String> con cada línea de un archivo .txt
	 * @return: lista
	 */
	public List<String> getLista () throws IOException {
		lectorDeArchivo = new FileReader(nombreArchivo);
		scanner = new Scanner(lectorDeArchivo);
		List<String> lista = new ArrayList<String> ();
		while (scanner.hasNext()) {
			lista.add(scanner.nextLine());
		}
		lectorDeArchivo.close();
		return lista;
	}
	
	/**
	 * Método que elige aleatoriamente una palabra dado un array de String.
	 * Elige una palabra para algún animal, ya sea un tiburón, una piraña o
	 * un tiburón negro.
	 * @return: palabras[aleatorio]
	 */
	public String elegirPalabra () throws IOException {
		String[] palabras = (String[]) this.getLista().toArray(new String[0]);
		int aleatorio = random.nextInt(palabras.length);
		return palabras[aleatorio];
	}
	
	/**
	 * Método que guarda la nueva lista de highScores.
	 * Toma de parámetro un List<String>
	 * @param: lista
	 */
	public void guardarHighScore (List<String> lista) throws FileNotFoundException, UnsupportedEncodingException {
		writer = new PrintWriter(this.nombreArchivo,"UTF-8");
		for (int i=0; i<lista.size(); i++) {
			writer.println(lista.get(i));
		}
		writer.close();
	}
	
	
}
