package RPG;

public class Room{
	
	public final int x;
	public final int y;
	public final String id;
	private boolean roomClear;
	private Door[] doors;
	
	public Room(int x, int y, String id, Door[] doors){
	
		this.x=x;
		this.y=y;
		this.id=id.toLowerCase();
		roomClear=false;
		this.doors=doors;
	
	}
	
	/**
	 * X & Y are the dimensions of the room.
	 * 
	 * the array of Doors will contain all doors the room contains starting from 0,0.  (no doors will appear on corners so if Y=0, 
	 * it'll display a door going north, if y=room.y it will display a south
	*door, Similarly if x=0 we get a door going west and x=room.x we get a door going East, 
	**/
	

}