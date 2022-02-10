package Assignment1;

public class LinkedList {

	private Node last;
	private Node first;
	private int size;
	
	public LinkedList() {
		first = null;
		last = null;
		size = 0;
	}

	public void push(int data) {
		
		Node temp = new Node(data);
		
		if(first == null)
			first = temp;
		else
			last.setNext(temp);
		temp.setPrev(last);
		last = temp;

		size++;
	}
	
	public void display() {
		
		if(first != null) {
			
			Node temp = first;
			
			while(temp != null) {
				System.out.print(temp.getData() + "\t");
				temp = temp.getNext();
			}
			
			System.out.println();
			
			temp = last;
			while(temp != null) {
				System.out.print(temp.getData() + "\t");
				temp = temp.getPrev();
			}
			
			System.out.println();
			System.out.println("The size is: " + size);
		}
		
		else
			System.out.println("The list empty");
			
	}
	
	public void pop() {
		
		if(size >= 2) {
			last = last.getPrev();
			last.setNext(null);
			size--;
		}
		if(size == 1) {
			first = null;
			last = null;
			size--;
		}
	}
	
	public void pop(int index) {
		
		if(index < 0 || index > size)
			System.out.println("Choice of index is out of data range.");
		else {
			if(index == 1) {
				first = first.getNext();
				first.setPrev(null);
			}
			else if(index == size) {
				last = last.getPrev();
				last.setNext(null);
			}
			else {
				Node temp = first;
				for(int i =1; i<index; i++)
					 temp = temp.getNext();
				
				temp.getPrev().setNext(temp.getNext());
				temp.getNext().setPrev(temp.getPrev());	
			}
			
			size--;
		}
	}
	
	//This should return the reference to where the node is located
	public Node find(int data) {
		
		Node temp = first;
		
		while(temp != null) {
			if(temp.getData() == data)
				break;
			temp = temp.getNext();
		}
		
		if(temp == null)
			return null;
		else
			return temp;
		
	}
	
