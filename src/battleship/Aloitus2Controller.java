package battleship;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/*
 * Controls Laivanupotus_aloitusnaytto_2 and sets relevant data for the game object.
 */
public class Aloitus2Controller {

	private Game game;
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
	private Label alertLabel;

	@FXML
	private Button backButton;

	@FXML
	private Button confirmButton;

	public void initialize() {
		GameHolder holder=GameHolder.getInstance(); //Load game data from GameHolder
		game= holder.getGame();
		confirmButton.setDisable(true);
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
	
    /*
     * Check that each ship combo box has a chosen value.
     * Inform player whether their current selections comply with battleship game rules or not.
     * If both checks are met, enable the continue button.
     */
	@FXML
	void checkValue(ActionEvent event) {
		alertLabel.setText("Choose a number for each ship");

		if(destroyerAmount.getValue()!=null && submarineAmount.getValue()!=null && cruiserAmount.getValue()!=null && battleshipAmount.getValue()!=null && carrierAmount.getValue()!=null) {
			int area= game.playerBoard1.boardSizeX*game.playerBoard1.boardSizeY;
			int ships= destroyerAmount.getValue()*2+submarineAmount.getValue()*3+cruiserAmount.getValue()*3+battleshipAmount.getValue()*4+carrierAmount.getValue()*5;
			if(area>2*ships && (destroyerAmount.getValue()>=1 || submarineAmount.getValue()>=1 || cruiserAmount.getValue()>=1 || battleshipAmount.getValue()>=1 || carrierAmount.getValue()>=1)) {
				confirmButton.setDisable(false);
				alertLabel.setText("");
			}

			else if(area>2*ships && destroyerAmount.getValue()==0 && submarineAmount.getValue()==0 && cruiserAmount.getValue()==0 && battleshipAmount.getValue()==0 && carrierAmount.getValue()==0) {
				confirmButton.setDisable(true);
				alertLabel.setText("Choose at least one ship");
			}

			else if(area<=2*ships){
				confirmButton.setDisable(true);
				alertLabel.setText("Too many ships");
			}
		}
	}

	/*
	 * Return to Laivanupotus_aloitusnaytto_1.
	 */
	@FXML
	void Back(ActionEvent event) {
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
	 * Continue to ship placement and save ship amounts to game-object.
	 */
	@FXML
	void Confirm(ActionEvent event) {
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		Parent root;
		game.setShips(destroyerAmount.getValue(), submarineAmount.getValue(), cruiserAmount.getValue(), battleshipAmount.getValue(), carrierAmount.getValue());
		try {
			root=FXMLLoader.load(getClass().getResource("Aluksien sijainnit.fxml"));
		}
		catch(Exception e) {
			e.printStackTrace();
			return;
		}
		Scene scene= new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public void setGame(Game game) {
		this.game=game;
	}
}
