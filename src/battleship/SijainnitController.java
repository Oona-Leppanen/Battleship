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
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;

public class SijainnitController {

	private DataFormat DRAGGABLE_SHIP_TYPE = GameHolder.getInstance().getDataFormat();
	private Game game;

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

	public void initialize(){
		GameHolder holder=GameHolder.getInstance();
		game=holder.getGame();
		GridPane gp=new GridPane();
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
				view.setFitWidth(330/x);
				view.setFitHeight(330/x);
				GridPane.setConstraints(view, i, j);
				gp.getChildren().add(view);
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

	public void addImageListener(ImageView image) {
		image.setOnDragDropped(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent dragEvent) {
				/* data dropped */
				System.out.println("onDragDropped");
				/* if there is a string data on dragboard, read it and use it */
				Dragboard db = dragEvent.getDragboard();
				boolean success = false;
				if (db.hasString()) {
					success = true;
				}
				/* let the source know whether the string was successfully 
				 * transferred and used */
				ImageView target= (ImageView) dragEvent.getSource();
				GridPane targetGrid= (GridPane) target.getParent().getParent();
				int x = GridPane.getRowIndex(target);
				int y = GridPane.getColumnIndex(target);
				Rectangle r= CreateShip();
				//GridPane.setRowSpan(r, 2);
				GridPane.setColumnSpan(r, 2);
				GridPane.setConstraints(r, y, x);
				targetGrid.getChildren().add(r);
				System.out.println("Laivaa on upotettu");
				dragEvent.setDropCompleted(success);

				dragEvent.consume();

			}
		});


		image.setOnDragOver(new EventHandler <DragEvent>() {
			public void handle(DragEvent event) {
				event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
				event.consume();
			}
		});

		image.setOnDragEntered(new EventHandler <DragEvent>() {
			public void handle(DragEvent event) {
				/* the drag-and-drop gesture entered the target */
				System.out.println("onDragEntered");
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

	@FXML
	void dragShip(MouseEvent event) {
		Rectangle r= (Rectangle) event.getSource();
		if(r.getOpacity()>0.5) {
			Dragboard db = r.startDragAndDrop(TransferMode.ANY);
			r.setRotate(90);
			db.setDragView(r.snapshot(null, null));
			r.setRotate(0);
			ClipboardContent content = new ClipboardContent();
			content.put(DRAGGABLE_SHIP_TYPE, "");
			db.setContent(content);
			event.consume();
		}
	}

	@FXML
	void dragComplete(DragEvent event) {
		Rectangle r=(Rectangle) event.getSource();
		r.setOpacity(0.5);
		System.out.println("SUCCESS");
	}

	public Rectangle CreateShip() {
		Rectangle rec= new Rectangle(110, 23, Color.BLACK);
		rec.setLayoutX(24);
		rec.setLayoutY(28);
		return rec;
	}
}
