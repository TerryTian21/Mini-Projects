package FloodProgram;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		int counter = 0;
		
		System.out.println("How large of a grid do you prefer?");
		Grid game = new Grid(input.nextInt());
		
		Node first = game.getFirst();
		
		
		while(game.win() == false) {
			
			game.display();
			System.out.println("Please enter a number (1-8) to flood the grid with.");
			game.flood(first, first.getData(), input.nextInt());
			
			counter++;
		}

		System.out.println("You took " + counter + " turns to win.");
		
	}

}
