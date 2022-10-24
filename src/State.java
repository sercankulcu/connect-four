
import java.util.Vector;

class State extends Board {
	
	public int mDepth = 0; // depth of the tree
	public int mAction = -1; // last action
	public int mBeta = 10000;
	public int mAlpha = -10000;
	public Vector<State> mSuccessors = new Vector<State>();
	
	State() {
		
		mAction = -1;
		mDepth = 0;
		initializeBoard();
		mSuccessors = new Vector<State>();
	}
	
	State(Board board) {
		
		mAction = -1;
		mDepth = 0;
		initializeBoard();
		mSuccessors = new Vector<State>();
		
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 7; j++) {
				mLocation[i][j] = board.mLocation[i][j];
			}
		}

		mNextPlayer = board.mNextPlayer;
		
		for(int i = 0; i < 7; i++) {
			mColumns[i] = board.mColumns[i];
		}
		
		mX = board.mX;
		mY = board.mY;
		mWinner = board.mWinner;
	}
}
