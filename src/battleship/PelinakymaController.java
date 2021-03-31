package battleship;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
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

		if(game.player1turn) {
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
		if(game.player1turn) {
			setBigBoard(game.playerBoard2);
			setMiniBoard(game.playerBoard1);
		}
		else {
			setBigBoard(game.playerBoard1);
			setMiniBoard(game.playerBoard2);
		}

	}
	
	void setBigBoard(Board board) {
		GridPane gp1=new GridPane();
		Image image=new Image(getClass().getResource("Sea view4.jpg").toExternalForm());

		for(int i=0; i<game.sizeX;i++) {
			for(int j=0;j<game.sizeY;j++) {
				ImageView view;
				view=new ImageView(image);
				view.setFitWidth(308/x);
				view.setFitHeight(308/x);
				view.setPreserveRatio(true);
				if(board.pelilauta[i][j]==0 || board.pelilauta[i][j]==1) {
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
				if(board.pelilauta[i][j]==2) {
					ImageView r= createMiss(308);
					GridPane.setConstraints(r, i, j);
					gp1.getChildren().add(r);
				}
				if(board.pelilauta[i][j]==3) {
					ImageView r= createHit(308);
					GridPane.setConstraints(r, i, j);
					gp1.getChildren().add(r);
				}
			}
		}
		
		for(int k=0; k<board.sunkShips.size(); k++) {
			Rectangle r=createShip(308, board.sunkShips.get(k));
			r.setOpacity(0.5);
			gp1.getChildren().add(r);
		}
		
		opponentBoard.getChildren().add(gp1);

		/*for(int i=0; i<board.shipsOnBoard.size(); i++) {
			gp1.getChildren().add(createShip(233, board.shipsOnBoard.get(i)));
		}
		for(int i=0; i<board.sunkShips.size(); i++) {
			Rectangle r=createShip(233, board.sunkShips.get(i));
			r.setOpacity(0.5);
			gp1.getChildren().add(r);
		}*/
	}

	void setMiniBoard(Board board) {
		GridPane gp2=new GridPane();
		Image image=new Image(getClass().getResource("Sea view4.jpg").toExternalForm());

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

		for(int i=0; i<board.shipsOnBoard.size(); i++) {
			Rectangle r=createShip(233, board.shipsOnBoard.get(i));
			r.toBack();
			gp2.getChildren().add(r);
		}
		
		for(int i=0; i<board.sunkShips.size(); i++) {
			Rectangle r=createShip(233, board.sunkShips.get(i));
			r.setOpacity(0.5);
			r.toBack();
			gp2.getChildren().add(r);
		}
		
		for(int i=0; i<game.sizeX;i++) {
			for(int j=0;j<game.sizeY;j++) {
				if(board.pelilauta[i][j]==3) {
					ImageView r= createHit(233);
					GridPane.setConstraints(r, i, j);
					gp2.getChildren().add(r);
				}
				if(board.pelilauta[i][j]==2) {
					ImageView r= createMiss(233);
					GridPane.setConstraints(r, i, j);
					gp2.getChildren().add(r);
				}
			}
		}
	}

	Rectangle createShip(int boardsize, Ship s) {
		int a = s.coordX;
		int b = s.coordY;
		int c = s.size;
		int viewsize = boardsize/x;
		int bordersize = 10/x;
		int shipw = viewsize*(c-1)+viewsize/3+bordersize*(c-1); //laivan leveys
		int shiph = viewsize/2+bordersize*2; //laivan korkeus

		if(s.vertical) {
			Rectangle rec= new Rectangle(shiph, shipw, Color.BLACK);
			GridPane.setConstraints(rec, a, b);
			GridPane.setHalignment(rec, HPos.CENTER);
			GridPane.setValignment(rec, VPos.CENTER);
			GridPane.setRowSpan(rec, c);
			return rec;
		}
		else {
			Rectangle rec= new Rectangle(shipw, shiph, Color.BLACK);
			GridPane.setConstraints(rec, a, b);
			GridPane.setHalignment(rec, HPos.CENTER);
			GridPane.setValignment(rec, VPos.CENTER);
			GridPane.setColumnSpan(rec, c);
			return rec;
		}
	}

	@FXML
	void Continue(ActionEvent event) {
		game.player1turn=!game.player1turn;
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

	public ImageView createHit(int boardsize) {
		Image image=new Image(getClass().getResource("hit.png").toExternalForm());
		ImageView view=new ImageView(image);
		GridPane.setHalignment(view, HPos.CENTER);
		GridPane.setValignment(view, VPos.CENTER);
		int gridsize = boardsize/x;
		view.setFitWidth(gridsize*4/5);
		view.setFitHeight(gridsize*4/5);
		addImageListener(view);
		view.setPreserveRatio(true);
		view.setDisable(true);
		return view;
	}
	public ImageView createMiss(int boardsize) {
		Image image=new Image(getClass().getResource("miss.jpg").toExternalForm());
		ImageView view=new ImageView(image);
		GridPane.setHalignment(view, HPos.CENTER);
		GridPane.setValignment(view, VPos.CENTER);
		int gridsize = boardsize/x;
		view.setFitWidth(gridsize*4/5);
		view.setFitHeight(gridsize*4/5);
		addImageListener(view);
		view.setPreserveRatio(true);
		return view;
	}

	void shoot(Board board, MouseEvent clickEvent) {
		ImageView target= (ImageView) clickEvent.getSource();
		GridPane targetGrid= (GridPane) target.getParent().getParent();
		int y = GridPane.getRowIndex(target.getParent());
		int x = GridPane.getColumnIndex(target.getParent());
		System.out.println(x + " ja " + y);

		if (board.hasAShip(x, y)) {
			ImageView r= createHit(308);
			GridPane.setConstraints(r, x, y);
			targetGrid.getChildren().add(r);
			board.pelilauta[x][y] = 3;
		}
		else if(board.pelilauta[x][y]==0) {
			ImageView r= createMiss(308);
			GridPane.setConstraints(r, x, y);
			targetGrid.getChildren().add(r);
			board.pelilauta[x][y] = 2;
			targetGrid.setDisable(true); //Comment this row for testing
		}

		for (int i=0; i < board.shipsOnBoard.size(); i++) {
			System.out.println("Laiva "+i);
			for (int j=0; j < board.shipsOnBoard.get(i).size; j++) {
				System.out.println("Koordinaatit ovat: "+ board.shipsOnBoard.get(i).onBoard(j)[0] + "," + board.shipsOnBoard.get(i).onBoard(j)[1]);
				if (board.shipsOnBoard.get(i).onBoard(j)[0] == x && board.shipsOnBoard.get(i).onBoard(j)[1] == y) {
					board.shipsOnBoard.get(i).gotHit();
					System.out.println("Thaat's a hit");
					if (!board.shipsOnBoard.get(i).isAlive()) {
						System.out.println("Laiva on uponnut ruudusta (" + ((board.shipsOnBoard.get(i).coordX)) + "," + ((board.shipsOnBoard.get(i).coordY)) + ")" );
						board.sinkAShip(board.shipsOnBoard.get(i));
						board.sunkShips.add(board.shipsOnBoard.get(i));
						removeHits(board.shipsOnBoard.get(i));

						Rectangle r=createShip(308, board.shipsOnBoard.get(i));
						r.setOpacity(0.5);
						((GridPane) opponentBoard.getChildren().get(0)).getChildren().add(r);

						board.shipsOnBoard.remove(i);
						board.lost(); //chekataan häviäminen, TODO tee jotain järkevää tällä metodilla
					}
					break;
				}
			}
			//System.out.println("You missed son, on ship number " + i);
		}
	}

	void removeHits(Ship s) {
		GridPane gp = ((GridPane) opponentBoard.getChildren().get(0));
		for(int i=0; i<s.size; i++) {
			ImageView node = getNodeByRowColumnIndex(s.onBoard(i)[1], s.onBoard(i)[0], gp);
			gp.getChildren().remove(node);
		}
	}

	//https://stackoverflow.com/questions/20825935/javafx-get-node-by-row-and-column
	public ImageView getNodeByRowColumnIndex (final int row, final int column, GridPane gridPane) {
		ImageView result = null;
		ObservableList<Node> childrens = gridPane.getChildren();

		for (Node node : childrens) {
			if(GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
				if(node.equals(ImageView.class)) {
					result = (ImageView) node;
					break;
				}
			}
		}

		return result;
	}

	private void addImageListener(ImageView image) {
		image.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent clickEvent) {
				if(game.player1turn) {
					shoot(game.playerBoard2, clickEvent);
				}
				else {
					shoot(game.playerBoard1, clickEvent);
				}
				((Node) clickEvent.getSource()).getParent().setDisable(true);
			}
		});	
	}
}