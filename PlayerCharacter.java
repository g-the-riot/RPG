package RPG;

public class PlayerCharacter extends AbstractCharacter {

	private int xp;
	
	public static final int HP_INCREMENT = 100;
	
	// "Default constructor" - use this for a new character
	public PlayerCharacter(String name) {
		super(name, 												//name
			  HP_INCREMENT,											//hp
			  HP_INCREMENT,											//maxHp
			  1,													//level
			  new Weapon(Weapon.Type.PROJECTILE, "Pea shooter"));	//weapon
		xp = 0;
	}
	
	// use this for an existing character
	public PlayerCharacter(String name, int hp, int xp, int level, Weapon weapon) {
		super(name, 												//name
			  hp,													//hp
			  HP_INCREMENT * level,									//maxHp
			  level,												//level
			  weapon);												//weapon
		this.xp = xp;
	}
	
	public int xp() {
		return xp;
	}
	
	//commands
	//public void changeName(String newName){
	//	name = newName;
	//}
	
	public void gainXP (int xp) {
		xp = Math.abs(xp);
		this.xp = this.xp + xp;
		
	}
	
	//public void changeWeapon(Weapon newWeapon) {
	//	weapon = newWeapon;
	//}

}