package RPG;

public interface Weapon {

     //EXPLOSIVE, BLADE, FOOD, MAGIC, PROJECTILE    
       
     public String name();
     
     public int damage();
     
     public int[][] range();
     //returns X,Y values of tiles that are in range of the weapon.
     
}