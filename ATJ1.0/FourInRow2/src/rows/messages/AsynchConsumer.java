package rows.messages;

import javax.jms.JMSException;
import javax.jms.Message ; 
import javax.jms.MessageListener ; 
import javax.jms.TextMessage ;
import javafx.application.*;
import javafx.scene.paint.Color;
import rows.controller.*;
import rows.view.*; 

public class AsynchConsumer implements MessageListener {
	private PlayerController player ; 
	private RAlerts alert ; 
	private Platform platform; 
	public AsynchConsumer(PlayerController p)
	{
		player = p ; 
	}
	@Override 
	public void onMessage(Message message) {
		if (message instanceof TextMessage)
			try {
				String result = ((TextMessage)message).getText() ;  
				System.out.println("RESULT: " + result) ; 
				//System.out.println(player.getMove()) ; 
				if (player.getMove() == true  ) {
					if (result.contains("victory")) {
						//player.failure();
						platform.runLater(new Runnable() {
							@Override
							public void run() {
								alert = new RAlerts() ; 
								alert.showAlert("kupa");
							}
						});
						
						return; 
					}
					System.out.println("Decoding") ; 
					//System.out.println("RESULT: " + result) ; 
		    		int column = Integer.decode(Character.toString(result.charAt(0))) ; 
		    		int row = Integer.decode(Character.toString(result.charAt(1))) ; 
		    		System.out.println("Column and row: " + Integer.toString(column) 
		    		+Integer.toString(row)) ; 
		    		player.fillSpace(column, row);
		    		player.setMove(false);
		    		
		    		
		    	} 
			
			}
			catch ( Exception e ) {
				e.printStackTrace();
			}
	}

}
