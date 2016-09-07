/*
* @(#)Buceador.java	0.1		24/08/2016

*
* Copyright (c) 2016.
* Paul Estrada León, Stefany Lindao Rodríguez, Elizabeth Sánchez Villamar.
* ESPOL. Guayaquil, Ecuador.
* Todos los derechos reservados.
*
*/

import javafx.application.Platform;
import javafx.scene.image.Image;

/**
 * La clase Buceador hereda de la clase Objeto.
 * Crea objetos de tipo Buceador.
 * 
 * @version: 	0.1		24/08/2016
 * @author: 	Paul Estrada León, Stefany Lindao Rodríguez, Elizabeth Sánchez Villamar.
 */
public class Buceador extends Objeto implements Runnable, Movimiento {

	/**
	 * Método que inicializa el nodo buceador dándole una posición y 
	 * una animación GIF como imagen de su instancia ImageView.
	 * @param: posicionX
	 * @param: posicionY
	 */
	public Buceador(Double posicionX, Double posicionY) {
		super("buceador", posicionX, posicionY, 1);
		super.getImageView().setImage(new Image("diver.gif"));
		super.getImageView().setLayoutX(posicionX);
		super.getImageView().setLayoutY(posicionY);
		super.getImageView().setFitWidth(200);
		super.getImageView().setPreserveRatio(true);
		super.setVivo(true);
	}
	
	
	@Override
	/**
	 * Método implementado de la interfaz Runnable. 
	 * Se encarga de mover el nodo a lo largo de la ventana y,
	 * además aumenta el nivel una vez que el nodo llega al fondo
	 * de la ventana.
	 */
	public void run() {
		while (isVivo()) {
			Platform.runLater(new Runnable() {
				public void run () {
					mover(0.0,1.0);
					if (getImageView().getLayoutY() >= 590.0) {
						Usuario.aumentarNivel();
						getImageView().setLayoutY(30.0);
					}
				}
			});
		
			try {
				Thread.sleep(150);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	/**
	 * Método implementado de la interfaz Movimiento. 
	 * Se encarga de cambiar la posición del nodo a través de sus componentes X y Y.
	 * @param: x
	 * @param: y
	 */
	public void mover(double x, double y) {
		super.getImageView().setLayoutX(super.getImageView().getLayoutX() + x);
		super.getImageView().setLayoutY(super.getImageView().getLayoutY() + y);
	}
}
