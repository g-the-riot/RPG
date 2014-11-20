package RPG;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

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
     AbstractWeapon weapon = null;
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
 
 public static Image createImage(String imgPath){
	    BufferedImage bufferedImage = null;
	    try {
		   bufferedImage = ImageIO.read(new File(imgPath));
		} 
	    catch (IOException e) {
		}
	    return bufferedImage;
 }
 
 public static BufferedImage[] loadRoomComponents(){
	 
	 BufferedImage[] roomComponents= new BufferedImage[9];
	 
		try {
			roomComponents[0] = ImageIO.read(new File("Assets/tile.png"));
			} 
		catch (IOException e) {
			}
		try {
			roomComponents[1] = ImageIO.read(new File("Assets/cornernw.png"));
			} 
		catch (IOException e) {
			}
		try {
			roomComponents[2] = ImageIO.read(new File("Assets/wall_n.png"));
			} 
		catch (IOException e) {
			}
		try {
			roomComponents[3] = ImageIO.read(new File("Assets/cornerne.png"));
			} 
		catch (IOException e) {
			}
		try {
			roomComponents[4] = ImageIO.read(new File("Assets/wall_e.png"));
			} 
		catch (IOException e) {
			}
		try {
			roomComponents[5] = ImageIO.read(new File("Assets/cornerse.png"));
			} 
		catch (IOException e) {
			}
		try {
			roomComponents[6] = ImageIO.read(new File("Assets/wall_s.png"));
			} 
		catch (IOException e) {
			}
		try {
			roomComponents[7] = ImageIO.read(new File("Assets/cornersw.png"));
			} 
		catch (IOException e) {
			}
		try {
			roomComponents[8] = ImageIO.read(new File("Assets/wall_w.png"));
			} 
		catch (IOException e) {
			}
		
		return roomComponents;
	}

}