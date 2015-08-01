/*
Ghost GUI Class 
Created by Kalebh Ryals
July 22, 2015

This class provides a GUI for the Ghost Program separate from program Logic
*/

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.Toolkit;

public class GhostGUI{

	//Variables//
	
	private Cleaner ghostCleaner = new Cleaner();
	private JFrame frame;
	private boolean useDefault;
	private int preferredHeightNorth;
	private int preferredHeightSouth;
	private int preferredHeightCenter;
	private Dimension northDim,southDim,centerDim;
	private JButton specifyButton,removeButton,restoreButton;
	private Image top;
	private Toolkit toolkit;
	private ImageIcon specify,remove,restore,specifyRollover,removeRollover,restoreRollover;
	private JLabel logoLabel;
	private static JLabel infoLabel;
	private JPanel north,center,south;
	
	public GhostGUI(){

		//Toolkit for Images//
		useDefault = true;
		toolkit = Toolkit.getDefaultToolkit();
		
		
		//Main Frame Settings//
		frame = new JFrame("Ghost");
		
		frame.setSize(720, 450);
		
		frame.setLayout(new BorderLayout(0,0));
		
		
		/*************************/
		//Components for JFrame//
		/************************/
		north = new JPanel();
		
		center = new JPanel();
		
		south = new JPanel();
		
		
		//Setting Preferred Heights and Widths for Components//
		
		//Heights//
		preferredHeightNorth = (int)(0.3 * frame.getHeight());
		
		preferredHeightSouth = (int)(0.3 * frame.getHeight());
		
		preferredHeightCenter = (int)(.40 * frame.getHeight());
		
		
		//Dimensions//
		northDim = new Dimension(frame.getWidth(),preferredHeightNorth);
		
		centerDim = new Dimension(frame.getWidth(),preferredHeightCenter);
		
		southDim = new Dimension(frame.getWidth(),preferredHeightSouth);

		
		//Assigning Heights and Widths to Components of JFrame//
		north.setPreferredSize(northDim);
		
		center.setPreferredSize(centerDim);
		
		center.setLayout(new GridLayout(1,3,0,0));
			
		south.setPreferredSize(southDim);
		
		
		/**********************/
		//Components for North//
		/**********************/
		
		//Initialize//
		logoLabel = new JLabel();
		
			//Basic Settings/
			logoLabel.setBorder(null);
			
			logoLabel.setPreferredSize(new Dimension(frame.getWidth(),preferredHeightNorth));
	
		//Adding Image to Label//	
		try{
		
				top = toolkit.getImage(this.getClass().getResource("/Resources/Logo.jpg"));
		
				Image scaled = top.getScaledInstance(720, 135, Image.SCALE_SMOOTH );
		
				logoLabel = new JLabel(new ImageIcon(scaled));

		}
		
		catch(Exception e){}
		
		//Add Label to North//		
		north.add(logoLabel);
		
		/*****************************************/
		//Button Components for Center of JFrame//
		/****************************************/
		
		//Create Buttons//
		specifyButton = new JButton();
		removeButton = new JButton();
		restoreButton = new JButton();

		//Set Borders//
		specifyButton.setBorder(null);
		specifyButton.setOpaque(true);
		specifyButton.setBackground(Color.BLACK);
		specifyButton.setFocusPainted(false);
		
		removeButton.setBorder(null);
		removeButton.setOpaque(true);
		removeButton.setBackground(Color.BLACK);
		removeButton.setFocusPainted(false);
		
		restoreButton.setBorder(null);
		restoreButton.setOpaque(true);
		restoreButton.setBackground(Color.BLACK);
		restoreButton.setFocusPainted(false);
		
		//Add Button Listeners
		specifyButton.addActionListener(new SpecifyListener());
		removeButton.addActionListener(new RemoveListener());
		restoreButton.addActionListener(new RestoreListener());
		
		//Add Buttons to Center//
		center.add(specifyButton);
		center.add(removeButton);
		center.add(restoreButton);
		
		/******************/
		//JLabel for South//
		/******************/

		//Update-able Message Label//
		new MessagePanel();
		infoLabel = MessagePanel.createMessagePanel(frame,preferredHeightSouth);
		
		//add infoLabel//
		south.add(infoLabel);
		
		//add Components to Frame//
		frame.add(north,BorderLayout.NORTH);
		frame.add(center,BorderLayout.CENTER);
		frame.add(south,BorderLayout.SOUTH);
		
		
		
		//SetJFrame to Visible//
		frame.setVisible(true);
		
		/*******************/
		//Icons for Buttons//
		/*******************/
		
		//Specify Library Image//
		specify = new ImageIcon(toolkit.getImage(this.getClass().getResource("/Resources/IconA.jpg")).getScaledInstance(specifyButton.getWidth(), (int)(specifyButton.getHeight()*1.15), Image.SCALE_SMOOTH));
		specifyRollover = new ImageIcon(toolkit.getImage(this.getClass().getResource("/Resources/IconAHover.jpg")).getScaledInstance(specifyButton.getWidth(), (int)(specifyButton.getHeight()*1.15), Image.SCALE_SMOOTH));
		
		//Remove Ghost Files Image//
		removeRollover = new ImageIcon(toolkit.getImage(this.getClass().getResource("/Resources/IconBHover.jpg")).getScaledInstance(specifyButton.getWidth(), (int)(specifyButton.getHeight()*1.15), Image.SCALE_SMOOTH));
		remove = new ImageIcon(toolkit.getImage(this.getClass().getResource("/Resources/IconB.jpg")).getScaledInstance(specifyButton.getWidth(), (int)(specifyButton.getHeight()*1.15), Image.SCALE_SMOOTH));
		
		//Restore Ghost File Images
		restoreRollover = new ImageIcon(toolkit.getImage(this.getClass().getResource("/Resources/IconCHover.jpg")).getScaledInstance(specifyButton.getWidth(), (int)(specifyButton.getHeight()*1.15), Image.SCALE_SMOOTH));
		restore = new ImageIcon(toolkit.getImage(this.getClass().getResource("/Resources/IconC.jpg")).getScaledInstance(specifyButton.getWidth(), (int)(specifyButton.getHeight()*1.15), Image.SCALE_SMOOTH));
		
		//Set Icons to different Button States//
		
		//Specify//
		specifyButton.setIcon(specify);
		specifyButton.setRolloverIcon(specifyRollover);
		specifyButton.setDisabledIcon(specify);
		
		//Remove//
		removeButton.setIcon(remove);
		removeButton.setRolloverIcon(removeRollover);
		removeButton.setPressedIcon(remove);
		
		//Restore//
		restoreButton.setIcon(restore);
		restoreButton.setRolloverIcon(restoreRollover);
		restoreButton.setPressedIcon(restore);
		
		//Frame Component Background Colors//
		south.setOpaque(true);
		south.setBackground(Color.BLACK);
		north.setOpaque(true);
		north.setBackground(Color.BLACK);
		north.setOpaque(true);
		north.setBackground(Color.BLACK);
		
		//Frame Final Touches//
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setResizable(false);
		
	}
	//Listener for Specifying iTunes Library//
	private class SpecifyListener implements ActionListener{

