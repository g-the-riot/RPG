package RPG;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class GameIO{

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

}