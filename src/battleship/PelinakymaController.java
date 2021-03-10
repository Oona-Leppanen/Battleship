package battleship;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PelinakymaController {

    @FXML
    private Button quitButton;

    @FXML
    private Button continueButton;

    @FXML
    void Continue(ActionEvent event) {
    	Node node = (Node) event.getSource(); // Tallennetaan nappi muuttujaan node
    	Stage stage = (Stage) node.getScene().getWindow(); // Haetaan napin scene ja Scenen ikkuna eli Stage-> tallennetaan stage
		Parent root;
		
		try {
			root=FXMLLoader.load(getClass().getResource("Valiruutu.fxml"));
		}
		catch(Exception e) {
			e.printStackTrace();
			return;
		}
		Scene scene= new Scene(root);
		stage.setScene(scene); // asetetaan uusi Scene
		stage.show(); // näytetään uusi scene
	}

    @FXML
    void Quit(ActionEvent event) {
    	Node node = (Node) event.getSource(); //Tallennetaan nappi muuttujaan node
    	Stage stage = (Stage) node.getScene().getWindow(); //Haetaan napin scen ja Scenen ikkuna eli Stage -> tallennetaan stage
    	stage.close();
    }

}
