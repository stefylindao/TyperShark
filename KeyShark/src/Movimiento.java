/*
* @(#)Movimiento.java	0.1		24/08/2016

*
* Copyright (c) 2016.
* Paul Estrada León, Stefany Lindao Rodríguez, Elizabeth Sánchez Villamar.
* ESPOL. Guayaquil, Ecuador.
* Todos los derechos reservados.
*
*/

/**
 * La interface Movimiento define el método mover que será utilizado
 * tanto por el buceador como por los animales marinos.
 * 
 * @version: 	0.1		24/08/2016
 * @author: 	Paul Estrada León, Stefany Lindao Rodríguez, Elizabeth Sánchez Villamar.
 */
public interface Movimiento {
	
	/**
	 * Método que permite el movimiento del buceador y
	 * de los animales marinos según sea el caso.
	 * @param: x
	 * @param: y
	 */
	public void mover (double x, double y);
}
