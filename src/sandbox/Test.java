package sandbox;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import RPG.GameWindow;



public class Test implements ActionListener{
	
		public static void main(String[] args){
			
			
			
			GameWindow a = new GameWindow();
//			Timer timer= new Timer(15000, new Test());
//			timer.setInitialDelay(1500);
//			timer.start();
//			GameWindow.test.move('l',GameWindow.currentRoom);
//			timer.start();
//			GameWindow.test.move('l',GameWindow.currentRoom);
//			timer.start();
			GameWindow.controller.takeTurn();
			GameWindow.controller.takeTurn();
			GameWindow.controller.takeTurn();
			
		}
		
		
		@Override
			public void actionPerformed(ActionEvent e) {
			
				GameWindow.controller.takeTurn();
		
			}//closes method
		}//closes gameListener
	
