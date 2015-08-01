/*
Cleaner Class 
Created by Kalebh Ryals
July 20, 2015

This Class determines iTunes Library Location and can remove Ghost Files as Well as restore Ghost Files
*/

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.Scanner;

public class Cleaner {

	//Variables//
	private LibraryFinder library;
	private File currentITL, currentXML, backupITL, tempBackupITL;
	private FileWriter fw;
	private BufferedWriter bw;
	private PrintWriter out;
	private String backupName, directory;
	
	//Initialize//
	public Cleaner(){
		
		library = new LibraryFinder();
		currentITL = null;
		setCurrentXML(null);
		new MessagePanel();
		MessagePanel.setMessage("");
		
	}

	//Default method to search for iTunes Library//
	public void findLibrary(){

		//Default Test Method//
		library.defaultTest();
		
		//Success//
		if(library.getRetry() == false){
			
			//Set iTunes Library and XML File//
			currentITL = library.getITL();
			setCurrentXML(library.getXML());
			directory = library.getDirectory();
			MessagePanel.setMessage("Default Library Found.");

		}
		//Failure//
		else{
			
			MessagePanel.setMessage("<html>Default Library not Found.<br>Check iTunes > Preferences > Advanced<br>Make sure iTunes Library XML is Enabled<br>Or Specify your Library Location. ");
		}
	}
	//OverLoaded Method for non Default Library Choice//
	public boolean findLibrary(String dir){

		//Test Directory//
		library.directoryTest(dir);
		
		//Success//
		if(library.getRetry() == false){
			
			//Set iTunes Library and XML//
			
			currentITL = library.getITL();
			setCurrentXML(library.getXML());
			directory = library.getDirectory();
			MessagePanel.setMessage("Library Initialized.");
			return true;
		}
		//Failure//
		else{
			MessagePanel.setMessage("<html>Library not Found.<br>Check iTunes > Preferences > Advanced<br>Make sure iTunes Library XML is Enabled<br>Or Specify your Library Location. ");
			return false;

		}
	}
	//Overloaded Method for different locations of Files. Not used in GUI, but useful for possibly updates//
	/*
	public boolean findLibrary(String itl, String xml){

		library.fileTest(itl,xml);
		
		if(library.getRetry() == false){
			
			currentITL = library.getITL();
			setCurrentXML(library.getXML());
			directory = library.getDirectory();
			MessagePanel.setMessage("Library Initialized.");
			return true;
		}
		else{
			MessagePanel.setMessage("Library not Found.");
			return false;
		}
	}
	*/
	//Main Function for Removing Ghost Files//
	public void clean(boolean iCloud){
		
		//Test for Valid Library Location//
		if(library.getRetry() == false){
			try{
				//Close iTunes//
				MessagePanel.setMessage("Closing iTunes..");
				Runtime.getRuntime().exec("./Resources/closeItunes");
				
				//Create Backup File//
				backup(currentITL);
				MessagePanel.setMessage("Backing up iTunes Library...");
				
				//Instructions if iCloud is to be used//
				if(iCloud == true){
					
					//OverWrite iTUnes Library//
					overwrite();
					MessagePanel.setMessage("Overwriting iTunes Library File...");
					
					//Execute Script for opening iTunes and selecting options//
					MessagePanel.setMessage("Executing Magical Scripts...");
					
					new ProcessBuilder("Resources/iCloudYes").start();
					MessagePanel.setMessage("Magical Scripts Have Been Executed...");
					
				}
				else{
					
					//OverWrite iTUnes Library//
					overwrite();
					MessagePanel.setMessage("Overwriting iTunes Library File...");
					
					//Execute Script for opening iTunes and selecting options//
					MessagePanel.setMessage("Executing Magical Scripts...");
					
					new ProcessBuilder("Resources/iCloudNo").start();
					//Runtime.getRuntime().exec("./Resources/iCloudNo");
					
					
					//Success///
					MessagePanel.setMessage("Beep");
					Thread.sleep(1000);
					MessagePanel.setMessage("Boop");
					Thread.sleep(1000);
					MessagePanel.setMessage("Bop");
					Thread.sleep(1000);
					MessagePanel.setMessage("Magical Scripts Have Been Executed...");

				}
			}
			
			catch(Exception e){
				
				e.printStackTrace();
				
			}
		}
		else{
			
			//Library not Valid//
			MessagePanel.setMessage("<html>Hey! Don't get ahead of yourself.<br>Specify your iTunes Library location. Our Default or your selection didn't work." +
					"<br> Its location can be found in the menu iTunes > Preferences > Advanced.</html>");
			
		}
		
	}
	//Clean up Backup Files and Damaged Library on Exit/
	public void quit(){
		
		try{
		
			//Backup File Deletion//
			MessagePanel.setMessage("Cleaning out Backup File");
			File damagedLibrary = new File(library.getDirectory()+"iTunes Library (Damaged)");
			tempBackupITL.delete();
			
			//Damaged Library Deletion//
			MessagePanel.setMessage("Deleting Damaged Library");
			Thread.sleep(500);
			damagedLibrary.delete();

		}
		catch(Exception e){}
	}
	//Restore Ghost FIles//
	public void restore(){
	
		try{
			
				//Close iTunes//
				MessagePanel.setMessage("Closing iTunes...");
				Runtime.getRuntime().exec("./Resources/closeItunes");
			
				//Delete Damaged Library//
				File damagedLibrary = new File(library.getDirectory()+"iTunes Library (Damaged)");
			
				if(damagedLibrary.exists()){
					MessagePanel.setMessage("Removing Dat Bunk Damaged Library");
					damagedLibrary.delete();
			
					//Load Backup//
					MessagePanel.setMessage("Swapping out iTunes Library Files");
					File tempITL = currentITL;
					currentITL.delete();
					tempBackupITL.renameTo(tempITL);
			
					//Reopen iTunes//
					Thread.sleep(1000);
					Runtime.getRuntime().exec("open /Applications/iTunes.app");
					MessagePanel.setMessage("<html>You Have Successfully Re-Crapped your Library<br>Congrats...</html>");
			
					library.defaultTest();
				
				}
				else{
				
					MessagePanel.setMessage("There's Nothing to Backup!");
				
				}
			}
		catch(Exception e){}
		}

