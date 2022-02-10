package Sudoku;

import java.util.Scanner;

public class Main {
	
	public static void main (String args[]) throws Exception {
		
		Scanner input = new Scanner(System.in);
		
		Board game = new Board();
		
		System.out.println("What difficulty do you wish? (easy/medium/hard/oni)");
		game.loadPuzzle(input.next());
		
		game.display();
		System.out.println();
		game.logicCycles();
		game.display();
	}

}
