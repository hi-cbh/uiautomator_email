package com.uitest.util;


import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.contact.cmp.Layout;
import com.contact.cmp.ListView;


public class TestDemo extends UiAutomatorBase {

	public static void main(String[] args) {
		String jarName = "TestDemo";
		String testClass = "com.uitest.util.TestDemo";
		String testName = "";
		String androidId = "4";
		new UiAutomatorHelper(jarName, testClass, testName, androidId);
	}
	
	public void testCase() throws UiObjectNotFoundException{
		switeVertical("android.support.v4.view.ViewPager", 20);
	}
}
