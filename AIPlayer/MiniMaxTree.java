package AIPlayer;

//Will Spurgeon and Daniel Pongratz

import java.io.PrintWriter;
import java.util.ArrayList;

import AIPlayer.PlayerMove.TypeOfMove;
import Player.Printer;
import Player.TestPlayer;

public class MiniMaxTree {
	public CustomBoard gameBoard;
	boolean thisIsMax;
	int playerNum;

	public PlayerMove moveForThisTree = null;

	ArrayList<MiniMaxTree> children;

	public MiniMaxTree(CustomBoard board, boolean rootIsMax, int playerNum) {

		this.gameBoard = CustomBoard.getBoardCopy(board);
		this.thisIsMax = rootIsMax;
		this.playerNum = playerNum;
	}

	/**
	 * 
	 * @return The value of the MiniMaxTree
	 */
	

	
	public int getUtilityOfRootBoard() {
		// i width
		// j height
		//testStuff();
		int myValue = 0;
		int opponentValue = 0;

		//Printer.printToDebugFile("Starting utility function");
		for (int i = 0; i < gameBoard.width; i++) {
			for (int j = 0; j < gameBoard.height; j++) {
				if (gameBoard.board[j][i] != 9){
					continue;
				}

				// //Printer.printToDebugFile("About to try right.");
				if (i != gameBoard.width) {
					// Check right
					int rowPlayerValue = 10;
					int inARow = 0;
					for (int y = i + 1; y < gameBoard.width; y++) {
						if (y == i + 1) {
							if (gameBoard.board[j][y] == 9) {
								break;
							}
							rowPlayerValue = gameBoard.board[j][y];
						} else {
							if (gameBoard.board[j][y] == rowPlayerValue) {
								inARow++;
							} else {
								if (playerNum == rowPlayerValue) {
									if (inARow > 0) {
										myValue = myValue + TestPlayer.weights.get(inARow);
									}
								} else {
									if (inARow > 0) {
										opponentValue = opponentValue + TestPlayer.weights.get(inARow);
									}
								}
								break;
							}
						}
					}
				}
				
				if (j != gameBoard.height) {
					// Check top
					int rowPlayerValue = 10;
					int inARow = 0;
					for (int y = j + 1; y < gameBoard.height; y++) {
						if (y == j + 1) {
							if (gameBoard.board[y][i] == 9) {
								break;
							}
							rowPlayerValue = gameBoard.board[y][i];
						} else {
							if (gameBoard.board[y][i] == rowPlayerValue) {
								inARow++;
							} else {
								if (playerNum == rowPlayerValue) {
									if (inARow > 0) {
										myValue = myValue + TestPlayer.weights.get(inARow);
									}
								} else {
									if (inARow > 0) {
										opponentValue = opponentValue + TestPlayer.weights.get(inARow);
									}
								}
								break;
							}
						}
					}
				}
				
				
				////Printer.printToDebugFile("About to try left.");
				if (i != 0) {
					// Check left.
					int rowPlayerValue = 10;
					int inARow = 0;
					for (int y = i - 1; y >= 0; y--) {
						if (y == i - 1) {
							if (gameBoard.board[j][y] == 9) {
								break;
							}
							rowPlayerValue = gameBoard.board[j][y];
						} else {
							if (gameBoard.board[j][y] == rowPlayerValue) {
								inARow++;
							} else {
								if (playerNum == rowPlayerValue) {
									if (inARow > 0) {
										myValue = myValue + TestPlayer.weights.get(inARow - 1);
									}
								} else {
									if (inARow > 0) {
										opponentValue = opponentValue + TestPlayer.weights.get(inARow - 1);
									}
								}
								break;
							}
						}
					}
				}
				if (j != 0) {
					// Check bottom.
					int rowPlayerValue = 10;
					int inARow = 0;
					for (int y = j - 1; y >= 0; y--) {
						if (y == j - 1) {
							if (gameBoard.board[y][i] == 9) {
								break;
							}
							rowPlayerValue = gameBoard.board[y][i];
						} else {
							if (gameBoard.board[y][i] == rowPlayerValue) {
								inARow++;
							} else {
								if (playerNum == rowPlayerValue) {
									if (inARow > 0) {
										myValue = myValue + TestPlayer.weights.get(inARow);
									}
								} else {
									if (inARow > 0) {
										opponentValue = opponentValue + TestPlayer.weights.get(inARow);
									}
								}
								break;
							}
						}
					}
				}

				////Printer.printToDebugFile("About to try top right.");
				if (j != gameBoard.height && i != gameBoard.width) {
					// Check Top right.
					int rowPlayerValue = 10;
					int inARow = 0;
					int xValue = i;
					for (int y = j + 1; y < gameBoard.height && xValue + 1 < gameBoard.width; y++) {
						if (y == j + 1) {
							if (gameBoard.board[y][xValue] == 9) {
								break;
							}
							rowPlayerValue = gameBoard.board[y][xValue];
						} else {
							if (gameBoard.board[y][xValue] == rowPlayerValue) {
								inARow++;
							} else {
								if (playerNum == rowPlayerValue) {
									if (inARow > 0) {
										myValue = myValue + TestPlayer.weights.get(inARow);
									}
								} else {
									if (inARow > 0) {
										opponentValue = opponentValue + TestPlayer.weights.get(inARow);
									}
								}
								break;
							}
						}
						xValue++;
					}
				}
				////Printer.printToDebugFile("About to try bottom right.");
				if (i + 1 != gameBoard.width && j != 0) {
					// Check Bottom right
					int rowPlayerValue = 10;
					int inARow = 0;
					int xValue = i;
					for (int y = j - 1; y >= 0 && xValue + 1 < gameBoard.width; y--) {

						if (y == j - 1) {
							if (gameBoard.board[y][xValue] == 9) {
								break;
							}
							rowPlayerValue = gameBoard.board[y][xValue];
						} else {
							if (gameBoard.board[y][xValue] == rowPlayerValue) {
								inARow++;
							} else {
								if (playerNum == rowPlayerValue) {
									if (inARow > 0) {
										myValue = myValue + TestPlayer.weights.get(inARow);
									}
								} else {
									if (inARow > 0) {
										opponentValue = opponentValue + TestPlayer.weights.get(inARow);
									}
								}
								break;
							}
						}
						xValue++;
					}
				}
				////Printer.printToDebugFile("About to try top left.");
				if (i != 0 && j + 1 != gameBoard.height) {
					// Check top left.
					int rowPlayerValue = 10;
					int inARow = 0;
					int xValue = i;
					for (int y = j + 1; y < gameBoard.height && xValue - 1 > 0; y++) {
						if (y == j + 1) {
							if (gameBoard.board[y][xValue] == 9) {
								break;
							}
							rowPlayerValue = gameBoard.board[y][xValue];
						} else {
							if (gameBoard.board[y][xValue] == rowPlayerValue) {
								inARow++;
							} else {
								if (playerNum == rowPlayerValue) {
									if (inARow > 0) {
										myValue = myValue + TestPlayer.weights.get(inARow);
									}
								} else {
									if (inARow > 0) {
										opponentValue = opponentValue + TestPlayer.weights.get(inARow);
									}
								}
								break;
							}
						}
						xValue--;
					}
				}
				////Printer.printToDebugFile("About to try bottom left.");
				if (j != 0 && i != 0) {
					// Check bottom left.
					int rowPlayerValue = 10;
					int inARow = 0;
					int xValue = i;
					for (int y = j - 1; y > 0 && xValue - 1 > 0; y--) {
						if (y == j - 1) {
							if (gameBoard.board[y][xValue] == 9) {
								break;
							}
							rowPlayerValue = gameBoard.board[y][xValue];
						} else {
							if (gameBoard.board[y][xValue] == rowPlayerValue) {
								inARow++;
							} else {
								if (playerNum == rowPlayerValue) {
									if (inARow > 0) {
										myValue = myValue + TestPlayer.weights.get(inARow);
									}
								} else {
									if (inARow > 0) {
										opponentValue = opponentValue + TestPlayer.weights.get(inARow);
									}
								}
								break;
							}
						}
						xValue--;	// was in the wrong place
					}
				}
			}
		}
		////Printer.printToDebugFile("My Value: "+myValue);
		////Printer.printToDebugFile("Opponents Value: "+opponentValue);
		////Printer.printToDebugFile("Returning utility value of: " + (myValue - opponentValue));
		return myValue - opponentValue;
	}

