package RPG;

import java.awt.Point;

public interface Weapon {

     //EXPLOSIVE, BLADE, FOOD, MAGIC, PROJECTILE    
       
     public String name();
     
     public int damage();
     
     public Point[] getRange(Point p);
     //returns X,Y values of tiles that are in range of the weapon.
     
}