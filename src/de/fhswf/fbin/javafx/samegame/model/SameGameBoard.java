package de.fhswf.fbin.javafx.samegame.model;

import static de.fhswf.fbin.javafx.samegame.common.Constants.BOARD_DIMENSION;
import java.util.Random;

public class SameGameBoard {
	enum DIRECTION {
		DIRECTION_DOWN, DIRECTION_UP, DIRECTION_RIGHT, DIRECTION_LEFT
	}

	/* Board size information */
	private int columns;

	private int rows;

	private int[][] arrBoard;

	private int remaining;

	public SameGameBoard(int columns, int rows) {
		this.columns = columns;
		this.rows = rows;
		this.remaining = columns * rows;

		setupBoard();
	}

	public SameGameBoard() {
		this(BOARD_DIMENSION[0], BOARD_DIMENSION[1]);
	}

	/**
	 * Function to randomly setup the board.
	 */
	private void setupBoard() {
		// Create the board if needed
		if (arrBoard == null)
			createBoard();

		Random rnd = new Random();

		// Randomly set each square to a color
		for (int row = 0; row < rows; row++)
			for (int col = 0; col < columns; col++)
				arrBoard[row][col] = rnd.nextInt(3) + 1;
	}

	/**
	 * Get the color at a particular location.
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	public int getBoardSpace(int row, int col) {
		int c = 0;

		// Check the bounds of the array
		if (!(row < 0 || row >= rows || col < 0 || col >= columns))
			c = arrBoard[row][col];

		return c;
	}

	/**
	 * @return the columns
	 */
	public int getColumns() {
		return columns;
	}

	/**
	 * @return the rows
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * @return the remaining
	 */
	public int getRemaining() {
		return remaining;
	}

	/**
	* 
	*/
	public void deleteBoard() {
		arrBoard = null;
	}

	private void createBoard() {
		// If there is already a board, delete it
		if (arrBoard != null)
			deleteBoard();

		// Create the array of rows
		arrBoard = new int[rows][columns];

		// Create each row
		for (int row = 0; row < rows; row++) {
			// Set each square to be empty
			for (int col = 0; col < columns; col++)
				arrBoard[row][col] = 0;
		}
	}

	/**
	 * @param row
	 * @param col
	 * @return
	 */
	public int deleteBlocks(int row, int col) {
		// Make sure that the row and column are valid
		if (row < 0 || row >= rows || col < 0 || col >= columns)
			return -1;

		// Can't delete background blocks
		int color = arrBoard[row][col];
		if (color == 0)
			return -1;

		// First check if there are any of the adjacent sides
		// with the same color
		int count = -1;
		if ((row - 1 >= 0 && arrBoard[row - 1][col] == color) || (row + 1 < rows && arrBoard[row + 1][col] == color)
				|| (col - 1 >= 0 && arrBoard[row][col - 1] == color)
				|| (col + 1 < columns && arrBoard[row][col + 1] == color)) {
			// Then call the recursive function to eliminate all
			// other touching blocks with same color
			arrBoard[row][col] = 0;
			count = 1;

			// Recursive call for up
			count += deleteNeighborBlocks(row - 1, col, color, DIRECTION.DIRECTION_DOWN);

			// Recursive call for down
			count += deleteNeighborBlocks(row + 1, col, color, DIRECTION.DIRECTION_UP);

			// Recursive call for left
			count += deleteNeighborBlocks(row, col - 1, color, DIRECTION.DIRECTION_RIGHT);

			// Recursive call for right
			count += deleteNeighborBlocks(row, col + 1, color, DIRECTION.DIRECTION_LEFT);

			// Finally compact the board
			compactBoard();

			// Remove the count from the number remaining
			remaining -= count;
		}

		// Return the total number of pieces deleted
		return count;
	}

