/*
* @(#)ControlHilos.java	0.1		24/08/2016

*
* Copyright (c) 2016.
* Paul Estrada Le�n, Stefany Lindao Rodr�guez, Elizabeth S�nchez Villamar.
* ESPOL. Guayaquil, Ecuador.
* Todos los derechos reservados.
*
*/

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Random;
import javafx.application.Platform;

/**
 * La clase ControlHilos maneja los hilos integrados al buceador
 * y los animales marinos.
 * 
 * @version: 	0.1		24/08/2016
 * @author: 	Paul Estrada Le�n, Stefany Lindao Rodr�guez, Elizabeth S�nchez Villamar.
 */
public class ControlHilos implements Runnable {

	private static boolean jugando;
	private Random random = new Random();
	
	private Buceador buzo;
	public static Thread hiloBuzo;
	public static Thread hilo1;
	public static Thread hilo2;
	public static Thread hilo3;
	public static Thread hilo4;
	public static Thread hilo5;
	private Tiburon[] tiburones;
	private Pirana[] piranas;
	private Pulpo[] pulpos;
	private TiburonNegro[] tiburonesNegros;
	
	/**
	 * M�todo que toma como par�metros los objetos que se env�an desde la clase OrganizadorJuego.
	 * Los guarda en sus respectivos Arrays e inicializa los 5 hilos que mover�n aleatoriamente a 
	 * estos objetos.
	 * @param: buzo
	 * @param: tiburones
	 * @param: piranas
	 * @param: tiburonesNegros
	 */
	public ControlHilos(Buceador buzo, Tiburon[] tiburones, Pirana[] piranas, TiburonNegro[] tiburonesNegros, Pulpo[] pulpos) {
		jugando = true;
		
		this.setBuzo(buzo);
		this.tiburones = tiburones;
		this.piranas = piranas;
		this.tiburonesNegros = tiburonesNegros;
		this.pulpos = pulpos;
		
		hiloBuzo = new Thread(buzo);
		hiloBuzo.start();
		
		hilo1 = new Thread();
		hilo2 = new Thread();
		hilo3 = new Thread();
		hilo4 = new Thread();
		hilo5 = new Thread();
	}
	
	/**
	 * M�todo llamado por el m�todo run, el cual, hace que revise qu� hilo est� desocupado 
	 * y listo para usar. Una vez elegido un hilo desocupado, se elige aleatoriamente un objeto 
	 * para que dicho hilo se encargue de darle vida.
	 */
	private void elegirHilo () throws FileNotFoundException, IOException {
		Hashtable<String, Integer> dic = this.posicionAleatoria();
		this.restaurarHilo();
		
		if (hilo1 != null && hilo1.isAlive() == false) {
			Objeto objeto = this.elegirObjeto(dic.get("x"),dic.get("y"));
			hilo1 = new Thread(objeto);
			objeto.setHilo(hilo1);
			hilo1.start();
		} else if (hilo2 != null && hilo2.isAlive() == false) {
			Objeto objeto = this.elegirObjeto(dic.get("x"),dic.get("y"));
			hilo2 = new Thread(objeto);
			objeto.setHilo(hilo2);
			hilo2.start();
		} else if (hilo3 != null && hilo3.isAlive() == false) {
			Objeto objeto = this.elegirObjeto(dic.get("x"),dic.get("y"));
			hilo3 = new Thread(objeto);
			objeto.setHilo(hilo3);
			hilo3.start();
		} else if (hilo4 != null && hilo4.isAlive() == false) {
			Objeto objeto = this.elegirObjeto(dic.get("x"),dic.get("y"));
			hilo4 = new Thread(objeto);
			objeto.setHilo(hilo4);
			hilo4.start();
		} else if (hilo5 != null && hilo5.isAlive() == false) {
			Objeto objeto = this.elegirObjeto(dic.get("x"),dic.get("y"));
			hilo5 = new Thread(objeto);
			objeto.setHilo(hilo5);
			hilo5.start();
		} 
	}
	
	/**
	 * M�todo llamado por el m�todo elegirHilo. Se encarga de borrar la informaci�n 
	 * previa del hilo que reci�n ha sido desocupado.
	 */
	private void restaurarHilo () {
		if (hilo1.isAlive() == false) {
			hilo1 = new Thread();
		} else if (hilo2.isAlive() == false) {
			hilo2 = new Thread();
		} else if (hilo3.isAlive() == false) {
			hilo3 = new Thread();
		} else if (hilo4.isAlive() == false) {
			hilo4 = new Thread();
		} else if (hilo5.isAlive() == false) {
			hilo5 = new Thread();
		} 
	}
	
