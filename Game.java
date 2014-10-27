package RPG;

import java.io.IOException;
import java.util.Scanner; 
import java.io.File;
import java.util.Vector;

public class Game {

	private File saveData;
	private Vector<PlayerCharacter> players;
	
	
	public Game(String path) throws IOException {
		init(path);
	}
	
	private void init(String path) throws IOException {
		saveData = new File(path);
		players = new Vector<PlayerCharacter>();
	}
	
	public Vector<PlayerCharacter> players() {
		return players;
	}
	
	public void fetchCharacterData() throws IOException{
		Scanner reader = new Scanner(saveData);
		String[] characterData = null;
		while(reader.hasNextLine()){
			characterData = reader.nextLine().split(",");
			players.add(createCharacter(characterData));
		}
	}
	
	private PlayerCharacter createCharacter(String[] data) {
		String name = data[0];
		int hp = Integer.parseInt(data[1]);
		int xp = Integer.parseInt(data[2]);
		int level = Integer.parseInt(data[3]);
		Weapon.Group group = weaponGroup(data[4]);
		String weaponName = data[5];
		Weapon w = new Weapon(group, weaponName);
		int playerId = Integer.parseInt(data[6]);
		return new PlayerCharacter(name, hp, xp, level, w, playerId);
	}
	
	private Weapon.Group weaponGroup(String type) {
		Weapon.Group group = Weapon.Group.FOOD;
		if (type.equals("EXPLOSIVE")) {
			group = Weapon.Group.EXPLOSIVE;
		}
		else if (type.equals("BLADE")) {
			group = Weapon.Group.BLADE;
		}
		else if (type.equals("MAGIC")) {
			group = Weapon.Group.MAGIC;
		}
		else if (type.equals("PROJECTILE")) {
			group = Weapon.Group.PROJECTILE;
		}
		return group;
	}


}