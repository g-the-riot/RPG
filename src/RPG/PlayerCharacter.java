package RPG;

import java.awt.Point;
import java.util.ArrayList;

public class PlayerCharacter extends AbstractCharacter {

 private int xp;
 
 private static final int HP_INCREMENT = 100;
 private int currentAP;
 public  ArrayList<Pickup> inventory = new ArrayList<Pickup>();
 
 // "Default constructor" - use this for a new character
 public PlayerCharacter(int x, int y, String name, AbstractWeapon w) {
      super(x,
            y,
            name,             //name
            HP_INCREMENT,           //hp
            HP_INCREMENT,           //maxHp
            1,             //level
            w, //weapon
            5, //actionPoints
            "Assets/player.png"
           ); 
      inventory.add(new Fists());
      inventory.add(w);
      xp = 0;
 }
 
 // use this for an existing character
 public PlayerCharacter(int x, int y, String name, int hp, int xp, int level, AbstractWeapon w) {
      super(x,
            y,
            name,             //name
            hp,             //hp
            HP_INCREMENT * level,         //maxHp
            level,            //level
            w,  //weapon
            5, //actionPoints
            "Assets/player.png"
           );
      inventory.add(new Fists());
      inventory.add(w);
      this.xp = xp;
 }
 
 public int xp() {
      return xp;
 }
 
 public int getCurrentAP(){
	 return currentAP;
 }
 
 public void setCurrentAP(int ap){
	 currentAP=ap;
 }
 
 public void gainXP (int xp) {
      xp = Math.abs(xp);
      this.xp = this.xp + xp;
      
 }
 
 public void attack(Character q) {
	 System.out.println(getName()+" is attacking "+((AbstractCharacter)q).getName()+"!");
     q.takeDamage(getWeapon().damage());
     System.out.println(((AbstractCharacter)q).getName()+" has "+((AbstractCharacter)q).hp()+" left!");
 }
 
 public void drop(Pickup p){
	 
	 ((BoardObject)p).getLocation().move((int)getLocation().getX(), (int)getLocation().getY());
	 
	 GameWindow.controller.getCurrentRoom().getObjects().add((BoardObject)p);
	 inventory.remove(((BoardObject)p).getIndex());
 }
 
 @Override
 public void pickUp(Pickup p){
	 inventory.add(p);
	 System.out.println(getName()+" just got a "+p.getName());
 };
 
 @Override
 public void takeTurn(Room r){

	 currentAP=this.getActionPoints();
	 
	 do{
		 System.out.println("You Currently have "+currentAP+" Action Points.");
		 int menuChoice = menu();
//		 GameWindow.controller.setMenuChoicePointer(menuChoice);
//		 GameWindow.frame.repaint();
		 if(menuChoice==1){
			 moveMenu(r);
		 }
		 else if(menuChoice==2){
			 attackMenu(r);
		 }
		 else if(menuChoice==3){
			inventoryMenu();
		 }
		 else{
			pickupMenu(r);
		 }
		 GameWindow.frame.repaint();
	 }
	 while(currentAP>0);
	
	 
 };
 
 private void attackMenu(Room r){
	 GameWindow.controller.setCurrentRound(GameController.ATTACK);
	 GameWindow.setMakeChoicePointer(1);
	 ArrayList<Point> range=getWeapon().getRange(getLocation()); //an array of points that are in range.
	 
	 for(int i=0;i<range.size();i++){
		 System.out.println(range.get(i)+" is in range!");
	 }
	 
	 boolean validMove=false;
	 int index=0;
	 for(int i=0; i<r.getObjects().size(); i++){
		 System.out.println("Checking if "+r.getObjects().get(i).getName()+" is a mob");
		 if(r.getObjects().get(i) instanceof Mob){
			 Mob q = (Mob)r.getObjects().get(i);//gets a list of mobs in the room
			 System.out.println("Found a mob!");
			 System.out.println("The mob is located at "+q.getLocation());
			 for(int j=0;j<range.size();j++){
			 	if(range.get(j).equals(q.getLocation())){
			 		System.out.println("Testing locations");
			 		q.setIndex(index+1); //an index of >0 is a flag that shows us they're in range.
			 		validMove=true;
			 		index++;
			 		System.out.println("a Mob is in range!");
			 	}
			 }//closes for(checking range)
		 }//closes if
	 }//closes for (checking for mobs)
	 System.out.println("ValidMove"+(validMove?"is":"is not")+"true");
	 if(validMove){
		 GameWindow.frame.repaint();
		 waitForInput();
		 int choice=GameWindow.controller.getMenuChoice();
		 Mob q=null;
		 for(int i=0;i<r.getObjects().size();i++){
			 if(r.getObjects().get(i).getIndex()==choice){
				 q=(Mob)r.getObjects().get(i);
				 q.setIndex(i);
				 System.out.println("a Mob is in range!");
			 }
		 }
		 
		 attack(q);
		 
		 r.getObjects().set(q.getIndex(), q);
		 
		 for(int i=0; i<r.getObjects().size(); i++){
			 if(r.getObjects().get(i) instanceof Mob){
				 r.getObjects().get(i).setIndex(0);
			 }//closes if
		 }//closes for (checking for mobs)
		 
		 currentAP--;
	 	}
	 else{
		 GameWindow.controller.setSystemMessage("Nobody's in range, dude!");
		 GameWindow.frame.repaint();
		 waitForInput();
		 GameWindow.controller.setNewSystemMessage(false);
		 GameWindow.frame.repaint();
	 }
 }
 
