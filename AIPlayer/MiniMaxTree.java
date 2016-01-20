package AIPlayer;

import java.util.ArrayList;

import referee.Board;

public class MiniMaxTree {
	Board gameBoard;
	boolean thisIsMax;
	
	ArrayList<MiniMaxTree> children;
	
	
	MiniMaxTree(Board board, boolean rootIsMax){
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
	
	public int performMiniMaxSearch(){
		//TODO: Generate children and perform search.
		int result;
		if(thisIsMax){
			result = Integer.MIN_VALUE;
		
			for(MiniMaxTree child: children){
				int thisChildsNum = child.performMiniMaxSearch();
				if(thisChildsNum > result){
					result = thisChildsNum;
				}
			}
		}else{
			result = Integer.MAX_VALUE;
			
			for(MiniMaxTree child: children){
				int thisChildsNum = child.performMiniMaxSearch();
				if(thisChildsNum < result){
					result = thisChildsNum;
				}
			}
		}
		
		return result;
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
