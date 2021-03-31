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

public class LoppuController {

	private Game game;
	
    @FXML
    private Button quitButton;

    @FXML
    private Button newButton;

    @FXML
    private Label winnerLabel;
    
    public void initialize(){
    	GameHolder holder=GameHolder.getInstance();
		game=holder.getGame();
		
    	if(game.player1turn) {
    		winnerLabel.setText(game.player1);
    	}
    	else {
    		winnerLabel.setText(game.player2);
    	}
    }

    @FXML
    void NewGame(ActionEvent event) {
    	Node node = (Node) event.getSource(); // Tallennetaan nappi muuttujaan node
    	Stage stage = (Stage) node.getScene().getWindow(); // Haetaan napin scene ja Scenen ikkuna eli Stage-> tallennetaan stage
		Parent root;
		
		try {
			root=FXMLLoader.load(getClass().getResource("Laivanupotus_aloitusnaytto_1.fxml"));
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