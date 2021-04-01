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
 * Controls Loppu.fxml and enables quiting the game and starting a new game.
 */
public class LoppuController {

	private Game game;
	
    @FXML
    private Button quitButton;

    @FXML
    private Button newButton;

    @FXML
    private Label winnerLabel;
    
    /*
     * Informs players who won.
     */
    public void initialize(){
    	GameHolder holder=GameHolder.getInstance(); //Load game data from GameHolder
		game=holder.getGame();
		
    	if(game.player1turn) {
    		winnerLabel.setText(game.player1);
    	}
    	else {
    		winnerLabel.setText(game.player2);
    	}
    }

    /*
     * Starts a new game and loads Laivanupotus_aloitusnaytto_1.fxml.
     */
    @FXML
    void NewGame(ActionEvent event) {
    	Node node = (Node) event.getSource();
    	Stage stage = (Stage) node.getScene().getWindow();
		Parent root;
		
		try {
			root=FXMLLoader.load(getClass().getResource("Laivanupotus_aloitusnaytto_1.fxml"));
		}
		catch(Exception e) {
			e.printStackTrace();
			return;
		}
		Scene scene= new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

    /*
     * Quits the game and closes the game window.
     */
    @FXML
    void Quit(ActionEvent event) {
    	Node node = (Node) event.getSource();
    	Stage stage = (Stage) node.getScene().getWindow();
    	stage.close();
    }

}