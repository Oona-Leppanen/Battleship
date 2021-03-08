package battleship;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ValiruutuController {

    @FXML
    private Button continueButton;

    @FXML
    void Continue(ActionEvent event) {
    	Node node = (Node) event.getSource(); //Tallennetaan nappi muuttujaan node
    	Stage stage = (Stage) node.getScene().getWindow(); //Haetaan napin scen ja Scenen ikkuna eli Stage -> tallennetaan stage
    	stage.close();
    }

}