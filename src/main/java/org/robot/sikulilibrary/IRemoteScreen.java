/**
 * 
 */
package org.robot.sikulilibrary;

import org.sikuli.script.*;

/**
 * 
 * @author ljszalai
 *
 */
public interface IRemoteScreen {
	//public IRemoteScreen getRemoteScreen(String name);
	public Match wait(String imgFile, double timeout) throws FindFailed;
	public boolean waitVanish(String imgFile, double timeout);
	public int click(String imgFile, int modifier) throws FindFailed;
	public int doubleClick(String imgFile, int modifier) throws FindFailed;
	public int rightClick(String imgFile, int modifier) throws FindFailed;
	public int dragDrop(String srcImg, String targetImg, int modifier) throws FindFailed;
	public int hover(String imgFile) throws FindFailed;
	public int type(String imgFile, String txt, int modifier) throws FindFailed;
	public int paste(String imgFile, String txt) throws FindFailed;
}
