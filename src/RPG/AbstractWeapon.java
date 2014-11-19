package RPG;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public abstract class AbstractWeapon extends BoardObject implements Weapon, Pickup {

     private int damage;
     
     
     public AbstractWeapon(String name, String imgPath, int damage) {
          super(name,imgPath);
          this.damage=damage;
     }
                    
     public AbstractWeapon(int x, int y, String name, String imgPath, int damage) {
          super(x , y , name, imgPath);
          this.damage=damage;
     }
     
     public String name() {
          return super.getName();
     }
     
     public int damage() {
          return damage;
     }
     
     @Override
     public String toString(){
          return ("with a name of "+name());
     }
     
}

class Explosive extends AbstractWeapon{
     
	 
	
     public Explosive(String name){
          super(name, "Assets/pickup.png", 5);          
     }
     
     public Explosive(int x, int y, String name){
         super(x, y ,name, "Assets/pickup.png", 5);          
    }
     
     @Override
     public String toString(){
          return ("I am an explosive weapon "+super.toString());
     }
     
     @Override
     public void use(){}
     
     @Override
     public String look(){
          return null;
     }
     
     @Override
     public Point[] getRange(Point p){
    	 ArrayList<Point> range = new ArrayList<Point>();
    	 
    	 if((p.getX()-1)!=0){
    		range.add(new Point((int)p.getX()-1,(int)p.getY()));
    	 }
    	 
    	 if((p.getX()+1)!=9){
    		range.add(new Point((int)p.getX()+1,(int)p.getY()));
    	 }
    	 
    	 if((p.getY()-1)!=0){
    		range.add(new Point((int)p.getX(),(int)p.getY()-1));
    	 }
    	 
    	 if((p.getY()+1)!=9){
    		range.add(new Point((int)p.getX(),(int)p.getY()+1));
    	 }    	  	
    	 
    	 return range.toArray(new Point[range.size()]);	 
     }
}

class Blade extends AbstractWeapon{
	
	
     
     public Blade(String name){
          super(name, "Assets/pickup.png", 4);
     }
     
     public Blade(int x, int y, String name){
         super(x, y, name, "Assets/pickup.png", 4);
    }
     
     @Override
     public String toString(){
          return ("I am a Blade weapon "+super.toString());
     }
     
     @Override
     public void use(){}
     
     @Override
     public String look(){
          return null;
     }
     
     @Override
     public Point[] getRange(Point p){
    	 ArrayList<Point> range = new ArrayList<Point>();
    	 
    	 if((p.getX()-1)!=0){
    		range.add(new Point((int)p.getX()-1,(int)p.getY()));
    	 }
    	 
    	 if((p.getX()+1)!=9){
    		range.add(new Point((int)p.getX()+1,(int)p.getY()));
    	 }
    	 
    	 if((p.getY()-1)!=0){
    		range.add(new Point((int)p.getX(),(int)p.getY()-1));
    	 }
    	 
    	 if((p.getY()+1)!=9){
    		range.add(new Point((int)p.getX(),(int)p.getY()+1));
    	 }    	  	
    	 
    	 return range.toArray(new Point[range.size()]);	 
     }
}

class Magic extends AbstractWeapon{
	

     
     public Magic (String name){
          super(name, "Assets/pickup.png" ,3);
     }
     
     public Magic (int x, int y, String name){
         super(x, y, name, "Assets/pickup.png" ,3);
    }
     
     @Override
     public String toString(){
          return ("I am a Magic weapon "+super.toString());
     }
     
     @Override
     public void use(){}
     
     @Override
     public String look(){
          return null;
     }
     
     @Override
     public Point[] getRange(Point p){
    	 ArrayList<Point> range = new ArrayList<Point>();
    	 
    	 if((p.getX()-1)!=0){
    		range.add(new Point((int)p.getX()-1,(int)p.getY()));
    	 }
    	 
    	 if((p.getX()+1)!=9){
    		range.add(new Point((int)p.getX()+1,(int)p.getY()));
    	 }
    	 
    	 if((p.getY()-1)!=0){
    		range.add(new Point((int)p.getX(),(int)p.getY()-1));
    	 }
    	 
    	 if((p.getY()+1)!=9){
    		range.add(new Point((int)p.getX(),(int)p.getY()+1));
    	 }    	  	
    	 
    	 return range.toArray(new Point[range.size()]);	 
     }
}

