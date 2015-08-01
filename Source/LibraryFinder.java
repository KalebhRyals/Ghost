/*
Determine Library Class 
Created by Kalebh Ryals
July 19, 2015

This class determines the location of 
the users iTunes Library .itl file as well
as the backup xml file.
*/


import java.io.*;

public class LibraryFinder{

	//Variables//
	private File currentITL;
	private File currentXML;
	private String directory;
	private boolean retry;

	//Constructor//
	public LibraryFinder(){

	currentITL = null;
	currentXML = null;

	}

	//Tests Default Library Location//
	public void defaultTest(){

		//Default Locations//
		File defaultITL = new File(System.getProperty("user.home") + "/Music/iTunes/iTunes Library.itl");
		File defaultXML = new File(System.getProperty("user.home") + "/Music/iTunes/iTunes Music Library.xml");

		//If Both Files Exist//
		if(defaultITL.exists() && defaultXML.exists()){

			//Set Library//
			setITL(defaultITL);
			setXML(defaultXML);
			directory = System.getProperty("user.home") + "/Music/iTunes/";
			setRetry(false);

		}
		//Else set to basic directory of / //
		else{
		setRetry(true);
		directory = "/";
		}

	}
	//Overloaded for non Default Library Location//
	public void directoryTest(String path){
		
		try{
		
			//Check Folder for Files//
			File newITL = new File(path + "iTunes Library.itl");
			File newXML = new File(path + "iTunes Music Library.xml");

			//Success//
			if(newITL.exists() && newXML.exists()){
	
				//Set Library//
				setITL(newITL);
				setXML(newXML);
				setDirectory(path);
				setRetry(false);
	
			}
			//Failure//
			else{
				setRetry(true);
			}
		}
		catch(Exception e){
			
			e.printStackTrace();
			setRetry(true);

		}
	}
	//Overloaded for different names? Possibly useful for future update//
	/*
	public void fileTest(String itl, String xml){

		try{
		
			File advancedITL = new File(itl);
			
			File advancedXML = new File(xml);
		
			if(!(advancedITL.exists())){

				System.out.println("Error ITL file not Found.");
				setRetry(true);
			}		
			else if(!(advancedXML.exists())){

				System.out.println("Error XML not Found.");		
				setRetry(true);
			}
			else{

				setITL(advancedITL);
				setXML(advancedXML);
				
				String tempDirectory = currentITL.getAbsolutePath();
				int index = tempDirectory.lastIndexOf(File.separatorChar);
				setDirectory(tempDirectory.substring(0,index+1));				
				setRetry(false);
	
			}
		}
		catch(Exception e){

			e.printStackTrace();
			setRetry(true);

		}


	}
	*/
	/***********************/
	//Get and Set Functions//
	/***********************/
	
	public File getITL(){

		return currentITL;

	}
	public File getXML(){

		return currentXML;	

	}
	public String getDirectory(){

		return directory;
	}
	public boolean getRetry(){

		return retry;
	
	}
	private void setRetry(boolean retry){
	
		this.retry = retry;
	
	}
	
	private void setITL(File itl){

		currentITL = itl;
	
	}
	private void setXML(File xml){

		currentXML = xml;
	
	}
	private void setDirectory(String directory){
		
		this.directory = directory;

	}
	

}
