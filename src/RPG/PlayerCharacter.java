package RPG;

public class PlayerCharacter extends AbstractCharacter {

 private int xp;
 
 private static final int HP_INCREMENT = 100;
 
 // "Default constructor" - use this for a new character
 public PlayerCharacter(int x, int y, String name) {
      super(x,
            y,
            name,             //name
            HP_INCREMENT,           //hp
            HP_INCREMENT,           //maxHp
            1,             //level
            new Projectile("Pea shooter"), //weapon
            5, //actionPoints
            "Assets/player.png"
           ); //weapon
      xp = 0;
 }
 
 // use this for an existing character
 public PlayerCharacter(int x, int y, String name, int hp, int xp, int level, Weapon weapon) {
      super(x,
            y,
            name,             //name
            hp,             //hp
            HP_INCREMENT * level,         //maxHp
            level,            //level
            weapon,  //weapon
            5, //actionPoints
            "Assets/player.png"
           );            
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
      q.takeDamage(weapon().damage());
 }
 
 public void drop(Pickup p){
 }
 
 @Override
 public void pickUp(Pickup p){};
 
 @Override
 public void takeTurn(){};
 
}