import java.lang.StringBuilder;
import java.util.List;
import java.util.LinkedList;
import java.util.Stack;
import java.lang.RuntimeException;

public class SudokuBoard {

	public static final int WIDTH = 9;
	public static final int HEIGHT = 9;

	private Cell[][] board;

	public SudokuBoard(int[][] values) {
		this();
		if(values.length != WIDTH && values[0].length != HEIGHT) {
			throw new RuntimeException("illegal state");
		}

		for (int x=0;x<WIDTH;x++) {
			for (int y=0;y<HEIGHT;y++) {
				if (values[y][x] != 0) {
					if (values[y][x] >= 1 && values[y][x] <=9) {
						makeMove(x,y,values[y][x] - 1);
					} else {
						throw new RuntimeException("illegal state");
					}
				}
			}
		}

	}

	public SudokuBoard() {
		board = new Cell[WIDTH][HEIGHT];

		for (int x=0;x<WIDTH;x++) {
			for (int y=0;y<HEIGHT;y++) {
				board[x][y] = new EmptyCell(x,y);
			}
		}
	}

	public List<Cell> getAffectingCells(Cell cell) {
		List<Cell> result = new LinkedList<>();

		for (int x=0;x<WIDTH;x++) {
			if (x != cell.getX()) {
				result.add(board[x][cell.getY()]);
			}
		}

		for (int y=0;y<HEIGHT;y++) {
			if (y != cell.getY()) {
				result.add(board[cell.getX()][y]);
			}
		}

		int[] quadrant = {cell.getX()/3,cell.getY()/3};

		 for (int x = 3*quadrant[0];x < 3*quadrant[0] + 3;x++) {
		 	for (int y = 3*quadrant[1];y < 3*quadrant[1] + 3;y++) {
		 		if (x != cell.getX() && y != cell.getY()) {
		 			result.add(board[x][y]);
		 		}
		 	}
		}

		return result;
	}

	public Move makeMove(int x,int y,int value) {
		Cell cell = this.board[x][y];
		if (!(cell instanceof EmptyCell)) {
			throw new RuntimeException("illegal state");
		} else {
			return new Move(this,(EmptyCell)cell,new Value((EmptyCell)cell,value));
		}
	}

	public Move makeMove(EmptyCell cell,int value) {
		return this.makeMove(cell.getX(),cell.getY(),value);
	}

	public Cell getCell(int x,int y) {
		return board[x][y];
	}

	public void setCell(int x,int y,Cell cell) {
		board[x][y] = cell;
	}

	public List<Cell> getAllCells() {
		List<Cell> result = new LinkedList<>();

		for (int x=0;x<WIDTH;x++) {
			for (int y=0;y<HEIGHT;y++) {
				result.add(getCell(x,y));
			}
		}

		return result;
	}

	public List<EmptyCell> getEmptyCells() {
		List<EmptyCell> result = new LinkedList<>();

		for (int x=0;x<WIDTH;x++) {
			for (int y=0;y<HEIGHT;y++) {
				Cell cell = getCell(x,y);
				if (cell instanceof EmptyCell) {
					result.add((EmptyCell)cell);
				}
			}
		}

		return result;
	} 

	public List<Value> getValueCells() {
		List<Value> result = new LinkedList<>();

		for (int x=0;x<WIDTH;x++) {
			for (int y=0;y<HEIGHT;y++) {
				Cell cell = getCell(x,y);
				if (cell instanceof Value) {
					result.add((Value)cell);
				}
			}
		}

		return result;
	}

	public String toString() {
		StringBuilder result = new StringBuilder("\n");
		for (int y=0;y<HEIGHT;y++) {
			for (int x=0;x<WIDTH;x++) {
				if (board[x][y].isValue()) {
					result.append(" "+Integer.toString(board[x][y].getValue()+1));
				} else {
					result.append(" E");
				}
			}
			result.append("\n");
		}

		return result.toString();
	}



	public static void main(String[] args) {
		SudokuBoard board = new SudokuBoard();
		System.out.println(board);
		System.out.println(board.getCell(1,1).possibleValues());
		System.out.println(board.getCell(0,8).possibleValues());
		try(Move move=board.makeMove(0,0,1)){
			try(Move move2=board.makeMove(0,1,2)){
				System.out.println(board);
				System.out.println(board.getCell(1,1).possibleValues());
				System.out.println(board.getCell(0,8).possibleValues());
			}
		}
		System.out.println(board);
		System.out.println(board.getCell(1,1).possibleValues());
		System.out.println(board.getCell(0,8).possibleValues());
	}
}