package RPG;
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
	}
	
	public int xp() {
		return xp;
	}
	
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
	
	
	
	

}