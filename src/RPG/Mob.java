package RPG;

import java.awt.Point;

public class Mob extends AbstractCharacter {
 
 private static final int HP_INCREMENT = 200;
 //public static boolean timeFreeze=false;
 
 //int x, int y, String name, int hp, int maxHp, int level, Weapon weapon, int actionPoints, String path
 
 public Mob(int x, int y, String name, int level, AbstractWeapon weapon) { 
      super(x, //position
            y, //position
            name,             //name
            HP_INCREMENT * level,         //hp
            HP_INCREMENT * level,         //maxHp
            level,            //level
            weapon,  //weapon 
            3,
            "Assets/mob.png" //path to image
            ); //actionPoints           
 }

 // Overriding to remove functionality
 public void changeName(String newName){

 }

 
 public void attack(Character q) {
	 System.out.println("Mob "+getName()+" is attacking "+((AbstractCharacter)q).getName()+"!");
	 System.out.println(((AbstractCharacter)q).getName()+" has "+((AbstractCharacter)q).hp()+" left!");
     q.takeDamage(getWeapon().damage());
 }
 
 @Override
 public void takeTurn(Room r){
	 int currentAP=this.getActionPoints();
	 BoardObject AITarget=setAITarget(r);
	 boolean targetComplete=false;
	 
	 do{
		 if(!targetComplete){
			 if(checkRange(AITarget)){
				 if(AITarget instanceof PlayerCharacter){
					 PlayerCharacter q=(PlayerCharacter) AITarget;
					 attack(q);
					 r.getObjects().set(0, q);
					 }//closes if
				 else{
					 AbstractWeapon q= (AbstractWeapon) AITarget;
					 pickUp((Pickup)q);
					 r.getObjects().remove(AITarget.getIndex());
					 targetComplete=true;
					 }//closes else
			 }//closes what happens if the target is within range.
			 else{
				 if(move(checkDirection(AITarget), r)){
				 }
			 }
			 currentAP--;
		 }
		 else{
			 AITarget=setAITarget(r); //if the mob picks up an item it needs a new target.
		 }
		 GameWindow.frame.repaint();
	 }
	 while(currentAP>0);
 }
	 
 
 private char checkDirection(BoardObject AITarget){
	 
	 char direction;
	 
	 int dx= (int)(this.getLocation().getX()-AITarget.getLocation().getX());
	 int dy= (int)(this.getLocation().getY()-AITarget.getLocation().getY());
	 
	 if(Math.abs(dx)>Math.abs(dy)){
		 if(dx>0){
			 direction='l';
		 }
		 else{
			 direction='r';
		 }
	 }
	 else{
		 if(dy>0){
			 direction='u';
		 }
		 else{
			 direction='d';
		 }
	 }
	 System.out.println("The mob has decided to move "+direction);
	 return direction;
 }
 
 private boolean checkRange(BoardObject AITarget){
	 
	 boolean inRange=false;
	 
	 if(AITarget instanceof PlayerCharacter){
		 Point[] range=this.getWeapon().getRange(this.getLocation());
		 
		 for(int i=0; i<range.length;i++){
		 	if(AITarget.getLocation().equals(range[i])){
		 		do{
		 			inRange=true;
		 		}
		 		while(!inRange);
		 		}
		 	}
		 }//closes playerCharacter Conditional
 

	 else{
	 		if(this.getLocation().equals(AITarget.getLocation())){
	 			inRange=true;
	 		}
	 	}//pickups can only be taken if you're standing on top of them.
	 /*	 	Also, crap, ranged weapons make this difficult.  For right now we're going to assume that all weapons have the same range 
	 	of one space orthogonally adjacent to the character*/

	 	
	 
	 return inRange;
 }
 
 private BoardObject setAITarget(Room r){
	 
	 BoardObject AITarget=r.getObjects().get(0);
	 //initial target = PlayerCharacter.
	 for(int i=1; i<r.getObjects().size(); i++){
		 if(r.getObjects().get(i) instanceof AbstractWeapon){
			 if(this.getLocation().distance(r.getObjects().get(i).getLocation())<this.getLocation().distance(AITarget.getLocation())){
				 AITarget=r.getObjects().get(i);
				 AITarget.setIndex(i);
			 }
		 }//if a weapon is closer to the Mob than the player character, it will go for that first.
	 }
	 System.out.println("The Mob is Targeting"+AITarget);
	 
	 return AITarget;
 }
 
 @Override
 public void pickUp(Pickup p){
	 this.changeWeapon((AbstractWeapon)p);
	 System.out.println("The mob has picked up a "+p);
 };
}