package Sudoku;

import java.io.File;
import java.util.Scanner;

public class Board {

	private Node [][] board = new Node [9][9];
	private String level = "";
	private Board[] savedBoard = new Board[81];
	private int numberOfGuesses = 0;
	
	public Board() {
		
		for(int x =0; x<9; x++)
			for(int y = 0; y <9; y++) {
				
				board[x][y] = new Node();
				board[x][y].setBoxID( 3*(x/3) + y/3+1);
			}
	}
	
	public void loadPuzzle(String level) throws Exception{
		
		this.level = level;
		
		String fileName = "easy.txt";
		if(level.contentEquals("medium"))
			fileName = "medium.txt";
		else if(level.contentEquals("hard"))
			fileName = "hard.txt";
		else if(level.contentEquals("test"))
			fileName = "test.txt";
		else if(level.contentEquals("oni"))
			fileName = "oni.txt";
	
		Scanner input = new Scanner(new File(fileName));
	
		
		for(int x=0; x<9; x++)
			for(int y = 0; y<9; y++) {
				
				int number = input.nextInt();
				if(number != 0)
					solve(x,y,number);
			}
		
		input.close();
		
	}
	
	public boolean isSolved() {
		
		boolean solved = true;
		
		for(int x=0; x<9; x++)
			for(int y = 0; y<9; y++)
				if(board[x][y].getNumber() == 0)
					return false;
		
		return solved;
		
	}
	
	/* Display Box ID
	public void displayBoxID() {
		
		for(int x=0; x<9; x++) {
			
			for(int y = 0; y<9; y++)
				System.out.print(board[x][y].getBoxID() + " ");
		
			System.out.println();
			
		}
			
	}
	*/
	
	public void display() {
		
		int counterx = 0;
		int countery = 0;
		
		for(int x =0; x <9; x++) {
		
			if(countery == 3) {
				System.out.println();
				System.out.print("-----------------------------");
				countery = 0;
			}
			
			counterx=0;
			countery++;
			System.out.println();
			
			for(int y =0; y<9; y++) {	
				
				if(counterx == 3) {
					System.out.print("| ");
					counterx = 0;
				}
						
				if(board[x][y].getNumber() == 10)
					System.out.print(board[x][y].getNumber() + " ");
				else
					System.out.print(board[x][y].getNumber() + "  ");
				
				counterx++;
			}
			
		}
	}
		
	
	public void solve(int x, int y, int number) {
		
		board[x][y].setNumber(number);
		
		for(int i =0; i<9; i++)
			for(int j =0; j<9; j++) {
				if((i == x && j != y) || (i != x && j == y))
					board[i][j].cantBe(number);
				else if(board[i][j].getBoxID() == board[x][y].getBoxID() && i != x && j!=y)
					board[i][j].cantBe(number);
			} 
		
	}
	public void logicCycles() throws Exception{
		
		/* Testing if logic 4 works
		logic4();
		System.out.println();
		display();
		*/
		
		//Testing Boxes
		/*for(int i =0; i <9;i++)
			for(int j=0; j<9;j++)
				if(board[i][j].getBoxID() == 1)
					System.out.println(board[i][j].canBe(8));
		 */
		
		//Testing Rows
		//for(int i =0; i<9;i++)
			//System.out.println(board[0][i].canBe(8));
		
		
		//Testing Columns
		//for(int i =0; i<9;i++)
			//System.out.println(board[i][0].canBe(8));
		
		
		/* This shows the number of potentials per cell
		System.out.println();
		for(int i =0; i<9; i++) {
			for(int j =0; j<9; j++)
				System.out.print(board[i][j].numberOfPotentials() + " ");
			System.out.println();
		}
		*/
	
		//Actual Logic Cycle Algorithm
		
		while(isSolved() == false) {
			
			int changesMade = 0;
			
			do {
				
				changesMade = 0;
				changesMade += logic1();
				changesMade += logic2();
				changesMade += logic3();
				changesMade += logic4();
				
				
				if(errorFound()) {	
					revert();
					logicCycles();			
				} 
					
				if(changesMade == 0)
					guess();
					
				/*This displays the board after each logic cycle
				display();
				System.out.println();
				*/
				
			}while(changesMade != 0);
			
		}
		
	}

	
	public int logic1() {
		
		int changesMade = 0;
		
		for(int x=0; x<9; x++)
			for(int y = 0; y<9; y++)
				if(board[x][y].getNumber() == 0 && board[x][y].numberOfPotentials() == 1) {
					solve(x,y,board[x][y].getFirstPotential());
					changesMade++;
				} 
				
		return changesMade;
	}
	
