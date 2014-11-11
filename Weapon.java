package RPG;

public class Weapon {
<<<<<<< HEAD
	
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
=======

 	private int damage;
	private String name;
	public enum Group {EXPLOSIVE, BLADE, FOOD, MAGIC, PROJECTILE};
	
	public Weapon(Group type, String name) {
		if(type == Group.EXPLOSIVE) {
			damage = 4;
		}
		else if(type == Group.BLADE) {
			damage = 3;
		}
		else if(type == Group.MAGIC) {
			damage = 2;
		}
		else {
			damage = 1;
>>>>>>> 855915d7b3d51bd16c5a6ebe74b23d3dc30c8776
		}
		this.name = name;
	}
	
<<<<<<< HEAD
	public int damage() {
		return damage;
	}
	
	public String name() {
		return name;
	}

=======
	public String name() {
		return name;
	}
	
	public int damage() {
		return damage;
	}
>>>>>>> 855915d7b3d51bd16c5a6ebe74b23d3dc30c8776

}