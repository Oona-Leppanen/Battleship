package battleship;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    	nextButton.setDisable(true);
    	for(int i=5; i<11;i++) {
    		widthChooser.getItems().add(i);
    		heightChooser.getItems().add(i);
    	}
    }
    
    /*
     * Check that game board size has been set. If true, enable next button
     */
    @FXML
    void checkValue(ActionEvent event) {
    	if(widthChooser.getValue()!=null && heightChooser.getValue()!=null) {
    		nextButton.setDisable(false);
    	}
    }
    
    /*
     * Game class initialization, saving game to GameHolder class and next GUI screen transition
     */
    @FXML
    void ButtonPressed(ActionEvent event) {
    	Game game=new Game(p1Input.getText(), p2Input.getText(), widthChooser.getValue(), heightChooser.getValue());
    	Node node = (Node) event.getSource();
    	Stage stage = (Stage) node.getScene().getWindow();
		Parent root;
	    GameHolder holder = GameHolder.getInstance();
	    holder.setGame(game);
		
		try {
			root=FXMLLoader.load(getClass().getResource("Laivanupotus_aloitusnaytto_2.fxml"));
		}
		catch(Exception e) {
			e.printStackTrace();
			return;
		}
		Scene scene= new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}