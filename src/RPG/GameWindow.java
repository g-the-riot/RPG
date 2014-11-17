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
	
	private static BufferedImage img= null;
	private static BoardObject[] boardObjects = new BoardObject[1];

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		boardObjects[0] = new PlayerCharacter(4,2, "Joseph");
		
		try {
			   img = ImageIO.read(new File("Assets/tile.png"));
			} 
		catch (IOException e) {
			}
		
		
	    String basePath = new File("").getAbsolutePath();
	    System.out.println(basePath);

							
				
		
		
		
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
	
	public class DrawJPanel extends JPanel
	{
	  //may need to have an array of images that this can access...  
	  
	  public void paintComponent(Graphics g){
	    super.paintComponent(g);
	    //drawImage(Image img, int x, int y, ImageObserver observer) //for now, just set imageobserver to this.
	    for(int i=0;i<10;i++){
	       	for(int j=0;j<10;j++){
	       		g.drawImage(img, i*60,j*60, this);
	       	}
	    }
	    for(int i=0;i<boardObjects.length;i++){
	    	g.drawImage(boardObjects[i].createImage(),(int)boardObjects[i].getLocation().getX()*60,(int)boardObjects[i].getLocation().getY()*60, this);
	    }
	  }
	}

}
