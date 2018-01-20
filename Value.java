import java.lang.UnsupportedOperationException;
import java.util.List;

public class Value extends Cell {
	private final int value;

	public Value(int x,int y,int value) {
		super(x,y);
		this.value = value;
	}

	public Value(EmptyCell ec,int value) {
		super(ec.getX(),ec.getY());
		this.value = value;
	}

	public boolean isValue() {
		return true;
	}

	public int getValue() {
		return value;
	}

	public List<Integer> possibleValues() {
		throw new UnsupportedOperationException();
	}

	public boolean setNotPossibleValue(int value) {
		throw new UnsupportedOperationException();
	}
	public boolean setPossibleValue(int value) {
		throw new UnsupportedOperationException();
	}
}