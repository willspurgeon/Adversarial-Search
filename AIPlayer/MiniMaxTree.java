package AIPlayer;

import java.util.ArrayList;

public class MiniMaxTree {
	Board gameBoard;
	boolean thisIsMax;

	
	public PlayerMove moveForThisTree = null;
	
	ArrayList<MiniMaxTree> children;
	
	
	public MiniMaxTree(Board board, boolean rootIsMax){

		this.gameBoard = board;
		this.thisIsMax = rootIsMax;
	}

	/**
	 * 
	 * @return The value of the MiniMaxTree
	 */
	public int getUtilityOfRootBoard() {
		// TODO: Implement heuristic here.
		return 0;
	}

	
	public int performMiniMaxSearch(int depth, int alpha, int beta){
		if(thisIsMax){
			for(MiniMaxTree child: children){
				int thisChildsNum = child.performMiniMaxSearch(depth - 1,alpha, beta);
				if(thisChildsNum > alpha){
					alpha = thisChildsNum;
					moveForThisTree = child.moveForThisTree;
				}
				if(alpha >= beta)break;
			}
		} else {
			result = Integer.MAX_VALUE;

			for (MiniMaxTree child : children) {
				int thisChildsNum = child.performMiniMaxSearch();
				if (thisChildsNum < result) {
					result = thisChildsNum;
			return alpha;
		}else{
			
			for(MiniMaxTree child: children){
				int thisChildsNum = child.performMiniMaxSearch(depth - 1, alpha, beta);
				if(thisChildsNum < beta){
					beta = thisChildsNum;
					moveForThisTree = child.moveForThisTree;

				}
				if(alpha >= beta)break;
			}
			return beta;
		}

		return result;

	}

	public void buildTreeToDepth(int i) {
		if (i < 1) {
			return;
		}
		int playerturn = -1;

		ArrayList<Board> Boardarr = new ArrayList<Board>();

		for (int j = 0; j < gameBoard.width; j++) {
			playerturn++;
			// Build all possible boards for drop.

			Board Temp = new Board(gameBoard.height, gameBoard.width, gameBoard.getN());

			Temp.dropADiscFromTop(j, playerturn % 2);

			Boardarr.add(Temp);
		}

		for (int j = 0; j < gameBoard.width; j++) {
			// Build all possible boards for pop.

			playerturn++;

			Board Temp = new Board(gameBoard.height, gameBoard.width, gameBoard.getN());
			if (Temp.canRemoveADiscFromBottom(j, playerturn % 2)) {
				Temp.removeADiscFromBottom(j);
				Boardarr.add(Temp);
			}

		}

		for (int j = 0; j < gameBoard.width; j++) {
			buildTreeToDepth(i - 1);
		}



	}

}
