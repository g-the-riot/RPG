package RPG;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public abstract class AbstractWeapon extends BoardObject implements Weapon, Pickup {

     private int damage;
     private boolean keyItem=false;
     private boolean reusable=true;
     
     
     public AbstractWeapon(String name, String imgPath, int damage) {
          super(name,imgPath);
          this.damage=damage;
     }
                    
     public AbstractWeapon(int x, int y, String name, String imgPath, int damage) {
          super(x , y , name, imgPath);
          this.damage=damage;
     }
     
     public boolean isItReusable(){
    	 return this.reusable;
     }
     
     public void setReusable(boolean reusable){
    	 this.reusable=reusable;
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
     
     public boolean isItKeyItem(){
    	 return keyItem;
     };
     
     public void setKeyItem(boolean k){
    	 this.keyItem=k;
     }
     
     public String look(){
    	 return "\nDoes "+damage()+" HP of Damage!";
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
     public void use(AbstractCharacter q){
    	 
     }
     
     @Override
     public String look(){
          return ("Some dangerous looking "+getName());
     }
     
     @Override
     public ArrayList<Point> getRange(Point p){
    	 ArrayList<Point> range = new ArrayList<Point>();
    	 int x=(int)p.getX();
    	 int y=(int)p.getY();
    	 
    	 
    	 if((x-1)!=0){
    		 System.out.println("The point "+(x-1)+","+y+" is in range.");
    		range.add(new Point((int)p.getX()-1,(int)p.getY()));
    	 }
    	 
    	 if((x+1)!=9){
    		 System.out.println("The point "+(x+1)+","+y+" is in range.");
    		range.add(new Point((int)p.getX()+1,(int)p.getY()));
    	 }
    	 
    	 if((y-1)!=0){
    		 System.out.println("The point "+x+","+(y-1)+" is in range.");
    		range.add(new Point((int)p.getX(),(int)p.getY()-1));
    	 }
    	 
    	 if((y+1)!=9){
    		 System.out.println("The point"+x+","+(y+1)+" is in range.");
    		range.add(new Point((int)p.getX(),(int)p.getY()+1));
    	 }    	  	
    	 
    	 return range;	 
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
     public void use(AbstractCharacter q){}
     
     @Override
     public String look(){
          return ("A really sharp "+getName()+super.look());
     }
     
     @Override
     public ArrayList<Point> getRange(Point p){
    	 ArrayList<Point> range = new ArrayList<Point>();
    	 int x=(int)p.getX();
    	 int y=(int)p.getY();
    	 
    	 
    	 if((x-1)!=0){
    		 System.out.println("The point "+(x-1)+","+y+" is in range.");
    		range.add(new Point((int)p.getX()-1,(int)p.getY()));
    	 }
    	 
    	 if((x+1)!=9){
    		 System.out.println("The point "+(x+1)+","+y+" is in range.");
    		range.add(new Point((int)p.getX()+1,(int)p.getY()));
    	 }
    	 
    	 if((y-1)!=0){
    		 System.out.println("The point "+x+","+(y-1)+" is in range.");
    		range.add(new Point((int)p.getX(),(int)p.getY()-1));
    	 }
    	 
    	 if((y+1)!=9){
    		 System.out.println("The point"+x+","+(y+1)+" is in range.");
    		range.add(new Point((int)p.getX(),(int)p.getY()+1));
    	 }    	  	
    	 
    	 return range;	 
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
     public void use(AbstractCharacter q){}
     
     @Override
     public String look(){
          return ("A dusty book called "+getName()+super.look());
     }
     
     @Override
     public ArrayList<Point> getRange(Point p){
    	 ArrayList<Point> range = new ArrayList<Point>();
    	 int x=(int)p.getX();
    	 int y=(int)p.getY();
    	 
    	 
    	 if((x-1)!=0){
    		 System.out.println("The point "+(x-1)+","+y+" is in range.");
    		range.add(new Point((int)p.getX()-1,(int)p.getY()));
    	 }
    	 
    	 if((x+1)!=9){
    		 System.out.println("The point "+(x+1)+","+y+" is in range.");
    		range.add(new Point((int)p.getX()+1,(int)p.getY()));
    	 }
    	 
    	 if((y-1)!=0){
    		 System.out.println("The point "+x+","+(y-1)+" is in range.");
    		range.add(new Point((int)p.getX(),(int)p.getY()-1));
    	 }
    	 
    	 if((y+1)!=9){
    		 System.out.println("The point"+x+","+(y+1)+" is in range.");
    		range.add(new Point((int)p.getX(),(int)p.getY()+1));
    	 }    	  	
    	 
    	 return range;	 
     }
}

class Food extends AbstractWeapon{
	
	private boolean rotten=false;
     
     public Food (String name){
          super(name, "Assets/pickup.png", 2);
          Random randumb=new Random();
          if(randumb.nextInt(10)==1){
        	  this.rotten=true;
        	  setReusable(false);
        	  super.setName(super.getName()+" (Rotten)");
          }
     }
     
     public Food (int x, int y, String name){
         super(x,y, name, "Assets/pickup.png", 2);
         Random randumb=new Random();
         if(randumb.nextInt(10)==1){
        	 this.rotten=true;
        	 super.setName(super.getName()+" (Rotten)");
       	  
         }
    }
     
     @Override
     public String toString(){
          return ("I am Food "+super.toString());
     }
     
     @Override
     public int damage() {
    	 int damage=super.damage();
    	 
    	 if(isRotten()){
    		 damage-=2;
    	 }
    	 
         return damage;
    }
     
     @Override
     public void use(AbstractCharacter q){
    	 if(isRotten()){
    		 q.takeDamage(super.damage()*10);
    	 }
    	 else{
    		 q.gainHp(damage()*10);
    	 }
    	 GameWindow.controller.setSystemMessage(q.getName()+" ate the "+getName()+"\n and "+(isRotten()?"lost ":"gained ")+(isRotten()?super.damage()*10:damage()*10)+" HP!");
     }
     
     @Override
     public String look(){
          return ("A mouthwatering "+getName()+super.look());
     }
     
     @Override
     public ArrayList<Point> getRange(Point p){
    	 ArrayList<Point> range = new ArrayList<Point>();
    	 int x=(int)p.getX();
    	 int y=(int)p.getY();
    	 
    	 
    	 if((x-1)!=0){
    		 System.out.println("The point "+(x-1)+","+y+" is in range.");
    		range.add(new Point((int)p.getX()-1,(int)p.getY()));
    	 }
    	 
    	 if((x+1)!=9){
    		 System.out.println("The point "+(x+1)+","+y+" is in range.");
    		range.add(new Point((int)p.getX()+1,(int)p.getY()));
    	 }
    	 
    	 if((y-1)!=0){
    		 System.out.println("The point "+x+","+(y-1)+" is in range.");
    		range.add(new Point((int)p.getX(),(int)p.getY()-1));
    	 }
    	 
    	 if((y+1)!=9){
    		 System.out.println("The point"+x+","+(y+1)+" is in range.");
    		range.add(new Point((int)p.getX(),(int)p.getY()+1));
    	 }    	  	
    	 
    	 return range;	 
     }

	public boolean isRotten() {
		return rotten;
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
     public void use(AbstractCharacter q){}
     
     @Override
     public String look(){
          return ("A scary looking "+getName()+super.look());
     }
     
     @Override
     public ArrayList<Point> getRange(Point p){
    	 ArrayList<Point> range = new ArrayList<Point>();
    	 int x=(int)p.getX();
    	 int y=(int)p.getY();
    	 
    	 
    	 if((x-1)!=0){
    		 System.out.println("The point "+(x-1)+","+y+" is in range.");
    		range.add(new Point((int)p.getX()-1,(int)p.getY()));
    	 }
    	 
    	 if((x+1)!=9){
    		 System.out.println("The point "+(x+1)+","+y+" is in range.");
    		range.add(new Point((int)p.getX()+1,(int)p.getY()));
    	 }
    	 
    	 if((y-1)!=0){
    		 System.out.println("The point "+x+","+(y-1)+" is in range.");
    		range.add(new Point((int)p.getX(),(int)p.getY()-1));
    	 }
    	 
    	 if((y+1)!=9){
    		 System.out.println("The point"+x+","+(y+1)+" is in range.");
    		range.add(new Point((int)p.getX(),(int)p.getY()+1));
    	 }    	  	
    	 
    	 return range;	 
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
          setKeyItem(true);
     }
     
     @Override
     public String toString(){
          return ("Your bare hands, knuckles bloodied from the fray.");
     }
     
     @Override
     public void use(AbstractCharacter q){
          System.out.println(options[new Random().nextInt(options.length)]);
     }
     
     @Override
     public String look(){
          return options[new Random().nextInt(options.length)]+super.look();
     }
     
     @Override
     public ArrayList<Point> getRange(Point p){
    	 ArrayList<Point> range = new ArrayList<Point>();
    	 int x=(int)p.getX();
    	 int y=(int)p.getY();
    	 
    	 
    	 if((x-1)!=0){
    		 System.out.println("The point "+(x-1)+","+y+" is in range.");
    		range.add(new Point((int)p.getX()-1,(int)p.getY()));
    	 }
    	 
    	 if((x+1)!=9){
    		 System.out.println("The point "+(x+1)+","+y+" is in range.");
    		range.add(new Point((int)p.getX()+1,(int)p.getY()));
    	 }
    	 
    	 if((y-1)!=0){
    		 System.out.println("The point "+x+","+(y-1)+" is in range.");
    		range.add(new Point((int)p.getX(),(int)p.getY()-1));
    	 }
    	 
    	 if((y+1)!=9){
    		 System.out.println("The point"+x+","+(y+1)+" is in range.");
    		range.add(new Point((int)p.getX(),(int)p.getY()+1));
    	 }    	  	
    	 
    	 return range;	 
     }
}