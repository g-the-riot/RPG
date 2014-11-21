package RPG;

import java.util.Random;

public class WeaponCreator{

     public static AbstractWeapon createWeapon(){
         
          final int EXPLOSIVE=0;
          final int BLADE=1;
          final int FOOD=2;
          final int MAGIC=3;
          final int PROJECTILE=4;
          
          AbstractWeapon w;
          
          Random randumb = new Random();
          
          int index = randumb.nextInt(4);
          
          if(index==EXPLOSIVE){
               w=new Explosive(randumb.nextInt(8)+1,randumb.nextInt(8)+1,"Din O Might");
          }
          
          else if(index==BLADE){
               w=new Blade(randumb.nextInt(8)+1,randumb.nextInt(8)+1,"Very Sharp Needle");
          }
          else if(index==FOOD){
               w=new Food(randumb.nextInt(8)+1,randumb.nextInt(8)+1,"Stale Bread");
          }
          else if(index==MAGIC){
               w=new Magic(randumb.nextInt(8)+1,randumb.nextInt(8)+1,"Arcane Tome");
          }
          else{
               w=new Projectile(randumb.nextInt(8)+1,randumb.nextInt(8)+1,"Nerf Gun");
          }
          
          return w;
     }
}