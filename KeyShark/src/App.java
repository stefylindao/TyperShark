/*
* @(#)App.java	0.1		24/08/2016

*
* Copyright (c) 2016.
* Paul Estrada Le�n, Stefany Lindao Rodr�guez, Elizabeth S�nchez Villamar.
* ESPOL. Guayaquil, Ecuador.
* Todos los derechos reservados.
*
*/

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

/**
 * La clase App contiene las distintas escenas de la aplicaci�n
 * TyperShark.
 * 
 * @version: 	0.1		24/08/2016
 * @author: 	Paul Estrada Le�n, Stefany Lindao Rodr�guez, Elizabeth S�nchez Villamar.
 */
public class App extends Application{

	private static Stage mainStage;
	
	private Image icon = new Image(getClass().getResourceAsStream("ic.png"));
	
	//Comentario de Stefany
	@Override
	/**
	 * M�todo que inicia el escenario con el PaneOrganizer del men� del juego.
	 * @param: stage
	 */
	public void start(Stage stage) throws Exception {
		mainStage = stage;
		OrganizadorGuardar.handler = new SceneHandler();
		
		OrganizadorMenu org = new OrganizadorMenu(new SceneHandler());
		Scene escenario = new Scene(org.getRaiz(),500,450);
		stage.getIcons().add(icon);
		stage.setResizable(false);
		stage.setScene(escenario);
		stage.setTitle("KeyShark");
		stage.show();
	}

	public static void main (String[] args) {
		Application.launch(args);
	}
	
	
	private static class SceneHandler implements EventHandler<ActionEvent> {
		@Override
		/**
		 * EventHandler llamado desde distintos botones en todo el programa.
		 * Cuando se lo llama, este se encarga de cambiar el escenario de la ventana 
		 * seg�n la preferencia del usuario mediante el uso de botones.
		 * @param: event
		 */
		public void handle(ActionEvent event) {
			try {
				if (((Button) event.getSource()).getText() == "Jugar") {
					OrganizadorJuego org = new OrganizadorJuego(new SceneHandler());
					Scene escenario = new Scene(org.getRaiz(),1000,700);
					mainStage.setScene(escenario);
				} else if (((Button) event.getSource()).getText() == "Acerca De") {
						OrganizadorAcercaDe org = new OrganizadorAcercaDe(new SceneHandler());
						Scene escenario = new Scene(org.getRaiz(),600,500);
						mainStage.setResizable(false);
						mainStage.setScene(escenario);
				} else if (((Button) event.getSource()).getText() == "Puntajes") {
					OrganizadorPuntaje org = new OrganizadorPuntaje(new SceneHandler());
					Scene escenario = new Scene(org.getRaiz(),600,500);
					mainStage.setResizable(false);
					mainStage.setScene(escenario);
				} else if (((Button) event.getSource()).getText() == "Guardar") {
					ManejoArchivos m = new ManejoArchivos("highScores.txt");
					m.guardarHighScore(HighScore.nuevaListaHighScore(OrganizadorGuardar.nicknameTF.getText(), Usuario.puntaje));
					Sonido.stop();
					OrganizadorMenu org = new OrganizadorMenu(new SceneHandler());
					Scene escenario = new Scene(org.getRaiz(),500,450);
					mainStage.setResizable(false);
					mainStage.setScene(escenario);
				} else if (((Button) event.getSource()).getText() == "Cargar") {
					OrganizadorCargarJuego org = new OrganizadorCargarJuego(new SceneHandler());
					Scene escenario = new Scene(org.getRaiz(),600,500);
					mainStage.setResizable(false);
					mainStage.setScene(escenario);
					
				} else {
					Sonido.stop();
					OrganizadorMenu org = new OrganizadorMenu(new SceneHandler());
					Scene escenario = new Scene(org.getRaiz(),500,450);
					mainStage.setResizable(false);
					mainStage.setScene(escenario);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
