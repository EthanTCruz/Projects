package testStack;

import java.util.Arrays;


import org.junit.Test;
import boardFunctions.Play;

public class Test_Play {


	@Test
	public void testMate() {
		
		Play.showBoard();
		Play.move(5,2,5,7);
		Play.showBoard();
		System.out.println(Play.kingCheck());
		Play.move(1,7,1,5);
		Play.showBoard();
		System.out.println(Arrays.toString(Play.getLog()) + Play.gameOver());
		Play.move(5,7,5,8);
		System.out.println(Arrays.toString(Play.getLog()) + Play.gameOver());
		Play.showBoard();
		
	}
	
	@Test
	public void testGame() {
	//	Play.startGame();
	}
	
	@Test
	public void QTest() {
		Play.showBoard();
		Play.setCurr('W');
		int [] moves = Play.allPosMoves(Play.getCurr());
		System.out.println(Arrays.toString(moves) + moves.length);
		int [] QW = Play.showMoves(5,2);
		System.out.println(Arrays.toString(QW) + QW.length);
		Play.dispMoves(QW);
		Play.move(5,2,6,3);
		Play.showBoard();
		Play.dispMoves(QW);
	}
	
	@Test
	public void PTest() {
	Play.showBoard();
	Play.move(8,2,8,4);
	Play.showBoard();
	Play.move(5,7,5,2);
	Play.showBoard();
	Play.move(8,4,8,5);
	Play.showBoard();
	
	}
	
	@Test
	public void resetTest() {
		Play.showBoard();
		Play.resetBoard();
		Play.showBoard();
		
	}





}
