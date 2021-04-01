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

/*
 * Controls Valiruutu.fxml and tells players who's turn is next.
 */
public class ValiruutuController {
	
	private Game game;

    @FXML
    private Button continueButton;
    
    @FXML
    private Label nextTurn;
    
    /*
     * Informs players who's turn is next.
     */
    public void initialize(){
    	GameHolder holder=GameHolder.getInstance(); //Load game data from GameHolder
		game=holder.getGame();
		
    	if(game.player1turn) {
    		nextTurn.setText("Next is " + game.player1 + "'s turn");
    	}
    	else {
    		nextTurn.setText("Next is " + game.player2 + "'s turn");
    	}
    }

    /*
     * Moves to Pelinakyma.
     */
    @FXML
    void Continue(ActionEvent event) {
    	Node node = (Node) event.getSource();
    	Stage stage = (Stage) node.getScene().getWindow();
		Parent root;
		
		try {
			root=FXMLLoader.load(getClass().getResource("Pelinakyma.fxml"));
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