

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import AIPlayer.Board;
import AIPlayer.MiniMaxTree;
import AIPlayer.PlayerMove;


public class TestPlayer {

	String playerName="TitsMcGee";
	BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	boolean first_move=false;
	int firstPlayer = 0;
	public int playerNum = 0;
	int opponentNum = 0;
	int timeLimit = 0;
	
	MiniMaxTree gameTree;
	
	Board gameBoard;
	
	public void processInput() throws IOException{	
		
    	String s=input.readLine();	
		List<String> ls = Arrays.asList(s.split(" "));
		//Add the last move to our game board.
		if(ls.size()==2){
			//System.err.println("THIS IS A DIAGNOSTIC MESSAGE");
			//This code is called when the referee returns the opponents move. 
			//Update game board and build tree/respond with new move.
			
			long startingTime = System.currentTimeMillis();
			System.out.println(ls.get(0)+" "+ls.get(1));
			
			if(Integer.parseInt(ls.get(1)) == 0){
				//POP out
				gameBoard.removeADiscFromBottom(Integer.parseInt(ls.get(0)));
			}else{
				//Drop
				gameBoard.dropADiscFromTop(Integer.parseInt(ls.get(0)), opponentNum);
			}
			
			//TODO: implement everything here.
			//Perform MiniMax and pruning using heuristic. 
			//Return a move within the time limit.
			PlayerMove bestFoundMove;
			gameTree.buildTreeToDepth(10);
			for(int i = 1; System.currentTimeMillis() > (startingTime + (timeLimit*1000))-2; i++){
				gameTree.performMiniMaxSearch(i, Integer.MIN_VALUE, Integer.MAX_VALUE);	
			}
			
			System.out.println(gameTree.moveForThisTree.column + " " + gameTree.moveForThisTree.moveType);
			
		}
		else if(ls.size()==1){
			System.out.println("game over!!!");
			System.exit(0);
		}
		else if(ls.size()==5){          //ls contains game info
			
			//height col N playerThatGoesFirst timeLimit
			int boardHeight = Integer.parseInt(ls.get(0));
			int boardWidth = Integer.parseInt(ls.get(1));
			int nNum = Integer.parseInt(ls.get(2));
			firstPlayer = Integer.parseInt(ls.get(3));
			timeLimit = Integer.parseInt(ls.get(4));
			
			Board gameBoard = new Board(boardHeight, boardWidth, nNum);
			gameTree = new MiniMaxTree(gameBoard, true, playerNum);
			if(!first_move){
				//make the first move
				System.out.println("4 1");
			}
			
		}
		else if(ls.size()==4){		//player1: aa player2: bb
			if(ls.get(1).equals(playerName)){
				playerNum = 1;
				opponentNum = 2;
				first_move = (firstPlayer == 1) ? true : false;
			}else{
				playerNum = 2;
				opponentNum = 1;
				first_move = (firstPlayer == 2) ? true : false;
			}
		}
		else
			System.out.println("not what I want");
	}
	
	public static void main(String[] args) throws IOException {
		TestPlayer rp=new TestPlayer();
		System.out.println(rp.playerName);
		while (true){
			rp.processInput();
		}

	}

}
