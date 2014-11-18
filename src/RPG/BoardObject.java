package RPG;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
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
     
     public void draw(Graphics g){
    	 /**
    	 Image img=createImage();
    	 g.drawImage(img,(int)location.getX(),(int)(location).getY(),null);
    	 
    	 This is totally the right idea, but it's not the right implementation.  Need to figure this out.
    	 * 
    	 */
     }
     
     public Image createImage(){
    	    BufferedImage bufferedImage = null;
    	    try {
 			   bufferedImage = ImageIO.read(new File(imgPath));
 			} 
    	    catch (IOException e) {
 			}
    	    return bufferedImage;
    	  }
}