	private int deleteNeighborBlocks(int row, int col, int color, DIRECTION direction) {
		// Check if it is on the board
		if (row < 0 || row >= rows || col < 0 || col >= columns)
			return 0;

		// Check if it has the same color
		if (arrBoard[row][col] != color)
			return 0;

		int count = 1;
		arrBoard[row][col] = 0;

		// If we weren't told to not go back up, check up
		if (direction != DIRECTION.DIRECTION_UP)
			count += deleteNeighborBlocks(row - 1, col, color, DIRECTION.DIRECTION_DOWN);

		// If we weren't told to not go back down, check down
		if (direction != DIRECTION.DIRECTION_DOWN)
			count += deleteNeighborBlocks(row + 1, col, color, DIRECTION.DIRECTION_UP);

		// If we weren't told to not go back left, check left
		if (direction != DIRECTION.DIRECTION_LEFT)
			count += deleteNeighborBlocks(row, col - 1, color, DIRECTION.DIRECTION_RIGHT);

		// If we weren't told to not go back right, check right
		if (direction != DIRECTION.DIRECTION_RIGHT)
			count += deleteNeighborBlocks(row, col + 1, color, DIRECTION.DIRECTION_LEFT);

		// Return the total number of pieces deleted
		return count;
	}

	private void compactBoard() {
		// First move everything down
		for (int col = 0; col < columns; col++) {
			int nextEmptyRow = rows - 1;
			int nextOccupiedRow = nextEmptyRow;

			while (nextOccupiedRow >= 0 && nextEmptyRow >= 0) {
				// First find the next empty row
				while (nextEmptyRow >= 0 && arrBoard[nextEmptyRow][col] != 0)
					nextEmptyRow--;

				if (nextEmptyRow >= 0) {
					// Then find the next occupied row from the next empty row
					nextOccupiedRow = nextEmptyRow - 1;

					while (nextOccupiedRow >= 0 && arrBoard[nextOccupiedRow][col] == 0)
						nextOccupiedRow--;

					if (nextOccupiedRow >= 0) {
						// Now move the block from occupied to empty
						arrBoard[nextEmptyRow][col] = arrBoard[nextOccupiedRow][col];
						arrBoard[nextOccupiedRow][col] = 0;
					}
				}
			}
		}

		// Then move everything from right to left
		int nextEmptyCol = 0;
		int nextOccupiedCol = nextEmptyCol;

		while (nextEmptyCol < columns && nextOccupiedCol < columns) {
			// First find the next empty column
			while (nextEmptyCol < columns && arrBoard[rows - 1][nextEmptyCol] != 0)
				nextEmptyCol++;

			if (nextEmptyCol < columns) {
				// Then find the next column with something in it
				nextOccupiedCol = nextEmptyCol + 1;

				while (nextOccupiedCol < columns && arrBoard[rows - 1][nextOccupiedCol] == 0)
					nextOccupiedCol++;

				if (nextOccupiedCol < columns) {
					// Move entire column to the left
					for (int row = 0; row < rows; row++) {
						arrBoard[row][nextEmptyCol] = arrBoard[row][nextOccupiedCol];
						arrBoard[row][nextOccupiedCol] = 0;
					}
				}
			}
		}
	}

	/**
	 * @return
	 */
	public boolean isGameOver() {
		boolean ret = true;

		// Go column by column, left to right
		for (int col = 0; col < columns && ret; col++) {
			// Row by row, bottom to top
			for (int row = rows - 1; row >= 0 && ret; row--) {
				int nColor = arrBoard[row][col];

				// Once we hit background, this column is done
				if (nColor != 0) {
					// Check above and right
					if (row - 1 >= 0 && arrBoard[row - 1][col] == nColor)
						ret = false;
					else if (col + 1 < columns && arrBoard[row][col + 1] == nColor)
						ret = false;
				}
			}
		}

		// No two found adjacent
		return ret;
	}
	
	public void resetBoard()
	{
	   this.remaining = columns * rows;
	   deleteBoard();
	   setupBoard();
	}
}
