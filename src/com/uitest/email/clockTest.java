package com.uitest.email;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.Configurator;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.uitest.util.UiAutomatorHelper;

import android.net.ParseException;
import android.os.RemoteException;

public class clockTest extends EmailTestBase {

	public static void main(String[] args) {
		String jarName = "clockTest";
		String testClass = "com.uitest.email.clockTest";
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

		long times = Configurator.getInstance().getActionAcknowledgmentTimeout();
		Configurator.getInstance().setActionAcknowledgmentTimeout(0);
		
		long times2 = Configurator.getInstance().getWaitForSelectorTimeout();
		Configurator.getInstance().setWaitForSelectorTimeout(0);
		
		clickById("com.sec.android.app.clockpackage:id/stopwatch_startButton");
		
		String start = getCurrentSysTime();
		
		UiDevice.getInstance().pressHome();
		
		//sleep(5000);
		
		findAndOpenApp("时钟");

		//clickByName("秒表");
		click(460, 120);
		
		//sleep(1000);
		
		
		//clickByName("停止");
		click(180, 1200);
		
		String end = getCurrentSysTime();
		
		System.out.println("times: " + getTimeDistance(end, start));
		Configurator.getInstance().setWaitForSelectorTimeout(times2);
		Configurator.getInstance().setActionAcknowledgmentTimeout(times);
	}
	
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		//exitApp();
	}
}
