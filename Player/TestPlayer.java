package Player;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import AIPlayer.Board;
import AIPlayer.MiniMaxTree;
import AIPlayer.PlayerMove;


public class TestPlayer {

	String playerName="Bogey";
	BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	boolean first_move=false;
	int firstPlayer = 0;
	public int playerNum = 0;
	int opponentNum = 0;
	int timeLimit = 0;
	
	MiniMaxTree gameTree;
	
	public static ArrayList<Integer> weights;
	
	Board gameBoard;
	
	public void processInput() throws IOException{	
		
    	String s=input.readLine();	
		List<String> ls = Arrays.asList(s.split(" "));
		//Add the last move to our game board.
		if(ls.size()==2){
			Printer.printToDebugFile("Beginning program.");
			//This code is called when the referee returns the opponents move. 
			//Update game board and build tree/respond with new move.
			Printer.printToDebugFile("About to build tree2");
			long startingTime = System.currentTimeMillis();
			//System.out.println(ls.get(0)+" "+ls.get(1));
			Printer.printToDebugFile("About to build tree3");
			if(Integer.parseInt(ls.get(1)) == 0){
				//POP out
				Printer.printToDebugFile("About to build tree4");
				gameBoard.removeADiscFromBottom(Integer.parseInt(ls.get(0)));
			}else{
				//Drop
				Printer.printToDebugFile("About to drop " + Integer.parseInt(ls.get(1)));
				gameBoard.dropADiscFromTop(Integer.parseInt(ls.get(0)), opponentNum);
				Printer.printToDebugFile("About to build tree7");
			}

			//Perform MiniMax and pruning using heuristic. 
			//Return a move within the time limit.
			Printer.printToDebugFile("About to build tree6");
			PlayerMove bestFoundMove;
			Printer.printToDebugFile("About to build tree");
			gameTree.buildTreeToDepth(2);
			for(int i = 1; System.currentTimeMillis() < (startingTime + (timeLimit*1000)); i++){
				gameTree.performMiniMaxSearch(i, Integer.MIN_VALUE, Integer.MAX_VALUE, true);	
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
			if(first_move){
				//make the first move
				System.out.println("4 1");
			}
			
			weights = new ArrayList<Integer>();
			for(int i = 1; i < nNum; i++){
				weights.add(i*i);
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
