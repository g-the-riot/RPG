package RPG;

public class Weapon {

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