package rows.controller;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent ; 
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle ;
import rows.model.RowModel ; 
import rows.view.*;
import com.sun.messaging.jms.JMSException;
import javax.jms.Queue ;


public class PlayerController {

	private boolean myMove ; 
	private Color color ; 
	private Circle [] circles ; 
	private int [] taken ; 
	private RAlerts alert ; 
	
	public PlayerController(Color c, Circle [] s, int [] t) {
		color = c ; 
		circles = s ; 
		taken = t ; 
		alert = new RAlerts() ;
	}
	public void fillSpace (int column, int row) {

		circles[6*column + row - 1].setFill(color);
		taken[column]++ ; 
		System.out.println("FILLING hey " + color.toString()) ; 
		
	}
	public void setMove(boolean b) {
		myMove = b ; 
	}
	public boolean getMove() {
		return myMove ; 
	}
	public void failure() {
		alert.showAlert("failure");
	}
}
