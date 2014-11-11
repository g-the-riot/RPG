package RPG;

public class Weapon {

 	private int damage;
	private String name;
	public enum Type {EXPLOSIVE, BLADE, FOOD, MAGIC, PROJECTILE};
	
	public Weapon(Type type, String name) {
		if(type == Type.EXPLOSIVE) {
			damage = 4;
		}
		else if(type == Type.BLADE) {
			damage = 3;
		}
		else if(type == Type.MAGIC) {
			damage = 2;
		}
		else {
			damage = 1;
		}
		this.name = name;
	}

	public String name() {
		return name;
	}
	
	public int damage() {
		return damage;
	}

}