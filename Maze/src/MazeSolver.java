import java.awt.Color;

import gdp.stdlib.StdDraw;
import gdp.stdlib.StdIn;

/**
 * 
 */

/**
 * @author Darjengar
 *
 */
public class MazeSolver {

//	public static void main(String[] args) {
//		int[][] test_maze = new int[][] {
//			{1, 1, 1, 2},
//			{0, 1, 0, 1},
//			{0, 1, 0, 1},
//			{3, 1, 1, 1}
//		};
//		draw(test_maze);
//		StdDraw.show(1000);
//		if (solve(test_maze, 0, test_maze.length-1)) {
//			System.out.println("Lösbar");
//		}
//		else {
//			System.out.println("Nicht lösbar");
//		}
//		draw(test_maze);
//		StdDraw.show(1000);
//	}
	
	public static boolean solve(int[][] maze, int row, int col) {
		if (maze[row][col] == 3) {
			maze[row][col] = 2;
			return true;
		}
		maze[row][col] = 2;
		// Check bottom border
		if ( row+1 > maze.length-1) {
			// Dead end
			if (maze[row][col-1] == 0) {
				maze[row][col] = 1;
			}
			else {
				// Step left
				if (maze[row][col-1] == 1 || maze[row][col-1] == 3) {
					if(solve(maze, row, col-1)) {
						return true;
					}
					maze[row][col] = 1;
				}
			}
		}
		// Check left border
		else if ( col-1 < 0 ) {
			// Dead end
			if (maze[row+1][col] == 0) {
				maze[row][col] = 1;
			}
			else {
				// Step down
				if (maze[row+1][col] == 1 || maze[row+1][col] == 3) {
					if (solve(maze, row+1, col)) {
						return true;
					}
					maze[row][col] = 1;
				}
			}
		}
		else {
			// Dead End
			if (maze[row+1][col] == 0 && maze[row][col-1] == 0) {
				if (row != 0 || col != maze.length-1) {
					maze[row][col] = 1;
				}
			}
			else {
				// Step left
				if (maze[row][col-1] == 1 || maze[row][col-1] == 3) {
					if(solve(maze, row, col-1)) {
						return true;
					}
				}
				// Step down
				if (maze[row+1][col] == 1 || maze[row+1][col] == 3) {
					if (solve(maze, row+1, col)) {
						return true;
					}
				}
				if (row != 0 || col != maze.length-1) {
					maze[row][col] = 1;
				}
			}
		}
		return false;
	}

	public static void draw(int[][] maze) {
		int BLOCK_SIZE = 64;
		initWindow(maze.length*BLOCK_SIZE, maze.length*BLOCK_SIZE);
		StdDraw.clear(StdDraw.WHITE);
		drawGrid(maze.length, maze.length, BLOCK_SIZE);
		for (int iii = 0; iii < maze.length; iii++) {
			for (int jjj = 0; jjj < maze.length; jjj++) {
				switch (maze[iii][jjj]) {
					case 0:
						fillBlock(jjj, maze.length-1-iii,
								BLOCK_SIZE, StdDraw.GRAY);
						break;
					case 1:
						fillBlock(jjj, maze.length-1-iii,
								BLOCK_SIZE, StdDraw.WHITE);
						break;
					case 2:
						fillBlock(jjj, maze.length-1-iii,
								BLOCK_SIZE, StdDraw.GREEN);
						break;
					case 3:
						fillBlock(jjj, maze.length-1-iii,
								BLOCK_SIZE, StdDraw.BLUE);
						break;
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
		StdDraw.setPenColor(StdDraw.BLACK);
		for (int iii = 0; iii <= height; iii++) {
			StdDraw.line(0, iii * blocksize, blocksize * width, iii * blocksize);
		}

		for (int iii = 0; iii <= width; iii++) {
			StdDraw.line(iii * blocksize, 0, iii * blocksize, blocksize * height);
		}
		StdDraw.setPenColor();
	}
	
	public static void fillBlock(int xpos, int ypos, int blocksize, Color color) {
		StdDraw.setPenColor(color);
		StdDraw.filledSquare(xpos * blocksize + blocksize / 2,
				ypos * blocksize + blocksize / 2, blocksize / 2);
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.square(xpos * blocksize + blocksize / 2,
				ypos * blocksize + blocksize / 2, blocksize / 2);
		StdDraw.setPenColor();
	}
}
