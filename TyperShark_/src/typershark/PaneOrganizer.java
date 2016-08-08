/**
 * 
 */

package typershark;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * @author Elizabeth Sánchez, Paul Estrada.
 *
 */
public class PaneOrganizer {
	
	private Pane root;
	String img = "oceano.jpg";
	
	Image imgDiver = new Image("diver.gif");
	Image imgShark = new Image("shark.gif");
	Image imgSharkb = new Image("sharkb.gif");
	
    ImageView diver = new ImageView();
    ImageView shark = new ImageView();
    ImageView sharkb = new ImageView();
    ImageView shark2 = new ImageView();
    ImageView sharkb2 = new ImageView();
    //ImageView sharkb2 = new ImageView();
    
	public PaneOrganizer(){
		root = new Pane();
		
        root.setStyle("-fx-background-image: url('" + img + "'); "
           + "-fx-background-position: center center; "
           + "-fx-background-repeat: stretch;");
        
        diver.setImage(imgDiver);
        shark.setImage(imgShark);
        shark2.setImage(imgShark);
        sharkb2.setImage(imgSharkb);
        sharkb.setImage(imgSharkb);
        
        diver.setFitWidth(250);
        diver.setFitHeight(250);
        diver.setX(0);
        diver.setY(0);
        
        shark.setFitWidth(200);
        shark.setFitHeight(100);
        shark.setX(500);
        shark.setY(25);
        
        sharkb.setFitWidth(200);
        sharkb.setFitHeight(150);
        sharkb.setX(500);
        sharkb.setY(100);
        
        shark2.setFitWidth(200);
        shark2.setFitHeight(100);
        shark2.setX(500);
        shark2.setY(200);
        
        sharkb2.setFitWidth(200);
        sharkb2.setFitHeight(150);
        sharkb2.setX(500);
        sharkb2.setY(270);
        
        root.getChildren().addAll(diver, shark, sharkb, shark2, sharkb2);
	}
	
	
	public Pane getRoot() {
		return root;
	}

}
