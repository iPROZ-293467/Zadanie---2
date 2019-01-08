package rows.view;

import javafx.application.Application;
import javafx.application.Platform ; 
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import rows.controller.*;

public class Main extends Application {
	
	private static String argument ; 
	 static Platform platform ; 
	@Override
	public void start(Stage primaryStage) {
		
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/rows/view/Rows2.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Four in a Row");
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		public static void main(String[] args) {
			int i = args.length ; 
			argument = args[i-1] ; 
			System.out.println(argument); 
			launch(args);
		}
		
	public static String getArgument() {
		return argument ; 
	}

}