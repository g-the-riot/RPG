package RPG;
import java.awt.Image;
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
     private String imgPath;
     private int index;

     
     public BoardObject(int x, int y, String name, String imgPath){
          this.location=new Point(x,y);
          this.name=name;
          this.imgPath=imgPath;
     }
     
     public BoardObject(String name, String imgPath){
     	 this.name=name;	
     	 this.imgPath=imgPath;
     	 this.location=new Point();
          
     }
     
     public Point getLocation(){
          return location;
     }
     
     public String getName(){
          return name;
     }
     
     public String getImgPath(){
    	 return imgPath;    	 
     }
     
    public Image createImage(){
    	 return GameIO.createImage(imgPath);
    }

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}