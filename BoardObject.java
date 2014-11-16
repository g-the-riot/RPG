package RPG;

import java.awt.Point;
/**Relevant methods from Point:
  * getX()
  * getY()
  * move(int x, int y)
  * distance(Point p)
  * equals() -AVOID COLLISIONS
  * */


abstract class BoardObject{

     private Point location;
     private String name;
     
     public BoardObject(int x, int y, String name){
          this.location=new Point(x,y);
          this.name=name;
     }
     
          public BoardObject(String name){
          this.location=new Point();
          this.name=name;
     }
     
     public Point getLocation(){
          return location;
     }
     
     public String getName(){
          return name;
     }
}