class Food extends AbstractWeapon{
	

     
     public Food (String name){
          super(name, "Assets/pickup.png", 2);
     }
     
     public Food (int x, int y, String name){
         super(x,y, name, "Assets/pickup.png", 2);
    }
     
     @Override
     public String toString(){
          return ("I am Food "+super.toString());
     }
     
     @Override
     public void use(){}
     
     @Override
     public String look(){
          return null;
     }
     
     @Override
     public Point[] getRange(Point p){
    	 ArrayList<Point> range = new ArrayList<Point>();
    	 
    	 if((p.getX()-1)!=0){
    		range.add(new Point((int)p.getX()-1,(int)p.getY()));
    	 }
    	 
    	 if((p.getX()+1)!=9){
    		range.add(new Point((int)p.getX()+1,(int)p.getY()));
    	 }
    	 
    	 if((p.getY()-1)!=0){
    		range.add(new Point((int)p.getX(),(int)p.getY()-1));
    	 }
    	 
    	 if((p.getY()+1)!=9){
    		range.add(new Point((int)p.getX(),(int)p.getY()+1));
    	 }    	  	
    	 
    	 return range.toArray(new Point[range.size()]);	 
     }
}

class Projectile extends AbstractWeapon{
	

     
     public Projectile (String name){
          super(name, "Assets/pickup.png", 2);
     }
     
     public Projectile (int x, int y, String name){
         super(x, y, name, "Assets/pickup.png", 2);
    }
     
     @Override
     public String toString(){
          return ("I am a projectile weapon "+super.toString());
     }
     
     @Override
     public void use(){}
     
     @Override
     public String look(){
          return null;
     }
     
     @Override
     public Point[] getRange(Point p){
    	 ArrayList<Point> range = new ArrayList<Point>();
    	 
    	 if((p.getX()-1)!=0){
    		range.add(new Point((int)p.getX()-1,(int)p.getY()));
    	 }
    	 
    	 if((p.getX()+1)!=9){
    		range.add(new Point((int)p.getX()+1,(int)p.getY()));
    	 }
    	 
    	 if((p.getY()-1)!=0){
    		range.add(new Point((int)p.getX(),(int)p.getY()-1));
    	 }
    	 
    	 if((p.getY()+1)!=9){
    		range.add(new Point((int)p.getX(),(int)p.getY()+1));
    	 }    	  	
    	 
    	 return range.toArray(new Point[range.size()]);	 
     }
}

class Fists extends AbstractWeapon{
	


     private String[] options={"Here is the church... here is the steeple...",
               "You stick your hands in your pockets.  There's a lot of stuff in here!",
               "In the middle of the fight, you stare at your hand. Have you ever really stared at your hand?  Trippy!",
               "You make a fist.  You can do some damage with these.",
               "You slap yourself in the face.  You don't know why you do this."};
     
     public Fists(){
          super("Your Bare Hands", "Assets/pickup.png", 1);
     }
     
     @Override
     public String toString(){
          return ("Your bare hands, knuckles bloodied from the fray.");
     }
     
     @Override
     public void use(){
          System.out.println(options[new Random().nextInt(options.length)]);
     }
     
     @Override
     public String look(){
          return options[new Random().nextInt(options.length)];
     }
     
     @Override
     public Point[] getRange(Point p){
    	 ArrayList<Point> range = new ArrayList<Point>();
    	 
    	 if((p.getX()-1)!=0){
    		range.add(new Point((int)p.getX()-1,(int)p.getY()));
    	 }
    	 
    	 if((p.getX()+1)!=9){
    		range.add(new Point((int)p.getX()+1,(int)p.getY()));
    	 }
    	 
    	 if((p.getY()-1)!=0){
    		range.add(new Point((int)p.getX(),(int)p.getY()-1));
    	 }
    	 
    	 if((p.getY()+1)!=9){
    		range.add(new Point((int)p.getX(),(int)p.getY()+1));
    	 }    	  	
    	 
    	 return range.toArray(new Point[range.size()]);	 
     }
}