	public int performMiniMaxSearch(int depth, int alpha, int beta, boolean calledFromRoot) {
		if (depth == 1) {
			////Printer.printToDebugFile("Getting utility of board");
			return getUtilityOfRootBoard();
		} else {
			if (thisIsMax) {
				if (children != null) {
					for (MiniMaxTree child : children) {
						int thisChildsNum = child.performMiniMaxSearch(depth - 1, alpha, beta, false); //Search each child in tree.
						if (thisChildsNum > alpha) {
							alpha = thisChildsNum;
							if (calledFromRoot) {
								moveForThisTree = child.moveForThisTree; //We should make this move.
							}
						}
						if (alpha >= beta)
							break; //Alpha-beta pruning.
					}
				}
				return alpha;
			} else {
				if (children != null) {
					for (MiniMaxTree child : children) {
						int thisChildsNum = child.performMiniMaxSearch(depth - 1, alpha, beta, false); //Search for each child in tree.
						if (thisChildsNum < beta) {
							beta = thisChildsNum;
							if (calledFromRoot){
								moveForThisTree = child.moveForThisTree; //We should make this move.
							}
						}
						if (alpha >= beta)
							break; //Alpha-beta pruning.
					}
				}
				return beta;
			}
		}
	}
	public void  testStuff()
	{
		
		gameBoard.dropADiscFromTop(0, 1);
		gameBoard.dropADiscFromTop(1, 2);
		gameBoard.dropADiscFromTop(1, 1);
		gameBoard.dropADiscFromTop(2, 2);
		gameBoard.dropADiscFromTop(2, 2);
		gameBoard.dropADiscFromTop(2, 1);
		gameBoard.dropADiscFromTop(4, 2);
		gameBoard.dropADiscFromTop(4, 2);
		gameBoard.dropADiscFromTop(5, 1);
		gameBoard.dropADiscFromTop(5, 1);
	
	}

