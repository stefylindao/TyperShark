/*
* @(#)OrganizadorMenu.java	0.1		24/08/2016

*
* Copyright (c) 2016.
* Paul Estrada Le�n, Stefany Lindao Rodr�guez, Elizabeth S�nchez Villamar.
* ESPOL. Guayaquil, Ecuador.
* Todos los derechos reservados.
*
*/

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

/**
 * La clase OrganizadorMenu permite modelar la escena
 * que presenta la ventana principal de la aplicaci�n.
 * 
 * Este es el primer PaneOrganizer que se presenta en la ventana 
 * Muestra un men� interactivo que se comunica con la clase App para 
 * alterar el escenario de la ventana y as� mostrar los dem�s escenarios 
 * que contiene la aplicaci�n.
 * 
 * @version: 	0.1		24/08/2016
 * @author: 	Paul Estrada Le�n, Stefany Lindao Rodr�guez, Elizabeth S�nchez Villamar.
 */
public class OrganizadorMenu {
	
	private BorderPane raiz;
	private HBox contenedorBotones;
	private Button botonJugar;
	private Button botonCargar;
	private Button botonAcercaDe;
	private Button botonPuntaje;
	private Button botonSalir;
	private ImageView logo;
	private String img = "fondoshark.png";
	
	/**
	 * M�todo que construye un PaneOrganizer.
	 * Muestra botones para que el usuario escoja la opci�n a realizar
	 * con la aplicaci�n.
	 * @param: handler
	 */
	public OrganizadorMenu (EventHandler<ActionEvent> handler) {
		raiz = new BorderPane();
		contenedorBotones = new HBox();
		
		logo = new ImageView();
		logo.setImage(new Image("logo.png"));
		logo.setFitHeight(400);
		logo.setFitWidth(500);
		
		raiz.setCenter(logo);
		
		botonJugar = new Button("Jugar");
		botonJugar.setFont(new Font("Comic Sans MS", 15.0));
		botonJugar.setTextFill(Color.WHITE);
		botonJugar.setOnAction(handler);
		botonJugar.setBackground(null);
		
		botonCargar = new Button("Cargar");
		botonCargar.setFont(new Font("Comic Sans MS", 15.0));
		botonCargar.setTextFill(Color.WHITE);
		botonCargar.setOnAction(handler);
		botonCargar.setBackground(null);
		
		botonAcercaDe = new Button("Acerca De");
		botonAcercaDe.setFont(new Font("Comic Sans MS", 15.0));
		botonAcercaDe.setTextFill(Color.WHITE);
		botonAcercaDe.setOnAction(handler);
		botonAcercaDe.setBackground(null);
		
		botonPuntaje = new Button("Puntajes");
		botonPuntaje.setFont(new Font("Comic Sans MS", 15.0));
		botonPuntaje.setTextFill(Color.WHITE);
		botonPuntaje.setOnAction(handler);
		botonPuntaje.setBackground(null);
		
		botonSalir = new Button("Salir");
		botonSalir.setFont(new Font("Comic Sans MS", 15.0));
		botonSalir.setTextFill(Color.WHITE);
		botonSalir.setBackground(null);
		botonSalir.setOnAction(e -> {
			System.exit(0);
		});
		
		raiz.setStyle("-fx-background-image: url('" + img + "'); "
		           + "-fx-background-position: center center; "
		           + "-fx-background-repeat: stretch;");
		
		contenedorBotones.getChildren().addAll(botonJugar, botonCargar, botonAcercaDe, botonPuntaje, botonSalir);
		contenedorBotones.setAlignment(Pos.CENTER);
		contenedorBotones.setPadding(new Insets(10, 10, 10, 10));
		contenedorBotones.setSpacing(10);
		raiz.setBottom(contenedorBotones);
	}
	
	/**
	 * M�todo que retorna un Pane como nodo principal.
	 * @return: raiz
	 */
	public Pane getRaiz () {
		return this.raiz;
	}
}
