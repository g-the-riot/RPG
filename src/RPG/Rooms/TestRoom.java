package RPG.Rooms;

import RPG.Door;
import RPG.Mob;
import RPG.Room;
import RPG.WeaponCreator;

public final class TestRoom extends Room {
		
	public TestRoom(){
		super(10, 10, "TestRoom");
		setDoors();
		setMobs();
	}

	@Override
	public void setDoors() {
		super.doors = new Door[2];
		doors[0]=new Door(5, 9, "Outside");
		doors[1]=new Door(2, 0, "TestRoom2");
	}

	@Override
	public void setMobs() {
		super.mobs=new Mob[1];
		//x , y, name, level, weapon
		mobs[0]=new Mob(7, 2, "Michael", 1, WeaponCreator.createWeapon());
	}

}
