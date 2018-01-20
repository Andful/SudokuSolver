import java.util.List;

public abstract class Cell {
	private final int x;
	private final int y;
	public Cell(int x,int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public abstract boolean isValue();
	public abstract int getValue();
	public abstract List<Integer> possibleValues();
	public abstract boolean setNotPossibleValue(int value);
	public abstract boolean setPossibleValue(int value);
}