	public void swap (int num1, int num2) {
		
		Node tempLeft = new Node();
		Node tempRight = new Node();
		Node temp = first;
		
		tempLeft = find(num1);
		tempRight = find (num2);
		
		for(int i =0; i<size; i++) {
			
			if(temp == tempLeft)
				break;
			else if(temp == tempRight) {
				tempRight = tempLeft;
				tempLeft = temp;
				break;
			}
			
			temp = temp.getNext();
		}
		
		//Nodes are side by side
		
		if(tempLeft.getNext() == tempRight) {
			
			if(size > 2) {
				if(tempLeft == first) {
					
					System.out.println("\n"+ tempLeft.getData() + " is the first node...");
					//Forwards
					first = tempRight;
					tempLeft.setNext(tempRight.getNext());
					tempRight.setNext(tempLeft);
					//Backwards
					tempLeft.getNext().setPrev(tempLeft);
					tempLeft.setPrev(tempRight);
					tempRight.setPrev(null);
				}
				
				else if(tempRight == last) {
					
					System.out.println("\n" + tempRight.getData() + " is the last node...");
					//Forwards
					tempLeft.getPrev().setNext(tempRight);
					tempRight.setNext(tempLeft);
					tempLeft.setNext(null);
					//Backwards
					last = tempLeft;
					tempRight.setPrev(tempLeft.getPrev());
					tempLeft.setPrev(tempRight);
					
				}
				
				else {
				
					//forward
					tempLeft.getPrev().setNext(tempRight);
					tempLeft.setNext(tempRight.getNext());
					tempRight.setNext(tempLeft);
					//backward 
					tempLeft.getNext().setPrev(tempLeft);
					tempRight.setPrev(tempLeft.getPrev());
					tempLeft.setPrev(tempRight);
				
				}
			}
			else if (size == 2 && tempLeft == first) {
				
				//Forwards
				first = tempRight;
				tempLeft.setNext(null);
				tempRight.setNext(tempLeft);
				
				//Backwards
				last = tempLeft;
				tempRight.setPrev(null);
				tempLeft.setPrev(tempRight);
			}
		}
		
		//When the 2 nodes are 1 apart 
		
		else if(tempLeft.getNext().getNext() == tempRight) {
			
			System.out.println("\n" + tempLeft.getData() + " and " + tempRight.getData() + " are one apart...");
			temp = tempRight.getNext();
			
			if(size > 3) {
				if(tempLeft == first) {
					
					//Forwards
					temp = tempRight.getNext();
					first = tempRight;
					tempRight.setNext(tempLeft.getNext());
					tempLeft.getNext().setNext(tempLeft);
					tempLeft.setNext(temp);
					
					//Backwards
					temp.setPrev(tempLeft);
					tempLeft.setPrev(tempRight.getPrev());
					tempRight.getPrev().setPrev(tempRight);
					tempRight.setPrev(null);
					
				}
				
				else if(tempRight == last) {
					
					//Forwards
					temp = tempLeft.getPrev();
					temp.setNext(tempRight);
					tempRight.setNext(tempLeft.getNext());
					tempLeft.getNext().setNext(tempLeft);
					tempLeft.setNext(null);
					
					//Backwards
					last = tempLeft;
					tempLeft.setPrev(tempRight.getPrev());
					tempRight.getPrev().setPrev(tempRight);
					tempRight.setPrev(temp);
				}
				
				else {
					
					//Forwards
					tempLeft.getPrev().setNext(tempRight);
					tempRight.setNext(tempLeft.getNext());
					tempLeft.getNext().setNext(tempLeft);
					tempLeft.setNext(temp);
					
					//Backwards
					temp = tempLeft.getPrev();
					tempLeft.getNext().setPrev(tempLeft);;
					tempLeft.setPrev(tempRight.getPrev());
					tempRight.getPrev().setPrev(tempRight);
					tempRight.setPrev(temp);
					
				}
			}
			
			else if(size == 3 && tempLeft == first) {
				
				//Forwards
				first = tempRight;
				tempRight.setNext(tempLeft.getNext());
				tempLeft.getNext().setNext(tempLeft);
				tempLeft.setNext(null);
				
				//Backwards
				last = tempLeft;
				tempLeft.setPrev(tempRight.getPrev());
				tempRight.getPrev().setPrev(tempRight);
				tempRight.setPrev(null);
				
			}
		}
		
		//If separations > 2
		else {
			
			temp = tempRight.getNext();
			
			if(tempLeft == first && tempRight != last) {
				
				//Forwards
				first = tempRight;
				tempRight.setNext(tempLeft.getNext());
				tempRight.getPrev().setNext(tempLeft);
				tempLeft.setNext(temp);
				
				//Backwards
				temp.setPrev(tempLeft);
				tempLeft.setPrev(tempRight.getPrev());
				tempRight.getNext().setPrev(tempRight);
				tempRight.setPrev(null);
				
			}
			else if(tempRight == last && tempLeft != first) {
				
				//Backwards
				temp = tempLeft.getPrev();
				last = tempLeft;
				tempLeft.setPrev(tempRight.getPrev());
				tempLeft.getNext().setPrev(tempRight);
				tempRight.setPrev(temp);
				
				//Forwards
				temp.setNext(tempRight);
				tempRight.setNext(tempLeft.getNext());
				tempLeft.getPrev().setNext(tempLeft);
				tempLeft.setNext(null);
				
			}
			else if(tempLeft == first && tempRight == last) {
				
				//Forwards
				first = tempRight;
				last = tempLeft;
				first.setNext(last.getNext());
				first.getPrev().setNext(last);
				last.setNext(null);
				
				//Backwards
				last.setPrev(first.getPrev());
				first.getNext().setPrev(first);
				first.setPrev(null);
				
			}
			else {
				
				//Forwards
				temp = tempRight.getNext();
				tempLeft.getPrev().setNext(tempRight);
				tempRight.setNext(tempLeft.getNext());
				tempRight.getPrev().setNext(tempLeft);
				tempLeft.setNext(temp);
				
				//Backwards
				temp.setPrev(tempLeft);
				temp = tempLeft.getPrev();
				tempLeft.setPrev(tempRight.getPrev());
				tempRight.getNext().setPrev(tempRight);
				tempRight.setPrev(temp);
			}
			
		}
		
	}
}
