package RPG;

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
	 
	  boolean valid=false;	
      int newX=(int)super.getLocation().getX();
      int newY=(int)super.getLocation().getY();
      
      if(d=='u'){
           newY-=1;
           if(newY>=0&&newY<=r.y-2){
                super.getLocation().move(newX,newY);
                valid=true;
           }
           else{
                System.out.println("You can't move that direction!");
           }
      }
      else if(d=='d'){
           newY+=1;
           System.out.println(newY);
           if(newY>=0&&newY<=r.y-2){
                super.getLocation().move(newX,newY);
                valid=true;
           }
           else{
                System.out.println("You can't move that direction!");
           }
      }
      else if(d=='l'){
           newX-=1;
           if(newX>=0&&newX<=r.x-2){
                super.getLocation().move(newX,newY);
                valid=true;
           }
           else{
                System.out.println("You can't move that direction!");
           }
      }
      else if(d=='r'){
           newX+=1;
           if(newX>0&&newX<=r.x-2){
        	   super.getLocation().move(newX,newY);
        	   valid=true;
           }
           else{
                System.out.println("You can't move that direction!");
           }
      }
      return valid;
 };
 
 public abstract void attack(Character q);

public int getActionPoints() {
	return actionPoints;
}

public void setActionPoints(int actionPoints) {
	this.actionPoints = actionPoints;
}
}
