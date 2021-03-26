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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class PelinakymaController {

	private Game game;
	int x;

	@FXML
	private Pane playerBoard;

	@FXML
	private Pane opponentBoard;

	@FXML
	private Label playLabel;

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

		if(true) {
			playLabel.setText(game.player1 + "'s turn");
		}
		else {
			playLabel.setText(game.player2 + "'s turn");
		}

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
				if(game.playerBoard2.pelilauta[i][j]==0 || game.playerBoard2.pelilauta[i][j]==1) {
					addImageListener(view);
				}

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
				if(game.playerBoard2.pelilauta[i][j]==2) {
					ImageView r= createMiss();
					GridPane.setConstraints(r, i, j);
					gp1.getChildren().add(r);
				}
				if(game.playerBoard2.pelilauta[i][j]==3) {
					ImageView r= createHit();
					GridPane.setConstraints(r, i, j);
					gp1.getChildren().add(r);
				}
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

	public ImageView createHit() {
		Image image=new Image(getClass().getResource("hit.png").toExternalForm());
		ImageView view=new ImageView(image);
		view.setFitWidth(308/x);
		view.setFitHeight(308/x);
		addImageListener(view);
		view.setPreserveRatio(true);
		return view;
	}
	public ImageView createMiss() {
		Image image=new Image(getClass().getResource("upotettu.jpg").toExternalForm());
		ImageView view=new ImageView(image);
		view.setFitWidth(308/x);
		view.setFitHeight(308/x);
		addImageListener(view);
		view.setPreserveRatio(true);
		return view;
	}

	private void addImageListener(ImageView image) {
		image.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent clickEvent) {
				ImageView target= (ImageView) clickEvent.getSource();
				GridPane targetGrid= (GridPane) target.getParent().getParent();
				int y = GridPane.getRowIndex(target.getParent());
				int x = GridPane.getColumnIndex(target.getParent());
				for (int i=0; i < game.playerBoard2.shipsOnBoard.size(); i++) {
					System.out.println("Laiva numero " + i);
					if (game.playerBoard2.pelilauta[game.playerBoard2.shipsOnBoard.get(i).onBoard(x)[0]][game.playerBoard2.shipsOnBoard.get(i).onBoard(y)[1]] == game.playerBoard2.pelilauta[x][y]) {
						game.playerBoard2.shipsOnBoard.get(i).gotHit();
						System.out.println("Thaat's a hit");
						if (!game.playerBoard2.shipsOnBoard.get(i).isAlive()) {
							game.playerBoard2.shipsOnBoard.remove(i);
							System.out.println("Laiva on uponnut ruudusta (" + ((game.playerBoard2.shipsOnBoard.get(i).coordX)+1) + "," + ((game.playerBoard2.shipsOnBoard.get(i).coordY)+1) + ")" );
						}
					}
					else {
						System.out.println("You missed son, on ship number " + i);
					}
				}
				if (game.playerBoard2.hasAShip(x, y)) {
					ImageView r= createHit();
					GridPane.setConstraints(r, x, y);
					targetGrid.getChildren().add(r);
					game.playerBoard2.pelilauta[x][y] = 3;					
				}
				else {
					ImageView r= createMiss();
					GridPane.setConstraints(r, x, y);
					targetGrid.getChildren().add(r);
					game.playerBoard2.pelilauta[x][y] = 2;
				}
				targetGrid.setDisable(true);


			}
		}
				);	
	}
}