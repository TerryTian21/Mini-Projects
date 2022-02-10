package Assignment1;

public class Main {

	public static void main(String[] args) {
		
		LinkedList sample = new LinkedList();
		
		sample.push(1);
		sample.push(2);
		sample.push(3);
		sample.push(4);
		sample.push(5);
		sample.display();
		
		sample.swap(1, 5);
		sample.display();
		
		sample.pop();
		sample.display();
	
		
	}

}
