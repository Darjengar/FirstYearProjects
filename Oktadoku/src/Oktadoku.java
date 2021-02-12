/**
 * 
 */

import gdp.stdlib.StdIn;
import java.util.BitSet;


/**
 * @author Joel Nauschuetz
 *
 */
public class Oktadoku {
	public enum Variante { normal, mitDiagonalen };
	private Variante v;
	private char[] board = new char[64];
	private int BOARD_WIDTH = 8;
	private int BOARD_HEIGHT = 8;
	private int SUBBOARD_WIDTH = 2;
	private int SUBBOARD_HEIGHT = 4;

	public Oktadoku(Variante var) {
		this.v = var;
	}

	public void read() {
		char tmp;
		int iii = 0;
		while(!StdIn.isEmpty()) {
			tmp = StdIn.readChar();
			if ( (int)(tmp-'0') >= 0 && (int)(tmp -'0') <= 8 ) {
				this.board[iii] =  tmp;
			}
			else if (tmp == '.'){
				this.board[iii] = ' ';
			}
			else {
				continue;
			}
			iii++;
		}
	}

	public void write() {
		if (v == Variante.mitDiagonalen) {
			System.out.println("Oktadoku mit Diagonalen");
		}
		else {
			System.out.println("Oktadoku");
		}
		this.printBoard();
	}

