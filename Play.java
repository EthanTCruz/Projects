package boardFunctions;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
public class Play {





	static boolean valid = true;
	
	
	//arrays for each side to determine whether or not castling is possible
	//index 0 equals columns 1-4
	//index 1 equals columns 5-8
	static boolean[] castleW = new boolean[] {true, true};
	static boolean[] castleB = new boolean[] {true,true};
	
	//castle coords in x,y format

	
	//Defualt promotions
	static String prefPW = "QW";
	static String prefPB = "QB";
	
	
	
	
	
	static int currPieceX;
	static int currPieceY;
	
	static char opp = 'B';
	static char curr = 'W';
	
	static int[] currMoves = new int[0];
	
//	static int[] enPosit = new int[0];

	

	public static String[][] board = new String[][] { { "  ", "1 ", "2 ", "3 ", "4 ", "5 ", "6 ", "7 ", "8 ", "  " },
			{ "1 ", "RW", "NW", "BW", "QW", "KW", "BW", "NW", "RW", "  " },
			{ "2 ", "PW", "PW", "PW", "PW", "QW", "PW", "PW", "PW", "  " },
			{ "3 ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
			{ "4 ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
			{ "5 ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
			{ "6 ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
			{ "7 ", "PB", "PB", "PB", "PB", "QB", "PB", "PB", "PB", "  " },
			{ "8 ", "RB", "NB", "BB", "QB", "KB", "BB", "NB", "RB", "  " },
			{ "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },

	};
	
	static String[][] defBoard = new String[][] { { "  ", "1 ", "2 ", "3 ", "4 ", "5 ", "6 ", "7 ", "8 ", "  " },
		{ "1 ", "RW", "NW", "BW", "QW", "KW", "BW", "NW", "RW", "  " },
		{ "2 ", "PW", "PW", "PW", "PW", "PW", "PW", "PW", "PW", "  " },
		{ "3 ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
		{ "4 ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
		{ "5 ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
		{ "6 ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
		{ "7 ", "PB", "PB", "PB", "PB", "PB", "PB", "PB", "PB", "  " },
		{ "8 ", "RB", "NB", "BB", "QB", "KB", "BB", "NB", "RB", "  " },
		{ "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },

};

	static int[] checkMove = new int[0];

	private static int[] log = new int[0];
	
	
	//Look at me and moveRecursions file
	public static String[][] getBoard() {
		return board;
	}



	public static void resetBoard() {
		board = new String[defBoard.length][];
		for(int i = 0; i < defBoard.length; i++)
		    board[i] = defBoard[i].clone();
		 
		currPieceX = -1;
		 currPieceY = -1;
		
		 opp = 'B';
		curr = 'W';
		
		currMoves = new int[0];
		log = new int[0];
		valid = true;
		
		
	}
	
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

	
	public static int[] enPMoves() {

		int[] enPosit = new int[0];
	
		int inY = log[log.length-3];
		int finX = log[log.length-2];
		int finY = log[log.length-1];
		char Piece = board[finY][finX].charAt(0);
		char side = board[finY][finX].charAt(1);
		String rP;
		if(Piece == 'P') {
			if(side == 'W') {
				if (finY-inY==2) {
					rP = board[finY][finX-1];
					System.out.println(rP);
					if ((rP.charAt(0)=='P')&&(rP.charAt(1)=='B')) {
						enPosit = enlarge(enPosit,finX-1,finY);
						enPosit = enlarge(enPosit,finX,finY-1);
					}
					rP = board[finY][finX+1];
					System.out.println(rP);
					if ((rP.charAt(0)=='P')&&(rP.charAt(1)=='B')) {
						enPosit = enlarge(enPosit,finX+1,finY);
						enPosit = enlarge(enPosit, finX,finY-1);
					}
						
					
				}
			} else if(side == 'B') {
				
			}
				if (inY-finY==2) {
					rP = board[finY][finX-1];
					System.out.println(rP);
					if ((rP.charAt(0)=='P')&&(rP.charAt(1)=='W')) {
						enPosit = enlarge(enPosit,finX-1,finY);
						enPosit = enlarge(enPosit,finX,finY+1);
					}
					rP = board[finY][finX+1];
					System.out.println(rP);
					if ((rP.charAt(0)=='P')&&(rP.charAt(1)=='W')) {
						enPosit = enlarge(enPosit,finX+1,finY);
						enPosit = enlarge(enPosit,finX,finY+1);
					}
				
			}
		}


		return enPosit;
	}
	
	public static boolean checkEn(int x1, int y1, int x2, int y2) {
		//xy1 vals represent init move
		//xy2 vals represent enP move to be done
		boolean en = false;
		int[] valids = enPMoves();
		//enP returns list of valid init positions to execute 
		int i = valids.length;
		
		for (int n = 0; n<i; n=n+4) {
		    if ((x1 == valids[n])&&(y1 == valids[n+1])&&(x2==valids[n+2])&&(y2==valids[n+3])) {
		    	
		        if( perfEnP(x1,y1,x2,y2) == true) {
		        return true;
		        }
		    }
		}
		
		return en;
	}
	
	public static boolean perfEnP(int x1, int y1, int x2, int y2) {
		boolean accomp = true;
		String init = board[y1][x1];
		String finMove = board[y2][x2];
		String erP = board[y2-1][x2];
		
		if (curr=='W') {
			board[y2][x2] = init;
			board[y1][x1] = "  ";
			board[y2-1][x2] = "  ";
			

		} else {
			board[y2][x2] = init;
			board[y1][x1] = "  ";
			board[y2+1][x2] = "  ";
		}
		
		if (!kingCheck()==false) {
			board[y2-1][x2] = erP;
			board[y2][x2] = finMove;
			board[y1][x1] = init;
			accomp = false;
			return accomp;
		}
		
		changeTurn();
		return accomp;
	}

	public static boolean[] checkCastle () {
		

//have this function retrun own int[] to not mess with castle array
		boolean[] castTemp = new boolean[] {false,false};
		//Array format {left,right}
		if ((curr == 'W')&&((castleW[0]==true)||(castleW[1]==true)) || (curr == 'B')&&((castleB[0]==true)||(castleB[1]==true))) {
			castTemp = new boolean[] {true,true};

			int y;
			if (curr == 'W') {
			 y = 1;

			} else {
			 y = 8;

			}
			changeTurn();
			int[] all = allPosMoves(curr);
			changeTurn();
			
			int i = all.length;

			for (int v = 0; v < i; v = v + 2) {
				if ((all[v] == 5) && (all[v + 1] == y)) {
					
					castTemp[1] = false;
					castTemp[0] = false;
					
				}
					//right side castle eval
					if ((all[v] == 6) && (all[v + 1] == y)||(board[y][6]!="  ")) {
						castTemp[1] = false;
					}
						if ((all[v] == 7) && (all[v + 1] == y)||(board[y][7]!="  ")) {
							castTemp[1] = false;
						}
							if ((all[v] == 8) && (all[v + 1] == y)) {
								castTemp[1] = false;
							}
								
								
						
									
					if ((all[v] == 4) && (all[v + 1] == y)||(board[y][4]!="  ")) {
						castTemp[0] = false;
					}
						if ((all[v] == 3) && (all[v + 1] == y)||(board[y][3]!="  ")) {
							castTemp[0] = false;
						}
							if ((all[v] == 2) && (all[v + 1] == y)||(board[y][2]!="  ")) {
								castTemp[0] = false;
							}
								if ((all[v] == 1) && (all[v + 1] == y)) {
									castTemp[0] = false;
								}
							
								
								
						
					
			
					
				
				
			}

			
		} 
		//add reversion to normal and store updated in temp for return

		if(curr == 'W') {
			if((castleW[0]==true)&&(castTemp[0]==true)) {
				castTemp[0] = true;
			} else {
				castTemp[0] = false;
			}
			if((castleW[1]==true)&&(castTemp[1]==true)) {
				castTemp[1] = true;
			} else {
				castTemp[1] = false;
			}
		} else {
			if((castleB[0]==true)&&(castTemp[0]==true)) {
				castTemp[0] = true;
			} else {
				castTemp[0] = false;
			}
			if((castleB[1]==true)&&(castTemp[1]==true)) {
				castTemp[1] = true;
			} else {
				castTemp[1] = false;
			}
		}
		
		return castTemp;
	}
	
	public static int[] showMoves(int x1, int y1) {
		char Piece = board[y1][x1].charAt(0);
		char side = board[y1][x1].charAt(1);
		currPieceX = x1;
		currPieceY = y1;

		checkMove = new int[0];
		


		// coding for W
		if (side == curr) {
		if (Piece == 'P') {

			if (getCurr()=='W') {

				if ((board[y1][0] == defBoard[2][0]) && (board[y1 + 1][x1] == "  ")) {
					addMove(x1, y1 + 1);
					if ((board[y1][0] == board[2][0]) && (board[y1 + 2][x1] == "  ")) {
						addMove(x1, y1 + 2);

					}
				}
				if (board[y1 + 1][x1] == "  ") {
					addMove(x1, y1 + 1);
				}
				if (board[y1 + 1][x1 + 1].charAt(1) == opp) {
					addMove(x1 + 1, y1 + 1);
				}
				if (board[y1 + 1][x1 - 1].charAt(1) == opp) {
					addMove(x1 - 1, y1 + 1);
				}

//				if ((board[y1 + 1][x1 + 1] == "  ")&&(enX == x1+1)&&(enY == y1)) {
//					
//				}
			} else {
				if ((board[y1][0] == defBoard[7][0]) && (board[y1 - 1][x1] == "  ")) {
					addMove(x1, y1 - 1);
					if ((board[y1][0] == defBoard[7][0]) && (board[y1 - 2][x1] == "  ")) {
						addMove(x1, y1 - 2);

					}
				}
				if (board[y1 - 1][x1] == "  ") {
					addMove(x1, y1 - 1);
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

	public static void diagonal(int x, int y) {
		diagonalUR(x, y);
		diagonalUL(x, y);
		diagonalDR(x, y);
		diagonalDL(x, y);
	}

	public static void diagonalDL(int x, int y) {
		if ((y > 1) && (x > 1)) {
			if (board[y - 1][x - 1] == "  ") {
				addMove(x-1, y - 1);
				diagonalDL(x - 1, y - 1);
			} else if (board[y - 1][x - 1].charAt(1) == opp) {
				addMove(x - 1, y - 1);
			}
		}
	}

	public static void diagonalDR(int x, int y) {
		if ((y > 1) && (x < 8)) {
			if (board[y - 1][x + 1] == "  ") {
				addMove(x + 1, y - 1);
				diagonalDR(x + 1, y - 1);
			} else if (board[y - 1][x + 1].charAt(1) == opp) {
				addMove(x + 1, y - 1);
			}
		}
	}

	public static void diagonalUL(int x, int y) {
		if ((y < 8) && (x > 1)) {
			if (board[y + 1][x - 1] == "  ") {
				addMove(x - 1, y + 1);
				diagonalUL(x - 1, y + 1);
			} else if (board[y + 1][x - 1].charAt(1) == opp) {
				addMove(x - 1,y + 1 );
			}
		}
	}

	public static void diagonalUR(int x, int y) {
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
	


	public static boolean perfCastle(int x1, int y1, int x2, int y2) {
		boolean performed = false;
		int y;
		if(curr == 'W') {
			y = 1;
		} else {
			y = 8;
		}
		if(((y1 == 1)&&(curr == 'W')&&(y2==1))||((y1 == 8)&&(curr == 'B')&&(y2==8))) {
			boolean[] checks = checkCastle();
			if(x1 == 5) {
			if((x2 == 7)) {
				if (checks[1]) {
					String blank = "  ";
					board[y][7] = board[y][5];
					board[y][6] = board[y][8];
					board[y][5] = blank;
					board[y][8] = blank;
					performed = true;
					if (curr == 'W') {
						castleW[1] = false;
						castleW[0] = false;
					} else {
						castleB[1] = false;
						castleB[0] = false;
					}
				}
				
			} else if(x2 == 3) {
				if (checks[0]) {
					String blank = "  ";
					board[y][3] = board[y][5];
					board[y][4] = board[y][1];
					board[y][5] = blank;
					board[y][1] = blank;
					performed = true;
					if (curr == 'W') {
						castleW[1] = false;
						castleW[0] = false;
					} else {
						castleB[1] = false;
						castleB[0] = false;
					}
					
				}
			}
			}
		}
		
		
		
		
		return performed;
	}
	
	
	public static boolean promote(int x, int y) {
		boolean promoted = false;
		if (curr=='W') {
			if(y == 8) {
				if(board[x][y] == "PW") {
					board[x][y] = prefPW;
					promoted = true;
				}
			}
		} else {
			if(y == 1) {
				if(board[y][x] == "PB") {
					board[y][x] = prefPB;
					promoted = true;
				}
			}
		}
		
		return promoted;
	}
	
	public static void move(int x1, int y1, int x2, int y2) {

		
		
		if (check(x1, y1, x2, y2) == true) {
			String temp;
			 temp = board[y2][x2];
			board[y2][x2] = board[y1][x1];
			board[y1][x1] = "  ";
			
			//move goes through if king is put in check otherwise board reverts to normal
		if(kingCheck()==false) {
			moveLog(x1, y1);
			moveLog(x2, y2);
			promote(x2,y2);
			if (curr=='W') {

				if((x1 == 8)&&(y1==1)) {
					castleW[1] = false;
				} else if((x1 == 1)&&(y1==1)) {
					castleW[0] = false;
				} else if((x1 == 5)&&(y1 == 1)) {
					castleW[0] = false;
					castleW[1] = false;
				}
			} else {
				if((x1 == 8)&&(y1==8)) {
					castleB[1] = false;
				} else if((x1 == 1)&&(y1==8)) {
					castleB[0] = false;
				} else if((x1 == 5)&&(y1 == 8)) {
					castleB[0] = false;
					castleB[1] = false;
				}
			}
			changeTurn();
			if(kingFind(curr)[0] == 0) {
				System.out.print(opp + " wins!!!");
			}
		} else {
			board[y1][x1] = board[y2][x2];
			board[y2][x2] = temp;
			valid = false;
			System.out.println("invalid move: " + x1+y1+x2+y2 );
		}
		} else if(perfCastle(x1,y1,x2,y2) == true) {
			
			changeTurn();
			
		} else if(checkEn(x1,y1,x2,y2)){
			if(kingCheck()==false) {
				moveLog(x1, y1);
				moveLog(x2, y2);
//				changeTurn();
			}
				if(kingFind(curr)[0] == 0) {
					System.out.print(opp + " wins!!!");
				}
		}else{
			valid = false;
			System.out.println("invalid move: " + x1+y1+x2+y2 );
		}
		
		noteBoard();

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
		int[] temp = new int[getLog().length + 2];
		int i = getLog().length + 2;

		for (int n = 0; n < i - 2;) {
			temp[n] = getLog()[n];
			n++;
		}
		setLog(temp);
		getLog()[i - 2] = x;
		getLog()[i - 1] = y;
		return getLog();
	}

	
	
	public static int[] enlarge(int[] currArray, int x, int y) {
		int[] temp = new int[currArray.length + 2];
		int i = currArray.length + 2;

		for (int n = 0; n < i - 2;) {
			temp[n] = currArray[n];
			n++;
		}
		currArray = temp;
		currArray[i - 2] = x;
		currArray[i - 1] = y;
		return currArray;
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

	public static int[] getLog() {
		return log;
	}

	public static void setLog(int[] log) {
		Play.log = log;
	}
	
	public static String writeBoard() {
		String tempBoard = new String();
		
		tempBoard = Arrays.deepToString(defBoard);
		
		return tempBoard;
	}
	
	public static void noteBoard() {
		try {
		      File myObj = new File("Rboard.txt");
		      if (myObj.createNewFile()) {
		        System.out.println("File created: " + myObj.getName());
		      } else {

		      }
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		try {
		      FileWriter myWriter = new FileWriter("filename.txt");
		      myWriter.write(writeBoard());
		      myWriter.close();
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
	public static void main(String[] args) {
		resetBoard();

		move(5,2,5,4);
		move(5,7,5,5);
		move(7,1,6,3);
		move(2,8,3,6);
		move(6,1,3,4);
		move(7,8,6,6);
		move(4,2,4,4);
		move(6,8,3,5);
		move(2,1,3,3);
		move(4,7,4,6);
		move(3,1,7,5);
		move(3,8,7,4);
		move(4,1,4,2);
		move(4,8,4,7);
		move(5,1,3,1);
		move(5,8,3,8);
	
	
//		move(5,1,7,1);
//		showBoard();
//		move(5,8,7,8);

		

		
		showBoard();

		
		System.out.println(Arrays.toString(castleW));
		System.out.println(Arrays.toString(castleB));
		System.out.println(Arrays.toString(checkCastle()));
		changeTurn();
		System.out.println(Arrays.toString(checkCastle()));
		changeTurn();
//		System.out.println(perfCastle(5,1,7,1));
//		System.out.println(checkEn(2,5,3,6));
		showBoard();
		System.out.println(Arrays.toString(allPosMoves(curr)));

		System.out.println(Arrays.toString(allPosMoves(curr)));


		

				


//		noteBoard();
//		startGame();




	}



	





}
