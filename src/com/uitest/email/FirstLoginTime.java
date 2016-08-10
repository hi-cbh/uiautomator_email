package com.uitest.email;

import com.uitest.util.UiAutomatorHelper;

import android.os.RemoteException;

public class FirstLoginTime extends EmailTestBase {

	public static void main(String[] args) {
		String jarName = "FirstLoginTime";
		String testClass = "com.uitest.email.FirstLoginTime";
		String testName = "";
		String androidId = "4";
		System.out.println("running");
		new UiAutomatorHelper(jarName, testClass, testName, androidId);
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		clearData();
		openEmail();
	}
	
	/**
	 * @throws RemoteException
	 */
	public void testCase_001() throws RemoteException {
		sleep(8000);
		switeToPage();
		firstLoginEmail("13427665104","yscs12345");
		
		firstLogoutEmail();
		sleep(2000);
		
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		exitApp();
	}
}
