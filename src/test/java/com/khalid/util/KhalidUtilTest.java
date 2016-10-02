package com.khalid.util;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class KhalidUtilTest {

	@Test
	public void testIsNullOrEmpty() {
		assertTrue(KhalidUtil.isNullOrEmpty(null));
		assertTrue(KhalidUtil.isNullOrEmpty(""));
		assertTrue(KhalidUtil.isNullOrEmpty("    "));
		assertFalse(KhalidUtil.isNullOrEmpty(" fdfdfd "));

		//fail("Not yet implemented");
	}
	
	@Test
	public void testParseCSV() {
		assertNull(KhalidUtil.parseCSV(" "));
		List<String> list = KhalidUtil.parseCSV(" delta,air france,united   ");
		assertTrue(list.size()==3);
		assertTrue(list.get(0).equalsIgnoreCase("delta"));

		List<String> list2 = KhalidUtil.parseCSV(" delta , air france , united   ");
		assertTrue(list2.size()==3);
		assertTrue(list2.get(0).equalsIgnoreCase("delta"));
		assertTrue(list2.get(1).equalsIgnoreCase("air france"));
		assertTrue(list2.get(2).equalsIgnoreCase("united"));

	
	}
}