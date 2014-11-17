package sandbox;

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
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;


public class Test {
	
	private static BufferedImage img= null;

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		try {
			   img = ImageIO.read(new File("C:\\Users\\Gregory\\Desktop\\tile.png"));
			} catch (IOException e) {
			}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test window = new Test();
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
	public Test() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setPreferredSize(new Dimension(616,639));; //X value off by 16, y by 39 because of the window.
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new DrawJPanel();
		frame.getContentPane().add(panel);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{434, 0};
		gridBagLayout.rowHeights = new int[]{130, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		frame.getContentPane().add(panel_1, gbc_panel_1);
		
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
	  }
	}

}
