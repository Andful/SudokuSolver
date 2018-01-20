import java.lang.UnsupportedOperationException;
import java.util.List;
import java.util.LinkedList;

public class EmptyCell extends Cell{

	private static final int NUMBER_POSSIBLE_VALUES = 9;
	private final boolean[] possibleValues;

	public EmptyCell(int x,int y) {
		super(x,y);
		possibleValues = new boolean[NUMBER_POSSIBLE_VALUES];
		for (int i=0;i<possibleValues.length;i++) {
			possibleValues[i] = true;
		}
	}

	public boolean isValue() {
		return false;
	}

	public int getValue() {
		throw new UnsupportedOperationException();
	}

	public List<Integer> possibleValues() {
		LinkedList<Integer> result = new LinkedList<>();
		for (int i=0;i<possibleValues.length;i++) {
			if (possibleValues[i]) {
				result.add(i);
			}
		}
		return result;
	}
	public boolean setNotPossibleValue(int value) {
		if (possibleValues[value]) {
			possibleValues[value] = false;
			return true;
		} else {
			return false;
		}
	}
	public boolean setPossibleValue(int value) {
		if (!possibleValues[value]) {
			possibleValues[value] = true;
			return true;
		} else {
			return false;
		}
	}
}