package RPG;

import java.util.ArrayList;
import java.util.Random;

public abstract class Room{
	
	public final int x;
	public final int y;
	public final String id;
	private boolean roomClear;
	public Door[] doors;
	protected Mob[] mobs;
	private ArrayList<BoardObject> roomObjects= new ArrayList<BoardObject>();
	
	public Room(int x, int y, String id){
	
		this.x=x;
		this.y=y;
		this.id=id.toLowerCase();
		roomClear=false;
	}
	
	public abstract void  setDoors();
	
	public abstract void setMobs();
	
	public ArrayList<BoardObject> getObjects(){
		return roomObjects;
	}
	
	public Door[] getDoors(){
		return doors;
	}
	
	public void setObjects(PlayerCharacter c, BoardObject[] all){
		
		setDoors();
		
		setMobs();
		
		Random randumb = new Random();
		
		roomObjects.add(c);
		//adds Character to room
		
		for(int i=0; i<mobs.length; i++){
			roomObjects.add(mobs[i]);
		}//adds mobs to room
		
		for(int i=0; i<doors.length; i++){
			roomObjects.add(doors[i]);
		}//adds doors to room
		
		for(int i=0; i<randumb.nextInt(4); i++){
			if(!(all[1] instanceof Character)){
				roomObjects.add(all[randumb.nextInt(all.length)]);
			}
		}//creates a random number of random items on the gameBoard. Minimum of one item, max of 3.
		
	}
	
	/**
	 * X & Y are the dimensions of the room.
	 * 
	 * the array of Doors will contain all doors the room contains starting from 0,0.  (no doors will appear on corners so if Y=0, 
	 * it'll display a door going north, if y=room.y it will display a south
	*door, Similarly if x=0 we get a door going west and x=room.x we get a door going East, 
	**/
	

}