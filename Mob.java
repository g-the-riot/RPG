package RPG;

public class Mob extends AbstractCharacter {
	
	private static final int HP_INCREMENT = 200;
	
	public Mob(String name, int level, Weapon weapon) {	
		super(name, 												//name
			  HP_INCREMENT * level,									//hp
			  HP_INCREMENT * level,									//maxHp
			  level,												//level
			  weapon);												//weapon 
	}

	// Overriding to remove functionality
	public void changeName(String newName){

	}
	
	// Overriding to remove functionality
	public void changeWeapon(Weapon newWeapon) {

	}
	
	public void attack(Character q) {
		
	}
}