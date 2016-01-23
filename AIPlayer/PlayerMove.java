package AIPlayer;

public class PlayerMove {
	public TypeOfMove moveType;
	
	public int column;
	
	enum TypeOfMove{
		 PopOut, Drop;
	}
	
	public PlayerMove(int col, TypeOfMove type){
		this.column = col;
		this.moveType = type;
	}
}
