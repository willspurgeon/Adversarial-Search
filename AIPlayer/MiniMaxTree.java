package AIPlayer;

import java.util.ArrayList;

public class MiniMaxTree {
	Board gameBoard;
	boolean thisIsMax;
	int playerNum;

	
	public PlayerMove moveForThisTree = null;
	
	ArrayList<MiniMaxTree> children;
	
	
	public MiniMaxTree(Board board, boolean rootIsMax, int playerNum){

		this.gameBoard = board;
		this.thisIsMax = rootIsMax;
		this.playerNum = playerNum;
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
		if(depth == 1){
			return getUtilityOfRootBoard();
		}
		
		if(thisIsMax){
			for(MiniMaxTree child: children){
				int thisChildsNum = child.performMiniMaxSearch(depth - 1,alpha, beta);
				if(thisChildsNum > alpha){
					alpha = thisChildsNum;
					moveForThisTree = child.moveForThisTree;
				}
				if(alpha >= beta)break;
			}
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
	}

	public void buildTreeToDepth(int i) {
		if (i < 1) {
			return;
		}

		ArrayList<Board> boardarr = new ArrayList<Board>();

		for (int j = 0; j < gameBoard.width; j++) {
			// Build all possible boards for drop.

			Board temp = Board.getBoardCopy(gameBoard);

			temp.dropADiscFromTop(j, thisIsMax ? playerNum : ((playerNum==1) ? 2: 1));
			boardarr.add(temp);
		}

		for (int j = 0; j < gameBoard.width; j++) {
			// Build all possible boards for pop.

			Board temp = new Board(gameBoard.height, gameBoard.width, gameBoard.getN());
			if (temp.canRemoveADiscFromBottom(j,  thisIsMax ? playerNum : ((playerNum==1) ? 2: 1))) {
				temp.removeADiscFromBottom(j);
				boardarr.add(temp);
			}
		}

		for (MiniMaxTree child: children) {
			child.buildTreeToDepth(i - 1);
		}
	}

}
