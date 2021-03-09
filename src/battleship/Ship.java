package battleship;


public class Ship {
	int size;
	int hits = 0;
	boolean alive = true;
	boolean vertical = true;

	public Ship (int size) {
		size = this.size;
	}

	int getSize() {
		return this.size;
	}

	boolean isAlive() {
		if (hits == size) {
			this.alive = false;
			return this.alive;
		}
		return this.alive;
	}

	void gotHit() {
		this.hits += 1;
	}
}

