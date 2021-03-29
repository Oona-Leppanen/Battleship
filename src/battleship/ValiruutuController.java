package battleship;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ValiruutuController {
	
	private Game game;

    @FXML
    private Button continueButton;
    
    @FXML
    private Label nextTurn;
    
    public void initialize(){
    	GameHolder holder=GameHolder.getInstance();
		game=holder.getGame();
		
    	if(game.player1turn) {
    		nextTurn.setText("Next is " + game.player1 + "'s turn");
    	}
    	else {
    		nextTurn.setText("Next is " + game.player2 + "'s turn");
    	}
    }

    @FXML
    void Continue(ActionEvent event) {
    	Node node = (Node) event.getSource(); // Tallennetaan nappi muuttujaan node
    	Stage stage = (Stage) node.getScene().getWindow(); // Haetaan napin scene ja Scenen ikkuna eli Stage-> tallennetaan stage
		Parent root;
		
		try {
			root=FXMLLoader.load(getClass().getResource("Pelinakyma.fxml"));
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