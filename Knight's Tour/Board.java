package KnightsTour;

public class Board {
	
	private Node first;
	private int counter = 1;
	
	public Node getFirst() {
		return first;
	}

	public void setFirst(Node first) {
		this.first = first;
	}

	public Board(int size) {
		
		first = new Node();
		
		Node rowMarker = first;
		Node columnMarker = first;
		
		for(int i =0; i<size-1; i++) {
			
			Node temp = new Node();
			columnMarker.setRight(temp);
			temp.setLeft(columnMarker);
			columnMarker = temp;
		}
		
		for(int i =0; i<size-1;i++) {
			
			Node temp = new Node();
			temp.setUp(rowMarker);
			rowMarker.setDown(temp);
			columnMarker = temp;
			
			for(int x = 0; x<size-1; x++) {
				
				temp = new Node();
				temp.setLeft(columnMarker);
				columnMarker.setRight(temp);
				temp.setUp(columnMarker.getUp().getRight());
				temp.getUp().setDown(temp);
				columnMarker = temp;

			}
			
			rowMarker = rowMarker.getDown();
			
		}
		
	}
	
	public void solve(Node Node){
		
		//Check if solved, needs editing.
		
		Node.setData(counter);
		
		if(counter == 64) {
			display();
			System.out.println();
		}
		
		//Path 1
		if(Node.getLeft() != null && Node.getLeft().getLeft() != null && Node.getLeft().getLeft().getUp() != null && Node.getLeft().getLeft().getUp().getData() == 0) {
			counter++;
			solve(Node.getLeft().getLeft().getUp());
		}
		//Path 2
		if(Node.getUp() != null && Node.getUp().getUp() != null && Node.getUp().getUp().getLeft() != null && Node.getUp().getUp().getLeft().getData() == 0) {
			counter++;
			solve(Node.getUp().getUp().getLeft());
		}
		
		//Path 3
		if(Node.getUp() != null && Node.getUp().getUp() != null && Node.getUp().getUp().getRight() != null && Node.getUp().getUp().getRight().getData() == 0) {
			counter++;
			solve(Node.getUp().getUp().getRight());
		}
		
		//Path 4
		if(Node.getRight() != null && Node.getRight().getRight() != null && Node.getRight().getRight().getUp() != null && Node.getRight().getRight().getUp().getData() == 0) {
			counter++;
			solve(Node.getRight().getRight().getUp());
		}
		
		//Path 5
		if(Node.getRight() != null && Node.getRight().getRight() != null && Node.getRight().getRight().getDown() != null && Node.getRight().getRight().getDown().getData() == 0) {
			counter++;
			solve(Node.getRight().getRight().getDown());
		}
		
		//Path 6
		if(Node.getDown() != null && Node.getDown().getDown() != null && Node.getDown().getDown().getRight() != null && Node.getDown().getDown().getRight().getData() == 0) {
			counter++;
			solve(Node.getDown().getDown().getRight());
		}
		
		//Path 7
		if(Node.getDown() != null && Node.getDown().getDown() != null && Node.getDown().getDown().getLeft() != null && Node.getDown().getDown().getLeft().getData() == 0) {
			counter++;
			solve(Node.getDown().getDown().getLeft());
		}
		
		//Path 8
		if(Node.getLeft() != null && Node.getLeft().getLeft() != null && Node.getLeft().getLeft().getDown() != null && Node.getLeft().getLeft().getDown().getData() == 0) {
			counter++;
			solve(Node.getLeft().getLeft().getDown());
		}
		
		Node.setData(0);
		counter--;
		
	}
	
	public void display() {
		
		Node temp = first;
		Node rowMarker = first;
		
		while(rowMarker != null) {
			
			while(temp!= null){
				
				System.out.print(temp.getData() + "\t");
				temp = temp.getRight();
			}
			
			temp = rowMarker.getDown();
			rowMarker = temp;
			System.out.println();
			
		}
		
	}

}
