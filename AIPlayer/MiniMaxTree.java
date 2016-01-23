package AIPlayer;

import java.util.ArrayList;

import AIPlayer.PlayerMove.TypeOfMove;
import Player.TestPlayer;

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
		//i width
		//j height
		
		int myValue = 0;
		int opponentValue = 0;
		
		
		for(int i = 0; i < gameBoard.width; i++){
			for(int j = 0; j < gameBoard.height; j++){
				if(gameBoard.board[j][i] != 9) break;
				
				if(i != gameBoard.width){
					//Check right
				}
				if(i != 0){
					//Check left.
					int rowPlayerValue = 10;
					int inARow = 0;
					for(int y = i-1; y<=0; y--){
						if(y==i-1){
							rowPlayerValue = gameBoard.board[j][y];
						}else{
							if(gameBoard.board[j][y] == rowPlayerValue){
								inARow++;
							}else{
								if(playerNum == rowPlayerValue){
									myValue = myValue + TestPlayer.weights.get(inARow);
								}else{
									opponentValue = opponentValue + TestPlayer.weights.get(inARow);;
								}
								break;
							}
						}
					}
				}
				
				if(j != 0){
					//Check bottom.
				}
				
				if(j != gameBoard.height && i != gameBoard.width){
					//Check Top right.
				}
				if(i != gameBoard.width && j != 0){
					//Check Bottom right
				}
				if(i != 0 && j != gameBoard.height){
					//Check top left.
				}
				
				if(j != 0 && i != 0){
					//Check bottom left.
				}
				
				
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		return 0;
	}

	
	public int performMiniMaxSearch(int depth, int alpha, int beta, boolean calledFromRoot){
		if(depth == 1){
			return getUtilityOfRootBoard();
		}
		
		if(thisIsMax){
			for(MiniMaxTree child: children){
				int thisChildsNum = child.performMiniMaxSearch(depth - 1,alpha, beta, false);
				if(thisChildsNum > alpha){
					alpha = thisChildsNum;
					if(calledFromRoot)moveForThisTree = child.moveForThisTree;
				}
				if(alpha >= beta)break;
			}
			return alpha;
		}else{
			
			for(MiniMaxTree child: children){
				int thisChildsNum = child.performMiniMaxSearch(depth - 1, alpha, beta, false);
				if(thisChildsNum < beta){
					beta = thisChildsNum;
					if(calledFromRoot)moveForThisTree = child.moveForThisTree;
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

		children = new ArrayList<MiniMaxTree>();

		for (int j = 0; j < gameBoard.width; j++) {
			// Build all possible boards for drop.

			Board temp = Board.getBoardCopy(gameBoard);
			if(temp.canDropADiscFromTop(j, thisIsMax ? playerNum : ((playerNum==1) ? 2: 1))){
				temp.dropADiscFromTop(j, thisIsMax ? playerNum : ((playerNum==1) ? 2: 1));
				MiniMaxTree tempTree = new MiniMaxTree(temp, !thisIsMax, playerNum);
				tempTree.moveForThisTree = new PlayerMove(j, TypeOfMove.Drop);
				children.add(tempTree);
			}
		}

		for (int j = 0; j < gameBoard.width; j++) {
			// Build all possible boards for pop.

			Board temp = new Board(gameBoard.height, gameBoard.width, gameBoard.getN());
			if (temp.canRemoveADiscFromBottom(j,  thisIsMax ? playerNum : ((playerNum==1) ? 2: 1))) {
				temp.removeADiscFromBottom(j);
				MiniMaxTree tempTree = new MiniMaxTree(temp, !thisIsMax, playerNum);
				tempTree.moveForThisTree = new PlayerMove(j, TypeOfMove.PopOut);
				children.add(tempTree);
			}
		}

		for (MiniMaxTree child: children) {
			child.buildTreeToDepth(i - 1);
		}
	}

}
