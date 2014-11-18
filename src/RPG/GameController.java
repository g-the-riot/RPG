package RPG;

import RPG.Rooms.*;

// Provides methods to control gameplay
public class GameController {

	private Room[] rooms={(new TestRoom())};
	private BoardObject[] allItems={
			(WeaponCreator.createWeapon()),
			(WeaponCreator.createWeapon()),
			(WeaponCreator.createWeapon()),
			(WeaponCreator.createWeapon()),
			(WeaponCreator.createWeapon()),};
	private Room currentRoom;
	private PlayerCharacter player;
	
	public GameController () {
		setCurrentRoom(rooms[0]);
		init();
	}
 
	/* Returns a Character object with the given name as found in the file at the provided path.
	 * Assumes all character names are unique.
	 */
	
	private void init(){
		createCharacter("Joseph");
		currentRoom.setObjects(player, allItems);
	}
 
	public void createCharacter(String name){
		System.out.println(rooms[0]);
		System.out.println(rooms[0].doors[0]);
		System.out.println(rooms[0].doors[0].getLocation());
		System.out.println(rooms[0].doors[0].getLocation().getX());
		int x = (int)rooms[0].doors[0].getLocation().getX();
		int y = (int)rooms[0].doors[0].getLocation().getY();
		if(x==0){
			x+=1;
		}
		else if(x==9){
			x-=1;
		}
		else if(y==0){
			y+=1;
		}
		else{
			y-=1;
		}
		player = new PlayerCharacter(x,y,name);
	}
	
	public PlayerCharacter getPlayer(){
		return player;
	}

 
	public void takeTurn(Character first, Character second) {
      first.attack(second); 
	}
 
	public void reviveCharacter(Character c) {
		
	}

	public Room getCurrentRoom() {
		return currentRoom;
	}

	private void setCurrentRoom(Room currentRoom) {
		this.currentRoom = currentRoom;
	}
 
	//Auxilliary methods
 
 
 }