package RPG;

import java.util.ArrayList;

import RPG.Rooms.TestRoom;

public class Test {

	public static void main(String[] args) {
		
		Room r=new TestRoom();
		ArrayList<BoardObject> stuff=r.getObjects();
		ArrayList<Mob> mobs= new ArrayList<Mob>();
		BoardObject a= new PlayerCharacter(4,2, "Joseph", WeaponCreator.createWeapon());
		BoardObject[] boardarray={new PlayerCharacter(1,1,"a",WeaponCreator.createWeapon())};
		r.setObjects((PlayerCharacter)a, boardarray);
		
		for(int i=0;i<stuff.size();i++){
			if(stuff.get(i) instanceof Mob){
				mobs.add((Mob)stuff.get(i));
			}
		}
		
		mobs.get(0).takeDamage(10);
		
		for(int i=0;i<stuff.size();i++){
			if(mobs.get(0).equals(stuff.get(i))){
				System.out.println("They match!");
			}
		}
		
		
		System.out.println(a instanceof PlayerCharacter);

	}

}
