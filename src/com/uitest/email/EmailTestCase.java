package com.uitest.email;

import com.uitest.util.UiAutomatorHelper;

import android.os.RemoteException;

public class EmailTestCase extends EmailTestBase {

	public static void main(String[] args) {
		String jarName = "EmailLoginTime";
		String testClass = "com.uitest.email.EmailTestCase";
		String testName = "";
		String androidId = "4";
		new UiAutomatorHelper(jarName, testClass, testName, androidId);
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		openEmail();
	}
	
	/**
	 * @throws RemoteException
	 */
	public void testCase_001() throws RemoteException {
		sleep(8000);
		logoutEmail();
		loginEmail("13427665104","yscs12345");
		sleep(2000);

	}
	
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		exitApp();
	}
}
