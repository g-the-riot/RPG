package RPG;

public class Door extends BoardObject{


	//Name should be the ID of the room that this door connects to.
	
	private boolean locked;
	private boolean goneThru;
	
	public Door (int x, int y, String name){
		super(x, y, name);
		locked=true;
		goneThru=false;
	}
	
	public void unlock(){
		locked=false;
	}
	
	public void goneThru(){
		goneThru=true;
	}

}