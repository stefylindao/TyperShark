/**
 * 
 */

package typershark;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * @author Elizabeth Sánchez, Paul Estrada, Stefany Lindao.
 *
 */
public class PaneOrganizerInicio {
	
	private VBox root;
	private Button jugar;
	private Button estadistica;
	private Button salir;
	
	private String img = "fondoshark.jpg";
    
	public PaneOrganizerInicio(){
		root = new VBox();
		
		jugar = new Button("Jugar");
		jugar.setStyle("-fx-font: 14 centaur; -fx-base: #000000;");
		jugar.setMaxSize(80, 40);
		
		estadistica = new Button("Estadísticas");
		estadistica.setStyle("-fx-font: 14 centaur; -fx-base: #000000;");
		estadistica.setMaxSize(80, 40);
		
		salir = new Button("Salir");
		salir.setStyle("-fx-font: 14 centaur; -fx-base: #000000;");
		salir.setMaxSize(80, 40);
		salir.setOnAction(new ClickHandlerQuit());
		
        root.setStyle("-fx-background-image: url('" + img + "'); "
           + "-fx-background-position: center center; "
           + "-fx-background-repeat: stretch;");
        
        root.getChildren().addAll(jugar, estadistica, salir);
        root.setAlignment(Pos.CENTER_RIGHT);
        root.setSpacing(10);
	}
	
	public Pane getRoot() {
		return root;
	}
	
	private class ClickHandlerQuit implements EventHandler<ActionEvent> {

        @Override
        //Metodo que permite terminar la ejecución de la aplicacion.
        public void handle(ActionEvent event) {
            System.exit(0);
        }  
    }

}
