package org.robotframework.remotelibrary;

import org.sikuli.script.*;
import java.lang.reflect.*;

/**
 * This work is based on project by David Luu which can 
 * be found at http://code.google.com/p/simplesikuli/
 * 
 * The original license was left unchanged.
 * License: Apache License, Version 2.0
 *          http://www.apache.org/licenses/LICENSE-2.0
 * 
 * This is a basic API library for accessing some Sikuli 
 * methods to detect objects, wait for them to appear/disappear,
 * click on them, etc. using captured "expected" PNG images 
 * of the objects in question.
 * 
 * Implemented with reference to http://sikuli.org/doc/java-x/
 * 
 * For use through:
 * 
 *   - command line (CLI) interface for stand-alone use, or
 *     external integration with other tools/frameworks via
 *     the system shell 
 * 
 *   - Robot Framework as a Java library (remote or not)
 *     http://www.robotframework.org
 * 
 *   - Java (XML-RPC) remote server/library interface,
 *     usable with Robot Framework or as stand-alone 
 *     automation server
 *     http://code.google.com/p/jrobotremoteserver/
 *     
 *   - integration with other Java code based tools/frameworks via
 *     integration or calling of this library code from Java
 *     
 * NOTE: Exceptions are caught and passed to standard output and
 *       method returns false. While this doesn't fit general
 *       programming design & Robot Framework specification for
 *       keyword failures, if you don't like it, feel free to remove
 *       the exception handling and let the exception propagate and
 *       be handled by the caller, and in the case of the CLI, let
 *       exception be handled at the main method. It is designed
 *       this way for simplicity, so one (and novice users)
 *       don't have to deal with exceptions. False = fail. And in
 *       the case of this wrapper library, generally if the
 *       exception happened, you pretty much know why (straightforward).
 *       
 *       And one can also modify to add a flag to enable/disable or
 *       just disable (no flag) dumping of the exception info, 
 *       as it can be a lot of extraneous text. 
 * 
 * @author ljszalai
 * Contact: lj.szalai@yahoo.com
 * 
 * Changes from original source:
 * 	- SCM has been changed to Git and it is planned to upload
 * project to GitHub
 * 	- unit tests planned and implemented
 * 	- project has been re-shaped based on project rfdblibrary
 * available at https://github.com/ThomasJaspers/robotframework-dblibrary
 * 
 * To satisfy prescribed maven dependency 'sikuli-script' follow these steps:
 * 	- download sikuli from sikuli.org
 * 	- go to folder where sikuli-script.jar is installed
 * 	- issue the following command there:
 * 		mvn install:install-file 
 * 			-Dfile=sikuli-script.jar 
 * 			-DgroupId=org.sikuli 
 * 			-DartifactId=sikuli-script 
 * 			-Dversion=0.10.2 
 * 			-Dpackaging=jar
 *
 */
public class SikuliLibrary{
	
	/**
	 * Checks that object defined by PNG image file exists
	 * on the screen within given timeout in seconds. 
	 * Returns true/1 if exists, false/0 if not.
	 * @param imgFile
	 * @return boolean
	 */
	public static boolean object_exists(String imgFile, double timeout){		
		try{
			Screen s = new Screen();
			s.wait(imgFile,timeout);
			System.out.println("Object "+imgFile+" found within "+timeout+" seconds.");
			return true;
			//return s.exists(imgFile);			
			//per Sikuli API docs, since exists() does not 
			//return TRUE/FALSE & works same as wait but
			//not throw FindFailed exception on not exist, then use wait() instead
			//http://sikuli.org/doc/java-x/org/sikuli/script/Region.html#exists(PSC, double)
		}
		catch (Throwable e) { //FindFailed exception
			System.out.println("Object "+imgFile+" not found within "+timeout+" seconds.");
			System.out.println("Here's a stack dump of the caught exception for more details of failure...");
			e.printStackTrace();
			return false; 
		}
	}
	
