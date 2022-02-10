package Assignment1;

public class Node {

	private Node prev;
	private Node next;
	private int data;
	
	public Node(int data) {
		this.data = data;
	}
	public Node() {
		data = -1;
		prev = null;
		next = null;
	}
	
	public Node getPrev() {
		return prev;
	}
	public void setPrev(Node prev) {
		this.prev = prev;
	}
	public Node getNext() {
		return next;
	}
	public void setNext(Node next) {
		this.next = next;
	}
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	
	
	
}
