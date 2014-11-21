package RPG;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Scanner;

public class PlayerCharacter extends AbstractCharacter {

 private int xp;
 
 private static final int HP_INCREMENT = 100;
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
 }
 
 @Override
 public void pickUp(Pickup p){
	 inventory.add(p);
	 System.out.println(getName()+" just got a "+p.getName());
 };
 
 @Override
 public void takeTurn(Room r){
	 Scanner input = new Scanner(System.in);

	 int currentAP=this.getActionPoints();
	 
	 do{
		 System.out.println("You Currently have "+currentAP+" Action Points.");
		 int menuChoice = menu();
//		 GameWindow.controller.setMenuChoicePointer(menuChoice);
//		 GameWindow.frame.repaint();
		 if(menuChoice==1){
			 GameWindow.controller.setCurrentRound(GameController.MOVE);
			 System.out.println("Choose: U/D/L/R");
			 getValidMoves(r);
			 char direction =getDirection();
			 if(move(direction, r)){
				 currentAP--;
			 }
		 }
		 else if(menuChoice==2){
			 System.out.println("Which enemy?");
			 for(int i=0; i<r.getObjects().size(); i++){
				 if(r.getObjects().get(i) instanceof Mob){
					 Mob q = (Mob)r.getObjects().get(i);
					 System.out.println((i+1)+".) "+q.getName());
				 }
			 }
			 int makeAChoice=input.nextInt();
			 Mob q=(Mob)r.getObjects().get(makeAChoice-1);
			 attack(q);
			 r.getObjects().set(makeAChoice-1, q);
			 currentAP--;
		 }
		 else if(menuChoice==3){
			 GameWindow.cl.show(GameWindow.cards, "Inventory");
			 System.out.println("Woah! Look at all your stuff!");
			 System.out.println("Hit Enter to go back.");
			 input.next();
			 GameWindow.cl.show(GameWindow.cards, "Room");
			 //TODO: create an inventory implementation.
		 }
		 else{
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
		 GameWindow.frame.repaint();
	 }
	 while(currentAP>0);
	 
	 input.close();
	 
 };
 
 private char getDirection(){
	 do{
		 System.out.print("");
	 }
	 while(!GameWindow.controller.isMenuChoiceMade());
	 GameWindow.controller.setMenuChoiceMade(false);
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
          if(dy>=0&&dy<=r.y-2){
               valid.add(new Point(newX,dy));
               r.getObjects().add(new MenuItem(newX, dy, "arrowup", MenuItem.ARROWUP));
          }
          else{
               System.out.println("You can't move that direction!");
          }

     dy=newY+1;
          if(dy>=0&&dy<=r.y-2){
       	   valid.add(new Point(newX,dy));
       	   r.getObjects().add(new MenuItem(newX, dy, "arrowdown", MenuItem.ARROWDOWN));
          }
          else{
               System.out.println("You can't move that direction!");
          }
     dx=newX-1;
          if(dx>=0&&dx<=r.x-2){
       	   valid.add(new Point(dx,newY));
       	   r.getObjects().add(new MenuItem(dx, newY, "arrowleft", MenuItem.ARROWLEFT));
          }
          else{
               System.out.println("You can't move that direction!");
               
          }
     
     dx= newX+1;
          if(dx>0&&dx<=r.x-2){
       	   valid.add(new Point(dx,newY));
       	   r.getObjects().add(new MenuItem(dx, newY, "arrowright", MenuItem.ARROWRIGHT));
          }
          else{
               System.out.println("You can't move that direction!");
          }
          GameWindow.frame.repaint();
     return valid;
	 
}
 
 private int menu(){
	 GameWindow.controller.setCurrentRound(GameController.MENU);
	 do{
		 System.out.print("");
	 }
	 while(!GameWindow.controller.isMenuChoiceMade());
	 GameWindow.controller.setMenuChoiceMade(false);
 	 return GameWindow.controller.getMenuChoice();
 }
 
}