	public int logic2() {
		
		int changesMade = 0;
		
		//Rows
		
		boolean checker = true;
		
		for(int x =0; x<9; x++)
			for(int y=0; y <9; y++)
				
				if(board[x][y].getNumber() == 0 && board[x][y].numberOfPotentials() > 1)
					
					for(int i = 1; i<10; i++) {
						
						if(board[x][y].canBe(i)) {
							
							for(int j=0; j<9; j++) {
								if(j==y)
									continue;
								else if(board[x][j].canBe(i)) {
									checker = false;
									break;
								}		
							}
							
							if(checker == true) {
								solve(x,y,i);
								changesMade++;
							}
						
						}
					}	
		
		//Columns
		
		checker = true;
		
		for(int x =0; x<9; x++)
			for(int y=0; y <9; y++)
				
				if(board[y][x].getNumber() == 0 && board[y][x].numberOfPotentials() > 1)
					
					for(int i = 1; i<10; i++) {
						
						if(board[y][x].canBe(i)) {
							
							for(int j=0; j<9; j++) {
								if(j==y)
									continue;
								else if(board[j][x].canBe(i)) {
									checker = false;
									break;
								}		
							}
							
							if(checker == true) {
								solve(y,x,i);
								changesMade++;
							}
						}
						
					}	

	
		return changesMade;
	}
	
	public int logic3() {
		
		boolean checker = true;
		int changesMade = 0;
		
		//BoxID
		for(int x =0; x<9; x++)
			for(int y=0; y <9; y++)
				
				if(board[x][y].getNumber() == 0 && board[x][y].numberOfPotentials() > 1)
					
					for(int i = 1; i<10; i++) {
						
						if(board[x][y].canBe(i)) {
							
							checker = searchBox(x,y,i);
							
							if(checker == true) {
								solve(x,y,i);
								changesMade++;
							}
				
						}
						
					}
			
		return changesMade;
	}
	
	public boolean searchBox(int i, int j, int num) {
		
		boolean checker = true;
		
		for(int x = 0; x<9; x++) 
			for(int y =0; y<9; y++) 
				if(x != i || y !=j)
					if(board[x][y].getBoxID() == board[i][j].getBoxID())
				
						if(board[x][y].canBe(num)) {
							checker = false;
							break;
						}
		
		return checker;
		
	}
	
	public int logic4() {
		
		int changesMade = 0;
		int tempCounter = 0;
		
		for(int i =0; i<9; i++)
			for(int j =0; j <9; j++)
				for(int x =0; x<9; x++)
					for(int y = 0; y<9; y++)
						if(x != i || y != j) 
							if(board[i][j].numberOfPotentials() ==2 && board[x][y].numberOfPotentials() == 2){
								
								tempCounter = 0;
								
								for(int k = 0; k<10; k++)
									if(board[i][j].canBe(k) &&  board[x][y].canBe(k))
										tempCounter++;
								
								if(tempCounter == 2) {
									
									//This is for rows
									if(i == x) {
										
										for(int k =0; k<9; k++)
											if(j != k && y != k) {
												board[i][k].cantBe(board[i][j].getFirstPotential());
												board[i][k].cantBe(board[i][j].getSecondPotential());
										}
										
									}
									//This is for Columns
									if(j == y) {

										for(int k =0; k<9; k++)
											if(x != k && i != k) {
												board[k][j].cantBe(board[i][j].getFirstPotential());
												board[k][j].cantBe(board[i][j].getSecondPotential());
										}
										

									}
									//This is for Boxes
									if(board[i][j].getBoxID() == board[x][y].getBoxID()) {
		
										for(int row =0; row <9; row++)
											for(int col= 0; col<9; col++) {
												
												if(board[row][col].getBoxID() == board[i][j].getBoxID() && (row != x || col != y) && (row !=i || col != j)) {
													
													board[row][col].cantBe(board[i][j].getFirstPotential());
													board[row][col].cantBe(board[i][j].getSecondPotential());
													
												}
											}
									}								
								}	
							}
		
		return changesMade;
	
	}

	public void setNumber(int x, int y, int number) {
		board[x][y].setNumber(number);
		
	}
	
	public void setPotential(int x, int y, int number, boolean tf) {
		board[x][y].setPotential(number, tf);
		
	}
	
	public void guess() throws Exception {
		
		Board copy = new Board();
		
		for(int i =0; i<9; i++)
			for(int j =0; j<9; j++) {
				copy.setNumber(i, j, board[i][j].getNumber());
				for(int pot =0; pot<10; pot++) {
					copy.setPotential(i, j, pot, board[i][j].canBe(pot));
				}
			}
		
		savedBoard[numberOfGuesses++] = copy;
		
		outerloop:
		for(int i =0; i <9; i++)
			for(int j =0; j <9; j++)
				if(board[i][j].getNumber() == 0) {
					solve(i,j,board[i][j].getFirstPotential());
					break outerloop;
				}
		
		//logicCycles();
	
	}
	
	public void revert() {
			
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				board[i][j].setNumber(savedBoard[numberOfGuesses-1].board[i][j].getNumber());
				for(int pot = 1; pot < 10; pot++)
					board[i][j].setPotential(pot, savedBoard[numberOfGuesses-1].board[i][j].canBe(pot));							}
		}
		
		outerloop:
		for(int i =0; i <9; i++)
			for(int j =0; j <9; j++)
				if(board[i][j].getNumber() == 0) {
					board[i][j].cantBe(board[i][j].getFirstPotential());
					break outerloop;
				}
		
		numberOfGuesses--;
	} 
	
	
	
	public boolean errorFound() {
		
		for(int x = 0; x < 9; x++) {
			for(int y = 0; y < 9; y++) {
				if(board[x][y].getNumber() == 0 && board[x][y].numberOfPotentials()==0) {
					return true;
					
				}
			}
		}
		
		return false;
	}
}


