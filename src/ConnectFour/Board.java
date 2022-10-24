package ConnectFour;
/*This class keeps the status of the board*/

public class Board {
	
	public int columnCount = 7;
	public int rowCount = 6;
	public int human = 1;
	public int computer = 2;
	public int empty = 0;

	public int[][] mLocation; // keeps location empty, red, green
	public int mNextPlayer; // keeps next player
	public int[] mColumns; // keeps the highest available location for each column 
	public int mX = 0; // keeps last X coordinate
	public int mY = 0; // keeps last Y coordinate
	public int mWinner = 0; // keeps winner red or green

	/* Constructor */
	public Board() {

		mNextPlayer = human; // human
		mLocation = new int[6][7];
		mColumns = new int[7];
		mWinner = empty;
		initializeBoard();
	}

	/*Make a move given column number*/
	public boolean Move(int pos) {
		
		if ((pos < 0) || (pos >= columnCount)) {
			
			System.out.println("invalid column \n");
			return false;
		}
		else {
			
			if (mColumns[pos] == columnCount - 1) {
				
				//System.out.println("Column full \n");
				return false;
			}
			else { // valid input
				
				mY = pos;
				mX = 5 - mColumns[pos];
				mColumns[pos]++;
				mLocation[mX][mY] = mNextPlayer;
				mNextPlayer = 3 - mNextPlayer; // turn
			}
		}
		return true;
	}
	
	/*Returns cell's info (empty, red, green)*/
	public int getCell(int row, int column)
    {
            if (row < 0 || row > 5 || column < 0 || column > 6) 
            	return -10; // invalid value
            
            return mLocation[row][column];
    }

	/*Initialize the board*/
	public void initializeBoard() {
		
		for(int i = 0; i < 6; i++)
		{
			for(int j = 0; j < 7; j++)
			{
				mLocation[i][j] = empty;
			}
		}
		for (int j = 0 ; j < 7; j++) {
		
			mColumns[j] = empty;      
		}
	}

	public boolean isOver() {
		
        boolean isFull = true;
        
        for (int i = 0; i < 6; i++) {
        	for (int j = 0; j < 7; j++) {
        		if (mLocation[i][j] == empty) {  
        			isFull = false;
        		}
        	}
        }

        if (isFull == true)
        {
        	return true; // game over
        }
        
        for (int i = 0; i < 6; i++) {
        	for (int j = 0; j < 4; j++) {
        		if(mLocation[i][j] == human && mLocation[i][j+1] == human && mLocation[i][j+2] == human && mLocation[i][j+3] == human) {
        			mWinner = human;
        			return true;
        		}
        	}
        }
        
        for (int i = 0; i < 7; i++) {
        	for (int j = 0; j < 3; j++) {
        		if(mLocation[j][i] == human && mLocation[j+1][i] == human && mLocation[j+2][i] == human && mLocation[j+3][i] == human) {
        			mWinner = human;
        			return true;
        		}
        	}
        }
        
        for (int i = 0; i < 3; i++) {
        	for (int j = 0; j < 4; j++) {
        		if(mLocation[i][j] == human && mLocation[i+1][j+1] == human && mLocation[i+2][j+2] == human && mLocation[i+3][j+3] == human) {
        			mWinner = human;
        			return true;
        		}
        	}
        }
        
        for (int i = 5; i > 2; i--) {
        	for (int j = 0; j < 4; j++) {
        		if(mLocation[i][j] == human && mLocation[i-1][j+1] == human && mLocation[i-2][j+2] == human && mLocation[i-3][j+3] == human) {
        			mWinner = human;
        			return true;
        		}
        	}
        }
        
        for (int i = 0; i < 6; i++) {
        	for (int j = 0; j < 4; j++) {
        		if(mLocation[i][j] == computer && mLocation[i][j+1] == computer && mLocation[i][j+2] == computer && mLocation[i][j+3] == computer) {
        			mWinner = computer;
        			return true;
        		}
        	}
        }
        
        for (int i = 0; i < 7; i++) {
        	for (int j = 0; j < 3; j++) {
        		if(mLocation[j][i] == computer && mLocation[j+1][i] == computer && mLocation[j+2][i] == computer && mLocation[j+3][i] == computer) {
        			mWinner = computer;
        			return true;
        		}
        	}
        }
        
        for (int i = 0; i < 3; i++) {
        	for (int j = 0; j < 4; j++) {
        		if(mLocation[i][j] == computer && mLocation[i+1][j+1] == computer && mLocation[i+2][j+2] == computer && mLocation[i+3][j+3] == computer) {
        			mWinner = computer;
        			return true;
        		}
        	}
        }
        
        for (int i = 5; i > 2; i--) {
        	for (int j = 0; j < 4; j++) {
        		if(mLocation[i][j] == computer && mLocation[i-1][j+1] == computer && mLocation[i-2][j+2] == computer && mLocation[i-3][j+3] == computer) {
        			mWinner = computer;
        			return true;
        		}
        	}
        }

        return false;
	}
}
