package RPG;

import java.io.IOException;

public class RPGTest {
 
 public static void main(String[] args) throws IOException{
  
      GameController game = new GameController();
      //AbstractCharacter c = game.getCharacter("C:\\Users\\gtheriot\\Desktop\\RPGCooking\\RPG\\Characters.txt", "Bob");
      //AbstractCharacter d = game.getCharacter("C:\\Users\\gtheriot\\Desktop\\RPGCooking\\RPG\\Characters.txt", "Mob");
      //System.out.println(c.name());
      //System.out.println(d.name());
      
      AbstractWeapon w=new Fists();
      w.use();
      System.out.println(w.look());
                              
 /**Relevant methods from Point:
  * getX()
  * getY()
  * move(int x, int y)
  * distance(Point p)
  * equals() -AVOID COLLISIONS
  * */
 }

}