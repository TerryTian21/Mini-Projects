package KnightsTour;

public class Node {
	
	private Node left;
	private Node right;
	private Node up;
	private Node down;
	private int data;


	public Node() {
		
		data = 0;
		left = null;
		right = null;
		up = null;
		down = null;
	}


	public Node getLeft() {
		return left;
	}


	public void setLeft(Node left) {
		this.left = left;
	}


	public Node getRight() {
		return right;
	}


	public void setRight(Node right) {
		this.right = right;
	}


	public Node getUp() {
		return up;
	}


	public void setUp(Node up) {
		this.up = up;
	}


	public Node getDown() {
		return down;
	}


	public void setDown(Node down) {
		this.down = down;
	}


	public int getData() {
		return data;
	}


	public void setData(int data) {
		this.data = data;
	}



}
