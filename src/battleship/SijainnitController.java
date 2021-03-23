package battleship;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class SijainnitController {

	private Game game;

	@FXML
	private Pane pane;
	
    @FXML
    private Button backButton;

	@FXML
	private Button resetButton;

	@FXML
	private Button continueButton;

	public void initialize(){
		GameHolder holder=GameHolder.getInstance();
		game=holder.getGame();
		GridPane gp=new GridPane();
		//gp.setStyle();
		//gp.setStyle("-fx-border-color: black");
		
		//Class<?> clazz = MyClass.class;
		//InputStream input = clazz.getResourceAsStream("/org/o7planning/javafx/icon/java-32.png");
		//Image image=new Image(this.getClass().getResource("./battleship/Sea view4.jpg"));
		Image image=new Image(getClass().getResource("Sea view4.jpg").toExternalForm());
		//("Sea view4.jpg");//
		
		int x;
		if(game.sizeX>game.sizeY) {
			x=game.sizeX;
		}
		else {
			x=game.sizeY;
		}
		for(int i=0; i<game.sizeX;i++) {
			for(int j=0;j<game.sizeY;j++) {
				ImageView view;
				view=new ImageView(image);
				view.setFitWidth(330/x);
				view.setFitHeight(330/x);
				view.setPreserveRatio(true);
				
				StackPane pane=new StackPane(view);
				pane.setPrefWidth(340/x);
				pane.setPrefHeight(340/x);
				pane.setAlignment(Pos.CENTER);
				pane.setStyle("-fx-background-color: black");
				//
				//view.setSmooth(true);
				//view.setCache(true);
				//Rectangle r= new Rectangle(330/x,330/x,Color.WHITE);
				//r.setStroke(Color.BLACK);
				//r.setOpacity(0.5);
				
				GridPane.setConstraints(pane, i, j); // column=0 row=0
				gp.getChildren().add(pane);//r
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
	
	@FXML
    void Back(ActionEvent event) {
		Node node = (Node) event.getSource(); // Tallennetaan nappi muuttujaan node
		Stage stage = (Stage) node.getScene().getWindow(); // Haetaan napin scene ja Scenen ikkuna eli Stage-> tallennetaan stage
		Parent root;

		try {
			root=FXMLLoader.load(getClass().getResource("Laivanupotus_aloitusnaytto_2.fxml"));
		}
		catch(Exception e) {
			e.printStackTrace();
			return;
		}
		Scene scene= new Scene(root);
		stage.setScene(scene); // asetetaan uusi Scene
		stage.show(); // näytetään uusi scene
		game.board1set=false;
    }
}
