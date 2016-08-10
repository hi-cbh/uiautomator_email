package com.uitest.email;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.uitest.util.UiAutomatorHelper;

import android.net.ParseException;
import android.os.RemoteException;

public class SendEmailTime extends EmailTestBase {

	public static void main(String[] args) {
		String jarName = "SendEmailTime";
		String testClass = "com.uitest.email.SendEmailTime";
		String testName = "";
		String androidId = "4";
		new UiAutomatorHelper(jarName, testClass, testName, androidId);
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		//openEmail();
	}
	
	/**
	 * @throws RemoteException
	 * @throws UiObjectNotFoundException 
	 * @throws java.text.ParseException 
	 * @throws ParseException 
	 */
	public void testCase_001() throws RemoteException, UiObjectNotFoundException, ParseException, java.text.ParseException {
		//sleep(8000);
		//logoutEmail();
		//loginEmail("13427665104","yscs12345");
		sendEmail();
		
		//sleep(2000);

	}
	
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		//exitApp();
	}
}
