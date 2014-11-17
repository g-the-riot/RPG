package RPG;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import java.io.IOException;

public class GameWindow {
	
	private static BufferedImage tile= null;
	private static BufferedImage wall_n= null;
	private static BufferedImage[] roomComponents= new BufferedImage[9];
	private static BoardObject[] boardObjects = new BoardObject[1];

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		boardObjects[0] = new PlayerCharacter(4,2, "Joseph");
		
		loadRoomComponents();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameWindow window = new GameWindow();
					window.frame.pack();
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the application.
	 */
	public GameWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setPreferredSize(new Dimension(605,628)); // When resizable is true, X value off by 16, y by 39 because of the window.
		//when false x is off by 5, y by 28)
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		JPanel panel = new DrawJPanel();
		frame.getContentPane().add(panel, "Room");
		
	}
	
	private static void loadRoomComponents(){
		
		try {
			roomComponents[0] = ImageIO.read(new File("Assets/tile.png"));
			} 
		catch (IOException e) {
			}
		try {
			roomComponents[1] = ImageIO.read(new File("Assets/cornernw.png"));
			} 
		catch (IOException e) {
			}
		try {
			roomComponents[2] = ImageIO.read(new File("Assets/wall_n.png"));
			} 
		catch (IOException e) {
			}
		try {
			roomComponents[3] = ImageIO.read(new File("Assets/cornerne.png"));
			} 
		catch (IOException e) {
			}
		try {
			roomComponents[4] = ImageIO.read(new File("Assets/wall_e.png"));
			} 
		catch (IOException e) {
			}
		try {
			roomComponents[5] = ImageIO.read(new File("Assets/cornerse.png"));
			} 
		catch (IOException e) {
			}
		try {
			roomComponents[6] = ImageIO.read(new File("Assets/wall_s.png"));
			} 
		catch (IOException e) {
			}
		try {
			roomComponents[7] = ImageIO.read(new File("Assets/wall_sw.png"));
			} 
		catch (IOException e) {
			}
		try {
			roomComponents[8] = ImageIO.read(new File("Assets/wall_w.png"));
			} 
		catch (IOException e) {
			}
	}
	
	/**
	 * [0] tile
	 * [1] Corner NW (0,0)
	 * [2] Wall N
	 * [3] Corner NE (NoOfCol<<Room.X>>, 0)
	 * [4] Wall_E
	 * [5] Corner SE (NoOfCol<<Room.X>>, NoOfRows<<Room.Y>>)
	 * [6] Wall S
	 * [7] Corner SW (0, NoOfRows <<Room.y>>)
	 * [8] Wall W
	 * @author gtheriot
	 *
	 */
	
	public class DrawJPanel extends JPanel
	{
	  //may need to have an array of images that this can access...  
	  
	  public void paintComponent(Graphics g){
	    super.paintComponent(g);
	    //drawImage(Image img, int x, int y, ImageObserver observer) //for now, just set imageobserver to this.
	    for(int i=0;i<10;i++){
	       	for(int j=0;j<10;j++){
	       		
	       		if(i==0){
	       			if(j==0){
	       				g.drawImage(roomComponents[1],i*60,j*60,this);
		       		}
	       			else if(j==9){
		       			g.drawImage(roomComponents[7],i*60,j*60,this);
		       		}
	       			else{
	       				g.drawImage(roomComponents[8],i*60,j*60,this);
	       			}      			
	       		}
	       		
	       		else if(i==9){
	       			
	       			if(j==0){
		       			g.drawImage(roomComponents[3],i*60,j*60,this);
		       		}
	       			else if(j==9){
		       			g.drawImage(roomComponents[5],i*60,j*60,this);
		       		}
	       			
	       			g.drawImage(roomComponents[4],i*60,j*60,this);
	       		}
	       		
	       		else if(j==0){
	       			g.drawImage(roomComponents[2],i*60,j*60,this);
	       		}

	       		else if(j==9){
	       			g.drawImage(roomComponents[6],i*60,j*60,this);
	       		}
	       		else {
	       			g.drawImage(roomComponents[0],i*60,j*60,this);
	       		}//and that's the tiles

	       	}//this is a hot mess that's not displaying properly.  I need to chew on this some more.
	    }
	    for(int i=0;i<boardObjects.length;i++){
	    	g.drawImage(boardObjects[i].createImage(),(int)boardObjects[i].getLocation().getX()*60,(int)boardObjects[i].getLocation().getY()*60, this);
	    }
	  }
	}

}
