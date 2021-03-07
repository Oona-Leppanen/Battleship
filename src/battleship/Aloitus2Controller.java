package battleship;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

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

    }
}
