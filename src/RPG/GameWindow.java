package RPG;

import java.awt.Graphics;

import javax.swing.JFrame;

import java.awt.BorderLayout;
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
	public static BufferedImage pointerUp=(BufferedImage)GameIO.createImage("Assets/pointer.png");
	public static GameController controller= new GameController();
	public static JPanel cards; 
	public static CardLayout cl;
	public static Font bigFont;
	public static Font normalFont;
	public static Font smallFont;
	
	
	public static JFrame frame;

	public GameWindow() {
		initialize();
		
		try{
			bigFont=Font.createFont(Font.TRUETYPE_FONT, new File("Assets/8BITWONDER.TTF")).deriveFont((float)60);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(bigFont);
			}
		catch(Exception e){
			System.out.println("Didn't happen buddy.");
		}
		
		try{
			smallFont=Font.createFont(Font.TRUETYPE_FONT, new File("Assets/8BITWONDER.TTF")).deriveFont((float)15);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(smallFont);
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
		frame.setPreferredSize(new Dimension(605,748)); // When resizable is true, X value off by 16, y by 39 because of the window.
		//when false x is off by 5, y by 28)
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			cl = new CardLayout();
			cards = new JPanel(cl);		
		frame.setContentPane(cards);
		
		BorderLayout bl=new BorderLayout();
		
		JPanel room = new JPanel(bl);
		bl.setHgap(0);
		bl.setVgap(0);
		room.setPreferredSize(new Dimension(600,720));
			JPanel roomA = new RoomPanel();
			roomA.setPreferredSize(new Dimension(600, 600));
			
			JPanel roomB = new StatusPanel();
			roomB.setBackground(Color.black);
			roomB.setPreferredSize(new Dimension(600,60));
			
			JPanel roomC = new MenuPanel();
			roomC.setPreferredSize(new Dimension(600,60));
			roomC.setBackground(Color.black);
		
		room.add(roomB, BorderLayout.NORTH);
		room.add(roomA, BorderLayout.CENTER);
		room.add(roomC, BorderLayout.SOUTH);
		
		BorderLayout bl2=new BorderLayout();
		JPanel inv = new JPanel(bl2);
		bl2.setHgap(0);
		bl2.setVgap(0);
		inv.setBackground(Color.black);
		inv.setPreferredSize(new Dimension(600,720));
			JPanel invA= new InventoryName();
			invA.setPreferredSize(new Dimension(600, 150));
			invA.setBackground(Color.black);
			
			JPanel invB = new JPanel();
			invB.setPreferredSize(new Dimension(600,570));
			invB.setBackground(Color.black);
		inv.add(invA, BorderLayout.NORTH);
		inv.add(invB, BorderLayout.CENTER);
		//come back to this nonsense later, I guess.
		
		cards.add(room, "Room");
		cards.add(inv, "Inventory");
		
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
	public class StatusPanel extends JPanel{
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			g.setColor(Color.white);
			g.setFont(GameWindow.normalFont);
			g.drawString(boardObjects.get(0).getName(),5,1*px-10);
			g.drawString(controller.getCurrentRoom().id,4*px-30, 1*px-10);
			g.drawString((((PlayerCharacter)boardObjects.get(0)).hp()+" * "+((PlayerCharacter)boardObjects.get(0)).maxHP()),8*px-25,1*px-10);
			
		}
	}
	
	public class MenuPanel extends JPanel{
		
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			g.setColor(Color.white);
			g.setFont(GameWindow.smallFont);
			g.drawString("Move", 1*px-5, 1*px-40);
			g.drawString("Attack",  3*px-5, 1*px-40);
			g.drawString("Inventory", 5*px, 1*px-40);
			g.drawString("Pick Up", 8*px-5, 1*px-40);
			if(controller.getMenuChoicePointer()==1){
				g.drawImage(pointerUp,1*px+10,1*px-35, this);
			}
			else if(controller.getMenuChoicePointer()==2){
				g.drawImage(pointerUp,3*px+25 ,1*px-35, this);
			}
			else if(controller.getMenuChoicePointer()==3){
				g.drawImage(pointerUp, 6*px-5, 1*px-35, this);
			}
			else if(controller.getMenuChoicePointer()==4){
				g.drawImage(pointerUp, 8*px+25, 1*px-35, this);
			}
			
		}
	}
	
	public class InventoryName extends JPanel{
		
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
	  @Override
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
	     
		  for(int i=1;i<boardObjects.size();i++){
			  g.drawImage(boardObjects.get(i).createImage(),(int)boardObjects.get(i).getLocation().getX()*px,(int)boardObjects.get(i).getLocation().getY()*px, this);
		  }
		  
		  g.drawImage(boardObjects.get(0).createImage(),(int)boardObjects.get(0).getLocation().getX()*px,(int)boardObjects.get(0).getLocation().getY()*px, this);
		  //draws player character on top (he's always in position 0).
	  }
	}
}

