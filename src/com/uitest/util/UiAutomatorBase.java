package com.uitest.util;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.graphics.Rect;
import android.net.ParseException;
import android.os.RemoteException;

import com.android.uiautomator.core.UiCollection;
import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.contact.cmp.*;


/**
 * 基础模块封装uiautomator，方便下一级的调用
 * @author Administrator
 *
 */
public class UiAutomatorBase extends UiAutomatorTestCase{
	//UiDevice ud = UiDevice.getInstance();

	/**
	 * 在这里不能使用UiDevice.getInstance().getDisplayWidth()，出现uidevice未初始化;
	 */
	
	
	
	public String getCurrentSysTime() {
		SimpleDateFormat formattime1 = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss:SSS");
		//long ctime = System.currentTimeMillis();
		long ctime = System.nanoTime()/1000000L;
		String currenttime = formattime1.format(new Date(ctime));
		return currenttime;
	}
	public long getTimeDistance(String time1, String time2)
			throws ParseException, java.text.ParseException {
		SimpleDateFormat formattime1 = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss:SSS");
		Date t1 = formattime1.parse(time1);
		Date t2 = formattime1.parse(time2);
		long d = t1.getTime() - t2.getTime();
		return d;

	}
	
	  /**
   * 追加文件：使用FileOutputStream，在构造FileOutputStream时，把第二个参数设为true
   * 
   * @param fileName
   * @param content
   */
  public static void saveToFile(String FileName, String conent) {
  	//String FileName = "test.txt";
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
	
	/////////////////////公共方法
	/**
	 * 需求：根据控件name，输入内容
	 * @param name
	 * @param content
	 */
	public static void inputTextByName(String name, String content) {
		System.out.println("[start] inputTextByName: " + name + " , input: " + content);
		UiObject uo = getUiObjectByText(name);
		if (!uo.exists()) {
			System.out.println("[end] can not find: " + name );
			return;
		}
		EditText.clearEditText(uo);
		EditText.setText(uo, content);
		System.out.println("[end] inputTextByName: " + name + " , input: " + content);
	}
	

	public static void inputText(UiObject uo, String content) {
		System.out.println("[start] inputText: " + content);
		if (!uo.exists()) {
			System.out.println("[end] can not find: " );
			return;
		}
		EditText.clearEditText(uo);
		EditText.setText(uo, content);
		System.out.println("[end] inputText: " + " , input: " + content);
	}
	
	
	/**
	 * 需求：根据控件name，输入内容
	 * @param name
	 * @param content
	 */
	public void inputTextById(String id, String content) {
		System.out.println("[start] inputTextByName: " + id + " , input: " + content);
		UiObject uo = getUiObjectByResourceIdMatches(id);
		if (!uo.exists()) {
			System.out.println("[end] can not find: " + id );
			return;
		}
		EditText.clearEditText(uo);
		EditText.setText(uo, content);
		System.out.println("[end] inputTextByName: " + id + " , input: " + content);
	}
	
	/**
	 * 需求：根据坐标点击后，输入内容
	 * @param name
	 * @param content
	 */
	public void inputTextByPoint(String id, String content) {
		System.out.println("[start] inputTextByName: " + id + " , input: " + content);
		UiObject uo = getUiObjectByResourceIdMatches(id);
		if (!uo.exists()) {
			System.out.println("[end] can not find: " + id );
			return;
		}
		
		Rect ct;
		try {
			ct = uo.getBounds();
			click(ct.top, ct.top);
			EditText.setText(uo, content);
			System.out.println("[end] inputTextByName: " + id + " , input: " + content);
		} catch (UiObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	/**
	 * 需求：根据资源name点击Uiobject
	 * @param name
	 */
	public void clickByName(String name) {
		System.out.println("clickByName: " + name);
		UiObject uo;
		try {
			uo = getUiObjectByText(name);
			if (uo.exists()) {
				uo.click();
			} else {
				System.out.println("clickByName: " + name
						+ " not exists, return null");
			}
		} catch (Exception e) {
			System.out.println("[error] clickByName");
		}
	}

	/**
	 * 需求：根据资源id点击Uiobject
	 * @param id
	 */
	public static void clickById(String id) {
		System.out.println("clickById: " + id);
		UiObject uo;
		try {
			uo = getUiObjectByResourceIdMatches(id);
			if (uo.exists()) {
				uo.click();
			} else {
				System.out.println("clickById: " + id
						+ " not exists, return null");
			}
		} catch (Exception e) {
			System.out.println("[error] clickById");
		}
	}
	
	/**
	 * 需求：根据资源id点击Uiobject
	 * @param id
	 */
	public static void clickAndWaitForNewWindowById(String id) {
		System.out.println("clickAndWaitForNewWindowById: " + id);
		UiObject uo;
		try {
			uo = getUiObjectByResourceIdMatches(id);
			if (uo.exists()) {
				uo.clickAndWaitForNewWindow();
			} else {
				System.out.println("clickAndWaitForNewWindowById: " + id
						+ " not exists, return null");
			}
		} catch (Exception e) {
			System.out.println("[error] clickById");
		}
	}

	public void swipeToRight() {
		System.out.println("drag");
		int width = UiDevice.getInstance().getDisplayWidth();
		int height = UiDevice.getInstance().getDisplayHeight();
		UiDevice.getInstance().swipe(width / 2, height * 4 / 5, width, height * 4 / 5, 80);
		System.out.println("[ doing ] swipeToRight ");
	}
	
	public void swipeToLeft() {
		System.out.println("drag");
		int width = UiDevice.getInstance().getDisplayWidth();
		int height = UiDevice.getInstance().getDisplayHeight();
		UiDevice.getInstance().swipe(width* 4 / 5, height * 4 / 5, width / 5, height * 4 / 5, 30);
		System.out.println("[ doing ] swipeToRight ");
	}
	public void swipeToDown() {
		System.out.println("drag");
		int width = UiDevice.getInstance().getDisplayWidth();
		int height = UiDevice.getInstance().getDisplayHeight();
		UiDevice.getInstance().swipe(width / 2, height / 5, width / 2, height * 4 / 5, 80);
		System.out.println("[ doing ] swipeToDown ");
	}

	public String getTextViewNameById(String id){
		try {
			return getUiObjectByResourceIdMatches(id).getText();
		} catch (UiObjectNotFoundException e) {
			// TODO Auto-generated catch block
			return "";
			//e.printStackTrace();
		}
	}
	
	
	public void wakeAndUnlock() {
		System.out.println("wakeAndUnlock");
		try {
			System.out.println("isScreenOn: " + UiDevice.getInstance().isScreenOn());
			if (!UiDevice.getInstance().isScreenOn()) {
				System.out.println("wakeUp");
				UiDevice.getInstance().wakeUp();
				sleep(500);
				swipeToRight();
			}
			// 点击
			UiDevice.getInstance().pressHome();
		} catch (RemoteException e) {
			System.out.println("不能唤醒或开锁");
		}
	}

	/**
	 * 页面是否存在控件，通过ID
	 */
	public static boolean waitForExiststById(String id){
		System.out.println("waitForExiststById: " + id);
		UiObject uo = getUiObjectByResourceIdMatches(id);
		if (uo.waitForExists(30000)) {
			System.out.println("waitForExiststById: true");
			return true;
		} else {
			System.out.println("waitForExiststById: false");
			return false;
		}
	}
	
	
	/**
	 * 页面是否存在控件，通过ID
	 */
	public static boolean isExistById(String id){
		System.out.println("isExistById: " + id);
		UiObject uo = getUiObjectByResourceIdMatches(id);
		if (uo.exists()) {
			System.out.println("isExistById: true");
			return true;
		} else {
			System.out.println("isExistById: false");
			return false;
		}
	}

	/**
	 * 页面是否存在控件，通过name
	 */
	public boolean isExistByName(String name){
		System.out.println("isExistByName: " + name);
		UiObject uo = getUiObjectByText(name);
		if (uo.exists()) {
			System.out.println("isExistByName: true");
			return true;
		} else {
			System.out.println("isExistByName: false");
			return false;
		}
	}
	
	/**
	 * 使用多属性查找元素是否存在
	 */
	public boolean isExistByIdAndName(String id, String name){
		System.out.println("isExistByIdAndName: " + id);
		UiObject uo;
		uo = new UiObject(new UiSelector().resourceIdMatches(".*"+id).text(name));
		if(uo.exists()){
			System.out.println("isExistByIdAndName: true");
			return true;
		}
		return false;
	}

	public void clickLongById(UiObject uo){
		System.out.println("start long click");
		if(!uo.exists()){
			System.out.println("clickLongById null");
			return;
		}
		//uo.longClick();
		myLongClick(uo, 100);
		System.out.println("end long click");
	}
	
	
	public void myLongClick(UiObject uo, int step){
		Rect buttonRect;
		try {
			buttonRect = uo.getBounds();
			getUiDevice().swipe(buttonRect.centerX(), buttonRect.centerY(),  buttonRect.centerX(), buttonRect.centerY(), step);
		} catch (UiObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * 获取listView列表元素的数量，缺点数量不能超过半屏
	 * @param id
	 * @return
	 */
	public int getChildCount(String id){
		UiCollection collection=new UiCollection(new UiSelector().classNameMatches(".*ListView"));
		int cnt=collection.getChildCount(new UiSelector().resourceIdMatches(".*"+id));
		return cnt;
	}
	
	/**
	 * 统计登录界面的textView数量。判断是否为登录
	 * @return
	 */
	public int getTextViewCount(){
		UiCollection collection=new UiCollection(new UiSelector().className("android.widget.ViewFlipper"));
		int cnt=collection.getChildCount(new UiSelector().className("android.widget.TextView"));
		return cnt;
	}
	
	
	
	
	/**
	 * 需求：根据文本获取UiObject对象
	 * @param text
	 * @return
	 */
	public static UiObject getUiObjectByText(String text){
		return new UiObject(new UiSelector().text(text));
	}
	
	/**
	 * 需求：根据文本获取UiObject对象
	 * @param text
	 * @return
	 */
	public static UiObject getUiObjectByTextMatches(String text){
		return new UiObject(new UiSelector().textMatches(".*"+text));
	}
	
	/**
	 * 需求：根据资源ID出获取UiObject对象
	 * @param text
	 * @return
	 */
	public static UiObject getUiObjectByResourceId(String id){
		return new UiObject(new UiSelector().resourceId(id));
	}
	
	/**
	 * 需求：根据资源ID获取UiObject对象
	 * @param text
	 * @return
	 */
	public static UiObject getUiObjectByResourceIdMatches(String id){
		return new UiObject(new UiSelector().resourceIdMatches(".*"+id));
	}
	
	/**
	 * 需求：根据资源ID获取UiObject对象
	 * @param text
	 * @return
	 */
	public static UiObject getUiObjectByResourceIdMatchesIndex(String id, int index){
		return new UiObject(new UiSelector().resourceIdMatches(".*"+id).index(index));
	}
	
	/**
	 * 需求：根据description获取UiObject对象
	 * @param text
	 * @return
	 */
	public static UiObject getUiObjectByDescription(String desc){
		return new UiObject(new UiSelector().description(desc));
	}
	
	/**
	 * 需求：根据description获取UiObject对象
	 * @param text
	 * @return
	 */
	public static UiObject getUiObjectByDescriptionMatches(String desc){
		return new UiObject(new UiSelector().descriptionMatches(".*"+desc));
	}
	
	
	public static UiObject getAllViewByClassName(String className, int num){
		UiCollection collection=new UiCollection(new UiSelector().index(0));
		//int cnt=collection.getChildCount(new UiSelector().className("android.widget.ImageButton"));
		//System.out.println("getAllImageButton: " + cnt);
		try {
			return collection.getChildByInstance(new UiSelector().className(className), num);
		} catch (UiObjectNotFoundException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	
	/**
	 * 根据ID 获取对象的文本
	 * @param id
	 * @return
	 */
	public String getTextById(String id){
		try {
			UiObject uo = getUiObjectByResourceIdMatches(id);
			if(!uo.exists()){
				return "";
			}
			
			return uo.getText();
		} catch (UiObjectNotFoundException e) {
			return "";
		}
	}
	
	public UiObject getUiObjectScrollListViewByText(String text){
		UiScrollable scroll=new UiScrollable(new UiSelector().className("android.widget.ScrollView"));
		UiObject object = null;
		try {
			scroll.scrollTextIntoView(text);
			object=new UiObject(new UiSelector().text(text));
		} catch (UiObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return object;
	}
	
	
	/**
	 * 水平滑动屏幕
	 * @param num
	 */
	public void switeHorizontal(int num){
	    switeHorizontal("android.support.v4.view.ViewPager",num);
	}
	
	/**
	 * 垂直滑动屏幕
	 * @param num
	 */
	public void switeVertical(String ClassName, int num){
		System.out.println("switeVertical " + num);
		UiScrollable scroll=new UiScrollable(new UiSelector().className(ClassName));
	    
	    
    	for(int i = 0; i < num; i++){
    		//向前滑动
    		try {
				scroll.scrollForward();
				sleep(2000);
			} catch (UiObjectNotFoundException e) {
				e.printStackTrace();
			}
		}
	   
	    
	}
	
	/**
	 * 水平滑动屏幕
	 * @param num
	 */
	public void switeHorizontal(String ClassName, int num){
		System.out.println("switeVertical " + num);
		UiScrollable scroll=new UiScrollable(new UiSelector().className(ClassName));
	    scroll.setAsHorizontalList();
	    
    	for(int i = 0; i < num; i++){
    		//向前滑动
    		try {
				scroll.scrollForward();
				sleep(2000);
			} catch (UiObjectNotFoundException e) {
				e.printStackTrace();
			}
		}
	    scroll.setAsVerticalList();
	    
	}
	
	/**
	 * 点击屏幕中间
	 */
	public void clickPageCenter(){
		int width = UiDevice.getInstance().getDisplayWidth();
		int height = UiDevice.getInstance().getDisplayHeight();
		UiDevice.getInstance().click(width/2, height/2);
	}
	
	public void click(int x, int y){

		UiDevice.getInstance().click(x, y);
	}
	/**
	 * 同类控件，根据num，获取按顺序的对象
	 * @param srcObject
	 * @param destClass
	 * @param upOffset
	 * @param dowmOffset
	 * @return
	 */
	public static UiObject getSameLineAndSameClassObject(String rootClass, String destClass, int num) {
		UiCollection collection = new UiCollection(new UiSelector().className(rootClass));
		UiObject CheckObject = null;

		int num1 = collection.getChildCount(new UiSelector().className(destClass));

		System.out.println("cnt: " + num1);
		try {
			if (num > num1) {
				return CheckObject;
			} else {
				CheckObject = collection.getChildByInstance(
						new UiSelector().className(destClass), num);
			}
		} catch (UiObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return CheckObject;
	}
	
	/**
	 * 同类控件，根据num，获取按顺序的对象
	 * @param srcObject
	 * @param destClass
	 * @param upOffset
	 * @param dowmOffset
	 * @return
	 */
	public void clickSameClassObjectByOne(String rootClass, String destClass, int num) {
		try {
			getSameClassObject("class", rootClass, "id", destClass, num).click();
		} catch (UiObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 点击相同类型的对象，按顺序点击
	 * @param srcObject
	 * @param destClass
	 * @param upOffset
	 * @param dowmOffset
	 * @return
	 */
	public  void clickSameClassObjectByMore(String rootClass, String destClass,
			int num) {
		for (int i = 0; i < num; i++) {
			clickSameClassObjectByOne(rootClass, destClass, i);
		}
	}
	
	public UiObject getSameClassObject(String type1, String root, String type2, String sub, int num) 
			throws UiObjectNotFoundException {
		
		UiCollection collection = null;
		UiObject CheckObject = null;
		if(type1.equals("class"))
		{
			collection = new UiCollection(new UiSelector().className(root));
		}
		else if(type1.equals("id"))
		{
			collection = new UiCollection(new UiSelector().resourceId(root));
		}
		else if(type1.equals("text"))
		{
			collection = new UiCollection(new UiSelector().text(root));
		}
		
		if(type2.equals("class"))
		{
			CheckObject = collection.getChildByInstance(new UiSelector().className(sub), num);
		}
		else if(type2.equals("id"))
		{
			CheckObject = collection.getChildByInstance(new UiSelector().resourceId(sub), num);
		}
		else if(type2.equals("text"))
		{
			CheckObject = collection.getChildByInstance(new UiSelector().text(sub), num);
		}
		return CheckObject;
	}
	
	
	
	/**
	 * 同行，根据num，获取按顺序的对象
	 * @param srcObject
	 * @param destClass
	 * @param upOffset
	 * @param dowmOffset
	 * @return
	 */
	public static UiObject getSameLineAndSameClassObjectById(String id, String destClass, int num) {
		UiCollection collection = new UiCollection(new UiSelector().resourceId(id));
		UiObject CheckObject = null;

		int num1 = collection.getChildCount(new UiSelector().className(destClass));

		System.out.println("cnt: " + num1);
		try {
			if (num > num1) {
				return CheckObject;
			} else {
				CheckObject = collection.getChildByInstance(
						new UiSelector().className(destClass), num);
			}
		} catch (UiObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return CheckObject;
	}
	
	/**
	 * 同行，根据num，获取按顺序的对象
	 * @param srcObject
	 * @param destClass
	 * @param upOffset
	 * @param dowmOffset
	 * @return
	 */
	public static UiObject getEndClassObjectById(String id, String destClass) {
		UiCollection collection = new UiCollection(new UiSelector().resourceId(id));
		UiObject CheckObject = null;

		int num1 = collection.getChildCount(new UiSelector().className(destClass));

		System.out.println("cnt: " + num1);
		try {
			if (num1 == 0) {
				return CheckObject;
			} else {
				CheckObject = collection.getChildByInstance(
						new UiSelector().className(destClass), num1-1);
			}
		} catch (UiObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return CheckObject;
	}
}
