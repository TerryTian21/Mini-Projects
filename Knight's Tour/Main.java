package KnightsTour;

public class Main {

	public static void main(String args[]) {
	
		Board game = new Board(8);
		
		Node first = game.getFirst();
		game.solve(first);
	
	}
}
