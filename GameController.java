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
			if(playerInfo[0].equals(name)) {
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
		Weapon weapon = null;
		int level = 1;
		String name = playerInfo[0];
		String type = playerInfo[1];
		if(type.equals("Player Character")) {
			int hp = Integer.parseInt(playerInfo[2]);
			int xp = Integer.parseInt(playerInfo[3]);
			level = Integer.parseInt(playerInfo[4]);
			weapon = new Weapon(Weapon.Type.EXPLOSIVE, playerInfo[5]);
			c = new PlayerCharacter(name, hp, xp, level, weapon);
		}
		else if (type.equals("Mob")) {
			level = Integer.parseInt(playerInfo[2]);
			weapon = new Weapon(Weapon.Type.PROJECTILE, playerInfo[4]);
			c = new Mob(name,level, weapon);
		}
		return c;			
	}
	

}