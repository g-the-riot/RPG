package RPG;

import java.util.ArrayList;
import java.util.Scanner;

public class PlayerCharacter extends AbstractCharacter {

 private int xp;
 
 private static final int HP_INCREMENT = 100;
 private static ArrayList<Pickup> inventory = new ArrayList<Pickup>();
 
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
      q.takeDamage(getWeapon().damage());
 }
 
 public void drop(Pickup p){
 }
 
 @Override
 public void pickUp(Pickup p){};
 
 @Override
 public void takeTurn(Room r){
	 Scanner input = new Scanner(System.in);

	 int currentAP=this.getActionPoints();
	 
	 do{
		 int menuChoice = menu();
	 
		 if(menuChoice==1){
			 System.out.println("Choose: U/D/L/R");
			 char direction =input.next().charAt(0);
			 move(direction, r);
			 currentAP--;
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
			 Mob q=(Mob)r.getObjects().get(makeAChoice);
			 attack(q);
			 r.getObjects().set(makeAChoice, q);
			 currentAP--;
		 }
		 else{
			 //TODO: create an inventory implementation.
		 }
		 GameWindow.frame.repaint();
	 }
	 while(currentAP>0);
	 
	 input.close();
	 
 };
 
 private int menu(){
	 
	 Scanner input = new Scanner(System.in);
	 
	 System.out.println("Make a choice:");
	 System.out.println("1. Move");
	 System.out.println("2. Attack");
	 System.out.println("3. Inventory");
 
 
	 return input.nextInt();
 }
 
}