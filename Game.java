import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
public class Game {
	public static void main(String[] args) {
		SnakeNLadder snakeLadder = new SnakeNLadder();
		snakeLadder.startGame();
	}
} 
class SnakeNLadder {
	final static int WINPOINT = 100;
	static Map<Integer, Integer> snake = new HashMap<>();
	static Map<Integer, Integer> ladder = new HashMap<>();
	{
		snake.put(99, 54);
		snake.put(70, 55);
		snake.put(52, 42);
		snake.put(25, 2);
		snake.put(95, 72);

		ladder.put(6, 25);
		ladder.put(11, 40);
		ladder.put(60, 85);
		ladder.put(46, 90);
		ladder.put(17, 69);
	}

	//this method will generate random number from 1-6
	public int rollDice() {
		int n = 0;
		Random r = new Random();
		n = r.nextInt(7);
		return (n == 0 ? 1 : n);
	}

	
	public int calculatePlayerValue(int playerPosition, int diceValue) {
		int playerNewPosition=playerPosition+diceValue;

		if (playerNewPosition > WINPOINT)
			return playerPosition;		

		if (null !=snake.get(playerNewPosition)) {
			System.out.println("Oops..swallowed by the snake..");
			playerNewPosition=snake.get(playerNewPosition);
		}
		
		if (null !=ladder.get(playerNewPosition)) {
			System.out.println("YAY! climbing the ladder..");
			playerNewPosition=ladder.get(playerNewPosition);
		}
		
		return playerNewPosition;
	}
	
	public boolean isWin(int playerPosition) {
		return WINPOINT==playerPosition;
	}
	
	public void startGame() {
		int player1Position=0, player2Position=0;
		int currentPlayer=-1;		
		Scanner scan= new Scanner(System.in);
		
		int diceValue = 0;
		while(true){
		    System.out.println(currentPlayer == -1 
		    ? "\n\nFirst player's turn" : "\n\nSecond player's turn");
		    diceValue=rollDice();
           if (currentPlayer==-1) {
		      player1Position=calculatePlayerValue(player1Position, diceValue);
		      System.out.println("First Player Position:"+player1Position);
		      System.out.println("Second Player Position:"+player2Position);
		      
		      if (isWin(player1Position)) {
		         System.out.println("Congratulations! First player won");
		         break;
		      }
		      } 
              else {
		        player2Position = calculatePlayerValue(player2Position, diceValue);
		        System.out.println("First Player Position:"+player1Position);
		        System.out.println("Second Player Position:"+player2Position);
		     
		        if (isWin(player2Position)) {
		          System.out.println("Congratulations! Second player won");
		        break;
		        }
		      }
		      currentPlayer = -currentPlayer;
		} 
	}
}