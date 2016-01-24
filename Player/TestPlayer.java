package Player;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import AIPlayer.CustomBoard;
import AIPlayer.MiniMaxTree;
import AIPlayer.PlayerMove;


public class TestPlayer {

	String playerName="Tucker";
	BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	boolean first_move=false;
	int firstPlayer = 0;
	public int playerNum = 0;
	int opponentNum = 0;
	int timeLimit = 0;
	
	MiniMaxTree gameTree;
	
	public static ArrayList<Integer> weights;
	
	CustomBoard gameBoard;
	
	public void processInput() throws IOException{	
		
    	String s=input.readLine();	
		List<String> ls = Arrays.asList(s.split(" "));
		if(ls.size()==2){
			//This code is called when the referee returns the opponents move. 
			//Update game board and build tree/respond with new move.
			long startingTime = System.currentTimeMillis();
			if(Integer.parseInt(ls.get(1)) == 0){
				//POP out
				gameBoard.removeADiscFromBottom(Integer.parseInt(ls.get(0)));
			}else{
				//Drop
				Printer.printToDebugFile("About to drop " + Integer.parseInt(ls.get(0)));
				Printer.printToDebugFile("OpponentNum " + opponentNum);
				int colNum = Integer.parseInt(ls.get(0));
				Printer.printToDebugFile("ColNum: " + colNum);
				Printer.printToDebugFile("Can drop? Col: " + colNum + " " + gameBoard.canDropADiscFromTop(colNum, opponentNum));
				if(gameBoard.canDropADiscFromTop(colNum, opponentNum)){
					gameBoard.dropADiscFromTop(colNum, opponentNum);
				}
			}

			//Perform MiniMax and pruning using heuristic. 
			//Return a move within the time limit.
			PlayerMove bestFoundMove;
			int treeDepth = 2;
			gameTree.buildTreeToDepth(treeDepth);

			for(int i = 1; System.currentTimeMillis() < (startingTime + (timeLimit*1000)); i++){
				if (i <= treeDepth) {
					Printer.printToDebugFile("MiniMax for i value: " + i);
					gameTree.performMiniMaxSearch(i, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
					Printer.printToDebugFile("Done MiniMax for i value: " + i);
				}else{
					break;
				}
			}
			Printer.printToDebugFile("About to send move: " + gameTree.moveForThisTree.column + " " + gameTree.moveForThisTree.moveType.ordinal());
			System.out.println(gameTree.moveForThisTree.column + " " + gameTree.moveForThisTree.moveType.ordinal());

			Printer.printToDebugFile("Sent Move");
		}else if(ls.size()==1){
			System.out.println("game over!!!");
			System.exit(0);
		}else if(ls.size()==5){          //ls contains game info
			//Receive game information.
			//height col N playerThatGoesFirst timeLimit
			int boardHeight = Integer.parseInt(ls.get(0));
			int boardWidth = Integer.parseInt(ls.get(1));
			int nNum = Integer.parseInt(ls.get(2));
			firstPlayer = Integer.parseInt(ls.get(3));
			timeLimit = Integer.parseInt(ls.get(4));
			
			first_move = (playerNum == firstPlayer) ? true: false;
			Printer.printToDebugFile("First player " + firstPlayer);
			Printer.printToDebugFile("We go first? " + first_move);
			
			Printer.printToDebugFile("Creating new board. Height, Width: " + boardHeight + " " + boardWidth);
			gameBoard = new CustomBoard(boardHeight, boardWidth, nNum);
			gameTree = new MiniMaxTree(gameBoard, true, playerNum);
			if(first_move){
				//make the first move
				System.out.println("4 1");
			}
			
			weights = new ArrayList<Integer>();
			for(int i = 1; i <= 10*nNum; i++){
				weights.add(i*i);
			}
			
		}else if(ls.size()==4){		//player1: aa player2: bb
			//Receive the names of the players.
			if(ls.get(1).equals(playerName)){
				playerNum = 1;
				opponentNum = 2;
			}else{
				playerNum = 2;
				opponentNum = 1;
			}
			
			Printer.printToDebugFile("We are player " + playerNum);
		}else{
			Printer.printToDebugFile("NOT WHAT I WANT");
			System.out.println("not what I want");
		}
	}
	
	public static void main(String[] args) throws IOException {
		TestPlayer rp=new TestPlayer();
		Printer.printToDebugFile("\n\n");
		System.out.println(rp.playerName);
		while (true){
			rp.processInput();
		}

	}

}
