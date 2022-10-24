package ConnectFour;

public class MinMaxPlayer {

	private State currentState;
	private int treeDepth = 6;

	/*Calculate score of the board*/
	public int Utility(State s) {

		int score = 0;
		int row = 5 - s.mX;
		int col = s.mY;
		
		int computer = 2;
		int human = 1;
		if (s.isOver()) {
			
			if (s.mWinner == computer)
			{
				score = 1000;
				return score;
			}
			else if (s.mWinner == human)
			{
				score = -1000;
				return score;
			}
			else if (s.mWinner == 0)
			{
				score = 0;
			}
		}

		if ((col >= 2) && (s.mLocation[row][col-1] == computer) && (s.mLocation[row][col-2] == computer)) 
			score += 12;
		//right
		if ((col <= 4) && (s.mLocation[row][col+1] == computer) && (s.mLocation[row][col+2] == computer))
			score += 12;
		//check y direction
		if ((row <= 3) && (s.mLocation[row+1][col] == computer) && (s.mLocation[row+2][col] == computer)) 
			score += 12;
		//check left diagonal
		if ((col >= 2) && (row <= 3) && (s.mLocation[row+1][col-1] == computer) && (s.mLocation[row+2][col-2] == computer))
			score += 12;

		if ((col <= 4) && (row <= 3) && (s.mLocation[row+1][col+1] == computer) && (s.mLocation[row+2][col+2] == computer))
			score += 12;

		if ((col >= 2) && (row >= 2) && (s.mLocation[row-1][col-1] == computer) && (s.mLocation[row-2][col-2] == computer))
			score += 12;

		if ((col <= 4) && (row >= 2) && (s.mLocation[row-1][col+1] == computer) && (s.mLocation[row-2][col+2] == computer))
			score += 12;

		//check x direction.
		//left
		if ((col >= 3) && (s.mLocation[row][col-1] == human) && (s.mLocation[row][col-2] == human) && (s.mLocation[row][col-3] == human))
			score += 16;
		//right
		if ((col <= 3) && (s.mLocation[row][col+1] == human) && (s.mLocation[row][col+2] == human) && (s.mLocation[row][col+3] == human))
			score += 16;
		//check y direction
		if ((row <= 2) && (s.mLocation[row+1][col] == human) && (s.mLocation[row+2][col] == human) && (s.mLocation[row+3][col] == human))
			score += 16;
		//check left diagonal
		if ((col >= 3) && (row <= 2) && (s.mLocation[row+1][col-1] == human) && (s.mLocation[row+2][col-2] == human) && (s.mLocation[row+3][col-3] == human))
			score += 16;

		if ((col <= 3) && (row <= 2) && (s.mLocation[row+1][col+1] == human) && (s.mLocation[row+2][col+2] == human) && (s.mLocation[row+3][col+3] == human))
			score += 16;

		if ((col >= 3) && (row >= 3) && (s.mLocation[row-1][col-1] == human) && (s.mLocation[row-2][col-2] == human) && (s.mLocation[row-3][col-3] == human))
			score += 16;

		if ((col <= 3) && (row >= 3) && (s.mLocation[row-1][col+1] == human) && (s.mLocation[row-2][col+2] == human) && (s.mLocation[row-3][col+3] == human))
			score += 16;

		if ((col >=2 ) && (s.mLocation[row][col-1] == human) && (s.mLocation[row][col-2] == human))
			score += 8;
		//right
		if ((col <= 4) && (s.mLocation[row][col+1] == human) && (s.mLocation[row][col+2] == human))
			score += 8;
		//check y direction
		if ((row <= 3) && (s.mLocation[row+1][col] == human) && (s.mLocation[row+2][col] == human))
			score += 8;
		//check left diagonal
		if ((col >= 2) && (row <= 3) && (s.mLocation[row+1][col-1] == human) && (s.mLocation[row+2][col-2] == human))
			score += 8;

		if ((col <= 4) && (row <= 3) && (s.mLocation[row+1][col+1] == human) && (s.mLocation[row+2][col+2] == human))
			score += 8;

		if ((col >= 2) && (row >= 2) && (s.mLocation[row-1][col-1] == human) && (s.mLocation[row-2][col-2] == human))
			score += 8;

		if ((col <= 4) && (row >= 2) && (s.mLocation[row-1][col+1] == human) && (s.mLocation[row-2][col+2] == human))
			score += 8;

		if(col == 3 && s.mNextPlayer == human) {

			score += 4;
		}
			
		
		if((col == 2 || col == 4) && s.mNextPlayer == human) {

			score += 3;
		}
		
		return score;
	}

	/* Constructor */
	public MinMaxPlayer() {

	}

	/*Make a move*/
	public void play(Board board) {

		currentState = new State(board);
		createSuccessors(currentState);
		
		int max = -10000;
		int move = 0;
		for(int i = 0; i < currentState.mSuccessors.size(); i++) {
			
			State cell = (State) currentState.mSuccessors.get(i);
			System.out.println("action: " + cell.mAction + " alpha: " + cell.mAlpha + " beta: " + cell.mBeta);

				if(cell.mBeta > max) {
					max = cell.mBeta;
					move = cell.mAction;
				}
		}
		
		System.out.println("move: " + move);
		board.Move(move);
	}

	/*create sub tree*/
	
	private int createSuccessors(State s) {
		
		if(s.mDepth == treeDepth || s.isOver()) {
			
			int value = Utility(s);
			return value;
		}
		
		if(s.mDepth % 2 == 0) {
			
			s.mAlpha = -10000;
		}
		else {

			s.mBeta = 10000;
		}
		
		for (int i = 0; i < s.columnCount; i++) {
			
			State temp = new State(s);
			boolean isValidMove = temp.Move(i);
			if (isValidMove == true) { // check whether it is full
			
				temp.mDepth = s.mDepth + 1; // increment depth
				temp.mAction = i;
				temp.mAlpha = s.mAlpha; // take parents current alpha beta values for pruning
				temp.mBeta = s.mBeta;
				s.mSuccessors.add(temp);
					
				int value = createSuccessors(temp);
				
				if(s.mDepth % 2 == 0) {// max player
			
					if(value > s.mAlpha)
						s.mAlpha = value;
					
					if(s.mBeta < s.mAlpha) {
			//			System.out.println("pruned 1");
						return s.mBeta;
					}
					
				}
				else { // min player
					
					if(value < s.mBeta)
						s.mBeta = value;					
									
					if(s.mAlpha > s.mBeta) {
			//			System.out.println("pruned 2");
						return s.mAlpha;
					}
					
				}
			}
		}

		if(s.mDepth % 2 == 1) { 
			
			return s.mBeta;
		}
		else {
			
			return s.mAlpha;
		}
		
	}
}
