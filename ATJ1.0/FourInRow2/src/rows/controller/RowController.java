package rows.controller;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent ; 
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle ;
import rows.model.RowModel ; 
import rows.view.*;



import rows.messages.*;


public class RowController {


	    @FXML private GridPane grid;
	    @FXML private Circle circle01;
	    @FXML private Circle circle11;
	    @FXML private Circle circle02;
	    @FXML private Circle circle12;
	    @FXML private Circle circle21;
	    @FXML private Circle circle22;

	    @FXML
	    private Circle circle03;

	    @FXML
	    private Circle circle13;

	    @FXML
	    private Circle circle23;

	    @FXML
	    private Circle circle04;

	    @FXML
	    private Circle circle14;

	    @FXML
	    private Circle circle24;

	    @FXML
	    private Circle circle05;

	    @FXML
	    private Circle circle15;

	    @FXML
	    private Circle circle25;

	    @FXML
	    private Circle circle06;

	    @FXML
	    private Circle circle16;

	    @FXML
	    private Circle circle26;

	    @FXML
	    private Circle circle31;

	    @FXML
	    private Circle circle41;

	    @FXML
	    private Circle circle51;

	    @FXML
	    private Circle circle61;

	    @FXML
	    private Circle circle32;

	    @FXML
	    private Circle circle42;

	    @FXML
	    private Circle circle52;

	    @FXML
	    private Circle circle62;

	    @FXML
	    private Circle circle33;

	    @FXML
	    private Circle circle43;

	    @FXML
	    private Circle circle53;

	    @FXML
	    private Circle circle63;

	    @FXML
	    private Circle circle34;

	    @FXML
	    private Circle circle44;

	    @FXML
	    private Circle circle54;

	    @FXML
	    private Circle circle64;

	    @FXML
	    private Circle circle35;

	    @FXML
	    private Circle circle45;

	    @FXML
	    private Circle circle55;

	    @FXML
	    private Circle circle65;

	    @FXML
	    private Circle circle36;

	    @FXML
	    private Circle circle46;

	    @FXML
	    private Circle circle56;

	    @FXML
	    private Circle circle66;

	    @FXML
	    private Circle choiceCircle0;

	    @FXML
	    private Circle choiceCircle1;

	    @FXML
	    private Circle choiceCircle2;

	    @FXML
	    private Circle choiceCircle3;

	    @FXML
	    private Circle choiceCircle4;

	    @FXML
	    private Circle choiceCircle5;

	    @FXML
	    private Circle choiceCircle6;

	    int [] takenSpaces = {0,0,0,0,0,0,0} ;  
	    private Producer producer ; 
	    private Consumer consumer ; 
	    private PlayerController me, opponent ; 
	    private String myQueueName , opQueueName ;
	    Circle [] spaces ; 
	    
	    
	    public void initialise() {
	    	String arg = Main.getArgument() ; 
	    	Circle [] circles = {circle01,circle02,circle03,circle04,circle05,circle06,
	  	    		circle11,circle12,circle13,circle14,circle15,circle16,
	  	    		circle21, circle22, circle23, circle24, circle25, circle26, 
	  	    		circle31 , circle32, circle33, circle34, circle35, circle36, 
	  	    		circle41, circle42, circle43, circle44, circle45, circle46, 
	  	    		circle51, circle52, circle53, circle54, circle55, circle56, 
	  	    		circle61, circle62, circle63, circle64, circle65, circle66} ; 
	    	spaces = circles;  
	    	if (arg.contains("BLUEVIOLET")  ) {
	    		me = new PlayerController(Color.BLUEVIOLET , spaces, takenSpaces) ;
	    		opponent = new PlayerController(Color.SKYBLUE, spaces, takenSpaces );
	    		me.setMove(true); 
	    		opponent.setMove(false);
	    	
	    	
	    		myQueueName = "ATJQueue" ; 
	    		opQueueName = "ATJ2Queue" ; 
	    	}
	    	else {
	    		me = new PlayerController ( Color.SKYBLUE , spaces, takenSpaces);
	    		opponent = new PlayerController (Color.BLUEVIOLET, spaces, takenSpaces) ;
	    		System.out.println("Initialising");
	    		me.setMove(false);
	    		opponent.setMove(true);
	   
	    		myQueueName = "ATJ2Queue" ; 
	    		opQueueName = "ATJQueue" ; 
	    	}
	    	try {
	    		producer = new Producer("localhost:4848/jms",myQueueName)  ;
	    		consumer = new Consumer ("localhost:4848/jms", opQueueName) ;  
	    		consumer.browseQueue();
	    		System.out.println("Setting listener") ; 
	    		consumer.setMessageListener(new AsynchConsumer(opponent));
	    	
	    	} catch (javax.jms.JMSException e) {
	    		e.printStackTrace();
	    	}
	    	
	    }
	    
	   
	     public void choiceClick(MouseEvent event) {

    		try {
    			move(event) ; 
    		}
    		catch (Exception e)
    		{
    			System.out.println("Na-ah") ; 
    			e.printStackTrace();
    			return ; 
    		} 

	    	 
    	}

	     public void move(MouseEvent event) throws Exception
	     {
	    	
	    	 if (opponent == null ) {
	    		 initialise() ; 
	    	 		System.out.println(opponent.getMove()) ; 
	    	 		return ; 
	    	 }
	    	 
	    	 if (opponent.getMove() == false) {
		    	 double x = event.getSceneX() ;
		    	 int column = RowModel.findColumn(x) ; 
		    	 int row = RowModel.findRow(column, takenSpaces[column]) ; 
		    	 me.fillSpace(column, row);
		    	 if ( RowModel.victory(column, row, spaces)) {
			    	 System.out.println("Sending");
		    		 producer.sendQueueMessage("victory");
		    		 RAlerts alert = new RAlerts() ; 
		    		 alert.showAlert("victory");
		    		 return ; 
		    	 }
		    	 System.out.println("Sending");
		    	 producer.sendQueueMessage(Integer.toString(column) + Integer.toString(row));
		    	 me.setMove(false); 
		    	 opponent.setMove(true);
	    	}
	    	
	    	
	    	 
	    	 

	     }
	     
}