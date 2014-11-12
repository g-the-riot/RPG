package RPG;

import java.io.IOException;

public class RPGTest {
	
	public static void main(String[] args) throws IOException{
		
		GameController game = new GameController();
		Character c = game.getCharacter("/Users/Evenie/Desktop/RPG/Characters.txt", "Bob");
		Character d = game.getCharacter("/Users/Evenie/Desktop/RPG/Characters.txt", "Mob");
		System.out.println(c.name());
		System.out.println(d.name());
	}

}