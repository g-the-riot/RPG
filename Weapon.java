package RPG;

public class Weapon {
	
	// Instance variable
	private int damage;
	private String name;
	
	// Type constants
	public enum Type {SWORD, EXPLOSIVE, PROJECTILE, WAND};
	
	public Weapon(Type type, String name) {
		if (type == Type.EXPLOSIVE) {
			damage = 3;
		}
		else if (type == Type.PROJECTILE) {
			damage = 1;
		}
		else {
			damage = 2;
		}
		this.name = name;
	}
	
	public int damage() {
		return damage;
	}
	
	public String name() {
		return name;
	}


}