 private void moveMenu(Room r){
	 GameWindow.controller.setCurrentRound(GameController.MOVE);
	 System.out.println("Choose: U/D/L/R");
	 getValidMoves(r);
	 char direction =getDirection();
	 if(move(direction, r)){
		 currentAP--;
	 }
 }
 
 private void inventoryMenu(){
	 GameWindow.controller.setCurrentRound(GameController.INVENTORYMAIN);
	 GameWindow.cl.show(GameWindow.cards, "Inventory");
	 GameWindow.getInventoryPanel().requestFocus();
	 waitForInput();
	 int input=GameWindow.controller.getMenuChoice();
	 System.out.println("The choice is "+input);
	 Pickup p;
	 
	 if(input<inventory.size()&&input>=0){
		p=inventory.get(input);
		((BoardObject)p).setIndex(input);
		System.out.println("What do you want to do?");
		System.out.println("1) Use");
		System.out.println("2) Equip");
		System.out.println("3) Look");
		System.out.println("4) Drop");
		inventorySubMenu(p);
		GameWindow.cl.show(GameWindow.cards, "Room");
		System.out.println("I have allegedly told it to shift back to room now");
		GameWindow.getRoomPanel().requestFocus();
		waitForInput();
		GameWindow.controller.setNewSystemMessage(false);
		GameWindow.frame.repaint();
	 } 
	 else if(input==-1){
		 GameWindow.cl.show(GameWindow.cards, "Room");
		 System.out.println("I have allegedly told it to shift back to room now");
		 GameWindow.getRoomPanel().requestFocus();
	 }
 }
 
 private void inventorySubMenu(Pickup p){
	 	GameWindow.controller.setCurrentRound(GameController.INVENTORYSUB);
	 	GameWindow.getInventoryPanel().setYPointer(0);	
	 	GameWindow.frame.repaint();
		waitForInput();
		int input=GameWindow.controller.getMenuChoice();
		if(input==0){
			if(!(p instanceof AbstractWeapon)||p instanceof Food){
				p.use((AbstractCharacter)this);
				if(!p.isItReusable()){
					inventory.remove(((BoardObject)p).getIndex());
				}
				setCurrentAP(getCurrentAP()-1);
				//Each use method should set the system message itself.
			}
			else{
				GameWindow.controller.setSystemMessage("What? Like eat this weapon? No.  Use equip, dude.");
				System.out.println(GameWindow.controller.getSystemMessage());
			}
		}
		else if(input==1){
			if(p instanceof AbstractWeapon){
				changeWeapon((AbstractWeapon)p);
				GameWindow.controller.setSystemMessage(getName()+" equipped the "+p.getName());
				System.out.println(GameWindow.controller.getSystemMessage());
			}
			else{
				GameWindow.controller.setSystemMessage("Sorry! Rad as it would be, you can't equip "+p.getName());
				System.out.println(GameWindow.controller.getSystemMessage());
			}
		}
		else if(input==2){
			GameWindow.controller.setSystemMessage(p.look());
			System.out.println(GameWindow.controller.getSystemMessage());
		}
		else{
			if(p.isItKeyItem()){
				GameWindow.controller.setSystemMessage("No dude, that's way too important!");
				System.out.println(GameWindow.controller.getSystemMessage());
			}
			else{
				drop(p);
				GameWindow.controller.setSystemMessage("You gingerly place the item at your feet.");
				System.out.println(GameWindow.controller.getSystemMessage());
			}
		}
 }
 
