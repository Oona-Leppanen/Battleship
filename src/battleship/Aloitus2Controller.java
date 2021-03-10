package battleship;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class Aloitus2Controller {

    @FXML
    private ComboBox<Integer> destroyerAmount;

    @FXML
    private ComboBox<Integer> submarineAmount;

    @FXML
    private ComboBox<Integer> cruiserAmount;

    @FXML
    private ComboBox<Integer> battleshipAmount;

    @FXML
    private ComboBox<Integer> carrierAmount;

    @FXML
    private Button confirmButton;

    public void initialize() {
    	for(int i=0; i<6; i++) {
        	destroyerAmount.getItems().add(i);
    	}
    	for(int i=0; i<5; i++) {
        	submarineAmount.getItems().add(i);
    	}
    	for(int i=0; i<4; i++) {
        	cruiserAmount.getItems().add(i);
    	}
    	for(int i=0; i<3; i++) {
        	battleshipAmount.getItems().add(i);
    	}
    	for(int i=0; i<2; i++) {
        	carrierAmount.getItems().add(i);
    	}

    }
    
    @FXML
    void confirm(ActionEvent event) {
    	Node node = (Node) event.getSource(); // Tallennetaan nappi muuttujaan node
    	Stage stage = (Stage) node.getScene().getWindow(); // Haetaan napin scene ja Scenen ikkuna eli Stage-> tallennetaan stage
		Parent root;
		
		try {
			root=FXMLLoader.load(getClass().getResource("Aluksien sijainnit.fxml"));
		}
		catch(Exception e) {
			e.printStackTrace();
			return;
		}
		Scene scene= new Scene(root);
		stage.setScene(scene); // asetetaan uusi Scene
		stage.show(); // näytetään uusi scene
	}
}
