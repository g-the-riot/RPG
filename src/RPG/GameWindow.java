package RPG;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;

import javax.swing.JPanel;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class GameWindow {
	
	private static final int px=60;
	private static BufferedImage[] roomComponents;
	private static ArrayList<BoardObject> boardObjects;
	public static Room currentRoom;
	public static GameController controller= new GameController();
	public static JPanel cards; 
	public static CardLayout cl;
	public JPanel panel2;
	public static Font bigFont;
	public static Font normalFont;
	
	
	public static JFrame frame;

	public GameWindow() {
		initialize();
		cl = new CardLayout();
		cards = new JPanel(cl);
		try{
			bigFont=Font.createFont(Font.TRUETYPE_FONT, new File("Assets/8BITWONDER.TTF")).deriveFont((float)60);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(bigFont);
			}
		catch(Exception e){
			System.out.println("Didn't happen buddy.");
		}
		try{
			normalFont=Font.createFont(Font.TRUETYPE_FONT, new File("Assets/8BITWONDER.TTF")).deriveFont((float)20);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(normalFont);
			}
		catch(Exception e){
			System.out.println("Didn't happen buddy.");
		}
	
		frame = new JFrame("My Super Great Game");
		frame.setResizable(false);
		frame.setPreferredSize(new Dimension(605,628)); // When resizable is true, X value off by 16, y by 39 because of the window.
		//when false x is off by 5, y by 28)
	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(cards);
		
		JPanel panel = new RoomPanel();
		
		panel2= new InventoryPanel();	
		panel2.setBackground(Color.black);
		cards.add(panel, "Room");
		cards.add(panel2, "Inventory");
		
		frame.pack();
		frame.setVisible(true);
	}
	
	private void initialize() {
		
		currentRoom= controller.getCurrentRoom();
		boardObjects=currentRoom.getObjects();
		roomComponents=GameIO.loadRoomComponents();

		
	}
	
	
	
	/**
	 * [0] tile
	 * [1] Corner NW (0,0)
	 * [2] Wall N
	 * [3] Corner NE (NoOfCol<<Room.X>>-1, 0)
	 * [4] Wall_E
	 * [5] Corner SE (NoOfCol<<Room.X>>-1, NoOfRows<<Room.Y>>-1)
	 * [6] Wall S
	 * [7] Corner SW (0, NoOfRows <<Room.y>>)
	 * [8] Wall W
	 * @author gtheriot
	 *
	 */
	
	
	public class InventoryPanel extends JPanel{
		
		public void paintComponent(Graphics g){
			
			super.paintComponent(g);
			g.setColor(Color.white);
			g.setFont(GameWindow.bigFont);
			g.drawString("Inventory",25,70);
			
		}
		
	}
	
	public class RoomPanel extends JPanel
	{
	  //may need to have an array of images that this can access...  
	  
	  public void paintComponent(Graphics g){
		  
		  if(controller.getCurrentRoom()!=currentRoom){
			  initialize();
		  }
		
		  super.paintComponent(g);
		  //drawImage(Image img, int x, int y, ImageObserver observer) //for now, just set imageobserver to this.
		  g.drawImage(roomComponents[1],0*px,0*px,this);
		  g.drawImage(roomComponents[3],(currentRoom.x-1)*px,0*px,this);
		  g.drawImage(roomComponents[5],(currentRoom.x-1)*px,(currentRoom.y-1)*px,this);
		  g.drawImage(roomComponents[7],0*px,(currentRoom.y-1)*px,this);
	    
		  for(int j=1;j<(currentRoom.y-1);j++){
			  g.drawImage(roomComponents[8],0*px,j*px,this);
		  }
	    
		  for(int j=1;j<(currentRoom.y-1);j++){
			  g.drawImage(roomComponents[4],(currentRoom.x-1)*px,j*px,this);
		  }
	    
		  for(int i=1;i<(currentRoom.x-1);i++){
			  g.drawImage(roomComponents[2],i*px,0*px,this);
		  }
	    
		  for(int i=1;i<(currentRoom.x-1);i++){
			  g.drawImage(roomComponents[6],i*px,(currentRoom.y-1)*px,this);
		  }
	    
		  for(int i=1;i<(currentRoom.x-1);i++){
			  for(int j=1;j<(currentRoom.y-1);j++){
				  g.drawImage(roomComponents[0],i*px,j*px,this);
			  }//and that's the tiles
		  }
	     
		  for(int i=0;i<boardObjects.size();i++){
			  g.drawImage(boardObjects.get(i).createImage(),(int)boardObjects.get(i).getLocation().getX()*px,(int)boardObjects.get(i).getLocation().getY()*px, this);
		  }
	  }
	}
}

