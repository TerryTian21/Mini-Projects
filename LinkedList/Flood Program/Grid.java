package FloodProgram;

public class Grid {
	
	private Node first;
	
	public Grid(int size) {
		
		first = new Node(generate());
		
		Node rowMarker = first;
		Node columnMarker = first;
		
		for(int i =0; i<size-1; i++) {
			
			Node temp = new Node(generate());
			columnMarker.setRight(temp);
			temp.setLeft(columnMarker);
			columnMarker = temp;
			
		}
		
		for(int i =0; i<size-1;i++) {
			
			Node temp = new Node(generate());
			temp.setUp(rowMarker);
			rowMarker.setDown(temp);
			columnMarker = temp;
			
			for(int x = 0; x<size-1; x++) {
				
				temp = new Node(generate());
				temp.setLeft(columnMarker);
				columnMarker.setRight(temp);
				temp.setUp(columnMarker.getUp().getRight());
				temp.getUp().setDown(temp);
				columnMarker = temp;

			}
			
			rowMarker = rowMarker.getDown();
			
		}
		
	}
	
	public static int generate() {
		
		int num = (int)(Math.random()*(8)+1);
		return num;
		
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
	
	public Node getFirst() {
		return first;
	}

	public void setFirst(Node first) {
		this.first = first;
	}

	public void flood(Node Node, int prevNumber, int newNumber) {
		
		if (Node.getData() != prevNumber) {
			return;
		}
	
		Node.setData(newNumber);

		if(Node.getLeft() != null && Node.getLeft().getData() != Node.getData())
			flood (Node.getLeft(), prevNumber, newNumber);
			
		if(Node.getRight() != null && Node.getRight().getData() != Node.getData())
			flood (Node.getRight(), prevNumber, newNumber);
		
		if(Node.getUp() != null && Node.getUp().getData() != Node.getData())
			flood (Node.getUp(), prevNumber, newNumber);
		
		if(Node.getDown() != null && Node.getDown().getData() != Node.getData())
			flood (Node.getDown(), prevNumber, newNumber);
		
	}
	
	public boolean win() {
		
		Node temp = first;
		Node rowMarker = first;
		
		while(rowMarker != null) {
			
			while(temp!= null){
				
				if(temp.getData() != first.getData())
					return false;
				temp = temp.getRight();
			}
			
			temp = rowMarker.getDown();
			rowMarker = temp;
		}
		
		return true;

		
	}

}
