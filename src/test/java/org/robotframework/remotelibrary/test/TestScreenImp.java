/**
 * 
 */
package org.robotframework.remotelibrary.test;

import org.robotframework.remotelibrary.IRemoteScreen;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;

/**
 * @author ljszalai
 *
 */
public class TestScreenImp implements IRemoteScreen {

	/* (non-Javadoc)
	 * @see org.robotframework.remotelibrary.IRemoteScreen#wait(java.lang.String, double)
	 */
	public Match wait(String imgFile, double timeout) throws FindFailed {
		if (imgFile.equalsIgnoreCase("exception")) {
			throw new FindFailed("Test excetption. Nothing serious.");
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.robotframework.remotelibrary.IRemoteScreen#waitVanish(java.lang.String, double)
	 */
	public boolean waitVanish(String imgFile, double timeout) {
		if (imgFile.equalsIgnoreCase("exception")) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see org.robotframework.remotelibrary.IRemoteScreen#click(java.lang.String, int)
	 */
	public int click(String imgFile, int modifier) throws FindFailed {
		if (imgFile.equalsIgnoreCase("exception")) {
			throw new FindFailed("Test excetption. Nothing serious.");
		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.robotframework.remotelibrary.IRemoteScreen#doubleClick(java.lang.String, int)
	 */
	public int doubleClick(String imgFile, int modifier) throws FindFailed {
		if (imgFile.equalsIgnoreCase("exception")) {
			throw new FindFailed("Test excetption. Nothing serious.");
		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.robotframework.remotelibrary.IRemoteScreen#rightClick(java.lang.String, int)
	 */
	public int rightClick(String imgFile, int modifier) throws FindFailed {
		if (imgFile.equalsIgnoreCase("exception")) {
			throw new FindFailed("Test excetption. Nothing serious.");
		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.robotframework.remotelibrary.IRemoteScreen#dragDrop(java.lang.String, java.lang.String, int)
	 */
	public int dragDrop(String srcImg, String targetImg, int modifier)
			throws FindFailed {
		if (srcImg.equalsIgnoreCase("exception")) {
			throw new FindFailed("Test excetption. Nothing serious.");
		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.robotframework.remotelibrary.IRemoteScreen#hover(java.lang.String)
	 */
	public int hover(String imgFile) throws FindFailed {
		if (imgFile.equalsIgnoreCase("exception")) {
			throw new FindFailed("Test excetption. Nothing serious.");
		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.robotframework.remotelibrary.IRemoteScreen#type(java.lang.String, java.lang.String, int)
	 */
	public int type(String imgFile, String txt, int modifier) throws FindFailed {
		if (imgFile.equalsIgnoreCase("exception")) {
			throw new FindFailed("Test excetption. Nothing serious.");
		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.robotframework.remotelibrary.IRemoteScreen#paste(java.lang.String, java.lang.String)
	 */
	public int paste(String imgFile, String txt) throws FindFailed {
		if (imgFile.equalsIgnoreCase("exception")) {
			throw new FindFailed("Test excetption. Nothing serious.");
		}
		return 0;
	}

}