	/**
	 * M�todo que se encarga de elegir mediante aleatorios un tipo de animal. Una vez elegido 
	 * el tipo de animal, se escoge al primer animal desocupado de su respectivo array.
	 * @param: x
	 * @param: y
	 * @return: tiburones
	 * @return: piranas
	 * @return: tiburonesNegros
	 */
	private Objeto elegirObjeto(int x, int y) throws FileNotFoundException, IOException {
		while (true) {
			int index = random.nextInt(this.tiburones.length + this.piranas.length + this.tiburonesNegros.length + this.pulpos.length);
			if (index == 0) {
				for (int i=0;i<this.tiburones.length;i++) {
					if (this.tiburones[i].isVivo() == false) {
						this.tiburones[i].getImageView().setLayoutX(x);
						this.tiburones[i].getImageView().setLayoutY(y);
						this.tiburones[i].getLabel().setLayoutX(x + 100);
						this.tiburones[i].getLabel().setLayoutY(y + 70);
						this.tiburones[i].getLabel().requestFocus();
						this.tiburones[i].colocarPalabra();
						this.tiburones[i].resetMatando();
						return this.tiburones[i];
					}
				}
			} else if (index == 1) {
				for (int i=0;i<this.piranas.length;i++) {
					if (this.piranas[i].isVivo() == false) {
						this.piranas[i].getImageView().setLayoutX(x);
						this.piranas[i].getImageView().setLayoutY(y);
						this.piranas[i].getLabel().setLayoutX(x + 40);
						this.piranas[i].getLabel().setLayoutY(y + 20);
						this.piranas[i].getLabel().requestFocus();
						this.piranas[i].colocarPalabra();
						this.piranas[i].resetMatando();
						return this.piranas[i];
					}
				}
				
			} else if (index == 2) {
				for (int i=0;i<this.pulpos.length;i++) {
					if (this.pulpos[i].isVivo() == false) {
						this.pulpos[i].getImageView().setLayoutX(x);
						this.pulpos[i].getImageView().setLayoutY(y);
						this.pulpos[i].getLabel().setLayoutX(x + 50);
						this.pulpos[i].getLabel().setLayoutY(y + 30);
						this.pulpos[i].getLabel().requestFocus();
						this.pulpos[i].colocarPalabra();
						this.pulpos[i].resetMatando();
						return this.pulpos[i];
					}
				}
			} else {
				for (int i=0;i<this.tiburonesNegros.length;i++) {
					if (this.tiburonesNegros[i].isVivo() == false) {
						this.tiburonesNegros[i].getImageView().setLayoutX(x);
						this.tiburonesNegros[i].getImageView().setLayoutY(y);
						this.tiburonesNegros[i].getLabel().setLayoutX(x + 100);
						this.tiburonesNegros[i].getLabel().setLayoutY(y + 140);
						this.tiburonesNegros[i].getLabel().requestFocus();
						this.tiburonesNegros[i].colocarPalabra();
						this.tiburonesNegros[i].resetMatando();
						return this.tiburonesNegros[i];
					}
				}
			}
		}
	}

	/**
	 * M�todo que genera un Hashtable<String,Integer> con dos posiciones aleatorias: X y Y
	 * @return: dic
	 */
	private Hashtable<String,Integer> posicionAleatoria() {
		int posicionY = (random.nextInt(400) + 50);
		int posicionX = 1000;
		Hashtable<String, Integer> dic = new Hashtable<String, Integer>();
		dic.put("x", posicionX);
		dic.put("y", posicionY);
		return dic;
	}
	
	
	@Override
	/**
	 * M�todo implementado por la interfaz Runnable.
	 * Se encarga de llamar repetidas veces al m�todo elegirHilo para que siempre hayan hilos 
	 * activos en el juego.
	 */
	public void run() {
		while (jugando) {
			Platform.runLater(new Runnable() {
				public void run () {
					try {
						elegirHilo();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			
			try {
				Thread.sleep((random.nextInt(3) + 1) * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
		
	/**
	 * M�todo est�tico que finaliza el juego. Hace que se detengan todos los objetos en la ventana.
	 */
	public static void detenerJuego () {
		jugando = false;
		
		OrganizadorJuego.buzo.setVivo(false);
		
		OrganizadorJuego.tiburon1.setVivo(false);
		OrganizadorJuego.tiburon2.setVivo(false);
		OrganizadorJuego.tiburon3.setVivo(false);
		
		OrganizadorJuego.pirana1.setVivo(false);
		OrganizadorJuego.pirana2.setVivo(false);
		OrganizadorJuego.pirana3.setVivo(false);
		OrganizadorJuego.pirana4.setVivo(false);
		
		OrganizadorJuego.tiburonNegro1.setVivo(false);
		OrganizadorJuego.tiburonNegro2.setVivo(false);
		
		OrganizadorJuego.pulpo1.setVivo(false);
		OrganizadorJuego.pulpo2.setVivo(false);
		
	}

	/**
	 * M�todo que permite obtener el buceador.
	 * @return: buzo
	 */
	public Buceador getBuzo() {
		return buzo;
	}

	/**
	 * M�todo que da acceso al buceador para su
	 * posterior modificaci�n.
	 * @param: buzo
	 */
	public void setBuzo(Buceador buzo) {
		this.buzo = buzo;
	}
}
