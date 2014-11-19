package sandbox;

import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.Timer;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import RPG.GameWindow;

import java.io.IOException;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;


public class Test implements ActionListener{
	
		public static void main(String[] args){
			
			GameWindow a = new GameWindow();
			GameWindow.test.move('u',GameWindow.currentRoom);
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
			GameWindow.controller.takeTurn();
			
		}
		
		
		@Override
			public void actionPerformed(ActionEvent e) {
			
				GameWindow.frame.repaint();
				GameWindow.test.move('l',GameWindow.currentRoom);
			}//closes method
		}//closes gameListener
	
