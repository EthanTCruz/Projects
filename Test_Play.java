package testStack;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
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
		Play.move(5,7,5,8);
		Play.showBoard();

	}
	
	@Test
	public void testGame() {
		Play.startGame();
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





}
