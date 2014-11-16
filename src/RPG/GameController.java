package RPG;

import java.io.File;
import java.util.Scanner;
import java.io.IOException;

// Provides methods to control gameplay
public class GameController {

 public GameController () {}
 
 /* Returns a Character object with the given name as found in the file at the provided path.
  * Assumes all character names are unique.
  */     
 public AbstractCharacter getCharacter(String path, String name) throws IOException {
      AbstractCharacter c = null;
      File playerData = new File(path);
      Scanner reader = new Scanner(playerData);
      while (reader.hasNextLine()) {
           String[] playerInfo = reader.nextLine().split(",");
           if(playerInfo[0].equals(name)) {
                c = createCharacter(playerInfo);
           } 
      }
      reader.close();
      return c; 
 }
 
 public void takeTurn(Character first, Character second) {
      first.attack(second); 
 }
 
 public void reviveCharacter(Character c) {
      
 }
 
 //Auxilliary methods
 
 private AbstractCharacter createCharacter(String[] playerInfo) {
      AbstractCharacter c = null;
      Weapon weapon = null;
      int level = 1;
      String name = playerInfo[0];
      String type = playerInfo[1];
      if(type.equals("Player Character")) {
           int hp = Integer.parseInt(playerInfo[5]);
           int xp = Integer.parseInt(playerInfo[6]);
           level = Integer.parseInt(playerInfo[2]);
                //EXPLOSIVE, BLADE, FOOD, MAGIC, PROJECTILE 
           if(playerInfo[3].equals("Explosive")){
                weapon = new Explosive(playerInfo[4]);
           }
           else if(playerInfo[3].equals("Blade")){
                weapon = new Blade(playerInfo[4]);
           }
           else if(playerInfo[3].equals("Food")){
                weapon = new Food(playerInfo[4]);
           }
           else if(playerInfo[3].equals("Magic")){
                weapon = new Magic(playerInfo[4]);
           }              
           else if(playerInfo[3].equals("Projectile")){
                weapon = new Projectile(playerInfo[4]);
           }
           
           c = new PlayerCharacter(0,0,name, hp, xp, level, weapon); //junk data in for position
      }
      else if (type.equals("Mob")) {
           level = Integer.parseInt(playerInfo[2]);
           if(playerInfo[3].equals("Explosive")){
                weapon = new Explosive(playerInfo[4]);
           }
           else if(playerInfo[3].equals("Blade")){
                weapon = new Blade(playerInfo[4]);
           }
           else if(playerInfo[3].equals("Food")){
                weapon = new Food(playerInfo[4]);
           }
           else if(playerInfo[3].equals("Magic")){
                weapon = new Magic(playerInfo[4]);
           }              
           else if(playerInfo[3].equals("Projectile")){
                weapon = new Projectile(playerInfo[4]);
           }
           c = new Mob(0,0,name,level, weapon);//junk data in for position
      }
      return c;   
 }

/**
 * [0] = name
 * [1] = type of character
 * [2] = level
 * [3] = weapon type
 * [4] = weapon name
 * [5] = HP (player character only)
 * [6] = XP (player character only)
 * */

}