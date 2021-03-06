package com.uitest.contact;

import com.uitest.util.UiAutomatorHelper;

import android.os.RemoteException;

public class TestContactCase extends TestContactBase {

	public static void main(String[] args) {
		String jarName = "LoginTime";
		String testClass = "com.uitest.contact.TestContactCase";
		String testName = "";
		String androidId = "4";
		new UiAutomatorHelper(jarName, testClass, testName, androidId);
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		openContact();
	}
	
	/**
	 * 登录时延，和通讯录
	 * @throws RemoteException
	 */
	public void testCase_001() throws RemoteException {
		Logout();
		Login("13427665104","yscs12345");
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		exitApp();
	}
}
