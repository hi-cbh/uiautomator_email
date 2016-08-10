package com.uitest.contact;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;


import android.net.ParseException;
import android.os.RemoteException;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.contact.activity.MainActivity_contact;

public class TestContactBase extends MainActivity_contact {
	
	String packageName = "com.chinamobile.contacts.im";


	/**
	 * 创建已创建的联系人已被删除
	 */
	public void ConfirmDelContacts(String name) {
		back("tab_contacts");
		if(super.search(name)){
			try {
				String searchName = getListViewFirstName().getText().toString().trim();
				System.out.println("name " + searchName);

				assertEquals("删除联系人失败,联系人仍存在", true, false);
			} catch (UiObjectNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			assertEquals("创建联系人失败！,没有找到联系人", true, true);
			//清理数据
			clearRecord();
			searchCancel();
		}
	}

	/**
	 * 确认新创建的联系人是否存在
	 */
	public void ConfirmNewContact(String name, String phone) {		
		back("tab_contacts");
		if(super.search(name)){
			try {
				String searchName = getListViewFirstName().getText().toString().trim();
				System.out.println("name " + searchName);

				String searchPhone = getListViewFirstPhoneInSearch().getText().toString().trim();
				System.out.println("phone " + searchPhone);
				
				assertEquals("创建联系人失败！,名称不正确", true, searchName.equals(name));
				assertEquals("创建联系人失败！,号码不正确", true, searchPhone.equals(phone));
				
				//清理数据
				clearRecord();
				searchCancel();
			} catch (UiObjectNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			assertEquals("创建联系人失败！,没有找到联系人", true, false);
		}
	}

	public void deleteContactName(String name) {
		super.deleteContactByName(name);
	}
	
	public void deleteContactPhone(String name) {
		super.deleteContactByPhone(name);
	}

	/**
	 * 打开和通讯录
	 */
	public void openContact() {
		 findAndOpenApp("和通讯录");
		 sleep(2000);
		 
		 String curpackageName=UiDevice.getInstance().getCurrentPackageName();
		 assertEquals("openContact error ",packageName, curpackageName);
	}
	
	public void openEmail(){
		 findAndOpenApp("139邮箱");
		 sleep(2000);
		 
		 String curpackageName=UiDevice.getInstance().getCurrentPackageName();
		 assertEquals("openEmail error ","cn.cj.pe", curpackageName);
	}
	
	
	/**
	 * 需求：创建联系人
	 * 
	 * @param name
	 * @param phone
	 */
	public void newContact(String name, String phone) {
		super.newContact(name, phone);
	}
	
	/**
	 * 需求：创建联系人
	 * 
	 * @param name
	 * @param phone
	 */
	public void swipeDownNewContact(String name, String phone) {
		super.swipeDownNewContact(name, phone);
	}
	
	/**
	 * 清除所有联系人
	 */
	public void clearContact(){
		super.clearContact();
	}
	
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
		        UiObject close = getUiObjectByResourceId("com.android.systemui:id/recents_RemoveAll_button");
		        
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
	
	public void back(String name){
		super.back(name);
	}
	
	/**
	 * 输入用户、密码，登录
	 * 
	 * @param username
	 * @param password
	 */
	public void Login(String username, String password) {

		// 点击和通讯录
		clickById("iab_title");


		//sleep(3000);
		int num = getTextViewCount();
		System.out.println("num1: " + num);
		// System.out.println("isLoginState() " + isLoginState());
		// 判断是否为登录状态，若未登录状态，进行下一步；否则返回
		if (!isLoginState()) {
			// 点击设置
			clickById("setting_layout");
			// 点击登录
			clickById("setting_item_login");
			// 点击互联网登录
			clickById("btn_login_dynamic");

			// 输入用户名
			inputTextById("setting_new_login_mobile_et_num", username);

			// 输入密码
			inputTextById("setting_new_login_mobile_et_password",
					password);

			
			String startime = getCurrentSysTime();
			System.out.println("startime: " + startime);
			// 点击登录
			clickById("setting_new_login_mobile_btn_login");
			
			if(!waitForExiststById("setting_item_login_logout_text")){
				saveToFile("run time: 0 ms, Login failed!");
				assertEquals("Login failed! login time more than 20s", true, false);
			}
			
			String endtime = getCurrentSysTime();
			System.out.println("endtime: " + endtime);
			try {
				long runtime =  getTimeDistance(endtime , startime);
				saveToFile("runtime: " + runtime + "ms");
				System.out.println("run time: " + runtime);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// 等待登录时间(根据网络状态)
			sleep(10000);
		}

		back("tab_contacts");

	}
	
	  /**
     * 追加文件：使用FileOutputStream，在构造FileOutputStream时，把第二个参数设为true
     * 
     * @param fileName
     * @param content
     */
    public static void saveToFile(String conent) {
    	String FileName = "test.txt";
    	String ROOT_PATH="/mnt/sdcard/AppTestReport/";
    	String Path = ROOT_PATH + FileName;
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(Path, true)));
            out.write(conent);
            out.write("\r\n");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
	
	/**
	 * 是否在登录状态，false为未登录状态，true为已登录
	 * 
	 * @param username
	 * @return
	 */
	public boolean isLoginState() {
		try {
			int num = getTextViewCount();
			System.out.println("num2: " + num);
			if(num == 2){
				return true;
			}
			else if (num == 1){
				return false;
			}
			else{
				return false;
			}
		} catch (Exception ex) {
			// 找不到元素
			return false;
		}
		
	}
	
	/**
	 * 退出登录
	 * 
	 */
	public void Logout() {

		// 点击和通讯录
		clickById("iab_title");

		// 判断是否为已登录，若已登录状态，直接返回
		if (isLoginState()) {
			// 点击设置
			clickById("setting_layout");
			// 点击退出
			clickById("setting_item_login_logout_text");
			// 点击确认
			clickById("dialog_btn_positive");
		}

		back("tab_contacts");
		sleep(5000);
	}	
}
