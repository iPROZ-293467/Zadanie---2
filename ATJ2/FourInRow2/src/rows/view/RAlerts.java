package rows.view;

import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;

public class RAlerts {
	
	private Alert alert ; 
	
	public RAlerts() {
		alert = new Alert(AlertType.INFORMATION) ; 
	}
	
	public void showAlert(String result)
	{
		alert.setTitle("END OF GAME");
		if ( result.contains("victory") ) {
			alert.setContentText("Congratulations, you won!"); 
		} else alert.setContentText("We're sorry, you lost.");
		alert.showAndWait() ; 
	}
	

}
