package RPG;
<<<<<<< HEAD

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
=======
import java.util.Random;

public class PlayerCharacter {

	public static final int LEVEL_XP = 100;
	
	private String name; //declaration of instance variable
	private int hp;
	private int xp;
	private int level;
	private Weapon weapon;
	private int id;
	
	public PlayerCharacter(String name, int id) {
		this.name = name;
		hp = 100;
		xp = 0;
		level = 1;
		weapon = new Weapon(Weapon.Group.FOOD, "Raw Carrot");
		this.id = id;
	}
	
	public PlayerCharacter(String name, int hp, int xp, int level, Weapon weapon, int id) {
		this.name = name;
		this.hp = hp;
		this.xp = xp;
		this.level = level;
		this.weapon = weapon;
		this.id = id;
	}
	
	//queries
	public String name() {
		return name;
	}
	
	public int hp() {
		return hp;
>>>>>>> 855915d7b3d51bd16c5a6ebe74b23d3dc30c8776
	}
	
	public int xp() {
		return xp;
	}
	
<<<<<<< HEAD
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
=======
	public int level() {
		return level;
	}
	
	public Weapon weapon() {
		return weapon;
	}
	
	public boolean isDead() {
		return (this.hp <= 0);
	}
	
	public int rewardXp() {
		return 5 + level;
	}
	
	public int id() {
		return id;
	}
	
	//commands
	
	
	public void changeName(String name) {
		this.name = name;
	}
	
	/*
	 * Adds the given hp to this PlayerCharacter's hp()
	 * Require: hp >= 0
	 * Ensure: this.hp() = this.hp() + hp
	 */
	public void addHP(int hp) {
		//if(hp > 0) {
			this.hp = this.hp + hp;
		//}
	}
	
	public void addXP(int xp) {
		this.xp = this.xp + xp;
		if(this.xp >= (this.level * LEVEL_XP)) {
			this.xp = this.xp - (this.level * LEVEL_XP);
			levelUp();
		}
	}
	
	private void levelUp(){
		level = level + 1;
	}
	
	public void takeDamage(int damage) {
		if (damage > hp) {
			hp = 0;
		}
		else {
			hp = hp - damage;
		}
	}
	
	public void attack(PlayerCharacter other) {
		other.takeDamage(new Random().nextInt(weapon.damage() + 1));
		if(other.isDead()) {
			addXP(other.rewardXp());
		}
	}
	
	
	
	
>>>>>>> 855915d7b3d51bd16c5a6ebe74b23d3dc30c8776

}