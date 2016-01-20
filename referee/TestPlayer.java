package referee;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


public class TestPlayer {

	String playerName="Bobert";
	BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	boolean first_move=false;
	int firstPlayer = 0;
	
	public void processInput() throws IOException{	
	
    	String s=input.readLine();	
		List<String> ls=Arrays.asList(s.split(" "));
		if(ls.size()==2){
			System.out.println(ls.get(0)+" "+ls.get(1));
		}
		else if(ls.size()==1){
			System.out.println("game over!!!");
			System.exit(0);
		}
		else if(ls.size()==5){          //ls contains game info
			System.out.println("0 1");  //first move
			
			//height col N playerThatGoesFirst timeLimit
			int boardHeight = Integer.parseInt(ls.get(0));
			int boardWidth = Integer.parseInt(ls.get(1));
			int nNum = Integer.parseInt(ls.get(2));
			firstPlayer = Integer.parseInt(ls.get(3));
			int timeLimit = Integer.parseInt(ls.get(4));
			
		}
		else if(ls.size()==4){		//player1: aa player2: bb
			//TODO combine this information with game information to decide who is the first player
			if(ls.get(1).equals(playerName)){
				first_move = (firstPlayer == 1) ? true : false;
			}else{
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