	public boolean check() {
		char[][] subboard;
		BitSet truth_map = new BitSet(8);

		// check main board
		if (v == Variante.mitDiagonalen) {
			// check diagonals
			for (int iii = 0; iii < 8; iii++) {
				if (!setValue(board[iii * 8 + iii], truth_map)) {
					return false;
				}
			}
			truth_map.clear();
			for (int iii = 0; iii < 8; iii++) {
				if (!setValue(board[56 - iii * 8 + iii], truth_map)) {
					return false;
				}
			}
		}
		// check horizontal
		for (int iii = 0; iii < 8; iii++) {
			truth_map.clear();

			for (int jjj = 0; jjj < 8; jjj++) {
				if (!setValue(board[iii * 8 + jjj], truth_map)) {
					return false;
				}
			}
		}
		// check vertical
		for (int iii = 0; iii < 8; iii++) {
			truth_map.clear();
			for (int jjj = 0; jjj < 8; jjj++) {
				if (!setValue(board[jjj * 8 + iii], truth_map)) {
					return false;
				}
			}
		}

		// check subboards
		for (int iii = 0; iii < 2; iii++) {
			for (int jjj = 0; jjj < 4; jjj++) {
				subboard = this.getSubboard(jjj, iii);
				for (int kkk = 0; kkk < subboard.length; kkk++) {
					truth_map.clear();
					for (int lll = 0; lll < subboard[kkk].length; lll++) {
						if (!setValue(subboard[kkk][lll], truth_map)) {
							return false;
						}
					}
				}
				for (int kkk = 0; kkk < 2; kkk++) {
					truth_map.clear();
					for (int lll = 0; lll < subboard.length; lll++) {
						if (!setValue(subboard[lll][kkk], truth_map)){
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	private boolean setValue(char value, BitSet truth_map) {
		switch (value) {
			case '1':
				if (truth_map.get(0)) {
					return false;
				}
				truth_map.set(0);
				break;
			case '2':
				if (truth_map.get(1)) {
					return false;
				}
				truth_map.set(1);
				break;
			case '3':
				if (truth_map.get(2)) {
					return false;
				}
				truth_map.set(2);
				break;
			case '4':
				if (truth_map.get(3)) {
					return false;
				}
				truth_map.set(3);
				break;
			case '5':
				if (truth_map.get(4)) {
					return false;
				}
				truth_map.set(4);
				break;
			case '6':
				if (truth_map.get(5)) {
					return false;
				}
				truth_map.set(5);
				break;
			case '7':
				if (truth_map.get(6)) {
					return false;
				}
				truth_map.set(6);
				break;
			case '8':
				if (truth_map.get(7)) {
					return false;
				}
				truth_map.set(7);
				break;
			default:
				break;
		}
		return true;
	}

	public void solve() {
		if ( solveHelper(0, false) ) {
			write();
		}
		else {
			System.out.println("nicht loesbar :-(");
		}
	}
	
	private void printBoard() {
		System.out.println("+-----+-----+-----+-----+");
		System.out.print("| ");
		for (int iii = 0; iii < this.board.length; iii++) {
			if (iii == this.board.length/2 - 1) {
				System.out.print(this.board[iii]);
				System.out.println(" |");
				System.out.println("+-----+-----+-----+-----+");
				System.out.print("| ");
			}
			else if (iii == this.board.length-1) {
				System.out.print(this.board[iii]);
				System.out.println(" |");
				System.out.println("+-----+-----+-----+-----+");
			}
			else if ((iii+1) % 8 == 0) {
				System.out.print(this.board[iii]);
				System.out.println(" |");
				System.out.print("| ");
			}
			else if (iii % 2 == 0 && iii % 8 != 0) {
				System.out.print("| ");
				System.out.print(this.board[iii]);
				System.out.print(" ");
			}
			else {
				System.out.print(this.board[iii]);
				System.out.print(" ");
			}
		}
	}
	
	private boolean solveHelper(int pos, boolean isOk) {
		if (board[pos] != ' ') {
			if (pos == board.length-1) {
				return true;
			}
			else {
				isOk = solveHelper(pos+1, isOk);
			}
		}
		else {
			board[pos] = '1';
			if ( checkPos(pos, '1') ) {
				if (pos == board.length-1) {
					return true;
				}
				else {
					isOk = solveHelper(pos+1, isOk);
				}
			}
			else {
				board[pos] = ' ';
			}
			
			if (isOk) {
				return true;
			}
			
			board[pos] = '2';
			if ( checkPos(pos, '2') ) {
				if (pos == board.length-1) {
					return true;
				}
				else {
					isOk = solveHelper(pos+1, isOk);
				}
			}
			else {
				board[pos] = ' ';
			}
			
			if (isOk) {
				return true;
			}
			
			board[pos] = '3';
			if ( checkPos(pos, '3') ) {
				if (pos == board.length-1) {
					return true;
				}
				else {
					isOk = solveHelper(pos+1, isOk);
				}
			}
			else {
				board[pos] = ' ';
			}
			
			if (isOk) {
				return true;
			}
			
			board[pos] = '4';
			if ( checkPos(pos, '4') ) {
				if (pos == board.length-1) {
					return true;
				}
				else {
					isOk = solveHelper(pos+1, isOk);
				}
			}
			else {
				board[pos] = ' ';
			}
			
			if (isOk) {
				return true;
			}
			
			board[pos] = '5';
			if ( checkPos(pos, '5') ) {
				if (pos == board.length-1) {
					return true;
				}
				else {
					isOk = solveHelper(pos+1, isOk);
				}
			}
			else {
				board[pos] = ' ';
			}
			
			if (isOk) {
				return true;
			}
			
			board[pos] = '6';
			if ( checkPos(pos, '6') ) {
				if (pos == board.length-1) {
					return true;
				}
				else {
					isOk = solveHelper(pos+1, isOk);
				}
			}
			else {
				board[pos] = ' ';
			}
			
			if (isOk) {
				return true;
			}
			
			board[pos] = '7';
			if ( checkPos(pos, '7') ) {
				if (pos == board.length-1) {
					return true;
				}
				else {
					isOk = solveHelper(pos+1, isOk);
				}
			}
			else {
				board[pos] = ' ';
			}
			
			if (isOk) {
				return true;
			}
			
			board[pos] = '8';
			if ( checkPos(pos, '8') ) {
				if (pos == board.length-1) {
					return true;
				}
				else {
					isOk = solveHelper(pos+1, isOk);
					if (!isOk) {
						board[pos] = ' ';
					}
				}
			}
			else {
				board[pos] = ' ';
			}
			
			if (isOk) {
				return true;
			}
		}
		if (isOk) {
			return true;
		}
		return false;
	}
	
	private boolean checkPos(int pos, char val) {
		// check diagonal
		if (this.v == Oktadoku.Variante.mitDiagonalen) {
			// diagonal
			if (pos % 9 == 0) {
				if ( checkHorz(pos, val) && checkVert(pos, val)
						&& checkDiag(pos, val) && checkSubboard(pos, val) ) {
					return true;
				}
			}
			// anti-diagonal
			else if (pos % 7 == 0) {
				if ( checkHorz(pos, val) &&
						checkVert(pos, val) && checkAnDiag(pos, val)
						&& checkSubboard(pos, val) ) {
					return true;
				}
			}
			else {
				if ( checkHorz(pos, val) && checkVert(pos, val)
						&& checkSubboard(pos, val) ) {
					return true;
				}
			}
		}
		else {
			if ( checkHorz(pos, val) && checkVert(pos, val)
					&& checkSubboard(pos, val) ) {
				return true;
			}
		}
		return false;
	}
	
	private char[][] getSubboard(int xpos, int ypos) {
		int ROWS = 4;
		int COLS = 2;
		char[][] subboard = new char[ROWS][COLS];

		for (int iii = 0; iii < ROWS; iii++) {
			for (int jjj = 0; jjj < COLS; jjj++) {
				subboard[iii][jjj] =
						this.board[(ypos * this.board.length/2  + iii * 8)
				                           + (xpos * COLS + jjj)];
			}
		}
		return subboard;
	}
	
	private boolean checkVert(int pos, char val) {
		int col = pos % 8;
		for (int iii = 0; iii < BOARD_HEIGHT; iii++) {
			if (val == board[col + iii*BOARD_WIDTH]
					&& col + iii*BOARD_WIDTH != pos) {
				return false;
			}
		}
		return true;
	}
	
	private boolean checkHorz(int pos, char val) {
		int row = pos/8;
		for (int iii = 0; iii < BOARD_WIDTH; iii++) {
			if (val == board[row * BOARD_WIDTH + iii]
					&& row * BOARD_WIDTH + iii != pos) {
				return false;
			}
		}
		return true;
	}
	
	private boolean checkDiag(int pos, char val) {
		for (int iii = 0; iii < BOARD_WIDTH; iii++) {
			if (val == board[9*iii] && 9*iii != pos) {
				return false;
			}
		}
		return true;
	}
	
	private boolean checkAnDiag(int pos, char val) {
		for (int iii = 0; iii < BOARD_WIDTH; iii++) {
			if (val == board[56 - 7*iii] && 56 - 7*iii != pos) {
				return false;
			}
		}
		return true;
	}
	
	private boolean checkSubboard(int pos, char val) {
		int x = (pos % this.BOARD_WIDTH) / this.SUBBOARD_WIDTH;
		int y = ((pos / this.BOARD_WIDTH))
					/ this.SUBBOARD_HEIGHT;
		char[][] subboard = this.getSubboard(x,y);
		
		for (int iii = 0; iii < subboard.length; iii++) {
			for (int jjj = 0; jjj < subboard[iii].length; jjj++) {
				if (subboard[iii][jjj] == val
						&& pos !=
							subboard[iii].length * x
							+ board.length/2 * y
							+ this.BOARD_WIDTH * iii + jjj) {
					return false;
				}
			}
		}
		return true;
	}
}
