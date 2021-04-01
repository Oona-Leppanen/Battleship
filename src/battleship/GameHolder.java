package battleship;

import javafx.scene.input.DataFormat;

/*
 * A holder class that is used for saving and loading game data. Required for passing game data between controller classes.
 * Also holds a DataFormat object for Drag and Drop in SijainnitController.
 * Inspiration from https://dev.to/devtony101/javafx-3-ways-of-passing-information-between-scenes-1bm8
 */
public final class GameHolder {
	  
	  private Game game;
	  private DataFormat data = new DataFormat("draggable ship"); 
	  private final static GameHolder INSTANCE = new GameHolder();
	  
	  private GameHolder() {}
	  
	  public static GameHolder getInstance() {
	    return INSTANCE;
	  }
	  
	  public void setGame(Game game) {
	    this.game = game;
	  }
	  
	  public Game getGame() {
	    return this.game;
	  }
	  
	  public DataFormat getDataFormat() {
		  return data;
	  }
	}