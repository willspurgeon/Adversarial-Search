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
	public int getUtilityOfRootBoard(){
		//TODO: Implement heuristic here.
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
	
	public void buildTreeToDepth(int i){
		if(i < 1){
			return;
		}
		
		for(int j = 0; j < gameBoard.width; j++){
			//Build all possible boards for drop.
		}
		
		for(int j = 0; j < gameBoard.width; j++){
			//Build all possible boards for pop.
		}
		
	}
	
}
