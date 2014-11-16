package RPG;

public class Mob extends AbstractCharacter {
 
 private static final int HP_INCREMENT = 200;
 //public static boolean timeFreeze=false;
 
 public Mob(int x, int y, String name, int level, Weapon weapon) { 
      super(x, //position
            y, //position
            name,             //name
            HP_INCREMENT * level,         //hp
            HP_INCREMENT * level,         //maxHp
            level,            //level
            weapon,  //weapon 
            3 ); //actionPoints           
 }

 // Overriding to remove functionality
 public void changeName(String newName){

 }
 
 // Overriding to remove functionality
 public void changeWeapon(Weapon newWeapon) {

 }
 
 public void attack(Character q) {
  // Need to implement this still!
 }
 
 @Override
 public void takeTurn(){};
 
 @Override
 public void pickUp(Pickup p){};
}