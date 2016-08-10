/**
 * 
 */

package typershark;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * @author Elizabeth Sánchez, Paul Estrada, Stefany Lindao.
 *
 */
public class PaneOrganizer {
	
	private Pane root;
	private String img = "oceano.jpg";
	
	private Image imgDiver = new Image("diver.gif");
	private Image imgShark = new Image("shark.gif");
	private Image imgSharkb = new Image("sharkb.gif");
	private Image imgPirana = new Image("pez.gif");
	
	private ImageView diver = new ImageView();
	private ImageView shark = new ImageView();
	private ImageView sharkb = new ImageView();
	private ImageView shark2 = new ImageView();
	private ImageView sharkb2 = new ImageView();
	private ImageView pirana = new ImageView();
    
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
        pirana.setImage(imgPirana);
        
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
        
        pirana.setFitWidth(75);
        pirana.setFitHeight(50);
        pirana.setX(500);
        pirana.setY(225);
        
        shark2.setFitWidth(200);
        shark2.setFitHeight(100);
        shark2.setX(500);
        shark2.setY(270);
        
        sharkb2.setFitWidth(200);
        sharkb2.setFitHeight(150);
        sharkb2.setX(500);
        sharkb2.setY(340);
        
        root.getChildren().addAll(diver, shark, sharkb, pirana);
	}
	
	
	public Pane getRoot() {
		return root;
	}

}