 private void pickupMenu(Room r){
	 boolean pickedUp=false;
	 for(int i=0;i<r.getObjects().size();i++){				 
		 if(r.getObjects().get(i) instanceof Pickup){
			 if(getLocation().equals(r.getObjects().get(i).getLocation())){
				 pickUp((Pickup)r.getObjects().get(i));
				 r.getObjects().remove(i);
				 currentAP--;
				 pickedUp=true;
			 }// and you're standing on it.  pick it up
		 }//if the thing on the board is a pickup				
	 }
	 if(!pickedUp){
		 System.out.println("There's nothing to pick up!");
	 }
 }
 
 private char getDirection(){
	 waitForInput();
	 return GameWindow.controller.getMenuDirection();
 }
 
 public boolean move(char d, Room r){
	 
	 ArrayList<Point> validPoints=getValidMoves(r);
	  boolean valid=false;	
      int newX=(int)super.getLocation().getX();
      int newY=(int)super.getLocation().getY();
      
      if(d=='u'){
           newY-=1;
      }
      else if(d=='d'){
           newY+=1;
      }
      else if(d=='l'){
           newX-=1;
      }
      else if(d=='r'){
           newX+=1;
      }
      for(int i=0;i<validPoints.size();i++){
    	  if(newX==(int)validPoints.get(i).getX()&&newY==(int)validPoints.get(i).getY()){
    		  super.getLocation().move(newX,newY);
    		  valid=true;
    	  }
      }
      
      removeMenuItems(r);
      
      GameWindow.frame.repaint();
      //getting rid of my arrows.
      
      return valid;
 };
 
 public void removeMenuItems(Room r){
	 int menuItems=0;
	 
	 for(int i=0;i<r.getObjects().size();i++){
   	  if(r.getObjects().get(i) instanceof MenuItem){
   		  menuItems++;
   	  }
     }
	 
	 if(menuItems>0){
		 do{
			 for(int i=0;i<r.getObjects().size();i++){
		    	  if(r.getObjects().get(i) instanceof MenuItem){
		    		  r.getObjects().remove(i);
		    		  menuItems--;
		    	  }
		      }
		 }
		 while(menuItems>0);
		 
	 }
 }
 
 @Override
 public ArrayList<Point> getValidMoves(Room r){
	 
	  ArrayList<Point> valid = new ArrayList<Point>();
	  
     int newX=(int)super.getLocation().getX();
     int newY=(int)super.getLocation().getY();
     int dy;
     int dx;
     
     dy=newY-1;
          if(dy>0&&dy<=r.y-2){
               valid.add(new Point(newX,dy));
               r.getObjects().add(new MenuItem(newX, dy, MenuItem.ARROWUP, MenuItem.ARROWUP));
          }
          else{
               System.out.println("You can't move that direction!");
          }

     dy=newY+1;
          if(dy>0&&dy<=r.y-2){
       	   valid.add(new Point(newX,dy));
       	   r.getObjects().add(new MenuItem(newX, dy, MenuItem.ARROWDOWN, MenuItem.ARROWDOWN));
          }
          else{
               System.out.println("You can't move that direction!");
          }
     dx=newX-1;
          if(dx>0&&dx<=r.x-2){
       	   valid.add(new Point(dx,newY));
       	   r.getObjects().add(new MenuItem(dx, newY, MenuItem.ARROWLEFT, MenuItem.ARROWLEFT));
          }
          else{
               System.out.println("You can't move that direction!");
               
          }
     
     dx= newX+1;
          if(dx>0&&dx<=r.x-2){
       	   valid.add(new Point(dx,newY));
       	   r.getObjects().add(new MenuItem(dx, newY, MenuItem.ARROWRIGHT, MenuItem.ARROWRIGHT));
          }
          else{
               System.out.println("You can't move that direction!");
          }
          GameWindow.frame.repaint();
     return valid;
	 
}
 
 private int menu(){
	 GameWindow.controller.setCurrentRound(GameController.MENU);
	 waitForInput();
 	 return GameWindow.controller.getMenuChoice();
 }
 
 private void waitForInput(){
	 do{
		 System.out.print("");
	 }
	 while(!GameWindow.controller.isMenuChoiceMade());
	 GameWindow.controller.setMenuChoiceMade(false);
 }
 
}