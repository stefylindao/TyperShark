/*
* @(#)Sonido.java	0.1		24/08/2016

*
* Copyright (c) 2016.
* Paul Estrada León, Stefany Lindao Rodríguez, Elizabeth Sánchez Villamar.
* ESPOL. Guayaquil, Ecuador.
* Todos los derechos reservados.
*
*/

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

/**
 * La clase Sonido reproduce un archivo de audio durante la ejecución de la
 * aplicación.
 * 
 * @version: 	0.1		24/08/2016
 * @author: 	Paul Estrada León, Stefany Lindao Rodríguez, Elizabeth Sánchez Villamar.
 */
public class Sonido {
	private static Media sound = new Media(new File("SMBWaterWorld.mp3").toURI().toString());
	private static MediaPlayer player = new MediaPlayer(sound);
	
	/**
	 * Método que permite que se ejecute el archivo de audio.
	 */
	public static void play() {
		player.play();
		player.getOnPlaying();
	}

	/**
	 * Método que permite el detenimiento de la ejecución 
	 * del archivo de audio.
	 */
	public static void stop() {
		player.stop();
	}
}
