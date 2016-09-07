/*
* @(#)TiburonNegro.java	0.1		24/08/2016

*
* Copyright (c) 2016.
* Paul Estrada León, Stefany Lindao Rodríguez, Elizabeth Sánchez Villamar.
* ESPOL. Guayaquil, Ecuador.
* Todos los derechos reservados.
*
*/

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * La clase TiburonNegro hereda de la clase Objeto.
 * Crea objetos de tipo TiburonNegro.
 * 
 * @version: 	0.1		24/08/2016
 * @author: 	Paul Estrada León, Stefany Lindao Rodríguez, Elizabeth Sánchez Villamar.
 */
public class TiburonNegro extends Objeto implements Movimiento, Animacion, ProcesarLetra, Runnable {

	private boolean matando;
	private int numVidas;
	private String palabraAsignada;
	private String letrasRestantes;
	private String letrasIngresadas = "";
	
	/**
	 * Método que construye un objeto que representa al tiburón negro, 
	 * le da una posición X y Y deseada junto a una imagen animada. 
	 * Este animal tendrá de 1 a 4 palabras hasta que se pueda
	 * eliminar por completo.
	 * @param: posicionX
	 * @param: posicionY
	 */
	public TiburonNegro(Double posicionX, Double posicionY) {
		super("tiburonNegro", posicionX, posicionY, 1);
		super.setVivo(false);
		super.getImageView().setImage(new Image("tiburonNegro.gif"));
		super.getImageView().setLayoutX(posicionX);
		super.getImageView().setLayoutY(posicionY);
		super.getImageView().setFitWidth(300);
		super.getImageView().setPreserveRatio(true);
		
		super.getLabel().setTextFill(Color.WHITE);
		super.getLabel().setLayoutX(posicionX);
		super.getLabel().setLayoutY(posicionY);
		
		matando = false;
	}

	
	@Override
	/**
	 * Método implementado por la interfaz Runnable.
	 * Se encarga de mover el objeto a través de la pantalla.
	 * A su vez verifica constantemente si se debe matar al animal,
	 * ya sea porque el usuario lo eliminó o porque este llegó al otro 
	 * extremo de la pantalla y desapareció.
	 * Si este objeto desaparece de la pantalla afectará la vida del jugador.
	 */
	public void run() {
		setVivo(true);
		while (isVivo()) {
			Platform.runLater(new Runnable() {
				public void run () {
					if (palabraAsignada.equals(letrasIngresadas.toLowerCase())) {
						numVidas --;
						if (numVidas <= 0) {
							if (matando == false) {
								Usuario.actualizarPuntaje(150);
								matando = true;
							} else {
								matar();
							}
						} else {
							try {
								colocarPalabra();
							} catch (IOException e) {
							}
						}
					} else if (getImageView().getLayoutX() <= 30.0) {
						if (matando == false) {
							try {
								Usuario.quitarVida();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							matando = true;
						} else {
							if (getImageView().getLayoutX() <= -300.0) {
								matar();
							} else {
								mover(-0.15,0.0);
							}
						}
					} else {
						mover(-0.15,0.0);
					}
				}
			});
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	@Override
	/**
	 * Método implementado por la interfaz Movimiento.
	 * Se encarga de cambiar las posiciones X y Y del 
	 * objeto para poder moverse a través de la pantalla.
	 * @param: x
	 * @param: y
	 */
	public void mover(double x, double y) {
		super.getImageView().setLayoutX(super.getImageView().getLayoutX() + (x*Usuario.nivel));
		super.getImageView().setLayoutY(super.getImageView().getLayoutY() + y);
		
		super.getLabel().setLayoutX(super.getLabel().getLayoutX() + (x*Usuario.nivel));
		super.getLabel().setLayoutY(super.getLabel().getLayoutY() + y);
	}

	@Override
	/**
	 * Método implementado por la interfaz Animación.
	 * Se encarga de realizar una animación que lleva al objeto 
	 * hasta el fondo de la pantalla haciéndolo desaparecer.
	 * Una vez desaparecido se desecha el hilo que le permitía
	 * moverse a este objeto y se cambia su estado de vivo a muerto.
	 */
	public void matar() {
		mover(-0.5,5.0);
		if (getImageView().getLayoutY() >= 700.0) {
			setVivo(false);
			this.setHilo(null);
		}
	}

	@Override
	/**
	 * Método implementado por la interfaz ProcesarLetra.
	 * Se encarga de verificar si las letras ingresadas por teclado 
	 * concuerdan con la palabra que lleva el objeto.
	 * @param: letra
	 */
	public void procesar(String letra) {
		if (super.isVivo() && this.letrasRestantes.startsWith(letra)) {
			this.letrasIngresadas = this.letrasIngresadas + letra.toUpperCase();
			this.letrasRestantes = recortarString(this.letrasRestantes);
			super.getLabel().setText(this.letrasIngresadas + this.letrasRestantes);
		}
	}
	
	/**
	 * Método que coloca una nueva palabra aleatoria al objeto.
	 * También se encarga de resetear atributos que son necesarios
	 * en el método procesar.
	 */
	public void colocarPalabra () throws FileNotFoundException, IOException {
		numVidas = new Random().nextInt(3);
		
		ManejoArchivos lector = new ManejoArchivos("palabras.txt");
		this.letrasIngresadas = "";
		this.palabraAsignada = lector.elegirPalabra();
		this.letrasRestantes = this.palabraAsignada;
		super.getLabel().setText(this.palabraAsignada);
	}
	
	/**
	 * Método que toma como parámetro un string y devuelve el mismo string 
	 * pero sin su primera letra. Usado en el método procesar.
	 * @param: palabra
	 * @return: nuevaPalabra
	 */
	public String recortarString (String palabra) {
		String[] array = palabra.split("");
		String nuevaPalabra = new String();
		for (int i=1;i<palabra.length();i++){
			nuevaPalabra = nuevaPalabra + array[i];
		}
		return nuevaPalabra;
	}
	
	/**
	 * Método invocado cuando el jugador usa el poder especial.
	 * Causa la muerte inmediata del objeto.
	 */
	public void muerteInmediata () {
		this.numVidas = 0;
		this.letrasIngresadas = this.palabraAsignada;
	}
	
	/**
	 * Método que cambia el estado del atributo matando a un 
	 * valor false. Este atributo es usado como parte del proceso 
	 * de animación de matar del objeto.
	 */
	public void resetMatando () {
		matando = false;
	}
}
