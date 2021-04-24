/**
 * 
 */

/**
 * @author Darjengar
 *
 */

import java.awt.Color;

import gdp.stdlib.StdDraw;
import gdp.stdlib.StdIn;

public class GameOfLife {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int BOARD_WIDTH = StdIn.readInt();
		int BOARD_HEIGHT = StdIn.readInt();
		int cells_alive = StdIn.readInt();
		
		int BLOCK_SIZE = 32;
		int WINDOW_WIDTH = BOARD_WIDTH * BLOCK_SIZE;
		int WINDOW_HEIGHT = BOARD_HEIGHT * BLOCK_SIZE;
		int[] board = new int[BOARD_WIDTH * BOARD_HEIGHT];
		int[] board_cpy = new int[BOARD_WIDTH * BOARD_HEIGHT];
		
		int neighbours_alive = 0;
		
		for (int iii = 0, xpos = 0, ypos = 0; iii < cells_alive; iii++) {
			xpos = StdIn.readInt();
			ypos = StdIn.readInt();	
			board[ypos * BOARD_WIDTH + xpos] = 1;
		}

		initWindow(WINDOW_WIDTH, WINDOW_HEIGHT);

		while (true) {
			StdDraw.clear(StdDraw.WHITE);
			drawBoard(board, BOARD_WIDTH, BOARD_HEIGHT, BLOCK_SIZE);
			StdDraw.show(200);
			for (int iii = 0; iii < BOARD_HEIGHT; iii++) {
				for (int jjj = 0; jjj < BOARD_WIDTH; jjj++) {
					neighbours_alive = checkNeighbours(board, BOARD_WIDTH,
							BOARD_HEIGHT, jjj, iii);
					/* GoL Logic */
					if (board[iii * BOARD_WIDTH + jjj] == 0
							&& neighbours_alive == 3) {
						board_cpy[iii * BOARD_WIDTH + jjj] = 1;
					}
					else if (board[iii * BOARD_WIDTH + jjj] == 1
							&& (neighbours_alive-1 == 2
								|| neighbours_alive-1 == 3)) {
						board_cpy[iii * BOARD_WIDTH + jjj] = 1;
					}
					else if (board[iii * BOARD_WIDTH + jjj] == 1){
						board_cpy[iii * BOARD_WIDTH + jjj] = 0;
					}
					else {
						board_cpy[iii * BOARD_WIDTH + jjj] = 0;
					}
				}
			}
			
			for (int iii = 0; iii < BOARD_HEIGHT; iii++) {
				for (int jjj = 0; jjj < BOARD_WIDTH; jjj++) {
					board[iii * BOARD_WIDTH + jjj] = board_cpy[iii * BOARD_WIDTH + jjj];
				}
			}
		}
	}
	
	public static void initWindow(int width, int height) {
		StdDraw.setCanvasSize(width, height);
		StdDraw.setXscale(0.0, width);
		StdDraw.setYscale(0.0, height);
	}
	
	public static void drawGrid(int width, int height, int blocksize) {
		StdDraw.setPenColor(StdDraw.BLUE);
		for (int iii = 0; iii <= height; iii++) {
			StdDraw.line(0, iii * blocksize, blocksize * width, iii * blocksize);
		}

		for (int iii = 0; iii <= width; iii++) {
			StdDraw.line(iii * blocksize, 0, iii * blocksize, blocksize * height);
		}
		StdDraw.setPenColor();
	}
	
	public static void drawBoard(int board[], int width, int height,
			int blocksize) {
		drawGrid(width, height, blocksize);
		for (int iii = 0; iii < height; iii++) {
			for (int jjj = 0; jjj < width; jjj++) {
				if (board[iii * width + jjj] == 1) {
					setBlock(jjj, iii, blocksize);
				}
			}
		}
	}
	
	public static void fillBlock(int xpos, int ypos, int blocksize, Color color) {
		StdDraw.setPenColor(color);
		StdDraw.filledSquare(xpos * blocksize + blocksize / 2,
				ypos * blocksize + blocksize / 2, blocksize / 2);
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.square(xpos * blocksize + blocksize / 2,
				ypos * blocksize + blocksize / 2, blocksize / 2);
		StdDraw.setPenColor();
	}
	public static void setBlock(int xpos, int ypos, int blocksize) {
		fillBlock(xpos, ypos, blocksize, StdDraw.GREEN);
	}
	
	public static void clearBlock(int xpos, int ypos, int blocksize) {
		fillBlock(xpos, ypos, blocksize, StdDraw.WHITE);
	}
	
	public static void printBoard(int board[], int width, int height) {
		for (int iii = 0; iii < height; iii++) {
			for (int jjj = 0; jjj < width; jjj++) {
				System.out.print(board[iii * width + jjj] + " ");
			}
			System.out.println();
		}
	}
	
	public static int checkNeighbours(int board[], int width, int height,
			int xpos, int ypos) {
		int counter = 0;
		/* Check 3x3 square */
		for (int iii = 0, n_ypos = 0; iii < 3; iii++) {
			n_ypos = ypos + iii - 1;
			if (n_ypos < 0) {
				n_ypos = height-1;
			}
			else if (n_ypos > height - 1) {
				n_ypos = 0;
			}
			for (int jjj = 0, n_xpos = 0; jjj < 3; jjj++) {
				n_xpos = xpos + jjj - 1;
				if (n_xpos < 0)
				{
					n_xpos = width-1;
				}
				else if (n_xpos > width-1) {
					n_xpos = 0;
				}
				counter += board[n_ypos * width + n_xpos];
			}
		}
		return counter;
	}
}
