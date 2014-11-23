package RPG;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;

import javax.swing.JPanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class GameWindow {
	
	private final int px=60;
	private BufferedImage[] roomComponents;
	private ArrayList<BoardObject> boardObjects;
	public static Room currentRoom;
	public static BufferedImage pointerUp=(BufferedImage)GameIO.createImage("Assets/pointer.png");
	public static GameController controller= new GameController();
	public static JPanel cards; 
	public static CardLayout cl;
	public static Font bigFont;
	public static Font normalFont;
	public static Font smallFont;
	private static InventoryList invB;
	private static MenuPanel roomC;
	private static int makeChoicePointer;
	private static char menuDirection;
	
	public static JFrame frame;

	public GameWindow() {
		initialize();
		makeChoicePointer=1;
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
		
		
		ImageIcon img = new ImageIcon("Assets/mob.png");
		frame.setIconImage(img.getImage());
	
		
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
			
			roomC = new MenuPanel();
			roomC.setPreferredSize(new Dimension(600,60));
			roomC.setBackground(Color.black);
			roomC.setFocusable(true);
			roomC.addKeyListener(new GameListener());
		
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
			
			invB = new InventoryList();
			invB.setPreferredSize(new Dimension(600,570));
			invB.setBackground(Color.black);
			invB.setFocusable(true);
			invB.addKeyListener(new InventoryListener());
		inv.add(invA, BorderLayout.NORTH);
		inv.add(invB, BorderLayout.SOUTH);
		//come back to this nonsense later, I guess.
		
		cards.add(room, "Room");
		cards.add(inv, "Inventory");
		
		frame.pack();
		frame.setVisible(true);
	}
	
	public static InventoryList getInventoryPanel(){
		return invB;
	}
	
	public static MenuPanel getRoomPanel(){
		return roomC;
	}
	
	private void initialize() {
		
		currentRoom= controller.getCurrentRoom();
		boardObjects=currentRoom.getObjects();
		roomComponents=GameIO.loadRoomComponents();

		
	}
	
	
	public static int getMakeChoicePointer() {
		return makeChoicePointer;
	}

	public static void setMakeChoicePointer(int makeChoicePointer) {
		GameWindow.makeChoicePointer = makeChoicePointer;
	}



	public static char getMenuDirection() {
		return menuDirection;
	}

	public static void setMenuDirection(char menuDirection) {
		GameWindow.menuDirection = menuDirection;
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
			int ap=((PlayerCharacter)boardObjects.get(0)).getCurrentAP();
			String AP=ap>1?ap+" Actions":ap+" Action";
			g.drawString((AP),4*px-30, 1*px-10);
//			g.drawString(controller.getCurrentRoom().id,4*px-30, 1*px-10);
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
			if(GameWindow.getMakeChoicePointer()==1){
				g.drawImage(pointerUp,1*px+10,1*px-35, this);
			}
			else if(GameWindow.getMakeChoicePointer()==2){
				g.drawImage(pointerUp,3*px+25 ,1*px-35, this);
			}
			else if(GameWindow.getMakeChoicePointer()==3){
				g.drawImage(pointerUp, 6*px-5, 1*px-35, this);
			}
			else if(GameWindow.getMakeChoicePointer()==4){
				g.drawImage(pointerUp, 8*px+25, 1*px-35, this);
			}
			
		}
	}
	
	public class InventoryList extends JPanel{
		
		BufferedImage pointerRight=(BufferedImage)GameIO.createImage("Assets/pointerright.png");
		
		private static final int nameShift=45;
		private static final int itemPointerX=20;
		private int ypointer=0;
		
		public void paintComponent(Graphics g){
			
			super.paintComponent(g);
			g.setColor(Color.white);
			g.setFont(GameWindow.normalFont);
			
			drawPointer(g);
			
			for(int i=0;i<GameWindow.controller.getPlayer().inventory.size();i++){
				String a=GameWindow.controller.getPlayer().inventory.get(i).getName();
				String b=GameWindow.controller.getPlayer().inventory.get(i).equals(GameWindow.controller.getPlayer().getWeapon())?a+" (E)":a;
				g.drawString(b,60,nameShift*i+nameShift);
			}
			
			g.drawString("Back", itemPointerX+45,540);
			
			if(GameWindow.controller.getCurrentRound().equals(GameController.INVENTORYSUB)){
				g.drawImage(GameIO.createImage("Assets/inventorymessage.png"),itemPointerX+5,450, this);
				g.setFont(GameWindow.smallFont);
				g.drawString("Use", 85, 475);
				g.drawString("Equip", 225,475);
				g.drawString("Look",350,475);
				g.drawString("Drop",500,475);
				drawPointer(g);
			}
		}
		
		public void drawPointer(Graphics g){
			
			if(GameWindow.controller.getCurrentRound().equals(GameController.INVENTORYMAIN)){
				
				if(ypointer<GameWindow.controller.getPlayer().inventory.size()){
					g.drawImage(pointerRight,itemPointerX,ypointer*nameShift+itemPointerX,this);
				}
				
				if(ypointer==-1){
					g.drawImage(pointerRight,itemPointerX,510,this);
				}
			}
			
			else if(GameWindow.controller.getCurrentRound().equals(GameController.INVENTORYSUB)){
				
				if(ypointer==0){
					g.drawImage(pointerRight,50,445, this);
				}
				else if(ypointer==1){
					g.drawImage(pointerRight,190,445, this);
				}
				else if(ypointer==2){
					g.drawImage(pointerRight,315,445, this);
				}
				else{
					g.drawImage(pointerRight,465,445, this);
				}
			}
		}
		
		public int getYPointer(){
			return ypointer;
		}
		
		public void setYPointer(int y){
			ypointer=y;
		}	
		
	}
	
	class InventoryListener implements KeyListener{

		@Override
		public void keyPressed(KeyEvent e) {
			
			int key = e.getKeyCode();
			
			System.out.println("Listening from Inventory!");
			
			if(GameWindow.controller.getCurrentRound().equals(GameController.INVENTORYMAIN)){
				inventoryMain(key);
			}
			
			else if(GameWindow.controller.getCurrentRound().equals(GameController.INVENTORYSUB)){
				inventorySub(key);
			}
			
			
		}
		
		public void inventorySub(int key){
			
			int menu=GameWindow.getInventoryPanel().getYPointer();
			if (key == KeyEvent.VK_LEFT||key==KeyEvent.VK_KP_LEFT) {
				if(GameWindow.getInventoryPanel().getYPointer()==0){
					menu=3;
				}
				else{
					menu=GameWindow.getInventoryPanel().getYPointer()-1;
				}
				System.out.println("The new value is "+menu);
				GameWindow.getInventoryPanel().setYPointer(menu);
				GameWindow.frame.repaint();
			}
			if (key == KeyEvent.VK_RIGHT||key ==KeyEvent.VK_KP_RIGHT) {
				if(GameWindow.getInventoryPanel().getYPointer()==3){
					menu=0;
				}
				else{
					menu=GameWindow.getInventoryPanel().getYPointer()+1;
				}
				System.out.println("The new value is "+menu);
				GameWindow.getInventoryPanel().setYPointer(menu);
				GameWindow.frame.repaint();
	       
			}
	    
			if(key==KeyEvent.VK_ENTER||key==KeyEvent.VK_SPACE){
				GameWindow.controller.setMenuChoice(GameWindow.getInventoryPanel().getYPointer());
				GameWindow.controller.setMenuChoiceMade(true);
				System.out.println("You Hit Enter or Space.");
			}
		}
		
		public void inventoryMain(int key){
			
			int dy=0;

			if (key == KeyEvent.VK_LEFT||key==KeyEvent.VK_KP_LEFT) {
				dy=GameWindow.getInventoryPanel().getYPointer();
				System.out.println("The value of dy before modification is :"+dy);
				if(dy==0){
					dy=-1;
				}
				else if(dy==-1){
					dy=GameWindow.controller.getPlayer().inventory.size()-1;
				}
				else{
					dy-=1;
				}
				System.out.println("The value of dy after pressing left is :"+dy);
				GameWindow.getInventoryPanel().setYPointer(dy);				
				
				GameWindow.frame.repaint();
				
			}
			
			if (key == KeyEvent.VK_UP||key==KeyEvent.VK_KP_UP) {
				dy=GameWindow.getInventoryPanel().getYPointer();
				System.out.println("The value of dy before modification is :"+dy);
				if(dy==0){
					dy=-1;
				}
				else if(dy==-1){
					dy=GameWindow.controller.getPlayer().inventory.size()-1;
				}
				else{
					dy-=1;
				}
				System.out.println("The value of dy after pressing up is :"+dy);
				GameWindow.getInventoryPanel().setYPointer(dy);
				
				GameWindow.frame.repaint();
				
			}
			
			if (key == KeyEvent.VK_RIGHT||key ==KeyEvent.VK_KP_RIGHT) {
				dy=GameWindow.getInventoryPanel().getYPointer();
				System.out.println("The value of dy before modification is :"+dy);
				if(dy==GameWindow.controller.getPlayer().inventory.size()-1){
					dy=-1;
				}
				else if(dy==-1){
					dy=0;
				}
				else{
					dy+=1;
				}
				System.out.println("The value of dy after pressing right is :"+dy);

				GameWindow.getInventoryPanel().setYPointer(dy);
				GameWindow.frame.repaint();
				      
			}



			if (key == KeyEvent.VK_DOWN||key==KeyEvent.VK_KP_DOWN) {
				dy=GameWindow.getInventoryPanel().getYPointer();
				System.out.println("The value of dy before modification is :"+dy);
				if(dy==GameWindow.controller.getPlayer().inventory.size()-1){
					dy=-1;
				}
				else if(dy==-1){
					dy=0;
				}
				else{
					dy+=1;
				}
				System.out.println("The value of dy after pressing down is :"+dy);
				GameWindow.getInventoryPanel().setYPointer(dy);
				GameWindow.frame.repaint();
			}
			
			if(key==KeyEvent.VK_ENTER||key==KeyEvent.VK_SPACE){
				System.out.println("The value of dy being entered is :"+GameWindow.getInventoryPanel().getYPointer());
				GameWindow.controller.setMenuChoice(GameWindow.getInventoryPanel().getYPointer());
				GameWindow.controller.setMenuChoiceMade(true);
				System.out.println("You Hit Enter or Space.");
			}
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
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
		  //this sometimes throws an exception while moving. not sure what's going o
		  
		  g.drawImage(boardObjects.get(0).createImage(),(int)boardObjects.get(0).getLocation().getX()*px,(int)boardObjects.get(0).getLocation().getY()*px, this);
		  //draws player character on top (he's always in position 0).
		  
		  if(GameWindow.controller.isNewSystemMessage()){
			  g.drawImage(GameIO.createImage("Assets/systemmessage.png"), 25,250, this);
			  g.setColor(Color.white);
			  g.setFont(smallFont);
			  g.drawString(GameWindow.controller.getSystemMessage(), 35, 295);
		  }
	  }
	}
	

	
	class GameListener implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
					    
		}

		@Override
		public void keyPressed(KeyEvent e) {
			System.out.println("Listening from GameListener");
			
			System.out.println(GameWindow.controller.getCurrentRound());
			int key = e.getKeyCode();
			if(GameWindow.controller.getCurrentRound().equals(GameController.MENU)){
				menuEvent(key);
			}
			else if(GameWindow.controller.getCurrentRound().equals(GameController.MOVE)){
				moveEvent(key);
			}
			if(GameWindow.controller.getCurrentRound().equals(GameController.INVENTORYSUB)){
				if(key==KeyEvent.VK_ENTER||key==KeyEvent.VK_SPACE){
					GameWindow.controller.setMenuChoice(getMakeChoicePointer());
					GameWindow.controller.setMenuChoiceMade(true);
					System.out.println("You Hit Enter or Space.");
				}
			}
		}//closes keypressed THIS IS THE IMPORTANT ONE

		@Override
		public void keyReleased(KeyEvent e) {			
		}
		
		public void menuEvent(int key){
			
			
			if (key == KeyEvent.VK_LEFT||key==KeyEvent.VK_KP_LEFT) {
				int menu=0;
				if(GameWindow.getMakeChoicePointer()==1){
					menu=4;
				}
				else{
					menu=GameWindow.getMakeChoicePointer()-1;
				}
				GameWindow.setMakeChoicePointer(menu);
				GameWindow.frame.repaint();
			}
			if (key == KeyEvent.VK_RIGHT||key ==KeyEvent.VK_KP_RIGHT) {
				int menu=0;
				if(GameWindow.getMakeChoicePointer()==4){
					menu=1;
				}
				else{
					menu=GameWindow.getMakeChoicePointer()+1;
				}
				GameWindow.setMakeChoicePointer(menu);
				GameWindow.frame.repaint();
	       
			}
	    
			if(key==KeyEvent.VK_ENTER||key==KeyEvent.VK_SPACE){
				GameWindow.controller.setMenuChoice(getMakeChoicePointer());
				GameWindow.controller.setMenuChoiceMade(true);
				System.out.println("You Hit Enter or Space.");
			}
			
			
		}//menu event
	
		public void moveEvent(int key){
			
			if (key == KeyEvent.VK_LEFT||key==KeyEvent.VK_KP_LEFT) {
				GameWindow.setMenuDirection('l');
			}
			if (key == KeyEvent.VK_RIGHT||key ==KeyEvent.VK_KP_RIGHT) {
				GameWindow.setMenuDirection('r');       
			}

			if (key == KeyEvent.VK_UP||key==KeyEvent.VK_KP_UP) {
				GameWindow.setMenuDirection('u');
			}

			if (key == KeyEvent.VK_DOWN||key==KeyEvent.VK_KP_DOWN) {
				GameWindow.setMenuDirection('d');
			}
			deselectArrow();
			selectArrow();
	    
			if(key==KeyEvent.VK_ENTER||key==KeyEvent.VK_SPACE){
				GameWindow.controller.setMenuChoice(getMakeChoicePointer());
				GameWindow.controller.setMenuDirection(GameWindow.getMenuDirection());
				GameWindow.controller.setMenuChoiceMade(true);
				System.out.println("You Hit Enter or Space.");
			}
		
		}//close moveEvent
		
		public void deselectArrow(){
			for(int i=0;i<GameWindow.controller.getCurrentRoom().getObjects().size();i++){
				if(GameWindow.controller.getCurrentRoom().getObjects().get(i) instanceof MenuItem){
					if(GameWindow.controller.getCurrentRoom().getObjects().get(i).getName().equals(MenuItem.ARROWUPSELECTED)){
						int x=(int)GameWindow.controller.getCurrentRoom().getObjects().get(i).getLocation().getX();
						int y=(int)GameWindow.controller.getCurrentRoom().getObjects().get(i).getLocation().getY();
						GameWindow.controller.getCurrentRoom().getObjects().set(i, new MenuItem(x,y,MenuItem.ARROWUP,MenuItem.ARROWUP));
					}
					else if(GameWindow.controller.getCurrentRoom().getObjects().get(i).getName().equals(MenuItem.ARROWDOWNSELECTED)){
						int x=(int)GameWindow.controller.getCurrentRoom().getObjects().get(i).getLocation().getX();
						int y=(int)GameWindow.controller.getCurrentRoom().getObjects().get(i).getLocation().getY();
						GameWindow.controller.getCurrentRoom().getObjects().set(i, new MenuItem(x,y,MenuItem.ARROWDOWN,MenuItem.ARROWDOWN));
					}
					else if(GameWindow.controller.getCurrentRoom().getObjects().get(i).getName().equals(MenuItem.ARROWLEFTSELECTED)){
						int x=(int)GameWindow.controller.getCurrentRoom().getObjects().get(i).getLocation().getX();
						int y=(int)GameWindow.controller.getCurrentRoom().getObjects().get(i).getLocation().getY();
						GameWindow.controller.getCurrentRoom().getObjects().set(i, new MenuItem(x,y,MenuItem.ARROWLEFT,MenuItem.ARROWLEFT));
					}
					else if(GameWindow.controller.getCurrentRoom().getObjects().get(i).getName().equals(MenuItem.ARROWRIGHTSELECTED)){
						int x=(int)GameWindow.controller.getCurrentRoom().getObjects().get(i).getLocation().getX();
						int y=(int)GameWindow.controller.getCurrentRoom().getObjects().get(i).getLocation().getY();
						GameWindow.controller.getCurrentRoom().getObjects().set(i, new MenuItem(x,y,MenuItem.ARROWRIGHT,MenuItem.ARROWRIGHT));
					}
					
				}
			}
			GameWindow.frame.repaint();
		}
	
		public void selectArrow(){
			String target;
			String highlight;
			
			if(GameWindow.getMenuDirection()=='u'){
				target=MenuItem.ARROWUP;
				highlight=MenuItem.ARROWUPSELECTED;
			}
			else if(GameWindow.getMenuDirection()=='l'){
				target=MenuItem.ARROWLEFT;
				highlight=MenuItem.ARROWLEFTSELECTED;
			}
			else if(GameWindow.getMenuDirection()=='d'){
				target=MenuItem.ARROWDOWN;
				highlight=MenuItem.ARROWDOWNSELECTED;
			}
			else{
				target=MenuItem.ARROWRIGHT;
				highlight=MenuItem.ARROWRIGHTSELECTED;
			}
			
			for(int i=0;i<GameWindow.controller.getCurrentRoom().getObjects().size();i++){
				if(GameWindow.controller.getCurrentRoom().getObjects().get(i).getName().equals(target)){
					int x=(int)GameWindow.controller.getCurrentRoom().getObjects().get(i).getLocation().getX();
					int y=(int)GameWindow.controller.getCurrentRoom().getObjects().get(i).getLocation().getY();
					GameWindow.controller.getCurrentRoom().getObjects().set(i, new MenuItem(x,y,highlight,highlight));
				}
			}
			GameWindow.frame.repaint();
		}
	
	}
}

