import java.util.List;

class Backtrack {
	public static void recursive(SudokuBoard sudokuBoard) {
		EmptyCell nextCell = null;

		List<EmptyCell> ec = sudokuBoard.getEmptyCells();

		if (ec.size() == 0 ){
			System.out.println(sudokuBoard);
		}

		for (EmptyCell e:ec) {
			if (nextCell == null || nextCell.possibleValues().size() > e.possibleValues().size()) {
				nextCell = e;
			}
		}
		if (nextCell != null) {
			for (int value:nextCell.possibleValues()) {
				try(Move move = sudokuBoard.makeMove(nextCell,value)) {
					recursive(sudokuBoard);
				}
			}
		}
	}

	public static void main(String[] args) {
		SudokuBoard sudokuBoard = new SudokuBoard(
			new int[][]
			{
				{5,3,0,0,7,0,0,0,0},
				{6,0,0,1,9,5,0,0,0},
				{0,9,8,0,0,0,0,6,0},
				{8,0,0,0,6,0,0,0,3},
				{4,0,0,8,0,3,0,0,1},
				{7,0,0,0,2,0,0,0,6},
				{0,6,0,0,0,0,2,8,0},
				{0,0,0,4,1,9,0,0,5},
				{0,0,0,0,8,0,0,7,9}
			}
		);
		System.out.println(sudokuBoard);
		recursive(sudokuBoard);
	}
}