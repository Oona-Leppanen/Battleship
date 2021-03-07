package battleship;

import java.util.ArrayList;



public class Ship {
	int size;
	ArrayList<Integer> CoordX; 
	ArrayList<Integer> CoordY;
	int hits = 0;
	boolean alive = true;	
	
	public Ship (int size, ArrayList<Integer> CoordX, ArrayList<Integer> CoordY) {
		size = this.size;
		CoordX = new ArrayList<Integer>();
		CoordY = new ArrayList<Integer>();
	}
	
	int getSize() {
		return this.size;
	}

	boolean isAlive() {
		return this.alive;
	}
	
	void gotHit() {
		this.hits += 1;
	}
	
	int getX(int x) {
		return this.CoordX.get(x);
	}
	
	int getY(int y) {
		return this.CoordY.get(y);
	}
}

