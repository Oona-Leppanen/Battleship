package battleship;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class Aloitus1Controller {

    @FXML
    private ComboBox<Integer> widthChooser;

    @FXML
    private ComboBox<Integer> heightChooser;

    @FXML
    private TextField p1Input;

    @FXML
    private TextField p2Input;

    @FXML
    private Button nextButton;
    
    public void initialize() {
    	for(int i=5; i<11;i++) {
    		widthChooser.getItems().add(i);
    		heightChooser.getItems().add(i);

    	}
    }

    @FXML
    void ButtonPressed(ActionEvent event) {
    	try {
        	System.out.println("Onneksi olkoon");

    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    }
}