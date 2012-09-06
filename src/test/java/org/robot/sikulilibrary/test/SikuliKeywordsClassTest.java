package org.robot.sikulilibrary.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.robot.sikulilibrary.SikuliKeywords;
import org.sikuli.script.Key;

public class SikuliKeywordsClassTest {

	private SikuliKeywords slib = new SikuliKeywords();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		slib.setScreen(new TestScreenImp());
	}

	@Test
	public void testObject_exists_OK() {
		slib.object_exists("test_picture", 2000);
	}

	@Test
	public void testObject_exists_exception() {
		slib.object_exists("exception",	 2000);
	}

	@Test
	public void testWait_for_object() {
		//fail("Not yet implemented");
	}

	@Test
	public void testWait_for_object_to_disappear() {
		//fail("Not yet implemented");
	}

	@Test
	public void testClick_object() {
		//fail("Not yet implemented");
	}

	@Test
	public void testDouble_click_object() {
		//fail("Not yet implemented");
	}

	@Test
	public void testRight_click_object() {
		//fail("Not yet implemented");
	}

	@Test
	public void testDrag_and_drop_object() {
		//fail("Not yet implemented");
	}

	@Test
	public void testHover_over_object() {
		//fail("Not yet implemented");
	}

	@Test
	public void testType_at_object() {
		//fail("Not yet implemented");
	}

	@Test
	public void testType_a_key_at_object() {
		//fail("Not yet implemented");
	}

	@Test
	public void testPaste_at_object() {
		//fail("Not yet implemented");
	}

	@Test
	public void testMain() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetKeyByName() {
		String ts1 = "Babe" + SikuliKeywords.getKeyByName("enter") + "Babe";
		assertTrue(ts1.equalsIgnoreCase("Babe\nBabe"));
		String ts2 = "Babe" + SikuliKeywords.getKeyByName("tab") + "Babe";
		assertTrue(ts2.equalsIgnoreCase("Babe\tBabe"));
		String ts3 = "Babe" + SikuliKeywords.getKeyByName("space") + "Babe";
		assertTrue(ts3.equalsIgnoreCase("Babe Babe"));
		String ts4 = "Babe" + SikuliKeywords.getKeyByName("down") + "Babe";
		assertTrue(ts4.equalsIgnoreCase("Babe" + Key.DOWN + "Babe"));
	}

}
