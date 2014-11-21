package RPG;

import java.awt.Point;
import java.util.ArrayList;

public abstract class AbstractCharacter extends BoardObject implements Character {
     
     
     private int hp;
     private int maxHp;
     private int level;
     private AbstractWeapon weapon;
     private int actionPoints;

 public AbstractCharacter(int x, int y, String name, int hp, int maxHp, int level, AbstractWeapon weapon, int actionPoints, String path) {
      super(x , y , name, path);
      this.maxHp = maxHp;
      this.hp = hp;
      this.level = level;
      this.weapon = weapon; 
      this.setActionPoints(actionPoints);
 }
 
 public String name() {
      return super.getName();
 }
 
 public int maxHP(){
	 return maxHp;
 }
 
 public int hp() {
      return hp;
      
 }
 
 public int level() {
      return level;
      
 }
 
 public boolean isDead() {
      return (hp <= 0);
  
 }
 
 public AbstractWeapon getWeapon() {
  return weapon;
  
 }
 
 public void changeWeapon(AbstractWeapon newWeapon) {
      weapon = newWeapon;
 }
 
 public void takeDamage(int damage) {
      //handling the error case may obfuscate a problem in the client
      damage = Math.abs(damage);
      System.out.println(damage+" HP of damage to "+getName());
      if(damage >= hp) {
           hp = 0;
      }
      else {
           hp = hp - damage;
      }
      
 }
 
 // contract here
 public void gainHp(int hp) {
      hp = Math.abs(hp);
      if (this.hp + hp <= maxHp) {
           this.hp = this.hp + hp;
      }
      else {
           this.hp = maxHp;
      }
 }
 /**Relevant methods from Point:
  * getX()
  * getY()
  * move(int x, int y)
  * distance(Point p)
  * equals() -AVOID COLLISIONS
  * */
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
      return valid;
 };
 
 public ArrayList<Point> getValidMoves(Room r){
	 
	  ArrayList<Point> valid = new ArrayList<Point>();
	  
      int newX=(int)super.getLocation().getX();
      int newY=(int)super.getLocation().getY();
      int dy;
      int dx;
      
      dy=newY-1;
           if(dy>=0&&dy<=r.y-2){
                valid.add(new Point(newX,dy));
           }
           else{
                System.out.println("You can't move that direction!");
           }

      dy=newY+1;
           if(dy>=0&&dy<=r.y-2){
        	   valid.add(new Point(newX,dy));
           }
           else{
                System.out.println("You can't move that direction!");
           }
      dx=newX-1;
           if(dx>=0&&dx<=r.x-2){
        	   valid.add(new Point(dx,newY));
           }
           else{
                System.out.println("You can't move that direction!");
                
           }
      
      dx= newX+1;
           if(dx>0&&dx<=r.x-2){
        	   valid.add(new Point(dx,newY));
           }
           else{
                System.out.println("You can't move that direction!");
           }
      return valid;
	 
 }
 
 public abstract void attack(Character q);

public int getActionPoints() {
	return actionPoints;
}

public void setActionPoints(int actionPoints) {
	this.actionPoints = actionPoints;
}
}
