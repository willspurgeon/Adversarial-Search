package AIPlayer;

//Will Spurgeon and Daniel Pongratz

public class PlayerMove {
	public TypeOfMove moveType;
	
	public int column;
	
	public enum TypeOfMove{
		 PopOut, Drop;
	}
	
	public PlayerMove(int col, TypeOfMove type){
		this.column = col;
		this.moveType = type;
	}
}