	public void buildTreeToDepth(int i) {
		if (i <= 1) {
			return;
		}

		//Printer.printToDebugFile("Building new tree. On depth level " + i);
		children = new ArrayList<MiniMaxTree>();

		for (int j = 0; j < gameBoard.width; j++) {
			// Build all possible boards for drop.

			CustomBoard temp = CustomBoard.getBoardCopy(gameBoard);
			if (temp.canDropADiscFromTop(j, thisIsMax ? playerNum : ((playerNum == 1) ? 2 : 1))) {
				temp.dropADiscFromTop(j, thisIsMax ? playerNum : ((playerNum == 1) ? 2 : 1));
				MiniMaxTree tempTree = new MiniMaxTree(temp, !thisIsMax, playerNum);
				tempTree.moveForThisTree = new PlayerMove(j, TypeOfMove.Drop);
				children.add(tempTree);
			}
		}

		for (int j = 0; j < gameBoard.width; j++) {
			// Build all possible boards for pop.

			CustomBoard temp = CustomBoard.getBoardCopy(gameBoard);
			if (temp.canRemoveADiscFromBottom(j, thisIsMax ? playerNum : ((playerNum == 1) ? 2 : 1))) {
				temp.removeADiscFromBottom(j);
				MiniMaxTree tempTree = new MiniMaxTree(temp, !thisIsMax, playerNum);
				tempTree.moveForThisTree = new PlayerMove(j, TypeOfMove.PopOut);
				children.add(tempTree);
			}
		}

		if(children != null){
			for (MiniMaxTree child : children) {
				child.buildTreeToDepth(i - 1); //Build the next layer.
			}
		}
	}

}
