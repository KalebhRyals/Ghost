public class GhostScripts{
	
	public static void iCloudYes(){
		
		try{
			closeItunes();
			Runtime.getRuntime().exec("open /Applications/iTunes.app");
			Runtime.getRuntime().exec("osacript -e 'delay 1\ntell application \"System Events\" to key code 36\ndelay 1'");
			Runtime.getRuntime().exec("osacript -e 'delay 1'");
			Runtime.getRuntime().exec("osascript -e 'tell application \"System Events\" to key code 36'");
			}
			catch(Exception e){}
			
		
	}
	public static void iCloudNo(){
		
		try{
			
			closeItunes();
			Runtime.getRuntime().exec("open /Applications/iTunes.app");
			Runtime.getRuntime().exec("osacript -e 'delay 1'");
			Runtime.getRuntime().exec("osascript -e 'tell application \"System Events\" to key code 36'");
			Runtime.getRuntime().exec("osacript -e 'delay 1'");
			Runtime.getRuntime().exec("osascript -e 'tell application \"System Events\" to key code 45'");
			
			
			
		}
		catch(Exception e){}
	}
	public static void closeItunes(){
		
		try{
			
			Runtime.getRuntime().exec("osascript -e 'tell application \"iTunes\" to quit'");
			
		}
		catch(Exception e){}
	}
	public static void openItunes(){
		
		try{
			
			Runtime.getRuntime().exec("open /Applications/iTunes.app");
		}
		catch(Exception e){}
		
	}
	
	
	
}