package com.uitest.util;

import android.os.RemoteException;
import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;

/**
 * 应用级，为应用提供公共的模块
 * @author Administrator
 *
 */
public class TestCodeBase extends UiAutomatorBase {
	
	/**
	 * 需求：根据name打开应用
	 * @param name
	 */
	public void findAndOpenApp(String name) {
		System.out.println("[start] findAndOpenApp" + name);
		UiDevice.getInstance().pressHome();
		
		if (getUiObjectByText("应用程序").exists()) {
			clickByName("应用程序");
		}
		UiObject uo;
		try {
			for (int i = 0; i < 3; i++) {
				uo = getUiObjectByText(name);
				if (uo.exists()) {
					uo.click();
					System.out.println("click " + name);
					return;
				} else {
					swipeToRight();
				}
			}
			System.out.println("UiObject name: " + name + " not exists");
		} catch (UiObjectNotFoundException e) {
			System.out.println("openApp error");
		}
	}

	
	/**
	 * 关闭所有应用
	 * @throws RemoteException
	 * @throws UiObjectNotFoundException
	 * @throws InterruptedException
	 */
	 public void exitApp() {
		 System.out.println("[start] close app");
	        try {
	        	UiDevice.getInstance().pressHome();
	        	
	        	sleep(2000);
				UiDevice.getInstance().pressRecentApps();
				
				//调出任务管理器
				UiObject recentapp = getUiObjectByResourceId("com.android.systemui:id/recents_root");
				
				if(recentapp.exists()){
					recentapp.waitForExists(2000); 
			        System.out.println("调出任务管理器");
				}
		        
		        sleep(2000);
		  
		        //点击关闭
		        //UiObject close = getUiObjectByResourceId("com.android.systemui:id/recents_RemoveAll_button");
		        UiObject close = getAllViewByClassName("android.widget.ImageButton", 1);
		        if(close.exists()){
		        	close.click();
					System.out.println("点击关闭");
				}
		        sleep(2000);
		        
		        UiDevice.getInstance().pressHome();
		        
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (UiObjectNotFoundException e) {
				e.printStackTrace();
			}
	        System.out.println("[end] close app");
	    }
	
	public void openApp(String name) {
		System.out.println("openApp");
		UiObject uo;
		try {
			uo = getUiObjectByDescription(name);
			if (uo.exists()) {
				uo.click();
			} else {
				System.out.println("UiObject name: " + name + " not exists");
			}
		} catch (UiObjectNotFoundException e) {
			System.out.println("openApp error");
		}
	}


}
