package RPG;

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
			 System.out.println("Choose: U/D/L/R");
			 char direction =input.next().charAt(0);
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
 
 private int menu(){
	 
	 do{}
		while(!GameWindow.controller.isMenuChoiceMade());
	 int choice=GameWindow.controller.getMenuChoice();

	  
	 return choice;
 }
 
}