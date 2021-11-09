package boardFunctions;
import java.util.*;
public class Play {

	public class showBoard {

	}



	static boolean valid = true;
	
	
	static int currPieceX;
	static int currPieceY;
	
	static char opp = 'B';
	private static char curr = 'W';
	
	static int[] currMoves = new int[0];
	
	

	static String[][] board = new String[][] { { "  ", "1 ", "2 ", "3 ", "4 ", "5 ", "6 ", "7 ", "8 ", "  " },
			{ "1 ", "RW", "NW", "BW", "QW", "KW", "BW", "NW", "RW", "  " },
			{ "2 ", "PW", "PW", "PW", "PW", "QW", "PW", "PW", "PW", "  " },
			{ "3 ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
			{ "4 ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
			{ "5 ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
			{ "6 ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
			{ "7 ", "PB", "PB", "PB", "PB", "PB", "PB", "PB", "PB", "  " },
			{ "8 ", "RB", "NB", "BB", "QB", "KB", "BB", "NB", "RB", "  " },
			{ "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },

	};

	static int[] checkMove = new int[0];

	static int[] log = new int[0];

	public static void showBoard() {
		
		System.out.println("           Current player is: " + getCurr());
		// Loop through all rows
		System.out.print("   __________________________________");
		System.out.println();
		for (int i = board.length - 1; i >= 0; i--) {
			System.out.print("  |  ");
			// Loop through all elements of current row
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.print("  |  ");
			System.out.println();

		}

		System.out.println("  |__________________________________|");
		System.out.println();
			
		if(kingFind(getCurr())[0] == 0) {
			System.out.print(opp + " wins!!!");
		}
		
	}

	// change add move to show places on board
	private static void addMove(int x1, int y1) {
		
	

		 if (!kingCheck()) {
				int[] temp = new int[] {x1,y1};
				checkMove = addArr(checkMove, temp);


				} else
		if (kingCheck()) {
		String Piece = board[currPieceY][currPieceX];
		board[currPieceY][currPieceX] = board[y1][x1];
		board[y1][x1] = Piece;
		if(!kingCheck()) {
			int[] temp = new int[] {x1,y1};
			checkMove = addArr(checkMove, temp);

		}

		board[y1][x1] = board[currPieceY][currPieceX];
		board[currPieceY][currPieceX] = Piece;
		}


		 
	}

	public static boolean check(int x1, int y1, int x2, int y2) {
		

		int i = showMoves(x1, y1).length;
		boolean check = false;

		int[] posMoves = new int[i];
		for (int n = 0; n < i;) {
			posMoves[n] = checkMove[n];
			n++;
		}

		for (int v = 0; v < i;) {

			if ((x2 == posMoves[v]) && (y2 == posMoves[v + 1])) {
				check = true;
				v = v + posMoves.length;
			}
			v = v + 2;
		}

		return check;

	}

	public static int[] showMoves(int x1, int y1) {
		char Piece = board[y1][x1].charAt(0);
		char side = board[y1][x1].charAt(1);
		currPieceX = x1;
		currPieceY = y1;
		
		checkMove = new int[0];


		// coding for W
		if (side == getCurr()) {
		if (Piece == 'P') {

			if (getCurr()=='W') {

				if ((board[y1][0] == board[2][0]) && (board[y1 + 1][x1] == "  ")) {
					addMove(x1, y1 + 1);
					if ((board[y1][0] == board[2][0]) && (board[y1 + 2][x1] == "  ")) {
						addMove(x1, y1 + 2);

					}
				}
				if (board[y1 + 1][x1 + 1].charAt(1) == opp) {
					addMove(x1 + 1, y1 + 1);
				}
				if (board[y1 + 1][x1 - 1].charAt(1) == opp) {
					addMove(x1 - 1, y1 + 1);
				}
			} else {
				if ((board[y1][0] == board[7][0]) && (board[y1 - 1][x1] == "  ")) {
					addMove(x1, y1 - 1);
					if ((board[y1][0] == board[7][0]) && (board[y1 - 2][x1] == "  ")) {
						addMove(x1, y1 - 2);

					}
				}
				if (board[y1 - 1][x1 + 1].charAt(1) == opp) {
					addMove(x1 + 1, y1 - 1);
				}
				if (board[y1 - 1][x1 - 1].charAt(1) == opp) {
					addMove(x1 - 1, y1 - 1);
				}

			}

		} else

		if (Piece == 'N') {
			if ((x1 < 8) && (y1 < 7)) {
				if ((board[y1 + 2][x1 + 1].charAt(1) == opp) || (board[y1 + 2][x1 + 1].charAt(1) == ' ')) {
					addMove(x1 + 1, y1 + 2);
				}
			}

			if ((x1 > 1) && (y1 < 7)) {
				if ((board[y1 + 2][x1 - 1].charAt(1) == opp) || (board[y1 + 2][x1 - 1].charAt(1) == ' ')) {
					addMove(x1 - 1, y1 + 2);
				}
			}

			if ((x1 < 8) && (y1 > 2)) {
				if ((board[y1 - 2][x1 + 1].charAt(1) == opp) || (board[y1 - 2][x1 + 1].charAt(1) == ' ')) {
					addMove(x1 + 1, y1 - 2);
				}
			}

			if ((x1 > 1) && (y1 > 2)) {
				if ((board[y1 - 2][x1 - 1].charAt(1) == opp) || (board[y1 - 2][x1 - 1].charAt(1) == ' ')) {
					addMove(x1 - 1, y1 - 2);
				}
			}

			if ((x1 < 7) && (y1 < 8)) {
				if ((board[y1 + 1][x1 + 2].charAt(1) == opp) || (board[y1 + 1][x1 + 2].charAt(1) == ' ')) {
					addMove(x1 + 2, y1 + 1);
				}
			}

			if ((x1 < 7) && (y1 > 1)) {
				if ((board[y1 - 1][x1 + 2].charAt(1) == opp) || (board[y1 - 1][x1 + 2].charAt(1) == ' ')) {
					addMove(x1 + 2, y1 - 1);
				}
			}

			if ((x1 > 2) && (y1 < 8)) {
				if ((board[y1 + 1][x1 - 2].charAt(1) == opp) || (board[y1 + 1][x1 - 2].charAt(1) == ' ')) {
					addMove(x1 - 2, y1 + 1);
				}
			}

			if ((x1 > 2) && (y1 > 1)) {
				if ((board[y1 - 1][x1 - 2].charAt(1) == opp) || (board[y1 - 1][x1 - 2].charAt(1) == ' ')) {
					addMove(x1 - 2, y1 - 1);
				}
			}

		} else if (Piece == 'Q') {
			straight(x1, y1);
			diagonal(x1, y1);

		} else if (Piece == 'B') {
			diagonal(x1, y1);

		} else if (Piece == 'R') {
			straight(x1, y1);
		} else if (Piece == 'K') {
			
			if(y1>1) {
			if ((board[y1-1][x1].charAt(1)== opp)|| (board[y1-1][x1].charAt(1)== ' ')) {
				addMove(x1,y1-1);	
			}}
			
			if(y1<8) {
			if ((board[y1+1][x1].charAt(1)== opp)|| (board[y1+1][x1].charAt(1)== ' ')) {
				addMove(x1,y1+1);	
			}}
			
			if((y1>1)&&(x1>1)) {
			if ((board[y1-1][x1-1].charAt(1)== opp)|| (board[y1-1][x1-1].charAt(1)== ' ')) {
				addMove(x1-1,y1-1);	
			}}
			
			if((y1>1)&&(x1<8)) {
			if ((board[y1-1][x1+1].charAt(1)== opp)|| (board[y1-1][x1+1].charAt(1)== ' ')) {
				addMove(x1+1,y1-1);	
			}}
			
			if((y1<8)&&(x1<8)) {
			if ((board[y1+1][x1+1].charAt(1)== opp)|| (board[y1+1][x1+1].charAt(1)== ' ')) {
				addMove(x1+1,y1+1);	
			}}
			
			if((y1<8)&&(x1>1)) {
			if ((board[y1+1][x1-1].charAt(1)== opp)|| (board[y1+1][x1-1].charAt(1)== ' ')) {
				addMove(x1,y1-1);	
			}}
			
			if(x1>1) {
			if ((board[y1][x1-1].charAt(1)== opp)|| (board[y1][x1-1].charAt(1)== ' ')) {
				addMove(x1-1,y1);	
			}}
			
			if(x1<8) {
			if ((board[y1][x1+1].charAt(1)== opp)|| (board[y1][x1+1].charAt(1)== ' ')) {
				addMove(x1+1,y1);	
			}}
			
			
		}
		}

		return checkMove;
		

	}

	
	
	// recursive functions for diagonal movements
	public static void diagonal(int x, int y) {
		diagonalUR(x, y);
		diagonalUL(x, y);
		diagonalDR(x, y);
		diagonalDL(x, y);
	}

	private static void diagonalDL(int x, int y) {
		if ((y > 1) && (x > 1)) {
			if (board[y - 1][x - 1] == "  ") {
				addMove(x-1, y - 1);
				diagonalDL(x - 1, y - 1);
			} else if (board[y - 1][x - 1].charAt(1) == opp) {
				addMove(x - 1, y - 1);
			}
		}
	}

	private static void diagonalDR(int x, int y) {
		if ((y > 1) && (x < 8)) {
			if (board[y - 1][x + 1] == "  ") {
				addMove(x + 1, y - 1);
				diagonalDR(x + 1, y - 1);
			} else if (board[y - 1][x + 1].charAt(1) == opp) {
				addMove(x + 1, y - 1);
			}
		}
	}

	private static void diagonalUL(int x, int y) {
		if ((y < 8) && (x > 1)) {
			if (board[y + 1][x - 1] == "  ") {
				addMove(x - 1, y + 1);
				diagonalUL(x - 1, y + 1);
			} else if (board[y + 1][x - 1].charAt(1) == opp) {
				addMove(x - 1,y + 1 );
			}
		}
	}

	private static void diagonalUR(int x, int y) {
		if ((y < 8) && (x < 8)) {
			if (board[y + 1][x + 1] == "  ") {
				addMove(x + 1, y + 1);
				diagonalUR(x + 1,y + 1 );
			} else if (board[y + 1][x + 1].charAt(1) == opp) {
				addMove(x + 1, y + 1);
			}
		}
	}
	
	// recursive functions for straight movements
	
	public static void straight(int x, int y) {
		straightRecFw(x, y);
		straightRecBw(x, y);
		straightRecR(x, y);
		straightRecL(x, y);
	}

	public static void straightRecFw(int x, int y) {

		if (y < 8) {
			if (board[y + 1][x] == "  ") {
				addMove(x, y + 1);
				straightRecFw(x, y + 1);
			} else if (board[y + 1][x].charAt(1) == opp) {
				addMove(x, y + 1);
			}
		}

	}

	public static void straightRecBw(int x, int y) {

		if (y > 1) {
			if (board[y - 1][x] == "  ") {
				addMove(x, y - 1);
				straightRecBw(x, y - 1);
			} else if (board[y - 1][x].charAt(1) == opp) {
				addMove(x, y - 1);
			}
		}

	}

	public static void straightRecR(int x, int y) {

		if (x < 8) {
			if (board[y][x + 1] == "  ") {
				addMove(x + 1, y);
				straightRecR(x + 1, y);
			} else if (board[y][x + 1].charAt(1) == opp) {
				addMove(x + 1, y);
			}
		}

	}

	public static void straightRecL(int x, int y) {

		if (x > 1) {
			if (board[y][x - 1] == "  ") {
				addMove(x - 1, y);
				straightRecL(x - 1, y);
			} else if (board[y][x - 1].charAt(1) == opp) {
				addMove(x - 1, y);
			}
		}

	}

	

	
	public static void move(int x1, int y1, int x2, int y2) {

		if (check(x1, y1, x2, y2) == true) {
			String temp;
			 temp = board[y2][x2];
			board[y2][x2] = board[y1][x1];
			board[y1][x1] = "  ";

		if(kingCheck()==false) {
			moveLog(x1, y1);
			moveLog(x2, y2);
			changeTurn();
		} else {
			board[y1][x1] = board[y2][x2];
			board[y2][x2] = temp;
			valid = false;
			System.out.println("invalid move");
		}
		} else {
			valid = false;
			System.out.println("invalid move");
		}

	}
	
	public static void dispMoves(int [] posMoves) {

		String [][] copyOfBoard = new String[board.length][];
		for(int j = 0; j < board.length; j++)
		    copyOfBoard[j] = board[j].clone();
		
		int i = posMoves.length;
		for (int v = 0; v < i;) {
			copyOfBoard[posMoves[v+1]][posMoves[v]] = "~~";
			v = v + 2;
		}

		System.out.println("           Current player is: " + getCurr());
		// Loop through all rows
		System.out.print("   __________________________________");
		System.out.println();
		for ( i = copyOfBoard.length - 1; i >= 0; i--) {
			System.out.print("  |  ");
			// Loop through all elements of current row
			for (int j = 0; j < copyOfBoard[i].length; j++) {
				System.out.print(copyOfBoard[i][j] + " ");
			}
			System.out.print("  |  ");
			System.out.println();

		}

		System.out.println("  |__________________________________|");
		System.out.println();
		
		
	}

	public static String showPiece(int x, int y) {
		return board[y][x];
	}

	public static int[] moveLog(int x, int y) {
		int[] temp = new int[log.length + 2];
		int i = log.length + 2;

		for (int n = 0; n < i - 2;) {
			temp[n] = log[n];
			n++;
		}
		log = temp;
		log[i - 2] = x;
		log[i - 1] = y;
		return log;
	}

	public static void enlarge(int[] currArray, int x, int y) {
		int[] temp = new int[currArray.length + 2];
		int i = currArray.length + 2;

		for (int n = 0; n < i - 2;) {
			temp[n] = currArray[n];
			n++;
		}
		currArray = temp;
		currArray[i - 2] = x;
		currArray[i - 1] = y;
	}

	public static int[] addArr(int[] a, int[] b) {
		int[] c = new int[a.length + b.length];
		int aSize = a.length;
		int bSize = b.length;
		for (int n = 0; n < aSize;) {
			c[n] = a[n];
			n++;
		}
		int i = 0;
		for (int m = aSize; m < aSize + bSize;) {
			c[m] = b[i];
			m++;
			i++;
		}
		return c;
	}

	public static int[] kingFind(char team) {
		String[] wKing = new String[1];
		wKing[0] = "KW";
		String[] bKing = new String[1];
		bKing[0] = "KB";
		int[] location = new int[2];
		String[] Piece = new String[1];
		if(team == 'W') {
		Piece[0] = wKing[0];
		} else {
		Piece[0] = bKing[0];
		}
		// Loop through all rows
		for (int i = board.length - 1; i >= 0; i--) {

			// Loop through all elements of current row
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == Piece[0]) {
					location[0] = j;
					location[1] = i;
				}
			}
		}
		return location;
	}

	public static int[] allPosMoves(char team) {
		int[] moves = new int[0];
		
		for (int j = board.length - 1; j >= 1; j--) {
			// Loop through all elements of current row
			for (int i = 0; i < board[j].length; i++) {

				if (board[j][i].charAt(1) == team) {

					moves = addArr(moves, showMoves(i, j));

				}
			}

		}

		return moves;
	}



	public static boolean kingCheck() {
		currMoves = Arrays.copyOf(checkMove, checkMove.length);
		int[] all = allPosMoves(opp);
		int i = all.length;
		boolean checked = false;
		int[] loc = kingFind(getCurr());
		for (int v = 0; v < i; v = v + 2) {
			if ((all[v] == loc[1]) && (all[v + 1] == loc[0])) {
				checked = true;
			}
		}
		checkMove = Arrays.copyOf(currMoves, currMoves.length);
		return checked;
	}
	

	public static void changeTurn() {
		if (getCurr() == 'W') {
			setCurr('B');
			
			opp = 'W';
		} else {
			setCurr('W');
			opp = 'B';
		}
		valid = true;
	}
	
	public static boolean gameOver() {
		boolean status = false;
		changeTurn();
			if(kingFind(getCurr())[0] == 0) {
				status = true;
				System.out.print(opp + " wins!!!");
			}
		
		changeTurn();
		return status;
		
	}
	
	public static void startGame() {
		showBoard();
	    Scanner myObj = new Scanner(System.in);  // Create a Scanner object


	while (gameOver()==false) {

	    System.out.println("Please input moves in folling format of \"x1,y1,x2,y2\"");
	    String move = myObj.nextLine();  
	    String[] tokens = move.split(",");
	    int x1 = Integer.parseInt(tokens[0]); 
	    int y1 = Integer.parseInt(tokens[1]); 
	    int x2 = Integer.parseInt(tokens[2]); 
	    int y2 = Integer.parseInt(tokens[3]); 
	    System.out.println("moves inputted: "+"moves("+x1+y1+x2+y2+")");
	    move(x1,y1,x2,y2);
	    //form at answer "()"
	    //parse move into integers for move()
	    if (valid==true) {
	    showBoard();
	    } else {
	    	System.out.println("invalid move");
	    }

	} 

		System.out.print(opp + " wins!!!");
	
	myObj.close();
	}

	public static char getCurr() {
		return curr;
	}

	public static void setCurr(char team) {
		curr = team;
	}


	public static void main(String[] args) {

		
		showBoard();
		move(5,2,5,7);
		showBoard();
		System.out.println(kingCheck());
		move(1,7,1,5);

		showBoard();
		move(5,7,5,8);
		showBoard();

//		startGame();
	}






}
