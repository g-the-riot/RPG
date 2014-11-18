package RPG;

public class Door extends BoardObject{


	//Name should be the ID of the room that this door connects to.
	
	private boolean locked;
	private boolean goneThru;
	
	
	public Door (int x, int y, String name){
		super(x, y, name, setPath(x,y)); //the null is the path to the image for the door
		locked=true;
		goneThru=false;		
	}
	
	private static String setPath(int x, int y){
		String path;
		if (x==0){
			path="Assets/doorlockedW.png";
		}
		else if(x==9){
			path="Assets/doorlockedE.png";
		}
		else if(y==0){
			path="Assets/doorlocked.png";
		}
		else{
			path="Assets/doorlockedS.png";
		}
		return path;
	}
	
	public void unlock(){
		locked=false;
	}
	
	public void goneThru(){
		goneThru=true;
	}

}