	/**
	 * Waits for object defined by PNG image file
	 * to appear on the screen, before timeout in seconds.
	 * Returns false if failed, otherwise true.
	 * @param imgFile
	 * @return boolean
	 */
	public static boolean wait_for_object(String imgFile, double timeout){		
		try{
			Screen s = new Screen();
			System.out.println("Waiting for object "+imgFile+" for "+timeout+" seconds.");
			s.wait(imgFile,timeout);
			return true;
		}
		catch (Throwable e) { //FindFailed exception
			System.out.println("Object "+imgFile+" not found after "+timeout+" seconds.");
			System.out.println("Here's a stack dump of the caught exception for more details of failure...");
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Waits for object defined by PNG image file
	 * to disappear from the screen before timeout
	 * in seconds. Returns TRUE if disappears, FALSE if not
	 * @param imgFile
	 * @return boolean
	 */
	public static boolean wait_for_object_to_disappear(String imgFile, double timeout){
		Screen s = new Screen();
		boolean result = s.waitVanish(imgFile,timeout);
		if(result)
			System.out.println("Object "+imgFile+" has disappeared within "+timeout+" seconds.");
		else
			System.out.println("Object "+imgFile+" did not disappear within "+timeout+" seconds.");
		return result;
	}
	
	/**
	 * Click on object defined by PNG image file.
	 * Specify any keys to press while clicking.
	 * E.g. ctrl click, shift click, etc.
	 * Valid key presses: ctrl, shift, alt, or meta key.
	 * For info on meta key, see: http://en.wikipedia.org/wiki/Meta_key
	 * Key press combinations not supported. Only one @ a time.
	 * Use null, "", or "none" as value if not using
	 * any key presses while clicking.
	 * @param imgFile
	 * @param modifierKey
	 * @return boolean
	 */
	public static boolean click_object(String imgFile, String modifierKey){		
		try{
			Screen s = new Screen();
			//int modifier = getModifierValue(s,modifierKey);		
			int modifier = getModifierValue(modifierKey);
			System.out.println("Click object "+imgFile+" with given modifier key: "+modifierKey);
			s.click(imgFile,modifier);	        
	        return true;
		}
		catch (Throwable e) { //FindFailed exception
			System.out.println("Failed to click object "+imgFile+" with given modifier key: "+modifierKey);
			System.out.println("Here's a stack dump of the caught exception for more details of failure...");
			e.printStackTrace();
			return false;
		}		
	}
	
	/**
	 * Double-click on object defined by PNG image file.
	 * Specify any keys to press while clicking.
	 * E.g. ctrl click, shift click, etc.
	 * Valid key presses: ctrl, shift, alt, or meta key.
	 * For info on meta key, see: http://en.wikipedia.org/wiki/Meta_key
	 * Key press combinations not supported. Only one @ a time.
	 * Use null, "", or "none" as value if not using
	 * any key presses while clicking.
	 * @param imgFile
	 * @param modifierKey
	 * @return boolean
	 */
	public static boolean double_click_object(String imgFile, String modifierKey){		
		try{
			Screen s = new Screen();
			//int modifier = getModifierValue(s,modifierKey);		
			int modifier = getModifierValue(modifierKey);
			System.out.println("Double-click object "+imgFile+" with given modifier key: "+modifierKey);
			s.doubleClick(imgFile,modifier);			
			return true;
		}
		catch (Throwable e) { //FindFailed exception
			System.out.println("Failed to double-click object "+imgFile+" with given modifier key: "+modifierKey);
			System.out.println("Here's a stack dump of the caught exception for more details of failure...");
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Right-click on object defined by PNG image file.
	 * Specify any keys to press while clicking.
	 * E.g. ctrl click, shift click, etc.
	 * Valid key presses: ctrl, shift, alt, or meta key.
	 * For info on meta key, see: http://en.wikipedia.org/wiki/Meta_key
	 * Key press combinations not supported. Only one @ a time.
	 * Use null, "", or "none" as value if not using
	 * any key presses while clicking.
	 * @param imgFile
	 * @param modifierKey
	 * @return boolean
	 */
	public static boolean right_click_object(String imgFile, String modifierKey){		
		try{
			Screen s = new Screen();
			//int modifier = getModifierValue(s,modifierKey);		
			int modifier = getModifierValue(modifierKey);
			System.out.println("Right-click object "+imgFile+" with given modifier key: "+modifierKey);
			s.rightClick(imgFile,modifier);			
			return true;
		}
		catch (Throwable e) { //FindFailed exception
			System.out.println("Failed to right-click object "+imgFile+" with given modifier key: "+modifierKey);
			System.out.println("Here's a stack dump of the caught exception for more details of failure...");
			e.printStackTrace();
			return false;
		}		
	}
	
	/**
	 * Drag object defined by source PNG image file to
	 * destination object defined by target PNG image file.
	 * Specify any keys to press while drag & drop.
	 * E.g. ctrl click, shift click, etc.
	 * Valid key presses: ctrl, shift, alt, or meta key.
	 * For info on meta key, see: http://en.wikipedia.org/wiki/Meta_key
	 * Key press combinations not supported. Only one @ a time.
	 * Use null, "", or "none" as value if not using
	 * any key presses while drag & drop.
	 * @param srcImg
	 * @param targetImg
	 * @param modifierKey
	 * @return boolean
	 */
	public static boolean drag_and_drop_object(String srcImg, String targetImg, String modifierKey){		
		try{
			Screen s = new Screen();
			//int modifier = getModifierValue(s,modifierKey);		
			int modifier = getModifierValue(modifierKey);
			//drag source to target
			System.out.println("Drag & drop object "+srcImg+" to object "+targetImg+" with given modifier key: "+modifierKey);
			s.dragDrop(srcImg,targetImg,modifier);			
			return true;
		}
		catch (Throwable e) { //FindFailed exception
			System.out.println("Failed to drag & drop object "+srcImg+" to object "+targetImg+" with given modifier key: "+modifierKey);
			System.out.println("Here's a stack dump of the caught exception for more details of failure...");
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Hover the mouse over the object defined by PNG image file.
	 * @param imgFile
	 * @return boolean
	 */
	public static boolean hover_over_object(String imgFile){
		try{
			Screen s = new Screen();
			System.out.println("Hovering over object "+imgFile);
			s.hover(imgFile);			
			return true;
		}
		catch (Throwable e) { //FindFailed exception
			System.out.println("Failed to hover over object "+imgFile);
			System.out.println("Here's a stack dump of the caught exception for more details of failure...");
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Type given text string at or into 
	 * object defined by PNG image file.
	 * Specify any special keys presses to press while typing.
	 * E.g.: ctrl, shift, alt, or meta key.
	 * For info on meta key, see: http://en.wikipedia.org/wiki/Meta_key
	 * Key press combinations not supported. Only one @ a time.
	 * Use null, "", or "none" as value if not using
	 * any special keys while typing.
	 * @param imgFile
	 * @param txt
	 * @param modifierKey
	 * @return boolean
	 */
	public static boolean type_at_object(String imgFile, String txt, String modifierKey){
		try{
			Screen s = new Screen();
			//int modifier = getModifierValue(s,modifierKey);		
			int modifier = getModifierValue(modifierKey);
			System.out.println("Type string \""+txt+"\" to object "+imgFile+" with given modifier key: "+modifierKey);
			s.type(imgFile,txt,modifier);
			return true;
		}
		catch (Throwable e) { //FindFailed exception
			System.out.println("Failed to type string \""+txt+"\" to object "+imgFile+" with given modifier key: "+modifierKey);
			System.out.println("Here's a stack dump of the caught exception for more details of failure...");
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Type given text string at or into 
	 * object defined by PNG image file.
	 * Specify any special keys presses to press while typing.
	 * E.g.: ctrl, shift, alt, or meta key.
	 * For info on meta key, see: http://en.wikipedia.org/wiki/Meta_key
	 * Key press combinations supported in an escaped manner.
	 * When string 'escape' sequence found in 'txt' it will be changed to
	 * the specified Key.xxxx in the txt inline. 
	 * Use null, "", or "none" as value if not using
	 * any special keys while typing.
	 * @param imgFile
	 * @param escape
	 * @param txt
	 * @param modifierKey
	 * @return boolean
	 */
	public static boolean type_at_object(String imgFile, String escape, String txt, String modifierKey){
		if (escape.toLowerCase().equals("return")) 
			txt = getKeyByName(escape) + txt; 
		return type_at_object(imgFile, txt, modifierKey);
	}
	/**
	 * Paste given text string at or into
	 * object defined by PNG image file.
	 * @param imgFile
	 * @param txt
	 * @return boolean
	 */
	public static boolean paste_at_object(String imgFile, String txt){
		try{
			Screen s = new Screen();
			System.out.println("Paste string \""+txt+"\" to object "+imgFile);
			s.paste(imgFile, txt);			
			return true;
		}
		catch (Throwable e) { //FindFailed exception
			System.out.println("Failed to paste string \""+txt+"\" to object "+imgFile);
			System.out.println("Here's a stack dump of the caught exception for more details of failure...");
			e.printStackTrace();
			return false;
		}
	}
	
	//private static int getModifierValue(Screen scr, String modifier){
	private static int getModifierValue(String modifier){
		//using reference: http://sikuli.org/doc/java-x/constant-values.html
		int modifierValue;
		if(modifier != null){
			if(modifier.toLowerCase() == ""){
				modifierValue = 0;
			}else if(modifier.toLowerCase() == "alt"){
				modifierValue = Key.C_ALT; //scr.K_ALT;
			}else if(modifier.toLowerCase() == "ctrl"){	
				modifierValue = Key.C_CTRL; //scr.K_CTRL;
			}else if(modifier.toLowerCase() == "meta"){
				//For info on meta key, see: http://en.wikipedia.org/wiki/Meta_key
				modifierValue = Key.C_META; //scr.K_META;
			}else if(modifier.toLowerCase() == "none"){
				modifierValue = 0;
			}else if(modifier.toLowerCase() == "shift"){
				modifierValue = Key.C_SHIFT; //scr.K_SHIFT;
			}else{ //if unrecognized, set to none
				modifierValue = 0;		
			}
		}else{ //null
			modifierValue = 0;
		}
		return modifierValue;
	}
	
	/**
	 * Self test for Sikuli Library,
	 * exposed as command-line (CLI) interface
	 * to the library's API methods for easy use of Sikuli  
	 * @param args
	 */
	public static void main(String[] args){		
		if(args.length < 2)
			displayUsage();
		String cmd = args[0];
		boolean retVal = false;
		int retCode = 0;
		try{
			//parse arguments & execute requested method			
			if(cmd.equalsIgnoreCase("exists")){
				retVal = object_exists(args[1],Integer.parseInt(args[2]));
			}else if(cmd.equalsIgnoreCase("waitfor")){
				retVal = wait_for_object(args[1],Integer.parseInt(args[2]));
			}else if(cmd.equalsIgnoreCase("wait2disappear")){
				retVal = wait_for_object_to_disappear(args[1],Integer.parseInt(args[2]));
			}else if(cmd.equalsIgnoreCase("click")){
				retVal = click_object(args[1],args[2]);
			}else if(cmd.equalsIgnoreCase("double-click")){
				retVal = double_click_object(args[1],args[2]);
			}else if(cmd.equalsIgnoreCase("right-click")){
				retVal = right_click_object(args[1],args[2]);
			}else if(cmd.equalsIgnoreCase("dragndrop")){
				retVal = drag_and_drop_object(args[1],args[2],args[3]);
			}else if(cmd.equalsIgnoreCase("hover")){
				retVal = hover_over_object(args[1]);
			}else if(cmd.equalsIgnoreCase("type")){
				retVal = type_at_object(args[1],args[2],args[3]);
			}else if(cmd.equalsIgnoreCase("paste")){
				retVal = paste_at_object(args[1],args[2]);
			}else{
				System.out.println("Unrecognized command.");
			}
			retCode = retVal ? 1 : 0;
			System.exit(retCode);
		}
		catch (Throwable e) { 
			System.out.println("Failed to execute command "+cmd+". Wrong # arguments?");
			System.out.println("Here's a stack dump of the caught exception for more details of failure...");
			e.printStackTrace();
			System.exit(retCode);
		}		
	}
	
	private static void displayUsage() {
		System.out.println("");
		System.out.println("simplesikuli - v1.0");
		System.out.println("");
		System.out.println("A simple command line interface & wrapper API library to Sikuli.");
		System.out.println("");
		System.out.println("Pre-requisite: Sikuli X installed on machine.");
		System.out.println("");
		System.out.println("Usage Info:");
		System.out.println("");
		System.out.println("  java [-cp .;sikuli-script.jar] org.robotframework.remotelibrary.SikuliLibrary command [parameters]");
		System.out.println("    you may or may not need to reference the Sikuli JAR file, or your class path");
		System.out.println("    reference may be different, depending on your environment & deployment configuration.");
		System.out.println("");		
		System.out.println("  or if everything packaged into a JAR...");
		System.out.println("");
		System.out.println("  java -jar sikuliLibrary.jar command [parameters]");
		System.out.println("");
		System.out.println("Validation of results: commands return a return code of 0 (false/fail) or 1");
		System.out.println("                       (true/pass), which can be used by calling script or");
		System.out.println("                       application to determine pass/fail. Commands also");
		System.out.println("                       output such info to standard output.");
		System.out.println("");
		System.out.println("Commands below, in format: command (followed by) parameters (if any)");
		System.out.println("");
		System.out.println("  exists pathToImageFileRepresentingObject timeoutInSeconds");
		System.out.println("  waitfor pathToImageFileRepresentingObject timeoutInSeconds");
		System.out.println("  wait2disappear pathToImageFileRepresentingObject timeoutInSeconds");
		System.out.println("  click pathToImageFileRepresentingObject modifierKey");
		System.out.println("  double-click pathToImageFileRepresentingObject modifierKey");
		System.out.println("  right-click pathToImageFileRepresentingObject modifierKey");
		System.out.println("  dragndrop pathToSourceImageFileRepresentingObject pathToTargetImageFileRepresentingObject modifierKey");
		System.out.println("  hover pathToImageFileRepresentingObject");
		System.out.println("  type pathToImageFileRepresentingObject textToTypeInQuotesIfHaveWhiteSpace modifierKey");
		System.out.println("  paste pathToImageFileRepresentingObject textToTypeInQuotesIfHaveWhiteSpace");
		System.out.println("");
		System.out.println("Modifier keys:");
		System.out.println("");
		System.out.println("  alt");
		System.out.println("  ctrl");
		System.out.println("  meta - see http://en.wikipedia.org/wiki/Meta_key for details");
		System.out.println("  shift");
		System.out.println("  none - specified as: none, null, or empty string \"\"");
		System.out.println("");
		System.out.println("For more details on functionality, visit Sikuli.org.");
		System.out.println("");		
		System.exit(0);
	}
	
	private static String getKeyByName(String key){
		String result = null;
		if (key.toLowerCase().equalsIgnoreCase("space")) result = Key.SPACE;
		else if (key.toLowerCase().equalsIgnoreCase("enter")) result = Key.ENTER;
		else if (key.toLowerCase().equalsIgnoreCase("backspace")) result = Key.BACKSPACE;
/*		   public static final String ENTER       = "\n";
		   public static final String BACKSPACE   = "\b";
		   public static final String TAB         = "\t";
		   public static final String ESC         = "\u001b";
		   public static final char C_ESC         = '\u001b';
		   public static final String UP          = "\ue000";
		   public static final char C_UP          = '\ue000';
		   public static final String RIGHT       = "\ue001";
		   public static final char C_RIGHT       = '\ue001';
		   public static final String DOWN        = "\ue002";
		   public static final char C_DOWN        = '\ue002';
		   public static final String LEFT        = "\ue003";
		   public static final char C_LEFT        = '\ue003';
		   public static final String PAGE_UP     = "\ue004";
		   public static final char C_PAGE_UP     = '\ue004';
		   public static final String PAGE_DOWN   = "\ue005";
		   public static final char C_PAGE_DOWN   = '\ue005';
		   public static final String DELETE      = "\ue006";
		   public static final char C_DELETE      = '\ue006';
		   public static final String END         = "\ue007";
		   public static final char C_END         = '\ue007';
		   public static final String HOME        = "\ue008";
		   public static final char C_HOME        = '\ue008';
		   public static final String INSERT      = "\ue009";
		   public static final char C_INSERT      = '\ue009';
		   public static final String F1          = "\ue011";
		   public static final char C_F1          = '\ue011';
		   public static final String F2          = "\ue012";
		   public static final char C_F2          = '\ue012';
		   public static final String F3          = "\ue013";
		   public static final char C_F3          = '\ue013';
		   public static final String F4          = "\ue014";
		   public static final char C_F4          = '\ue014';
		   public static final String F5          = "\ue015";
		   public static final char C_F5          = '\ue015';
		   public static final String F6          = "\ue016";
		   public static final char C_F6          = '\ue016';
		   public static final String F7          = "\ue017";
		   public static final char C_F7          = '\ue017';
		   public static final String F8          = "\ue018";
		   public static final char C_F8          = '\ue018';
		   public static final String F9          = "\ue019";
		   public static final char C_F9          = '\ue019';
		   public static final String F10         = "\ue01A";
		   public static final char C_F10         = '\ue01A';
		   public static final String F11         = "\ue01B";
		   public static final char C_F11         = '\ue01B';
		   public static final String F12         = "\ue01C";
		   public static final char C_F12         = '\ue01C';
		   public static final String F13         = "\ue01D";
		   public static final char C_F13         = '\ue01D';
		   public static final String F14         = "\ue01E";
		   public static final char C_F14         = '\ue01E';
		   public static final String F15         = "\ue01F";
		   public static final char C_F15         = '\ue01F';
		   public static final String SHIFT       = "\ue020";
		   public static final char C_SHIFT       = '\ue020';
		   public static final String CTRL        = "\ue021";
		   public static final char C_CTRL        = '\ue021';
		   public static final String ALT         = "\ue022";
		   public static final char C_ALT         = '\ue022';
		   public static final String META        = "\ue023";
		   public static final char C_META        = '\ue023';
		   public static final String CMD         = "\ue023";
		   public static final char C_CMD         = '\ue023';
		   public static final String WIN         = "\ue023";
		   public static final char C_WIN         = '\ue023';
		   public static final String PRINTSCREEN = "\ue024";
		   public static final char C_PRINTSCREEN = '\ue024';
		   public static final String SCROLL_LOCK = "\ue025";
		   public static final char C_SCROLL_LOCK = '\ue025';
		   public static final String PAUSE       = "\ue026";
		   public static final char C_PAUSE       = '\ue026';
		   public static final String CAPS_LOCK   = "\ue027";
		   public static final char C_CAPS_LOCK   = '\ue027';
		   public static final String NUM0        = "\ue030";
		   public static final char C_NUM0        = '\ue030';
		   public static final String NUM1        = "\ue031";
		   public static final char C_NUM1        = '\ue031';
		   public static final String NUM2        = "\ue032";
		   public static final char C_NUM2        = '\ue032';
		   public static final String NUM3        = "\ue033";
		   public static final char C_NUM3        = '\ue033';
		   public static final String NUM4        = "\ue034";
		   public static final char C_NUM4        = '\ue034';
		   public static final String NUM5        = "\ue035";
		   public static final char C_NUM5        = '\ue035';
		   public static final String NUM6        = "\ue036";
		   public static final char C_NUM6        = '\ue036';
		   public static final String NUM7        = "\ue037";
		   public static final char C_NUM7        = '\ue037';
		   public static final String NUM8        = "\ue038";
		   public static final char C_NUM8        = '\ue038';
		   public static final String NUM9        = "\ue039";
		   public static final char C_NUM9        = '\ue039';
		   public static final String SEPARATOR   = "\ue03A";
		   public static final char C_SEPARATOR   = '\ue03A';
		   public static final String NUM_LOCK    = "\ue03B";
		   public static final char C_NUM_LOCK    = '\ue03B';
		   public static final String ADD         = "\ue03C";
		   public static final char C_ADD         = '\ue03C';
		   public static final String MINUS       = "\ue03D";
		   public static final char C_MINUS       = '\ue03D';
		   public static final String MULTIPLY    = "\ue03E";
		   public static final char C_MULTIPLY    = '\ue03E';
		   public static final String DIVIDE      = "\ue03F";
		   public static final char C_DIVIDE      = '\ue03F';
	*/	
		return result;
	}
}