package RPG;

import java.util.ArrayList;

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
	private String lastRoomID;
	private PlayerCharacter player;
	private int menuChoice;
	private char menuDirection;
	private boolean menuChoiceMade=false;
	private PlayerCharacter q;
	private ArrayList<BoardObject> currentObjects;
	
	public GameController () {
		setCurrentRoom(rooms[0]);
		lastRoomID="Outside";
		init();
	}
 
	/* Returns a Character object with the given name as found in the file at the provided path.
	 * Assumes all character names are unique.
	 */
	
	private void init(){
		createCharacter("Joseph");
		currentRoom.setObjects(player, allItems);
		currentObjects=currentRoom.getObjects();
	}
 
	public void createCharacter(String name){
		Door lastDoor=null;
		
		for(int i=0; i<currentRoom.doors.length;i++){
			if(currentRoom.doors[i].getName().equals(lastRoomID)){
				lastDoor=currentRoom.doors[i];
			}			
		}
		
		int x = (int)lastDoor.getLocation().getX();
		int y = (int)lastDoor.getLocation().getY();
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
		player = new PlayerCharacter(x,y,name, WeaponCreator.createWeapon());
	}
	
	public PlayerCharacter getPlayer(){
		return player;
	}

	public PlayerCharacter getQ(){
		return q;
	}
 
	public void takeTurn() {
		
		for(int i=0;i<currentObjects.size();i++){
			
			if(currentObjects.get(i) instanceof PlayerCharacter){
				q=(PlayerCharacter)currentObjects.get(i);
				q.takeTurn(currentRoom);
				currentObjects.set(i, q);
			}
			
			else if(currentObjects.get(i) instanceof Mob){
				Mob q=(Mob)currentObjects.get(i);
				q.takeTurn(currentRoom);
				currentObjects.set(i, q);
			}
		
		}
		
	}
 
	public void reviveCharacter(Character c) {
		
	}

	public Room getCurrentRoom() {
		return currentRoom;
	}

	private void setCurrentRoom(Room currentRoom) {
		this.currentRoom = currentRoom;
	}

	public int getMenuChoice() {
		return menuChoice;
	}

	public void setMenuChoice(int menuChoice) {
		this.menuChoice = menuChoice;
	}

	public boolean isMenuChoiceMade() {
		return menuChoiceMade;
	}

	public void setMenuChoiceMade(boolean menuChoiceMade) {
		this.menuChoiceMade = menuChoiceMade;
	}

	public char getMenuDirection() {
		return menuDirection;
	}

	public void setMenuDirection(char menuDirection) {
		this.menuDirection = menuDirection;
	}
 
	//Auxilliary methods
 
 
 }