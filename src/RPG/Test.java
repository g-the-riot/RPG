package RPG;

public class Test {

	public static void main(String[] args) {
		BoardObject a= new PlayerCharacter(4,2, "Joseph", WeaponCreator.createWeapon());
		System.out.println(a instanceof PlayerCharacter);

	}

}
