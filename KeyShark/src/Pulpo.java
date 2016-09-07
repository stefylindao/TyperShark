/*
* @(#)Pulpo.java	0.1		24/08/2016

*
* Copyright (c) 2016.
* Paul Estrada Le�n, Stefany Lindao Rodr�guez, Elizabeth S�nchez Villamar.
* ESPOL. Guayaquil, Ecuador.
* Todos los derechos reservados.
*
*/

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * La clase Pulpo hereda de la clase Objeto.
 * Crea objetos de tipo Pulpo.
 * 
 * @version: 	0.1		24/08/2016
 * @author: 	Paul Estrada Le�n, Stefany Lindao Rodr�guez, Elizabeth S�nchez Villamar.
 */
public class Pulpo extends Objeto implements Movimiento, Animacion, ProcesarLetra, Runnable {

	private boolean matando;
	private String palabraAsignada;
	private String letrasRestantes;
	private String letrasIngresadas = "";
	//pulpo
	/**
	 * M�todo que construye un objeto que representa al tibur�n, 
	 * le da una posici�n X y Y deseada junto a una imagen animada. 
	 * @param: posicionX
	 * @param: posicionY
	 */
	public Pulpo(Double posicionX, Double posicionY) throws FileNotFoundException, IOException {
		super("pulpo", posicionX, posicionY, 1);
		super.setVivo(false);
		super.getImageView().setImage(new Image("pulpo.gif"));
		super.getImageView().setLayoutX(posicionX);
		super.getImageView().setLayoutY(posicionY);
		super.getImageView().setFitWidth(200);
		super.getImageView().setPreserveRatio(true);
		
		super.getLabel().setTextFill(Color.WHITE);
		super.getLabel().setLayoutX(posicionX);
		super.getLabel().setLayoutY(posicionY);
	}

	@Override
	/**
	 * M�todo implementado por la interfaz Runnable.
	 * Se encarga de mover el objeto a trav�s de la pantalla.
	 * A su vez verifica constantemente si se debe matar al animal,
	 * ya sea porque el usuario lo elimin� o porque este lleg� al otro 
	 * extremo de la pantalla y desapareci�.
	 * Si este objeto desaparece de la pantalla afectar� la vida del jugador.
	 */
	public void run() {
		setVivo(true);
		while (isVivo()) {
			Platform.runLater(new Runnable() {
				public void run () {
					if (palabraAsignada.equals(letrasIngresadas.toLowerCase())) {
						if (matando == false) {
							Usuario.actualizarPuntaje(200);
							Usuario.poderEspecial = true;
							OrganizadorJuego.activarImagenPoderEspecial(true);
							
							matando = true;
						} else {
							matar();
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
								mover(-0.25,0.0);
							}
						}
					} else {
						mover(-0.25,0.0);
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
	 * M�todo implementado por la interfaz Movimiento.
	 * Se encarga de cambiar las posiciones X y Y del 
	 * objeto para poder moverse a trav�s de la pantalla.
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
	 * M�todo implementado por la interfaz Animaci�n.
	 * Se encarga de realizar una animaci�n que lleva al objeto 
	 * hasta el fondo de la pantalla haci�ndolo desaparecer.
	 * Una vez desaparecido se desecha el hilo que le permit�a
	 * moverse a este objeto y se cambia su estado de vivo a muerto.
	 */
	public void matar() {
		mover(-0.5,5.0);
		if (getImageView().getLayoutY() >= 700.0) {
			this.setHilo(null);
			this.setVivo(false);
		}
	}

	@Override
	/**
	 * M�todo implementado por la interfaz ProcesarLetra.
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
	 * M�todo que coloca una nueva palabra aleatoria al objeto.
	 * Tambi�n se encarga de resetear atributos que son necesarios
	 * en el m�todo procesar.
	 */
	public void colocarPalabra () throws FileNotFoundException, IOException {
		ManejoArchivos lector = new ManejoArchivos("wordsSustentacion.txt");
		this.letrasIngresadas = "";
		this.palabraAsignada = lector.elegirPalabra();
		this.letrasRestantes = this.palabraAsignada;
		super.getLabel().setText(this.palabraAsignada);
	}
	
	/**
	 * M�todo que toma como par�metro un string y devuelve el mismo string 
	 * pero sin su primera letra. Usado en el m�todo procesar.
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
	 * M�todo invocado cuando el jugador usa el poder especial.
	 * Causa la muerte inmediata del objeto.
	 */
	public void muerteInmediata () {
		this.letrasIngresadas = this.palabraAsignada;
	}
	
	/**
	 * M�todo que cambia el estado del atributo matando a un 
	 * valor false. Este atributo es usado como parte del proceso 
	 * de animaci�n de matar del objeto.
	 */
	public void resetMatando () {
		matando = false;
	}
}