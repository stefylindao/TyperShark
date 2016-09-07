/*
* @(#)Objeto.java	0.1		24/08/2016

*
* Copyright (c) 2016.
* Paul Estrada León, Stefany Lindao Rodríguez, Elizabeth Sánchez Villamar.
* ESPOL. Guayaquil, Ecuador.
* Todos los derechos reservados.
*
*/

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * La clase Objeto es una superclase de los nodos que tienen 
 * movimiento en la pantalla. Implementa la interface Runnable.
 * Permite el modelamiento del buceador y los animales marinos del juego.
 * 
 * @version: 	0.1		24/08/2016
 * @author: 	Paul Estrada León, Stefany Lindao Rodríguez, Elizabeth Sánchez Villamar.
 */
public class Objeto implements Runnable {
	private String tipo;
	private int nivel;
	
	private double posicionX;
	private double posicionY;
	
	private Thread hilo;
	private Label label;
	
	private boolean vivo;
	
	private ImageView imageView;

	/**
	 * Método que construye un objeto con una posición X y Y dada,
	 * un tipo de objeto y un nivel.
	 * @param: tipo
	 * @param: posicionX
	 * @param: posicionY
	 * @param: nivel
	 */
	public Objeto (String tipo, double posicionX, double posicionY, int nivel) {
		this.setTipo(tipo);
		this.setPosicionX(posicionX);
		this.setPosicionY(posicionY);
		this.setNivel(nivel);
		this.setImageView(new ImageView());
		label = new Label();
	}

	/**
	 * Método que permite obtener el tipo de objeto.
	 * @return: tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Método que da acceso al tipo de objeto para su
	 * posterior modificación.
	 * @param: tipo
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * Método que permite obtener la posición en X.
	 * @return: posicionX
	 */
	public double getPosicionX() {
		return posicionX;
	}

	/**
	 * Método que da acceso a la posición en X para su
	 * posterior modificación.
	 * @param: posicionX
	 */
	public void setPosicionX(double posicionX) {
		this.posicionX = posicionX;
	}

	/**
	 * Método que permite obtener la posición en Y.
	 * @return: posicionY
	 */
	public double getPosicionY() {
		return posicionY;
	}

	/**
	 * Método que da acceso a la posición en Y para su
	 * posterior modificación.
	 * @param: posicionY
	 */
	public void setPosicionY(double posicionY) {
		this.posicionY = posicionY;
	}

	/**
	 * Método que permite obtener el nivel.
	 * @return: nivel
	 */
	public int getNivel() {
		return nivel;
	}

	/**
	 * Método que da acceso al nivel para su
	 * posterior modificación.
	 * @param: nivel
	 */
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	/**
	 * Método que permite obtener la visualización
	 * de una imagen.
	 * @return: imageView
	 */
	public ImageView getImageView() {
		return imageView;
	}

	/**
	 * Método que da acceso a la visualización de una 
	 * imagen para su posterior modificación.
	 * @param: imageView
	 */
	public void setImageView(ImageView imageView) {
		this.imageView = imageView;
	}

	@Override
	public void run() {
		
	}

	/**
	 * Método que permite obtener el hilo del objeto.
	 * @return: hilo
	 */
	public Thread getHilo() {
		return hilo;
	}

	/**
	 * Método que da acceso al hilo del objeto para su
	 * posterior modificación.
	 * @param: hilo
	 */
	public void setHilo(Thread hilo) {
		this.hilo = hilo;
	}

	/**
	 * Método que permite saber si el objeto está vivo.
	 * @return: vivo
	 */
	public boolean isVivo() {
		return vivo;
	}

	/**
	 * Método que da acceso a la vida del objeto para su
	 * posterior modificación.
	 * @param: vivo
	 */
	public void setVivo(boolean vivo) {
		this.vivo = vivo;
	}

	/**
	 * Método que permite obtener la etiqueta.
	 * @return: label
	 */
	public Label getLabel() {
		return label;
	}

	/**
	 * Método que da acceso a la etiqueta para su
	 * posterior modificación.
	 * @param: label
	 */
	public void setLabel(Label label) {
		this.label = label;
	}
	
}
