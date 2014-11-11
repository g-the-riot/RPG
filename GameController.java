package RPG;

import java.io.File;
import java.util.Scanner;
import java.io.IOException;

// Provides methods to control gameplay
public class GameController {

	public GameController () {}
	
	/* Returns a Character object with the given name as found in the file at the provided path.
     * Assumes all character names are unique.
	 */				 
	public Character getCharacter(String path, String name) throws IOException {
		Character c = null;
		File playerData = new File(path);
		Scanner reader = new Scanner(playerData);
		while (reader.hasNextLine()) {
			String[] playerInfo = reader.nextLine().split(",");
			if(playerInfo[1].equals(name)) {
				c = createCharacter(playerInfo);
			}	
		}
		reader.close();
		return c;	
	}
	
	public void takeTurn(Character first, Character second) {
			first.attack(second);	
	}
	
	public void reviveCharacter(Character c) {
		
	}
	
	//Auxilliary methods
	
	private Character createCharacter(String[] playerInfo) {
		Character c = null;
		String name = playerInfo[0];
		int hp = Integer.parseInt(playerInfo[1]);
		int xp = Integer.parseInt(playerInfo[2]);
		int level = Integer.parseInt(playerInfo[3]);
		Weapon weapon = new Weapon(Weapon.Type.EXPLOSIVE, playerInfo[5]);
		String type = playerInfo[6];
		if(type.equals("Player Character")) {
			c = new PlayerCharacter(name, hp, xp, level, weapon);
		}
		//else if (type.equals("Mob")) {
		//	c = new Mob(name, hp, level, weapon);
		//}
		return c;			
	}
	

}