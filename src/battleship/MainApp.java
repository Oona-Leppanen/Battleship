package battleship;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
	
	@Override
	public void start(Stage stage) throws Exception {
		Parent root;
		
		try {
			root=FXMLLoader.load(getClass().getResource("Laivanupotus_aloitusnaytto_1.fxml"));
		}
		catch(Exception e) {
			e.printStackTrace();
			return;
		}
		stage.setResizable(false);
		Scene scene= new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Battleship");
		stage.show();
	}

}
