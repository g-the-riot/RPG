package RPG;

import java.awt.Point;
import java.util.ArrayList;

public interface Weapon {

     //EXPLOSIVE, BLADE, FOOD, MAGIC, PROJECTILE    
       
     public String name();
     
     public int damage();
     
     public ArrayList<Point> getRange(Point p);
     //returns X,Y values of tiles that are in range of the weapon.
     
}