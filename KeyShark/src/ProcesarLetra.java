/*
* @(#)ProcesarLetra.java	0.1		24/08/2016

*
* Copyright (c) 2016.
* Paul Estrada León, Stefany Lindao Rodríguez, Elizabeth Sánchez Villamar.
* ESPOL. Guayaquil, Ecuador.
* Todos los derechos reservados.
*
*/

/**
 * La interface ProcesarLetra define el método procesar que será utilizado
 * por los animales animales al interactuar con la palabra o caracter adjuntado.
 * 
 * @version: 	0.1		24/08/2016
 * @author: 	Paul Estrada León, Stefany Lindao Rodríguez, Elizabeth Sánchez Villamar.
 */
public interface ProcesarLetra {
	
	/**
	 * Método que permite el procesamiento de la palabra
	 * o caracter según el animal marino involucrado.
	 * @param: letra
	 */
	public void procesar (String letra);
}
