import java.lang.RuntimeException;
import java.util.List;
import java.util.LinkedList;

public class Move implements AutoCloseable {

	private final EmptyCell ecell;
	private final Value value;
	private List<ChangePossible> cp;
	private SudokuBoard sudokuBoard;

	public Move(SudokuBoard sudokuBoard,EmptyCell ecell,Value value) {

		if(ecell.getX() != value.getX() || ecell.getY() != value.getY()) {
			throw new RuntimeException("illegal state");
		}

		this.ecell = ecell;
		this.value = value;
		this.sudokuBoard = sudokuBoard;

		sudokuBoard.setCell(ecell.getX(),ecell.getY(),value);

		List<Cell> cells = sudokuBoard.getAffectingCells(ecell);

		cp = new LinkedList<>();

		for(Cell e:cells) {
			if(e instanceof EmptyCell) {
				ChangePossible newCp = ChangePossible.getChangePossible((EmptyCell)e,value.getValue());
				if (newCp != null) {
					cp.add(newCp);
				}
			}
		}
	}

	public void revert() {
		sudokuBoard.setCell(ecell.getX(),ecell.getY(),ecell);

		for(ChangePossible e : cp) {
			e.revert();
		}
	}

	private static class ChangePossible {
		EmptyCell cell;
		int possibleValue;

		private ChangePossible(EmptyCell cell,int possibleValue) {
			this.cell = cell;
			this.possibleValue = possibleValue;
		}

		public void revert() {
			if(!cell.setPossibleValue(possibleValue)) {
				throw new RuntimeException("Illegal state");
			}
		}

		public static ChangePossible getChangePossible(EmptyCell cell,int possibleValue) {

			if(cell.setNotPossibleValue(possibleValue)) {
				return new ChangePossible(cell,possibleValue);
			} else {
				return null;
			}

		}
	}

	public void close() {
		revert();
	}
}