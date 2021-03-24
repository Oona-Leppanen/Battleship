package battleship;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class PelinakymaController {

	private Game game;

	@FXML
	private Pane playerBoard;

	@FXML
	private Pane opponentBoard;

	@FXML
	private Button quitButton;

	@FXML
	private Button continueButton;

	public void initialize(){
		GameHolder holder=GameHolder.getInstance();
		game=holder.getGame();
		GridPane gp1=new GridPane();
		GridPane gp2=new GridPane();
		Image image=new Image(getClass().getResource("Sea view4.jpg").toExternalForm());
		
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
				view.setFitWidth(308/x);
				view.setFitHeight(308/x);
				view.setPreserveRatio(true);
				
				StackPane pane=new StackPane(view);
				pane.setPrefWidth(315/x);
				pane.setPrefHeight(315/x);
				pane.setAlignment(Pos.CENTER);
				pane.setStyle("-fx-background-color: black");
				//Rectangle r= new Rectangle(300/x,300/x,Color.WHITE);
				//r.setStroke(Color.BLACK);
				//r.setOpacity(0.5);
				GridPane.setConstraints(pane, i, j); // column=0 row=0
				gp1.getChildren().add(pane); //r
			}
		}
		for(int i=0; i<game.sizeX;i++) {
			for(int j=0;j<game.sizeY;j++) {
				ImageView view;
				view=new ImageView(image);
				view.setFitWidth(233/x);
				view.setFitHeight(233/x);
				view.setPreserveRatio(true);
				
				StackPane pane=new StackPane(view);
				pane.setPrefWidth(240/x);
				pane.setPrefHeight(240/x);
				pane.setAlignment(Pos.CENTER);
				pane.setStyle("-fx-background-color: black");
				//Rectangle r= new Rectangle(240/x,240/x,Color.WHITE);
				//r.setStroke(Color.BLACK);
				//r.setOpacity(0.5);
				GridPane.setConstraints(pane, i, j); // column=0 row=0
				gp2.getChildren().add(pane); //r
			}
		}
		playerBoard.getChildren().add(gp2);
		opponentBoard.getChildren().add(gp1);
	}

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
		Node node = (Node) event.getSource(); // Tallennetaan nappi muuttujaan node
		Stage stage = (Stage) node.getScene().getWindow(); // Haetaan napin scene ja Scenen ikkuna eli Stage-> tallennetaan stage
		Parent root;

		try {
			root=FXMLLoader.load(getClass().getResource("Loppu.fxml"));
		}
		catch(Exception e) {
			e.printStackTrace();
			return;
		}
		Scene scene= new Scene(root);
		stage.setScene(scene); // asetetaan uusi Scene
		stage.show(); // näytetään uusi scene
	}

	public void addImageListener(ImageView image) {
		image.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent clickEvent) {
				ImageView target= (ImageView) clickEvent.getSource();
				GridPane targetGrid= (GridPane) target.getParent().getParent();
				int y = GridPane.getRowIndex(target);
				int x = GridPane.getColumnIndex(target);
				if (game.playerBoard2.hasAShip(x, y)) {
					 	
				}
			}
			
		}
		);
	}
}
