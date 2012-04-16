/**
 * 
 */
package org.robotframework.remotelibrary;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

/**
 * @author ljszalai
 *
 */
public class RemoteScreenImp implements IRemoteScreen {

	/* (non-Javadoc)
	 * @see org.robotframework.remotelibrary.IRemoteScreen#wait(java.lang.String, double)
	 */
	public Match wait(String imgFile, double timeout) throws FindFailed {
		Screen s = new Screen();
		return s.wait(imgFile,timeout);
	}

	/* (non-Javadoc)
	 * @see org.robotframework.remotelibrary.IRemoteScreen#waitVanish(java.lang.String, double)
	 */
	public boolean waitVanish(String imgFile, double timeout) {
		Screen s = new Screen();
		return s.waitVanish(imgFile,timeout);
	}

	/* (non-Javadoc)
	 * @see org.robotframework.remotelibrary.IRemoteScreen#click(java.lang.String, java.lang.String)
	 */
	public int click(String imgFile, int modifier) throws FindFailed {
		Screen s = new Screen();
		return s.click(imgFile,modifier);
	}

	/* (non-Javadoc)
	 * @see org.robotframework.remotelibrary.IRemoteScreen#doubleClick(java.lang.String, java.lang.String)
	 */
	public int doubleClick(String imgFile, int modifier) throws FindFailed {
		Screen s = new Screen();
		return s.doubleClick(imgFile,modifier);
	}

	/* (non-Javadoc)
	 * @see org.robotframework.remotelibrary.IRemoteScreen#rightClick(java.lang.String, java.lang.String)
	 */
	public int rightClick(String imgFile, int modifier) throws FindFailed {
		Screen s = new Screen();
		return s.rightClick(imgFile,modifier);
	}

	/* (non-Javadoc)
	 * @see org.robotframework.remotelibrary.IRemoteScreen#dragDrop(java.lang.String, java.lang.String, java.lang.String)
	 */
	public int dragDrop(String srcImg, String targetImg, int modifier)
			throws FindFailed {
		Screen s = new Screen();
		return s.dragDrop(srcImg,targetImg,modifier);
	}

	/* (non-Javadoc)
	 * @see org.robotframework.remotelibrary.IRemoteScreen#hover(java.lang.String)
	 */
	public int hover(String imgFile) throws FindFailed {
		Screen s = new Screen();
		return s.hover(imgFile);
	}

	/* (non-Javadoc)
	 * @see org.robotframework.remotelibrary.IRemoteScreen#type(java.lang.String, java.lang.String, int)
	 */
	public int type(String imgFile, String txt, int modifier) throws FindFailed {
		Screen s = new Screen();
		return s.type(imgFile,txt,modifier);
	}

	/* (non-Javadoc)
	 * @see org.robotframework.remotelibrary.IRemoteScreen#paste(java.lang.String, java.lang.String)
	 */
	public int paste(String imgFile, String txt) throws FindFailed {
		Screen s = new Screen();
		return s.paste(imgFile, txt);
	}


}
