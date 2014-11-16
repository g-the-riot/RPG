package RPG;

import java.util.Random;

public class WeaponCreator{

     public static Weapon createWeapon(){
         
          final int EXPLOSIVE=0;
          final int BLADE=1;
          final int FOOD=2;
          final int MAGIC=3;
          final int PROJECTILE=4;
          
          Weapon w;
          
          int index =new Random().nextInt(4);
          
          if(index==EXPLOSIVE){
               w=new Explosive("Boom");
          }
          
          else if(index==BLADE){
               w=new Blade("Shring");
          }
          else if(index==FOOD){
               w=new Food("Yum");
          }
          else if(index==MAGIC){
               w=new Magic("HocusPocus");
          }
          else{
               w=new Projectile("Fwip");
          }
          
          return w;
     }
}