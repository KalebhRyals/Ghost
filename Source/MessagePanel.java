/*
Message Panel Class 
Created by Kalebh Ryals
July 27, 2015

This class creates an update-able JLabel with a custom font
*/

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Font;
import java.io.*;
import javax.swing.*;
import javax.swing.SwingConstants;

public class MessagePanel extends JPanel{
	
	/**
	 * 
	 */
	//Variables//
	private static final long serialVersionUID = 1L;
	private static JLabel messageLabel;
	private static Font sertigFont;
	
	//Constructor//
	public MessagePanel(){
		
		messageLabel = new JLabel();
		//sertigFont = null;
		
	}
	//Create the JLabel//
	public  static JLabel createMessagePanel(JFrame frame,int preferredHeightSouth){
		
		//Make new Label//
		messageLabel = new JLabel("Created By Kalebh Ryals");
		
		//Set the Font//
		
		try{
			
			sertigFont = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File(MessagePanel.class.getClassLoader().getResource("Sertig.otf").toURI()).getAbsolutePath())).deriveFont(Font.PLAIN, 24);
		
		}
		catch(Exception ex){
			
			ex.printStackTrace();
		}
		
		//Label Settings//
		
		messageLabel.setFont(sertigFont);
		messageLabel.setForeground(Color.WHITE);
		messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		messageLabel.setBorder(null);
		messageLabel.setOpaque(true);
		messageLabel.setBackground(new Color(57,57,57));
		messageLabel.setPreferredSize(new Dimension(frame.getWidth(),preferredHeightSouth));

		return messageLabel;
		
	}
	//Set Label Message//
	public static void setMessage(String message){
	
		messageLabel.setText(message);
		messageLabel.repaint();
		
	}
	
	
}