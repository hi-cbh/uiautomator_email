package com.uitest.email;

import android.graphics.Rect;
import android.net.ParseException;

import com.android.uiautomator.core.Configurator;
import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.uitest.util.TestCodeBase;

public class EmailTestBase extends TestCodeBase {
	private static String saveFileName = "LoginTime.txt"; 
	private static String FirstLoginTime = "FirstLoginTime.txt"; 
	
	public void openEmail(){
		 findAndOpenApp("139邮箱");
		 sleep(2000);
		 
		 String curpackageName=UiDevice.getInstance().getCurrentPackageName();
		 assertEquals("openEmail error ","cn.cj.pe", curpackageName);
	}
	

	public void loginEmail(String phone, String pwd){
		
		// 输入用户名
		inputTextById("login_name", phone);

		// 输入密码
		inputTextById("login_password",pwd);
		
		long times = Configurator.getInstance().getActionAcknowledgmentTimeout();
		Configurator.getInstance().setActionAcknowledgmentTimeout(0);
		
		long times2 = Configurator.getInstance().getWaitForSelectorTimeout();
		Configurator.getInstance().setWaitForSelectorTimeout(0);
		
		clickById("login");
		
		String startime = getCurrentSysTime();
		System.out.println("startime: " + startime);
		
		
		if(!waitForExiststById("hjl_headicon")){
			saveToFile(saveFileName,"run time: 0 ms, Login failed!");
			assertEquals("Login failed! login time more than 20s", true, false);
		}
		
		String endtime = getCurrentSysTime();
		System.out.println("endtime: " + endtime);
		
		Configurator.getInstance().setActionAcknowledgmentTimeout(times);
		Configurator.getInstance().setWaitForSelectorTimeout(times2);
		try {
			long runtime =  getTimeDistance(endtime , startime);
			saveToFile(saveFileName, "runtime: " + runtime + "ms");
			System.out.println("run time: " + runtime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void firstLoginEmail(String phone, String pwd){
		
		// 输入用户名
		inputTextById("login_name", phone);

		// 输入密码
		inputTextById("login_password",pwd);
		
		
		String startime = getCurrentSysTime();
		System.out.println("startime: " + startime);
		clickById("login");
		
		if(!waitForExiststById("cn.cj.pe:id/title")){
			saveToFile(FirstLoginTime,"run time: 0 ms, Login failed!");
			assertEquals("Login failed! login time more than 30s", true, false);
		}
		
		String endtime = getCurrentSysTime();
		System.out.println("endtime: " + endtime);
		try {
			long runtime =  getTimeDistance(endtime , startime);
			saveToFile(FirstLoginTime, "runtime: " + runtime + "ms");
			System.out.println("run time: " + runtime);
			System.out.println("click");
			Rect rc = getUiObjectByResourceId("cn.cj.pe:id/hjl_headicon").getBounds();
			int x = rc.centerX();
			int y = rc.centerY();
			System.out.println("click " + x + ", " + y);
			click(x,y);
			sleep(2000);
			click(x,y);
			sleep(5000);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UiObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 退出139邮箱
	 */
	public void logoutEmail() {
		if (!isExistById("hjl_headicon")) {
			return;
		}
		sleep(1000);
		clickById("hjl_headicon");

		sleep(1000);

		try {
			
			if(isExistByName("设置"))
			{
				clickByName("设置");
			}
			else{
				UiObject setinguo = getUiObjectScrollListViewByText("设置");
				setinguo.click();
				sleep(2000);
			}
			
			UiObject exituo = getUiObjectScrollListViewByText("退出");
			if (exituo.exists()) {
				exituo.click();
				sleep(500);
				clickByName("确定");
				sleep(2000);
			}
		} catch (UiObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 退出139邮箱
	 */
	public void firstLogoutEmail() {
		if (!isExistById("hjl_headicon")) {
			return;
		}
		sleep(1000);
		clickById("hjl_headicon");

		sleep(5000);
		if(isExistById("img_cancel"))
		{
			//点击一次出现提示
			clickById("img_cancel");
		}
	
		try {
			if(isExistByName("设置"))
			{
				//点击一次出现提示
				clickByName("设置");
			}
			else{
				UiObject setinguo = getUiObjectScrollListViewByText("设置");
				setinguo.click();
				sleep(2000);
			}
			
			UiObject exituo = getUiObjectScrollListViewByText("退出");
			if (exituo.exists()) {
				exituo.click();
				sleep(500);
				clickByName("确定");
				sleep(2000);
			}
		} catch (UiObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public void switeToPage(){
		//如果没有找到广告页
		if(!isExistById("pager"))
		{
			assertEquals("没有清空缓存和数据", true, false);
			return;
		}
		
		switeHorizontal(5);
		
		clickPageCenter();
	   
		if(!isExistById("auto_lay"))
		{
			assertEquals("没有进入登录页", true, false);
			return;
		}
		//cn.cj.pe:id/auto_lay
	}
	
	public void clearData(){
		findAndOpenApp("设定");
		sleep(3000);
		clickByName("一般");
		sleep(3000);
		clickByName("应用程序管理器");
		sleep(3000);
		clickByName("139邮箱");
		sleep(3000);
		if(!isExistByName("清除数据"))
		{
			assertEquals("没有找到清除数据按钮", true, false);
			return;
		}
		clickByName("清除数据");
		sleep(3000);
		clickByName("确定");
		sleep(3000);
		UiDevice.getInstance().pressHome();
	}
	
	
	public void sendEmail() throws UiObjectNotFoundException, ParseException, java.text.ParseException{
//
//		//点击新建
//		getEndClassObjectById("cn.cj.pe:id/action_bar_messagelist", "android.widget.ImageView").click();
//
//		sleep(2000);
//		
//		//获取收件人对象
//		UiObject name = getSameLineAndSameClassObjectById("cn.cj.pe:id/to_layout", "android.widget.MultiAutoCompleteTextView", 0);
//		
//		//获取标题栏范围
//		Rect rc = getUiObjectByResourceId("cn.cj.pe:id/actionbar_title_sub").getBounds();
//		
//		sleep(1000);
//		
//		//输入收件人
//		inputText(name, "13427665104@139.com");
//		
//		sleep(2000);
//		
//		//点击标题，预防点击通讯录记录（无法识别悬浮窗）
//		click(rc.centerX(), rc.centerY());
//		
//		//输入主题
//		inputTextById("cn.cj.pe:id/subject", "contact");
//		
//		//点击添加附件
//		clickById("cn.cj.pe:id/add_attachment");
//		
//		//添加图片
//		clickById("cn.cj.pe:id/attach_display_photo");
//		clickSameClassObjectByOne("android.widget.GridView","cn.cj.pe:id/pic_picture",0);
//		clickSameClassObjectByOne("android.widget.GridView","cn.cj.pe:id/pic_picture",1);
//		clickSameClassObjectByOne("android.widget.GridView","cn.cj.pe:id/pic_picture",2);
//		clickSameClassObjectByOne("android.widget.GridView","cn.cj.pe:id/pic_picture",3);
		clickSameClassObjectByMore("android.widget.GridView","cn.cj.pe:id/pic_picture",4);
//		
//		//String longStr = "hello,1234567890qwertyuiopasdfghjkl;'zxcvbnm,.";
//
//		
//		//inputTextByPoint("cn.cj.pe:id/editTextField", longStr);
//
//		long times = Configurator.getInstance().getActionAcknowledgmentTimeout();
//		Configurator.getInstance().setActionAcknowledgmentTimeout(0);
//
//		long times2 = Configurator.getInstance().getWaitForSelectorTimeout();
//		Configurator.getInstance().setWaitForSelectorTimeout(0);
//		
//		String end = "0";
//		String start = getCurrentSysTime();
//		
//		clickById("cn.cj.pe:id/txt_send");
//		
//		UiObject uo;
//		int i = 0;
//		for(;;){
//			System.out.println("i= " + i);
//			uo = getUiObjectByResourceId("cn.cj.pe:id/hjl_icon_local_contact_title_name");
//			if(uo.getText().equals("发送完成")){
//				end = getCurrentSysTime();
//				break;
//			}
//			System.out.println("text: "+uo.getText());
//			i++;
//		}
//		System.out.println("start: " + start);
//		System.out.println("end: " + end);
//		System.out.println("times: " + getTimeDistance(end, start));
//		Configurator.getInstance().setActionAcknowledgmentTimeout(times);
//		Configurator.getInstance().setWaitForSelectorTimeout(times2);
	}
	
}
