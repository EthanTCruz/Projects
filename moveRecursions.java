package boardFunctions;



public class moveRecursions {
//where to store algorithims for recursive motions
	// recursive functions for diagonal movements
	
	
	String[][] Board = Play.getBoard();
	
//	
//	public static void diagonal(int x, int y) {
//		diagonalUR(x, y);
//		diagonalUL(x, y);
//		diagonalDR(x, y);
//		diagonalDL(x, y);
//	}
//
//	public static void diagonalDL(int x, int y) {
//		if ((y > 1) && (x > 1)) {
//			if (board[y - 1][x - 1] == "  ") {
//				addMove(x-1, y - 1);
//				diagonalDL(x - 1, y - 1);
//			} else if (board[y - 1][x - 1].charAt(1) == opp) {
//				addMove(x - 1, y - 1);
//			}
//		}
//	}
//
//	public static void diagonalDR(int x, int y) {
//		if ((y > 1) && (x < 8)) {
//			if (board[y - 1][x + 1] == "  ") {
//				addMove(x + 1, y - 1);
//				diagonalDR(x + 1, y - 1);
//			} else if (board[y - 1][x + 1].charAt(1) == opp) {
//				addMove(x + 1, y - 1);
//			}
//		}
//	}
//
//	public static void diagonalUL(int x, int y) {
//		if ((y < 8) && (x > 1)) {
//			if (board[y + 1][x - 1] == "  ") {
//				addMove(x - 1, y + 1);
//				diagonalUL(x - 1, y + 1);
//			} else if (board[y + 1][x - 1].charAt(1) == opp) {
//				addMove(x - 1,y + 1 );
//			}
//		}
//	}
//
//	public static void diagonalUR(int x, int y) {
//		if ((y < 8) && (x < 8)) {
//			if (board[y + 1][x + 1] == "  ") {
//				addMove(x + 1, y + 1);
//				diagonalUR(x + 1,y + 1 );
//			} else if (board[y + 1][x + 1].charAt(1) == opp) {
//				addMove(x + 1, y + 1);
//			}
//		}
//	}
//	
//	// recursive functions for straight movements
//	
//	public static void straight(int x, int y) {
//		straightRecFw(x, y);
//		straightRecBw(x, y);
//		straightRecR(x, y);
//		straightRecL(x, y);
//	}
//
//	public static void straightRecFw(int x, int y) {
//
//		if (y < 8) {
//			if (board[y + 1][x] == "  ") {
//				addMove(x, y + 1);
//				straightRecFw(x, y + 1);
//			} else if (board[y + 1][x].charAt(1) == opp) {
//				addMove(x, y + 1);
//			}
//		}
//
//	}
//
//	public static void straightRecBw(int x, int y) {
//
//		if (y > 1) {
//			if (board[y - 1][x] == "  ") {
//				addMove(x, y - 1);
//				straightRecBw(x, y - 1);
//			} else if (board[y - 1][x].charAt(1) == opp) {
//				addMove(x, y - 1);
//			}
//		}
//
//	}
//
//	public static void straightRecR(int x, int y) {
//
//		if (x < 8) {
//			if (board[y][x + 1] == "  ") {
//				addMove(x + 1, y);
//				straightRecR(x + 1, y);
//			} else if (board[y][x + 1].charAt(1) == opp) {
//				addMove(x + 1, y);
//			}
//		}
//
//	}
//
//	public static void straightRecL(int x, int y) {
//
//		if (x > 1) {
//			if (board[y][x - 1] == "  ") {
//				addMove(x - 1, y);
//				straightRecL(x - 1, y);
//			} else if (board[y][x - 1].charAt(1) == opp) {
//				addMove(x - 1, y);
//			}
//		}
//
//	}
}
