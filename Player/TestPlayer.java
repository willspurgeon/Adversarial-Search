package Player;

//Authors: Will Spurgeon and Daniel Pongratz

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

	String playerName="Will";
	BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	boolean first_move=false;
	int firstPlayer = 0;
	public int playerNum = 0;
	int opponentNum = 0;
	int timeLimit = 0;
	
	MiniMaxTree gameTree;
	
	public static ArrayList<Integer> weights;
	
	public void processInput() throws IOException{	
		
    	String s=input.readLine();	
		List<String> ls = Arrays.asList(s.split(" "));
		if(ls.size()==2){
			//This code is called when the referee returns the opponents move. 
			//Update game board and build tree/respond with new move.
			long startingTime = System.currentTimeMillis();
			if(Integer.parseInt(ls.get(1)) == 0){
				//POP out
				gameTree.gameBoard.removeADiscFromBottom(Integer.parseInt(ls.get(0)));
			}else{
				//Drop
				Printer.printToDebugFile("About to drop " + Integer.parseInt(ls.get(0)));
				Printer.printToDebugFile("OpponentNum " + opponentNum);
				int colNum = Integer.parseInt(ls.get(0));
				Printer.printToDebugFile("ColNum: " + colNum);
				Printer.printToDebugFile("Can drop? Col: " + colNum + " " + gameTree.gameBoard.canDropADiscFromTop(colNum, opponentNum));
				if(gameTree.gameBoard.canDropADiscFromTop(colNum, opponentNum)){
					Printer.printToDebugFile("DROPPING!");
					gameTree.gameBoard.dropADiscFromTop(colNum, opponentNum);
				}
			}

			gameTree.gameBoard.printBoardToDebug();
			//Perform MiniMax and pruning using heuristic. 
			//Return a move within the time limit.
			int treeDepth = 4;
			gameTree.buildTreeToDepth(treeDepth);
			
			for(int i = 1; System.currentTimeMillis() < (startingTime + (timeLimit*1000)); i++){
				if (i <= treeDepth) {
					Printer.printToDebugFile("MiniMax for i value: " + i);
					gameTree.performMiniMaxSearch(i, Integer.MIN_VALUE, Integer.MAX_VALUE, true); //Perform the MiniMax search to a specific depth.
					Printer.printToDebugFile("Done MiniMax for i value: " + i);
				}else{
					break;
				}
			}
			Printer.printToDebugFile("About to send move: " + gameTree.moveForThisTree.column + " " + gameTree.moveForThisTree.moveType.ordinal());
			System.out.println(gameTree.moveForThisTree.column + " " + gameTree.moveForThisTree.moveType.ordinal()); //Print out our move.
			if(gameTree.moveForThisTree.moveType == PlayerMove.TypeOfMove.Drop){
				Printer.printToDebugFile("About to drop a disc again.");
				if(gameTree.gameBoard.canDropADiscFromTop(gameTree.moveForThisTree.column, playerNum))
				gameTree.gameBoard.dropADiscFromTop(gameTree.moveForThisTree.column, playerNum);
			}else{
				Printer.printToDebugFile("About to remove a disc.");
				gameTree.gameBoard.removeADiscFromBottom(gameTree.moveForThisTree.column);
			}

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
			CustomBoard gameBoard = new CustomBoard(boardHeight, boardWidth, nNum); //Initialize the game board.
			gameTree = new MiniMaxTree(gameBoard, true, playerNum);
			if(first_move){
				//make the first move
				System.out.println("0 1");
				gameTree.gameBoard.dropADiscFromTop(0, playerNum);
			}
			
			weights = new ArrayList<Integer>();
			for(int i = 1; i <= 10*nNum; i++){
				weights.add(i*i); //Rows of length n are worth twice the value of rows with length n-1.
			}
			
		}else if(ls.size()==4){	
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