		public void actionPerformed(ActionEvent event){
				
				//File Finder//
				System.setProperty("apple.awt.fileDialogForDirectories", "true");
				FileDialog finder = new FileDialog(frame,"Specify iTunes Folder",FileDialog.LOAD);
				
				//Default Location//
				ghostCleaner.findLibrary();
				finder.setDirectory(ghostCleaner.getDirectory());
				finder.setVisible(true);
				
				//Choosing and testing Library Choice//
				String path = finder.getDirectory() + finder.getFile() + "/";
				ghostCleaner.findLibrary(path);
				useDefault = false;
				
			}
			
		}
	//Listener to Remove Ghost Files//
	private class RemoveListener implements ActionListener{
		
		public void actionPerformed(ActionEvent event){
			
			//Ask if iCloud should be used//
			
			//Options//
			String[] options = {"Yes","No","Cancel"};
			
			int answer = JOptionPane.showOptionDialog(frame, "Would you like to Activate the iCloud Music Library?",
					"iCloud Music Library",
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.PLAIN_MESSAGE,
					null,
					options,
					options[2]);
			
			//Execution of Options using CLeaner class//
			if(useDefault == false){
			
					if(answer == 0){
				
						ghostCleaner.clean(true);
				
					}
					else if(answer == 1){
				
						ghostCleaner.clean(false);
				
					}
			}
			else if(useDefault == true){
				
				ghostCleaner.findLibrary();
				
				if(answer == 0){
					
					ghostCleaner.clean(true);
			
				}
				else if(answer == 1){
			
					ghostCleaner.clean(false);
			
				}
				
				
			}
			
			
		}
}
//Listener for Restoring Ghost Files//
	private class RestoreListener implements ActionListener{
		
			public void actionPerformed(ActionEvent event){
			
				//Cleaner class Restore Function//
				ghostCleaner.restore();
				
			}
	}
	private class FrameListener extends ComponentAdapter{
		
			public void componentResized(ComponentEvent e){
				
				preferredHeightNorth = (int)(0.3 * frame.getHeight());
				
				preferredHeightSouth = (int)(0.3 * frame.getHeight());
				
				preferredHeightCenter = (int)(.40 * frame.getHeight());
				
				
				//Dimensions//
				northDim = new Dimension(frame.getWidth(),preferredHeightNorth);
				
				centerDim = new Dimension(frame.getWidth(),preferredHeightCenter);
				
				southDim = new Dimension(frame.getWidth(),preferredHeightSouth);

				
				//Assigning Heights and Widths to Components of JFrame//
				north.setPreferredSize(northDim);
				
				center.setPreferredSize(centerDim);
				
				center.setLayout(new GridLayout(1,3,0,0));
					
				south.setPreferredSize(southDim);
				
				frame.pack();
				
				
				
			}
		
	}
	
		
}