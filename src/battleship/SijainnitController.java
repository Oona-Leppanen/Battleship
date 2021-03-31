package battleship;

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
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;

public class SijainnitController {

	private DataFormat DRAGGABLE_SHIP_TYPE = GameHolder.getInstance().getDataFormat();
	private Game game;
	private boolean rotate=false;
	private boolean success;
	private int size;
	int x;

	@FXML
	private Pane pane;

	@FXML
	private Pane destroyerPane;

	@FXML
	private Pane submarinePane;

	@FXML
	private Pane cruiserPane;

	@FXML
	private Pane battleshipPane;

	@FXML
	private Pane carrierPane;

	@FXML
	private Button backButton;

	@FXML
	private Button resetButton;

	@FXML
	private Button continueButton;
	
	@FXML
	private Label placeLabel;

	@FXML
	private Label rotateLabel;

	@FXML
	private Label infoLabel;

	public void initialize(){
		GameHolder holder=GameHolder.getInstance();
		game=holder.getGame();
		GridPane gp=new GridPane();
		continueButton.setDisable(true);
		Image image=new Image(getClass().getResource("Sea view4.jpg").toExternalForm());
		if(!game.board1set) {
			placeLabel.setText(game.player1 + ", drag to place your ships");
		}
		else {
			placeLabel.setText(game.player2 + ", drag to place your ships");
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
				view.setFitWidth(330/x);
				view.setFitHeight(330/x);
				addImageListener(view);
				view.setPreserveRatio(true);

				StackPane pane=new StackPane(view);
				pane.setPrefWidth(340/x);
				pane.setPrefHeight(340/x);
				pane.setAlignment(Pos.CENTER);
				pane.setStyle("-fx-background-color: black");
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

		for (int i = 5; i > 0; i--) {
			Rectangle r = (Rectangle)destroyerPane.getChildren().get(i);
			if (game.destroyers < i) {
				r.setOpacity(0.5);
			}
		}
		for (int i = 4; i > 0; i--) {
			Rectangle r = (Rectangle)submarinePane.getChildren().get(i);
			if (game.submarines < i) {
				r.setOpacity(0.5);
			}
		}
		for (int i = 3; i > 0; i--) {
			Rectangle r = (Rectangle)cruiserPane.getChildren().get(i);
			if (game.cruisers < i) {
				r.setOpacity(0.5);
			}
		}
		for (int i = 2; i > 0; i--) {
			Rectangle r = (Rectangle)battleshipPane.getChildren().get(i);
			if (game.battleships < i) {
				r.setOpacity(0.5);
			}
		}
		if (game.carriers < 1) {
			Rectangle r = (Rectangle)carrierPane.getChildren().get(1);
			r.setOpacity(0.5);
		}
	}

	@FXML
	void Continue(ActionEvent event) {
		Node node = (Node) event.getSource(); // Tallennetaan nappi muuttujaan node
		Stage stage = (Stage) node.getScene().getWindow(); // Haetaan napin scene ja Scenen ikkuna eli Stage-> tallennetaan stage
		if(!game.board1set) {
			game.board1set=true;
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
		if(game.board1set) {
			game.playerBoard2.clearBoard();
		} else {
			game.playerBoard1.clearBoard();
		}
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
		game.playerBoard1.clearBoard();
		game.playerBoard2.clearBoard();

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

	private void addImageListener(ImageView image) {
		image.setOnDragDropped(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent dragEvent) {
				Ship s = new Ship(size,rotate);
				success = true;
				ImageView target= (ImageView) dragEvent.getSource();
				GridPane targetGrid= (GridPane) target.getParent().getParent();
				int y = GridPane.getRowIndex(target.getParent());
				int x = GridPane.getColumnIndex(target.getParent());
				if (!game.board1set) { //check whose turn is to place ships
					if (game.playerBoard1.willFit(s,x,y)) { //check if ship fits on p1 board
						infoLabel.setText("Press R to change ships' orientation:");
						infoLabel.setTextFill(Color.BLACK);
						infoLabel.setFont(new Font("System", 14));

						Rectangle r= CreateShip(rotate);
						game.playerBoard1.setAShip(s, x, y);
						if(rotate) {
							GridPane.setRowSpan(r, size);
						}
						else if(!rotate) {
							GridPane.setColumnSpan(r, size);
						}
						GridPane.setConstraints(r, x, y);
						GridPane.setHalignment(r, HPos.CENTER);
						GridPane.setValignment(r, VPos.CENTER);
						targetGrid.getChildren().add(r);
						dragEvent.setDropCompleted(success);

						dragEvent.consume();
					}
					else {
						infoLabel.setText("Invalid ship placement!");
						infoLabel.setTextFill(Color.RED);
						infoLabel.setFont(new Font("System", 18));

						System.out.println("Ei muuten mahtunu");
						success = false;
					}
				}
				else {
					if (game.playerBoard2.willFit(s,x,y)) {
						infoLabel.setText("Press R to change ships' orientation:");
						infoLabel.setTextFill(Color.BLACK);
						infoLabel.setFont(new Font("System", 14));

						Rectangle r= CreateShip(rotate);
						game.playerBoard2.setAShip(s, x, y);
						if(rotate) {
							GridPane.setRowSpan(r, size);
						}
						else if(!rotate) {
							GridPane.setColumnSpan(r, size);
						}
						GridPane.setConstraints(r, x, y);
						GridPane.setHalignment(r, HPos.CENTER);
						GridPane.setValignment(r, VPos.CENTER);
						targetGrid.getChildren().add(r);
						dragEvent.setDropCompleted(success);

						dragEvent.consume();
					}
					else {
						infoLabel.setText("Invalid ship placement!");
						infoLabel.setTextFill(Color.RED);
						infoLabel.setFont(new Font("System", 16));

						System.out.println("Ei muuten mahtunu");
						success = false;
					}
				}
			}
		});


		image.setOnDragOver(new EventHandler <DragEvent>() {
			public void handle(DragEvent event) {
				if(event.getSource().getClass()==ImageView.class) {
					event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
					event.consume();
				}
			}
		});

		image.setOnDragEntered(new EventHandler <DragEvent>() {
			public void handle(DragEvent event) {
				/* the drag-and-drop gesture entered the target */
				//System.out.println("onDragEntered");
				/* show to the user that it is an actual gesture target */
				if (event.getGestureSource() != image &&
						event.getDragboard().hasString()) {
					System.out.println("ASIAA!");
				}

				event.consume();
			}
		});

		image.setOnDragExited(new EventHandler <DragEvent>() {
			public void handle(DragEvent event) {
				/* mouse moved away, remove the graphical cues */
				event.consume();
			}
		});
	}
	
	public void setSizeShips(Rectangle r) {
		if(r.getParent().equals(destroyerPane)) {
			size=2;
			System.out.println("koko 2");
		}
		else if(r.getParent().equals(submarinePane) || r.getParent().equals(cruiserPane)) {
			size=3;
			System.out.println("koko 3");
		}
		else if(r.getParent().equals(battleshipPane)) {
			size=4;
			System.out.println("koko 4");
		}
		else if(r.getParent().equals(carrierPane)) {
			size=5;
			System.out.println("koko 5");
		}
	}
	
	@FXML
	void dragShip(MouseEvent event) {
		Rectangle r= (Rectangle) event.getSource();
		if(r.getOpacity()>0.5) {
			setSizeShips(r);
			success=false;
			Dragboard db = r.startDragAndDrop(TransferMode.ANY);
			if(rotate) {
				r.setRotate(90);
				db.setDragView(r.snapshot(null, null));
				r.setRotate(0);
			}
			else {
				db.setDragView(r.snapshot(null, null));
			}
			ClipboardContent content = new ClipboardContent();
			content.put(DRAGGABLE_SHIP_TYPE, "Token text");
			db.setContent(content);
			event.consume();
		}
	}

	@FXML
	void dragComplete(DragEvent event) {
		if(success) {
			Rectangle r=(Rectangle) event.getSource();
			r.setOpacity(0.5);
			System.out.println("SUCCESS");
			if(!game.board1set) {
				if(game.destroyers+game.battleships+game.carriers+game.cruisers+game.submarines==game.playerBoard1.shipsOnBoard.size()) {
					continueButton.setDisable(false);
				}
			}
			else {
				if(game.destroyers+game.battleships+game.carriers+game.cruisers+game.submarines==game.playerBoard2.shipsOnBoard.size()) {
					continueButton.setDisable(false);
				}
			}
		
		}
	}

	@FXML
	void toggleRotate(KeyEvent event) {
		if(event.getText().equals("r")){
			rotate = !rotate;
			if(!rotate) {
				rotateLabel.setText("Horizontal");
			}
			else {
				rotateLabel.setText("Vertical");
			}
		}
	}

	public Rectangle CreateShip(boolean rotate) {
		int viewsize = 330/x;
		int bordersize = 10/x;
		int shipw = viewsize*(size-1)+viewsize/3+bordersize*(size-1); //laivan leveys
		int shiph = viewsize/2+bordersize*2; //laivan korkeus
		if(rotate) {
			Rectangle rec= new Rectangle(shiph, shipw, Color.BLACK);
			return rec;
		}
		else {
			Rectangle rec= new Rectangle(shipw, shiph, Color.BLACK);
			return rec;
		}

	}
}