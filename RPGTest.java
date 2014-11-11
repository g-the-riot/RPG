package RPG;

import java.io.IOException;

public class RPGTest {
	
	public static void main(String[] args) throws IOException{
		
		GameController game = new GameController();
		game.getCharacter("/Users/Evenie/Desktop/RPG001/PlayerCharacters.txt", "Bob");
		
		
		
		
	}

}