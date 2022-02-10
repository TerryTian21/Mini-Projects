package Sudoku;

public class Node {

	private int number;
	private boolean potentials [] = {false, true, true, true, true, true, true, true, true, true};
	
	private int boxID;
	
	public Node() {
		
		number = 0;
	}
	//Methods Used
	
	public boolean canBe(int number) {
		
		if(potentials[number] == true)
			return true;
		else
			return false;
	}
	
	public void cantBe(int number) {
		potentials[number] = false;
	}
	
	public int numberOfPotentials() {
		
		int counter = 0;
		
		for(int i =0; i<10; i++)
			if(potentials[i] == true)
				counter++;
		
		return counter;
	}
	
	public int getFirstPotential() {
		
		int num = 0; 
		
		for(int i =0; i<10; i++)
			if(potentials[i] == true) {
				num = i;
				break;
			}

		return num;
	}
	
	public int getSecondPotential() {
		
		int num = 0;
		int counter = 0;
		
		for(int i =0; i <10; i++) {
			
			if(potentials[i] == true) {
				num = i;
				counter++;
			}
		
			if(counter == 2)
				break;
			
		}
		
		return num;
	}
	
	//TODO:
	
	public void eliminate(int number) {
		
		
	}

	//Getters and Setters
	
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
		
		for(int i =0; i<10; i++)
			if(i != number)
				potentials[i] = false;
	}

	public boolean[] getPotentials() {
		return potentials;
	}

	public void setPotentials(boolean[] potentials) {
		this.potentials = potentials;
	}

	public int getBoxID() {
		return boxID;
	}

	public void setBoxID(int boxID) {
		this.boxID = boxID;
	}

	public void setPotential(int number, boolean tf) {
		
		potentials[number] = tf;
		
	}
	
	
	
}