	//Method to overwrite iTunes Library.itl file//
	private void overwrite(){

		try{
			
			//Delete Contents//
			currentITL.delete();
			fw = new FileWriter(currentITL);
			bw = new BufferedWriter(fw);
			out = new PrintWriter(bw);
			
			//Corrupt that file with some Bob Saget//
			out.print("Bob Saget");
			out.close();
		}
		catch(IOException e){}
		
		

	}
	//Backup original iTunes Library//
	private void backup(File itlFile){

		//For Overwriting//
		FileChannel itlSource = null;
		FileChannel itlDestination  = null;
		
		//Create the Backup//
		backupITL = createBackupFile(itlFile);		

		try{

			//Take Current//
			FileInputStream fileInputStream = new FileInputStream(currentITL);
			itlSource = fileInputStream.getChannel();
			
			//Set Output//
			FileOutputStream fileOutputStream = new FileOutputStream(backupITL);
			itlDestination = fileOutputStream.getChannel();
			
			//Switcharoo//
			itlDestination.transferFrom(itlSource , 0 , itlSource.size());
			
			//Close Streams//
			fileInputStream.close();
			fileOutputStream.close();
			itlSource.close();
			itlDestination.close();
		}
		catch(IOException e){}
		
	}
	//Create a Backup File//
	private File createBackupFile(File itlFile){

		//Take Current Name and change extension to .bak//
		backupName = currentITL.getAbsolutePath();
		
		int startIndex = backupName.lastIndexOf(File.separatorChar);
		int endIndex = backupName.lastIndexOf(".");
		
		backupName = backupName.substring(startIndex+1,endIndex+1) + "bak";
		
		tempBackupITL = new File(library.getDirectory() + backupName);

		return tempBackupITL;


	}
	/***********************/
	//Get and Set Functions//
	/***********************/
	
	public String getDirectory(){
		
		return directory;
		
	}
	public File getCurrentXML() {
		return currentXML;
	}

	public void setCurrentXML(File currentXML) {
		this.currentXML = currentXML;
	}
}
