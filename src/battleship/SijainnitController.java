package battleship;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class SijainnitController {

	private Game game;

	@FXML
	private Pane pane;

	@FXML
	private Button resetButton;

	@FXML
	private Button continueButton;

	public void initialize(){
		GameHolder holder=GameHolder.getInstance();
		game=holder.getGame();
		GridPane gp=new GridPane();
		for(int i=0; i<game.sizeX;i++) {
			for(int j=0;j<game.sizeY;j++) {
				Rectangle r= new Rectangle(340/game.sizeX,340/game.sizeY,Color.WHITE);
				r.setStroke(Color.BLACK);
				r.setOpacity(0.5);
				GridPane.setConstraints(r, i, j); // column=0 row=0
				gp.getChildren().add(r);
			}
		}
		pane.getChildren().add(gp);
	}

	@FXML
	void Continue(ActionEvent event) {
		Node node = (Node) event.getSource(); // Tallennetaan nappi muuttujaan node
		Stage stage = (Stage) node.getScene().getWindow(); // Haetaan napin scene ja Scenen ikkuna eli Stage-> tallennetaan stage
		if(!game.board1set) {
			Parent root;
			try {
				root=FXMLLoader.load(getClass().getResource("Aluksien sijainnit.fxml"));
			}
			catch(Exception e) {
				e.printStackTrace();
				return;
			}
			Scene scene= new Scene(root);
			stage.setScene(scene); // asetetaan uusi Scene
			stage.show(); // näytetään uusi scene
			game.board1set=true;
		}
		else {
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
	}

	@FXML
	void Reset(ActionEvent event) {
		Node node = (Node) event.getSource(); // Tallennetaan nappi muuttujaan node
		Stage stage = (Stage) node.getScene().getWindow(); // Haetaan napin scene ja Scenen ikkuna eli Stage-> tallennetaan stage
		Parent root;

		try {
			root=FXMLLoader.load(getClass().getResource("Aluksien sijainnit.fxml"));
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
